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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kklisura.cdt.launch.ChromeLauncher;
import com.github.kklisura.cdt.protocol.commands.Page;
import com.github.kklisura.cdt.protocol.commands.Tracing;
import com.github.kklisura.cdt.services.ChromeDevToolsService;
import com.github.kklisura.cdt.services.ChromeService;
import com.github.kklisura.cdt.services.types.ChromeTab;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Tracing example.
 *
 * @author Kenan Klisura
 */
public class TracingExample {
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
    final Tracing tracing = devToolsService.getTracing();

    final List<Object> dataCollectedList = new LinkedList<>();

    // Add tracing data to dataCollectedList
    tracing.onDataCollected(
        event -> {
          if (event.getValue() != null) {
            dataCollectedList.addAll(event.getValue());
          }
        });

    // When tracing is complete, dump dataCollectedList to JSON file.
    tracing.onTracingComplete(
        event -> {
          // Dump tracing to file.
          System.out.println("Tracing completed! Dumping to a file.");

          dump("tracing.json", dataCollectedList);

          devToolsService.close();
        });

    page.onLoadEventFired(
        event -> {
          tracing.end();
        });

    // Enable page events.
    page.enable();

    // Start tracing
    tracing.start();

    // Navigate to github.com.
    page.navigate("http://github.com");

    devToolsService.waitUntilClosed();
  }

  private static void dump(String fileName, List<Object> data) {
    final ObjectMapper objectMapper = new ObjectMapper();

    try {
      File file = new File(fileName);
      objectMapper.writeValue(file, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
