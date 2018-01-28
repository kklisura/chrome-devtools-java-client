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

import com.github.kklisura.cdtp.protocol.events.network.RequestWillBeSent;
import com.github.kklisura.cdtp.protocol.support.types.EventHandler;
import com.github.kklisura.cdtp.services.ChromeDevToolsService;
import com.github.kklisura.cdtp.services.ChromeService;
import com.github.kklisura.cdtp.services.impl.ChromeServiceImpl;
import com.github.kklisura.cdtp.services.types.ChromeTab;

/** Hello world! */
public class App {
  public static void main(String[] args) throws Exception {
    final ChromeService chromeService = new ChromeServiceImpl(9222);
    final ChromeTab tab = chromeService.createTab();

    try (ChromeDevToolsService cdtpService = chromeService.createDevToolsService(tab)) {

      cdtpService
          .getNetwork()
          .onRequestWillBeSent(
              new EventHandler<RequestWillBeSent>() {
                @Override
                public void onEvent(RequestWillBeSent event) {
                  System.out.println(event.getRequest().getUrl());
                }
              });

      cdtpService.getNetwork().enable();

      // Network requestWillBeSent event
      // Page loadEventFired

      cdtpService.getPage().navigate("http://google.com");

      Thread.sleep(10000);
    }

    chromeService.closeTab(tab);
  }
}
