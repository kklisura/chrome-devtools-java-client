package com.github.kklisura.cdpt.examples;

import com.github.kklisura.cdtp.launch.ChromeLauncher;
import com.github.kklisura.cdtp.protocol.commands.Network;
import com.github.kklisura.cdtp.protocol.commands.Page;
import com.github.kklisura.cdtp.services.ChromeDevToolsService;
import com.github.kklisura.cdtp.services.ChromeService;
import com.github.kklisura.cdtp.services.types.ChromeTab;
import java.util.Arrays;

/**
 * Blocks an URLs given a patterns.
 *
 * @author Kenan Klisura
 */
public class BlockUrlGIvenPatternExample {
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
    final Network network = devToolsService.getNetwork();

    // Block some urls.
    network.setBlockedURLs(Arrays.asList(
        "**/*.css",
        "**/*.png",
        "**/*.svg"
    ));

    // Enable network events
    network.enable();

    // Wait for on load event
    page.onLoadEventFired(
        event -> {
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