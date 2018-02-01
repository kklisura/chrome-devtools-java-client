package com.github.kklisura.cdpt.examples;

import com.github.kklisura.cdtp.launch.ChromeLauncher;
import com.github.kklisura.cdtp.protocol.commands.Network;
import com.github.kklisura.cdtp.protocol.commands.Page;
import com.github.kklisura.cdtp.protocol.types.network.ErrorReason;
import com.github.kklisura.cdtp.services.ChromeDevToolsService;
import com.github.kklisura.cdtp.services.ChromeService;
import com.github.kklisura.cdtp.services.types.ChromeTab;

/**
 * Intercept and block per URL. Since requestIntercepted event is still Experimental it might not
 * work on your browser.
 *
 * @author Kenan Klisura
 */
public class InterceptAndBlockUrlsExample {
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

    network.onRequestIntercepted(
        event -> {
          String interceptionId = event.getInterceptionId();
          boolean blocked = isBlocked(event.getRequest().getUrl());

          System.out.printf(
              "%s - %s%s",
              (blocked ? "BLOCKED" : "ALLOWED"),
              event.getRequest().getUrl(),
              System.lineSeparator());

          ErrorReason errorReason = blocked ? ErrorReason.ABORTED : null;

          network.continueInterceptedRequest(
              interceptionId, errorReason, null, null, null, null, null, null);
        });

    page.onLoadEventFired(event -> devToolsService.close());

    network.setRequestInterceptionEnabled(Boolean.TRUE);
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
