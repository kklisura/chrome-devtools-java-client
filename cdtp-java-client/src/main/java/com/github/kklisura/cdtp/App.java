package com.github.kklisura.cdtp;

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

import static com.github.kklisura.cdtp.utils.ChromeDevToolsUtils.waitForEvent;

import com.github.kklisura.cdtp.launch.ChromeLauncher;
import com.github.kklisura.cdtp.protocol.commands.Network;
import com.github.kklisura.cdtp.protocol.commands.Page;
import com.github.kklisura.cdtp.services.ChromeDevToolsService;
import com.github.kklisura.cdtp.services.ChromeService;
import com.github.kklisura.cdtp.services.types.ChromeTab;
import java.util.Arrays;

/** Hello world! */
public class App {
  public static void main(String[] args) throws InterruptedException {
    // Create chrome launcher
    try (final ChromeLauncher launcher = new ChromeLauncher()) {
      // Launch chrome either as headless (true) or regular (false).
      final ChromeService chromeService = launcher.launch(false);

      // Create empty tab ie about:blank.
      final ChromeTab tab = chromeService.createTab();

      // Get DevTools service to this tab
      try (final ChromeDevToolsService devToolsService = chromeService.createDevToolsService(tab)) {
        // Get individual commands
        final Network network = devToolsService.getNetwork();
        final Page page = devToolsService.getPage();

        // Log requests that will be sent.
        network.onRequestWillBeSent(event -> System.out.println(event.getRequest().getUrl()));

        network.setBlockedURLs(Arrays.asList("**/*.png", "**/*.css"));

        // Enable network events.
        network.enable();

        // Navigate to github.com
        page.navigate("http://github.com");

        // Wait until loading is finished.
        waitForEvent(network::onLoadingFinished);

        Thread.sleep(10000);
      }

      // Close the tab.
      chromeService.closeTab(tab);
    }
  }
}
