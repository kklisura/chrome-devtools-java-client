package com.github.kklisura.cdt.examples;

import com.github.kklisura.cdt.launch.ChromeLauncher;
import com.github.kklisura.cdt.protocol.commands.Network;
import com.github.kklisura.cdt.protocol.commands.Page;
import com.github.kklisura.cdt.services.ChromeDevToolsService;
import com.github.kklisura.cdt.services.ChromeService;
import com.github.kklisura.cdt.services.types.ChromeTab;
import java.util.Arrays;

/**
 * Blocks specific urls from fetching. In this case png and css.
 *
 * @author Kenan Klisura
 */
public class BlockUrlsExample {
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

    network.setBlockedURLs(Arrays.asList("*.png", "*.css"));
    page.onLoadEventFired(event -> devToolsService.close());

    network.enable();

    // Enable page events.
    page.enable();

    // Navigate to github.com.
    page.navigate("http://github.com");

    devToolsService.waitUntilClosed();
  }

  public static boolean isBlocked(String url) {
    return url.endsWith(".png") || url.endsWith(".css");
  }
}
