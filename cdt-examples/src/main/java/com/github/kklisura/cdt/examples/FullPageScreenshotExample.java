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
import com.github.kklisura.cdt.protocol.commands.Emulation;
import com.github.kklisura.cdt.protocol.commands.Page;
import com.github.kklisura.cdt.protocol.types.page.CaptureScreenshotFormat;
import com.github.kklisura.cdt.protocol.types.page.LayoutMetrics;
import com.github.kklisura.cdt.protocol.types.page.Viewport;
import com.github.kklisura.cdt.services.ChromeDevToolsService;
import com.github.kklisura.cdt.services.ChromeService;
import com.github.kklisura.cdt.services.types.ChromeTab;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * Takes a full page screenshot. The output screenshot dimensions will be page width x page height,
 * for example when capturing https://news.ycombinator.com/ the output screenshot image will be
 * 1185x1214
 *
 * @author Kenan Klisura
 */
public class FullPageScreenshotExample {

  private static void captureFullPageScreenshot(
      ChromeDevToolsService devToolsService, Page page, String outputFilename) {
    final LayoutMetrics layoutMetrics = page.getLayoutMetrics();

    final double width = layoutMetrics.getContentSize().getWidth();
    final double height = layoutMetrics.getContentSize().getHeight();

    final Emulation emulation = devToolsService.getEmulation();
    emulation.setDeviceMetricsOverride(
        Double.valueOf(width).intValue(), Double.valueOf(height).intValue(), 1.0d, Boolean.FALSE);

    Viewport viewport = new Viewport();
    viewport.setScale(1d);

    // You can set offset with X, Y
    viewport.setX(0d);
    viewport.setY(0d);

    // Set a width, height of a page to take screenshot at
    viewport.setWidth(width);
    viewport.setHeight(height);

    dump(
        outputFilename,
        page.captureScreenshot(
            CaptureScreenshotFormat.PNG, 100, viewport, Boolean.TRUE, Boolean.FALSE));
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

    page.onLoadEventFired(
        event -> {
          System.out.println("Taking screenshot...");

          captureFullPageScreenshot(devToolsService, page, "screenshot.png");

          System.out.println("Done!");

          devToolsService.close();
        });

    // Enable page events.
    page.enable();

    // Navigate to github.com.
    page.navigate("https://news.ycombinator.com/");

    devToolsService.waitUntilClosed();
  }

  private static void dump(String fileName, String data) {
    FileOutputStream fileOutputStream = null;
    try {
      File file = new File(fileName);
      fileOutputStream = new FileOutputStream(file);
      fileOutputStream.write(Base64.getDecoder().decode(data));
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (fileOutputStream != null) {
        try {
          fileOutputStream.flush();
          fileOutputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
