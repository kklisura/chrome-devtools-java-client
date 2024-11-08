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

import com.github.kklisura.cdt.launch.ChromeLauncher;
import com.github.kklisura.cdt.protocol.commands.Page;
import com.github.kklisura.cdt.services.ChromeDevToolsService;
import com.github.kklisura.cdt.services.ChromeService;
import com.github.kklisura.cdt.services.types.ChromeTab;

/**
 * Simple navigate-to-url example with DevTools java client.
 *
 * <p>The following example will open chrome, create a tab with about:blank url and then navigate to
 * github.com. It will then wait 2seconds and it will navigate to twitter.com. It will again wait
 * for 2seconds and on the end it will close the tab and close the browser.
 *
 * @author Kenan Klisura
 */
public class SimpleNavigateToUrlExample {
  public static void main(String[] args) throws InterruptedException {
    // Create chrome launcher.
    try (final ChromeLauncher launcher = new ChromeLauncher()) {
      // Launch chrome either as headless (true) or regular (false).
      final ChromeService chromeService = launcher.launch(false);

      // Create empty tab ie about:blank.
      final ChromeTab tab = chromeService.createTab();

      // Get DevTools service to this tab
      try (final ChromeDevToolsService devToolsService = chromeService.createDevToolsService(tab)) {
        final Page page = devToolsService.getPage();

        // Navigate to github.com.
        page.navigate("http://github.com");

        // Wait a while...
        Thread.sleep(2000);

        // Navigate to twitter.com.
        page.navigate("http://twitter.com");

        // Wait a while...
        Thread.sleep(2000);
      }

      // Close the tab.
      chromeService.closeTab(tab);
    }
  }
}
