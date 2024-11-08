package com.github.kklisura.cdt.examples;

/*-
 * #%L
 * cdt-examples
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

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.github.kklisura.cdt.launch.ChromeArguments;
import com.github.kklisura.cdt.launch.ChromeLauncher;
import com.github.kklisura.cdt.services.ChromeDevToolsService;
import com.github.kklisura.cdt.services.ChromeService;
import com.github.kklisura.cdt.services.types.ChromeTab;
import org.slf4j.LoggerFactory;

/**
 * By setting logger `com.github.kklisura.cdt.launch.chrome.output` to DEBUG level, either
 * programmatically or via logging configuration (see logback.xml in the example) and by introducing
 * arguments to chrome to enable more verbose logging one can observe detailed chrome output. This
 * should be used to debug some issues with chrome and should not generally not be used in normal
 * course of operations. Note that, when you set this logger to DEBUG, a
 * chrome-launcher:read-line-thread thread will be alive, until browser gets closed.
 *
 * <p>Logger `com.github.kklisura.cdt.launch.chrome.output` can be set to DEBUG level
 * programmatically as in an example below (enableDebugChromeOutput method) or by introducing
 *
 * <pre>
 *   <logger name="com.github.kklisura.cdt.launch.chrome.output" level="DEBUG" />
 * </pre>
 *
 * logger to logging configuration.
 *
 * @author Kenan Klisura
 */
public class ChromeLoggingExample {

  /**
   * Programmatically set `com.github.kklisura.cdt.launch.chrome.output` to DEBUG level so chrome
   * output can be observed.
   */
  private static void enableDebugChromeOutput() {
    LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
    loggerContext.getLogger("com.github.kklisura.cdt.launch.chrome.output").setLevel(Level.DEBUG);
  }

  public static void main(String[] args) throws InterruptedException {
    enableDebugChromeOutput();

    // Create chrome launcher.
    final ChromeLauncher launcher = new ChromeLauncher();

    // Launch chrome either as headless (true) or regular (false).
    final ChromeService chromeService =
        launcher.launch(
            ChromeArguments.defaults(false)
                // Sets the correct arguments: enable-logging and logging level
                .enableLogging("stderr")
                .additionalArguments("v", "1")
                .build());

    // Create empty tab ie about:blank.
    final ChromeTab tab = chromeService.createTab();

    // Get DevTools service to this tab
    final ChromeDevToolsService devToolsService = chromeService.createDevToolsService(tab);

    // Wait a bit and then close the browser
    Thread.sleep(2000);

    devToolsService.close();
    launcher.close();
  }
}
