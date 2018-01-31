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

import com.github.kklisura.cdtp.launch.config.ChromeLauncherConfiguration;
import com.github.kklisura.cdtp.launch.support.ProcessLauncher;
import com.github.kklisura.cdtp.launch.support.annotations.ChromeArgument;
import com.github.kklisura.cdtp.launch.support.impl.ProcessLauncherImpl;
import com.github.kklisura.cdtp.services.ChromeService;
import com.github.kklisura.cdtp.services.impl.ChromeServiceImpl;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
  public static final String ENV_CHROME_PATH = "CHROME_PATH";

  private static final Logger LOGGER = LoggerFactory.getLogger(ChromeLauncher.class);

  private static final Pattern DEVTOOLS_LISTENING_LINE_MATCH =
      Pattern.compile("^DevTools listening on ws:\\/\\/.+?:(\\d+)\\/");

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

  private ProcessLauncher processLauncher;

  private Environment environment;
  private ShutdownHookRegistry shutdownHookRegistry;

  private ChromeLauncherConfiguration configuration;

  /** Instantiates a new Chrome launcher. */
  public ChromeLauncher() {
    this(new ChromeLauncherConfiguration());
  }

  /**
   * Instantiates a new Chrome launcher.
   *
   * @param configuration Launch configuration.
   */
  public ChromeLauncher(ChromeLauncherConfiguration configuration) {
    this(
        new ProcessLauncherImpl(),
        System::getenv,
        new RuntimeShutdownHookRegistry(),
        configuration);
  }

  /**
   * Instantiates a new Chrome launcher with a process launcher.
   *
   * @param processLauncher Process launcher.
   * @param environment Environment interface.
   * @param shutdownHookRegistry Shutdown hooks registry.
   */
  public ChromeLauncher(
      ProcessLauncher processLauncher,
      Environment environment,
      ShutdownHookRegistry shutdownHookRegistry,
      ChromeLauncherConfiguration configuration) {
    this.processLauncher = processLauncher;
    this.environment = environment;
    this.shutdownHookRegistry = shutdownHookRegistry;
    this.configuration = configuration;
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
    String envChrome = environment.getEnv(ENV_CHROME_PATH);
    if (envChrome != null) {
      boolean isExecutable = processLauncher.isExecutable(envChrome);

      if (isExecutable) {
        return Paths.get(envChrome).toAbsolutePath();
      }
    }

    for (String binary : CHROME_BINARIES) {
      boolean isExecutable = processLauncher.isExecutable(binary);

      if (isExecutable) {
        return Paths.get(binary).toAbsolutePath();
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
        if (!chromeProcess.waitFor(configuration.getShutdownWaitTime(), TimeUnit.SECONDS)) {
          chromeProcess.destroyForcibly();
          chromeProcess.waitFor(configuration.getShutdownWaitTime(), TimeUnit.SECONDS);
        }
        chromeProcess = null;

        LOGGER.info("Chrome process closed.");
      } catch (InterruptedException e) {
        LOGGER.error("Interrupted while waiting for chrome process to shutdown.", e);

        chromeProcess.destroyForcibly();
        chromeProcess = null;
      }

      try {
        shutdownHookRegistry.remove(shutdownHookThread);
      } catch (IllegalStateException e) {
        // Ignore this exceptions; We're removing hook even we're still in shutdown.
      }
    }
  }

  private int launchChromeProcess(Path chromeBinary, ChromeArguments chromeArguments)
      throws IOException {
    if (chromeProcess != null) {
      throw new IllegalStateException("Chrome process has already been started started.");
    }

    shutdownHookRegistry.register(shutdownHookThread);

    Map<String, Object> argumentsMap = getArguments(chromeArguments);

    List<String> arguments = argsMapToArgsList(argumentsMap);

    LOGGER.info(
        "Launching chrome process {} with arguments {}", chromeBinary.toString(), argumentsMap);

    chromeProcess = processLauncher.launch(chromeBinary.toString(), arguments);
    return waitForDevToolsServer(chromeProcess);
  }

  private int waitForDevToolsServer(final Process process) {
    final AtomicInteger port = new AtomicInteger();
    final AtomicBoolean success = new AtomicBoolean(false);

    Thread readLineThread =
        new Thread(
            () -> {
              BufferedReader reader = null;
              try {
                reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

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
              } catch (Exception e) {
                LOGGER.error("Failed while waiting for dev tools server.", e);
              } finally {
                closeQuietly(reader);
              }
            });

    readLineThread.start();

    try {
      readLineThread.join(TimeUnit.SECONDS.toMillis(configuration.getStartupWaitTime()));

      if (!success.get()) {
        close(readLineThread);
        throw new RuntimeException("Failed while waiting for chrome to start. Timeout expired!");
      }
    } catch (InterruptedException e) {
      close(readLineThread);

      LOGGER.error("Interrupted while waiting for dev tools server.", e);
      throw new RuntimeException("Interrupted while waiting for dev tools server.", e);
    }

    return port.get();
  }

  private void close(Thread thread) {
    close();
    try {
      thread.join(TimeUnit.SECONDS.toMillis(configuration.getThreadWaitTime()));
    } catch (InterruptedException e) {
      // Ignore this exception.
    }
  }

  private List<String> argsMapToArgsList(Map<String, Object> args) {
    List<String> result = new ArrayList<>();
    for (Map.Entry<String, Object> entry : args.entrySet()) {
      if (entry.getValue() != null && !Boolean.FALSE.equals(entry.getValue())) {
        if (Boolean.TRUE.equals(entry.getValue())) {
          result.add("--" + entry.getKey());
        } else {
          result.add("--" + entry.getKey() + "=" + entry.getValue());
        }
      }
    }

    return result;
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

  private static void closeQuietly(Closeable closeable) {
    if (closeable != null) {
      try {
        closeable.close();
      } catch (IOException e) {
        // Ignore this exception.
      }
    }
  }

  /** Environment interface. */
  @FunctionalInterface
  public interface Environment {
    /**
     * Returns environment value var.
     *
     * @param name Environment var name.
     * @return Value.
     */
    String getEnv(String name);
  }

  /** Shutdown hooks registry. */
  public interface ShutdownHookRegistry {
    /**
     * Registers a new shutdown hook thread.
     *
     * @param thread Thread.
     */
    default void register(Thread thread) {
      Runtime.getRuntime().addShutdownHook(thread);
    }

    /**
     * Removes a shutdown thread.
     *
     * @param thread Thread.
     */
    default void remove(Thread thread) {
      Runtime.getRuntime().removeShutdownHook(thread);
    }
  }

  /** Runtime based shutdown hook. */
  public static class RuntimeShutdownHookRegistry implements ShutdownHookRegistry {}
}
