package com.github.kklisura.cdtp.launch;

/*-
 * #%L
 * cdpt-java-client
 * %%
 * Copyright (C) 2018 Kenan Klisura
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.github.kklisura.cdtp.launch.support.ChromeArgument;
import com.github.kklisura.cdtp.services.ChromeService;
import com.github.kklisura.cdtp.services.impl.ChromeServiceImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder.Redirect;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Chrome launcher.
 *
 * @author Kenan Klisura
 */
public class ChromeLauncher implements AutoCloseable {
  /** CHROME_PATH environment var */
  public static final String CHROME_PATH = "CHROME_PATH";

  private static final Logger LOGGER = LoggerFactory.getLogger(ChromeLauncher.class);

  private static final Pattern DEVTOOLS_LISTENING_LINE_MATCH =
      Pattern.compile("^DevTools listening on ws:\\/\\/.+?:(\\d+)\\/");

  /** Default shutdown wait time in seconds. */
  private static final int DEFAULT_SHUTDOWN_WAIT_TIME = 10;

  /** Default startup wait time in seconds. */
  private static final int DEFAULT_STARTUP_WAIT_TIME = 10;

  /** 5 seconds wait time for threads to stop. */
  private static final int THREAD_JOIN_WAIT_TIME = 1000 * 5;

  private static final String[] CHROME_BINARIES =
      new String[] {
        "/usr/bin/chromium",
        "/usr/bin/chromium-browser",
        "/usr/bin/google-chrome-stable",
        "/usr/bin/google-chrome",
        "/Applications/Chromium.app/Contents/MacOS/Chromium",
        "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome",
        "/Applications/Google Chrome Canary.app/Contents/MacOS/Google Chrome Canary",
        "C:/Program Files (x86)/Google/Chrome/Application/chrome.exe"
      };

  private Process chromeProcess;

  private Thread shutdownHookThread = new Thread(this::close);

  /** Instantiates a new Chrome launcher. */
  public ChromeLauncher() {
    // Empty ctor.
  }

  /**
   * Launches a chrome given its path and arguments.
   *
   * @param chromeBinaryPath the chrome binary path
   * @param chromeArguments the chrome arguments
   * @return Chrome service.
   * @throws IOException the io exception
   */
  public ChromeService launch(Path chromeBinaryPath, ChromeArguments chromeArguments)
      throws IOException {
    int port = launchChromeProcess(chromeBinaryPath, chromeArguments);
    return new ChromeServiceImpl(port);
  }

  /**
   * Launches a chrome given its path and arguments.
   *
   * @param chromeArguments the chrome arguments
   * @return Chrome service.
   * @throws IOException the io exception
   */
  public ChromeService launch(ChromeArguments chromeArguments) throws IOException {
    return launch(getChromeBinaryPath(), chromeArguments);
  }

  /**
   * Launches a chrome with default arguments.
   *
   * @param headless Headless flag.
   * @return Chrome service.
   * @throws IOException the io exception
   */
  public ChromeService launch(boolean headless) throws IOException {
    return launch(getChromeBinaryPath(), ChromeArguments.defaults(headless).build());
  }

  /**
   * Launches a headless chrome with default arguments.
   *
   * @return Chrome service.
   * @throws IOException the io exception
   */
  public ChromeService launch() throws IOException {
    return launch(true);
  }

  /**
   * Returns the chrome binary path.
   *
   * @return Chrome binary path.
   */
  public Path getChromeBinaryPath() {
    String envChrome = System.getenv(CHROME_PATH);
    if (envChrome != null && !envChrome.trim().isEmpty()) {
      Path path = Paths.get(envChrome);

      if (isExecutable(path)) {
        return path.toAbsolutePath();
      }
    }

    for (String binary : CHROME_BINARIES) {
      Path path = Paths.get(binary);

      if (isExecutable(path)) {
        return path.toAbsolutePath();
      }
    }

    throw new RuntimeException(
        "Could not find chrome binary! Try setting CHROME_PATH env to chrome binary path.");
  }

  @Override
  public void close() {
    if (chromeProcess != null) {
      LOGGER.info("Closing chrome process...");
      chromeProcess.destroy();

      try {
        chromeProcess.waitFor(DEFAULT_SHUTDOWN_WAIT_TIME, TimeUnit.SECONDS);

        if (chromeProcess.isAlive()) {
          chromeProcess.destroyForcibly();
          chromeProcess.waitFor(DEFAULT_SHUTDOWN_WAIT_TIME, TimeUnit.SECONDS);
        }
        chromeProcess = null;

        LOGGER.info("Chrome process closed.");
      } catch (InterruptedException e) {
        LOGGER.error("Interrupted while waiting for chrome process to shutdown.", e);
      }

      try {
        Runtime.getRuntime().removeShutdownHook(shutdownHookThread);
      } catch (IllegalStateException e) {
        // Ignore this exceptions; We're removing hook even we're still in shutdown.
      }
    }
  }

