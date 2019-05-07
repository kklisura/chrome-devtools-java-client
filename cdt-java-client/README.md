# Chrome DevTools Java Client

## Description

Chrome DevTools Java Client is a DevTools client - in Java. (: It can be used for *instrumenting, inspecting, debuging and profiling Chromium, Chrome and other Blink-based browsers.* [1]

For more information on DevTools, see https://chromedevtools.github.io/devtools-protocol/.

Tested on Google Chrome Version 67.0.3396.87. Protocol files from [dev-tools-protocol#1aa7b31ca7](https://github.com/ChromeDevTools/devtools-protocol/tree/1aa7b31ca7bba982eceea8d4bd494b27850fb0df/json)

[1] https://chromedevtools.github.io/devtools-protocol/.

## Usage

Add the following dependency to your `pom.xml`:

```xml
<dependency>
  <groupId>com.github.kklisura.cdt</groupId>
  <artifactId>cdt-java-client</artifactId>
  <version>1.3.4</version>
</dependency>
```

You can use following code, taken from, `LogRequestsExample`:

```java
package com.github.kklisura.cdt.examples;

import com.github.kklisura.cdt.launch.ChromeLauncher;
import com.github.kklisura.cdt.protocol.commands.Network;
import com.github.kklisura.cdt.protocol.commands.Page;
import com.github.kklisura.cdt.services.ChromeDevToolsService;
import com.github.kklisura.cdt.services.ChromeService;
import com.github.kklisura.cdt.services.types.ChromeTab;

/**
 * Log requests example with DevTools java client.
 *
 * <p>The following example will open chrome, create a tab with about:blank url, subscribe to
 * requestWillBeSent event and then navigate to github.com.
 *
 * @author Kenan Klisura
 */
public class LogRequestsExample {
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

    // Log requests with onRequestWillBeSent event handler.
    network.onRequestWillBeSent(
        event ->
            System.out.printf(
                "request: %s %s%s",
                event.getRequest().getMethod(),
                event.getRequest().getUrl(),
                System.lineSeparator()));

    network.onLoadingFinished(
        event -> {
          // Close the tab and close the browser when loading finishes.
          chromeService.closeTab(tab);
          launcher.close();
        });

    // Enable network events.
    network.enable();

    // Navigate to github.com.
    page.navigate("http://github.com");

    devToolsService.waitUntilClosed();
  }
}
```

For more examples, see `cdt-examples`.
 
## Running unit tests

`make verify`
 
## Sonar analysis

`make sonar-analysis`

## License

Chrome DevTools Java Client is licensed under the Apache License, Version 2.0. See [LICENSE](LICENSE.txt) for the full license text.
