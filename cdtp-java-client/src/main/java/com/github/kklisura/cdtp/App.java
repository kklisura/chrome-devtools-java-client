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

import com.github.kklisura.cdtp.launch.ChromeLauncher;
import com.github.kklisura.cdtp.protocol.commands.Network;
import com.github.kklisura.cdtp.protocol.commands.Page;
import com.github.kklisura.cdtp.services.ChromeDevToolsService;
import com.github.kklisura.cdtp.services.ChromeService;
import com.github.kklisura.cdtp.services.types.ChromeTab;
import java.util.concurrent.CountDownLatch;

/** Hello world! */
public class App {
  public static void main(String[] args) throws Exception {
    // Create chrome launcher
    try (final ChromeLauncher chromeLauncher = new ChromeLauncher()) {

      // Launch chrome either as headless (true) or no.
      final ChromeService chromeService = chromeLauncher.launch(true);

      // Create empty tab (about:blank)
      final ChromeTab tab = chromeService.createTab();

      // Get dev tools service to this tab
      try (ChromeDevToolsService devToolsService = chromeService.createDevToolsService(tab)) {
        final Network network = devToolsService.getNetwork();
        final Page page = devToolsService.getPage();

        network.onRequestWillBeSent(event -> System.out.println(event.getRequest().getUrl()));
        network.enable();

        page.navigate("http://klix.ba");

        CountDownLatch countDownLatch = new CountDownLatch(1);

        network.onLoadingFinished(
            event -> {
              System.out.println("Finished");
              countDownLatch.countDown();
            });

        countDownLatch.await();
      }

      chromeService.closeTab(tab);
    }
  }
}
