package com.github.kklisura.cdt.launch;

/*-
 * #%L
 * cdt-java-client
 * %%
 * Copyright (C) 2018 - 2023 Kenan Klisura
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

import static com.github.kklisura.cdt.utils.ChromeDevToolsUtils.closeQuietly;
import static com.github.kklisura.cdt.utils.FilesUtils.deleteQuietly;
import static com.github.kklisura.cdt.utils.FilesUtils.randomTempDir;

import com.github.kklisura.cdt.launch.config.ChromeLauncherConfiguration;
import com.github.kklisura.cdt.launch.exceptions.ChromeProcessException;
import com.github.kklisura.cdt.launch.exceptions.ChromeProcessTimeoutException;
import com.github.kklisura.cdt.launch.support.ProcessLauncher;
import com.github.kklisura.cdt.launch.support.annotations.ChromeArgument;
import com.github.kklisura.cdt.launch.support.impl.ProcessLauncherImpl;
import com.github.kklisura.cdt.services.ChromeService;
import com.github.kklisura.cdt.services.impl.ChromeServiceImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
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

  private static final Logger CHROME_OUTPUT_LOGGER =
      LoggerFactory.getLogger(ChromeLauncher.class.getPackage().getName() + ".chrome.output");

  private static final String TEMP_PREFIX = "cdt-user-data-dir";

  private static final Pattern DEVTOOLS_LISTENING_LINE_PATTERN =
      Pattern.compile("^DevTools listening on ws:\\/\\/.+?:(\\d+)\\/");

  private static final String[] CHROME_BINARIES =
      new String[] {
        "/usr/bin/chromium",
        "/usr/bin/chromium-browser",
        "/usr/bin/google-chrome-stable",
        "/usr/bin/google-chrome",
        "/snap/bin/chromium",
        "/Applications/Chromium.app/Contents/MacOS/Chromium",
        "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome",
        "/Applications/Google Chrome Canary.app/Contents/MacOS/Google Chrome Canary",
        "C:/Program Files (x86)/Google/Chrome/Application/chrome.exe",
        "C:/Program Files/Google/Chrome/Application/chrome.exe"
      };

  private Process chromeProcess;

  private Thread shutdownHookThread = new Thread(this::close);

  private ProcessLauncher processLauncher;

  private Environment environment;
  private ShutdownHookRegistry shutdownHookRegistry;

  private ChromeLauncherConfiguration configuration;

  private Path userDataDirPath;

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
   * @throws IllegalStateException If chrome process has already been started.
   * @throws ChromeProcessException If an I/O error occurs during chrome process start.
   * @throws ChromeProcessTimeoutException If timeout expired while waiting for chrome to start.
   */
  public ChromeService launch(Path chromeBinaryPath, ChromeArguments chromeArguments)
      throws ChromeProcessException {
    int port = launchChromeProcess(chromeBinaryPath, chromeArguments);
    return new ChromeServiceImpl(port);
  }

  /**
   * Launches a chrome given its path and arguments.
   *
   * @param chromeArguments the chrome arguments
   * @return Chrome service.
   * @throws IllegalStateException If chrome process has already been started.
   * @throws ChromeProcessException If an I/O error occurs during chrome process start.
   * @throws ChromeProcessTimeoutException If timeout expired while waiting for chrome to start.
   */
  public ChromeService launch(ChromeArguments chromeArguments) throws ChromeProcessException {
    return launch(getChromeBinaryPath(), chromeArguments);
  }

  /**
   * Launches a chrome with default arguments.
   *
   * @param headless Headless flag.
   * @return Chrome service.
   * @throws IllegalStateException If chrome process has already been started.
   * @throws ChromeProcessException If an I/O error occurs during chrome process start.
   * @throws ChromeProcessTimeoutException If timeout expired while waiting for chrome to start.
   */
  public ChromeService launch(boolean headless) throws ChromeProcessException {
    return launch(getChromeBinaryPath(), ChromeArguments.defaults(headless).build());
  }

  /**
   * Launches a headless chrome with default arguments.
   *
   * @return Chrome service.
   * @throws IllegalStateException If chrome process has already been started.
   * @throws ChromeProcessException If an I/O error occurs during chrome process start.
   * @throws ChromeProcessTimeoutException If timeout expired while waiting for chrome to start.
   */
  public ChromeService launch() throws ChromeProcessException {
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

      throw new RuntimeException("CHROME_PATH environment value is not an executable file.");
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
    if (chromeProcess != null && chromeProcess.isAlive()) {
      LOGGER.info("Closing chrome process...");
      chromeProcess.destroy();

      try {
        if (!chromeProcess.waitFor(configuration.getShutdownWaitTime(), TimeUnit.SECONDS)) {
          chromeProcess.destroyForcibly();
          chromeProcess.waitFor(configuration.getShutdownWaitTime(), TimeUnit.SECONDS);
        }

        LOGGER.info("Chrome process closed.");
      } catch (InterruptedException e) {
        LOGGER.error("Interrupted while waiting for chrome process to shutdown.", e);

        chromeProcess.destroyForcibly();
      } finally {
        deleteQuietly(userDataDirPath);
      }

      try {
        shutdownHookRegistry.remove(shutdownHookThread);
      } catch (IllegalStateException e) {
        // Ignore this exceptions; We're removing hook even we're still in shutdown.
      }
    }
  }

  /**
   * Returns an exit value. This is just proxy to {@link Process#exitValue}.
   *
   * @return Exit value of the process if exited.
   * @throws {@link IllegalThreadStateException} if the subprocess has not yet terminated. {@link
   *     IllegalStateException} If the process hasn't even started.
   */
  public int exitValue() {
    if (chromeProcess == null) {
      throw new IllegalStateException("Chrome process has not been started started.");
    }
    return chromeProcess.exitValue();
  }

  /**
   * Tests whether the subprocess is alive. This is just proxy to {@link Process#isAlive()}.
   *
   * @return True if the subprocess has not yet terminated.
   * @throws IllegalThreadStateException if the subprocess has not yet terminated.
   */
  public boolean isAlive() {
    return chromeProcess != null && chromeProcess.isAlive();
  }

  /**
   * Launches a chrome process given a chrome binary and its arguments.
   *
   * @param chromeBinary Chrome binary path.
   * @param chromeArguments Chrome arguments.
   * @return Port on which devtools is listening.
   * @throws IllegalStateException If chrome process has already been started.
   * @throws ChromeProcessException If an I/O error occurs during chrome process start.
   * @throws ChromeProcessTimeoutException If timeout expired while waiting for chrome to start.
   */
  private int launchChromeProcess(Path chromeBinary, ChromeArguments chromeArguments)
      throws ChromeProcessException {
    if (isAlive()) {
      throw new IllegalStateException("Chrome process has already been started started.");
    }

    shutdownHookRegistry.register(shutdownHookThread);

    Map<String, Object> argumentsMap = getArguments(chromeArguments);

    // Special case for user data directory.
    if (chromeArguments.getUserDataDir() == null) {
      String userDatDir = randomTempDir(TEMP_PREFIX);
      userDataDirPath = Paths.get(userDatDir);
      argumentsMap.put(ChromeArguments.USER_DATA_DIR_ARGUMENT, userDatDir);
    }

    List<String> arguments = argsMapToArgsList(argumentsMap);

    LOGGER.info(
        "Launching chrome process {} with arguments {}", chromeBinary.toString(), argumentsMap);

    try {
      chromeProcess = processLauncher.launch(chromeBinary.toString(), arguments);

      return waitForDevToolsServer(chromeProcess);
    } catch (IOException e) {
      // Unsubscribe from registry on exceptions.
      shutdownHookRegistry.remove(shutdownHookThread);

      throw new ChromeProcessException("Failed starting chrome process.", e);
    } catch (Exception e) {
      close();
      throw e;
    }
  }

  /**
   * Waits for DevTools server is up on chrome process.
   *
   * @param process Chrome process.
   * @return DevTools listening port.
   * @throws ChromeProcessTimeoutException If timeout expired while waiting for chrome process.
   */
  private int waitForDevToolsServer(final Process process) throws ChromeProcessTimeoutException {
    final CompletableFuture<Integer> port = new CompletableFuture<>();
    final AtomicReference<String> chromeOutput = new AtomicReference<>("");

    Thread readLineThread =
        new Thread(
            () -> {
              StringBuilder chromeOutputBuilder = new StringBuilder();
              BufferedReader reader = null;
              try {
                reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                // Wait for DevTools listening line and extract port number.
                String line;
                while ((line = reader.readLine()) != null) {
                  CHROME_OUTPUT_LOGGER.debug(line);

                  if (!port.isDone()) {
                    Matcher matcher = DEVTOOLS_LISTENING_LINE_PATTERN.matcher(line);
                    if (matcher.find()) {
                      port.complete(Integer.parseInt(matcher.group(1)));
                      if (!CHROME_OUTPUT_LOGGER.isDebugEnabled()) {
                        // no need to keep reading, ending the thread
                        break;
                      }

                      // no longer needed, clearing
                      chromeOutputBuilder = null;
                      chromeOutput.set(null);
                    } else {
                      if (chromeOutputBuilder.length() != 0) {
                        chromeOutputBuilder.append(System.lineSeparator());
                      }
                      chromeOutputBuilder.append(line);
                      chromeOutput.set(chromeOutputBuilder.toString());
                    }
                  }
                }
              } catch (Exception e) {
                if (port.isDone()) {
                  LOGGER.debug("Error while reading Chrome process output.", e);
                } else {
                  LOGGER.error("Failed while waiting for dev tools server.", e);
                  port.completeExceptionally(e);
                }
              } finally {
                closeQuietly(reader);
              }
            });

    readLineThread.setName("chrome-launcher:read-line-thread");
    readLineThread.start();

    try {
      return port.get(configuration.getStartupWaitTime(), TimeUnit.SECONDS);
    } catch (TimeoutException e) {
      close(readLineThread);

      throw new ChromeProcessTimeoutException(
          "Failed while waiting for chrome to start: "
              + "Timeout expired! Chrome output: "
              + chromeOutput.get());
    } catch (InterruptedException e) {
      close(readLineThread);

      LOGGER.error("Interrupted while waiting for dev tools server.", e);
      throw new RuntimeException("Interrupted while waiting for dev tools server.", e);
    } catch (ExecutionException e) {
      close(readLineThread);
      // exception already logged before completeExceptionally
      throw new RuntimeException("Failed while waiting for dev tools server.", e);
    }
  }

  private void close(Thread thread) {
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
