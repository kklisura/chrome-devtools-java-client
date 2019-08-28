package com.github.kklisura.cdt.examples;

import com.github.kklisura.cdt.launch.ChromeLauncher;
import com.github.kklisura.cdt.protocol.commands.Network;
import com.github.kklisura.cdt.protocol.commands.Page;
import com.github.kklisura.cdt.protocol.commands.Performance;
import com.github.kklisura.cdt.protocol.types.performance.Metric;
import com.github.kklisura.cdt.services.ChromeDevToolsService;
import com.github.kklisura.cdt.services.ChromeService;
import com.github.kklisura.cdt.services.types.ChromeTab;
import java.util.List;

/**
 * Metric example
 *
 * @author Kenan Klisura
 */
public class PerformanceMetricsExample {
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

    performance.enable();
    network.onLoadingFinished(
        event -> {
          // Close the tab and close the browser when loading finishes.
          List<Metric> metrics = performance.getMetrics();
          try {
            for (Metric metric : metrics) {
              System.out.println(metric.getName() + ": " + metric.getValue());
            }
          } catch (Exception e) {
            System.out.println(e.getMessage());
          }
          chromeService.closeTab(tab);
          launcher.close();
        });

    // Enable network events.
    network.enable();

    // Navigate to github.com.
    page.navigate("https://www.github.com");

    devToolsService.waitUntilClosed();
  }
}
