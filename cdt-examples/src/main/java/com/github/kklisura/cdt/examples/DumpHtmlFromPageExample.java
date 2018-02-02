package com.github.kklisura.cdt.examples;

import com.github.kklisura.cdt.launch.ChromeLauncher;
import com.github.kklisura.cdt.protocol.commands.Page;
import com.github.kklisura.cdt.protocol.commands.Runtime;
import com.github.kklisura.cdt.protocol.types.runtime.Evaluate;
import com.github.kklisura.cdt.services.ChromeDevToolsService;
import com.github.kklisura.cdt.services.ChromeService;
import com.github.kklisura.cdt.services.types.ChromeTab;

/**
 * The following example dumps the index html from github.com.
 *
 * @author Kenan Klisura
 */
public class DumpHtmlFromPageExample {
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
    final Runtime runtime = devToolsService.getRuntime();

    // Wait for on load event
    page.onLoadEventFired(
        event -> {
          // Evaluate javascript
          Evaluate evaluation = runtime.evaluate("document.documentElement.outerHTML");
          System.out.println(evaluation.getResult().getValue());

          // Close devtools.
          devToolsService.close();
        });

    // Enable page events.
    page.enable();

    // Navigate to github.com.
    page.navigate("http://github.com");

    // Wait until devtools is closed.
    devToolsService.waitUntilClosed();

    // Close tab.
    chromeService.closeTab(tab);
  }
}
