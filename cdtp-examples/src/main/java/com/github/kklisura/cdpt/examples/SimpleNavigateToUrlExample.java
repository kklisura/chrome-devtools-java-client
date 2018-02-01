package com.github.kklisura.cdpt.examples;

import com.github.kklisura.cdtp.launch.ChromeLauncher;
import com.github.kklisura.cdtp.protocol.commands.Page;
import com.github.kklisura.cdtp.services.ChromeDevToolsService;
import com.github.kklisura.cdtp.services.ChromeService;
import com.github.kklisura.cdtp.services.types.ChromeTab;

/**
 * Simple navigate-to-url example with DevTools Protocol java client.
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
