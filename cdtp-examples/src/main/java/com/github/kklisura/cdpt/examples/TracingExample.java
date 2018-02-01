package com.github.kklisura.cdpt.examples;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kklisura.cdtp.launch.ChromeLauncher;
import com.github.kklisura.cdtp.protocol.commands.Page;
import com.github.kklisura.cdtp.protocol.commands.Tracing;
import com.github.kklisura.cdtp.services.ChromeDevToolsService;
import com.github.kklisura.cdtp.services.ChromeService;
import com.github.kklisura.cdtp.services.types.ChromeTab;
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
  public static void main(String[] args) throws InterruptedException {
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
