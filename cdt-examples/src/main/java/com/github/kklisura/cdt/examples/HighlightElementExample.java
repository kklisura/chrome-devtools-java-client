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
import com.github.kklisura.cdt.protocol.commands.*;
import com.github.kklisura.cdt.protocol.types.dom.RGBA;
import com.github.kklisura.cdt.protocol.types.overlay.HighlightConfig;
import com.github.kklisura.cdt.services.ChromeDevToolsService;
import com.github.kklisura.cdt.services.ChromeService;
import com.github.kklisura.cdt.services.types.ChromeTab;

/**
 * Highlights an elements same way the Chrome inspector does.
 *
 * @author Kenan Klisura
 */
public class HighlightElementExample {
  private static void highlightNode(
      ChromeDevToolsService devToolsService, HighlightConfig highlightConfig, String selector) {
    final DOM dom = devToolsService.getDOM();
    final Overlay overlay = devToolsService.getOverlay();

    final Integer nodeId =
        dom.querySelector(devToolsService.getDOM().getDocument().getNodeId(), selector);
    overlay.highlightNode(highlightConfig, nodeId, null, null, null);
  }

  public static void main(String[] args) {
    // Create chrome launcher.
    final ChromeLauncher launcher = new ChromeLauncher();

    // Launch chrome either as headless (true) or regular (false).
    final ChromeService chromeService = launcher.launch(false);

    // Create empty tab ie about:blank.
    final ChromeTab tab = chromeService.createTab();

    // Get DevTools service to this tab
    final ChromeDevToolsService devToolsService = chromeService.createDevToolsService(tab);

    // Get individual commands
    final Page page = devToolsService.getPage();
    final Network network = devToolsService.getNetwork();
    final Performance performance = devToolsService.getPerformance();

    devToolsService.getDOM().enable();
    devToolsService.getOverlay().enable();

    performance.enable();
    network.onLoadingFinished(
        event -> {
          HighlightConfig highlightConfig = new HighlightConfig();

          highlightConfig.setBorderColor(rgba(255, 229, 153, 0.66));
          highlightConfig.setContentColor(rgba(111, 168, 220, 0.66));
          highlightConfig.setCssGridColor(rgb(75, 0, 130));
          highlightConfig.setEventTargetColor(rgba(255, 196, 196, 0.66));
          highlightConfig.setMarginColor(rgba(246, 178, 107, 0.66));
          highlightConfig.setPaddingColor(rgba(147, 196, 125, 0.55));
          highlightConfig.setShapeColor(rgba(96, 82, 117, 0.8));
          highlightConfig.setShapeMarginColor(rgba(96, 82, 127, 0.6));

          highlightConfig.setShowExtensionLines(true);
          highlightConfig.setShowInfo(true);
          highlightConfig.setShowRulers(true);
          highlightConfig.setShowStyles(false);

          highlightNode(devToolsService, highlightConfig, "h1.h000-mktg");

          sleep();
        });

    // Enable network events.
    network.enable();

    // Navigate to github.com.
    page.navigate("https://www.github.com");

    devToolsService.waitUntilClosed();
  }

  private static void sleep() {
    try {
      Thread.sleep(100000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private static RGBA rgba(int r, int g, int b, double a) {
    RGBA result = new RGBA();
    result.setR(r);
    result.setG(g);
    result.setB(b);
    result.setA(a);
    return result;
  }

  private static RGBA rgb(int r, int g, int b) {
    RGBA result = new RGBA();
    result.setR(r);
    result.setG(g);
    result.setB(b);
    return result;
  }
}