  private static boolean isExecutable(Path path) {
    return Files.isRegularFile(path) && Files.isReadable(path) && Files.isExecutable(path);
  }

  private int launchChromeProcess(Path chromeBinary, ChromeArguments chromeArguments)
      throws IOException {
    if (chromeProcess != null) {
      throw new IllegalStateException("Chrome process has already been started started.");
    }

    Map<String, Object> argumentsMap = getArguments(chromeArguments);

    List<String> arguments = new ArrayList<>();
    arguments.add(chromeBinary.toString());

    appendArgumentsMap(arguments, argumentsMap);

    LOGGER.info(
        "Launching chrome process {} with arguments {}", chromeBinary.toString(), argumentsMap);

    // Install a shutdown hook.
    Runtime.getRuntime().addShutdownHook(shutdownHookThread);

    ProcessBuilder processBuilder =
        new ProcessBuilder(arguments).redirectErrorStream(true).redirectOutput(Redirect.PIPE);

    chromeProcess = processBuilder.start();
    return waitForDevToolsServer(chromeProcess);
  }

  private int waitForDevToolsServer(final Process process) {
    final AtomicInteger port = new AtomicInteger();
    final AtomicBoolean success = new AtomicBoolean(false);
    final CountDownLatch countDownLatch = new CountDownLatch(1);

    Thread readLineThread =
        new Thread(
            () -> {
              BufferedReader reader =
                  new BufferedReader(new InputStreamReader(process.getInputStream()));

              try {
                // Wait for DevTools listening line and extract port number.
                String line;
                while ((line = reader.readLine()) != null) {
                  Matcher matcher = DEVTOOLS_LISTENING_LINE_MATCH.matcher(line);
                  if (matcher.find()) {
                    port.set(Integer.parseInt(matcher.group(1)));
                    success.set(true);
                    break;
                  }
                }
              } catch (IOException e) {
                LOGGER.error("Failed while waiting for dev tools server.", e);
              } finally {
                try {
                  reader.close();
                } catch (IOException e) {
                  // Ignore this exception.
                }

                countDownLatch.countDown();
              }
            });

    readLineThread.start();

    try {
      if (!countDownLatch.await(DEFAULT_STARTUP_WAIT_TIME, TimeUnit.SECONDS)) {
        close(readLineThread);
        throw new RuntimeException("Failed while waiting for chrome to start. Timeout expired!");
      }
    } catch (InterruptedException e) {
      close(readLineThread);

      LOGGER.error("Interrupted while waiting for dev tools server.", e);
      throw new RuntimeException("Interrupted while waiting for dev tools server.", e);
    }

    if (!success.get()) {
      close(readLineThread);
      throw new RuntimeException("Failed while waiting for chrome to start. Timeout expired!");
    }

    return port.get();
  }

  private void close(Thread thread) {
    close();
    try {
      thread.join(THREAD_JOIN_WAIT_TIME);
    } catch (InterruptedException e1) {
      // Ignore this exception.
    }
  }

  private List<String> appendArgumentsMap(List<String> arguments, Map<String, Object> args) {
    for (Map.Entry<String, Object> entry : args.entrySet()) {
      if (entry.getValue() != null && !Boolean.FALSE.equals(entry.getValue())) {
        if (Boolean.TRUE.equals(entry.getValue())) {
          arguments.add("--" + entry.getKey());
        } else {
          arguments.add("--" + entry.getKey() + "=" + entry.getValue());
        }
      }
    }

    return arguments;
  }

  private Map<String, Object> getArguments(ChromeArguments arguments) {
    Map<String, Object> args = new HashMap<>(arguments.getAdditionalArguments());

    Field[] chromeArgumentsFields = ChromeArguments.class.getDeclaredFields();
    for (Field field : chromeArgumentsFields) {
      ChromeArgument fieldAnnotation = field.getAnnotation(ChromeArgument.class);
      if (fieldAnnotation != null) {
        try {
          field.setAccessible(true);
          Object fieldValue = field.get(arguments);
          if (fieldValue != null) {
            args.putIfAbsent(fieldAnnotation.value(), fieldValue);
          }
        } catch (IllegalAccessException e) {
          // We can ignore this exception.
        }
      }
    }

    return args;
  }
}
