package com.github.kklisura.cdtp.launch;

/*-
 * #%L
 * cdpt-java-client
 * %%
 * Copyright (C) 2018 Kenan Klisura
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

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.capture;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.github.kklisura.cdtp.launch.ChromeLauncher.Environment;
import com.github.kklisura.cdtp.launch.ChromeLauncher.ShutdownHookRegistry;
import com.github.kklisura.cdtp.launch.config.ChromeLauncherConfiguration;
import com.github.kklisura.cdtp.launch.support.ProcessLauncher;
import com.github.kklisura.cdtp.services.ChromeService;
import com.github.kklisura.cdtp.services.impl.ChromeServiceImpl;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.easymock.Capture;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Chrome launcher test.
 *
 * @author Kenan Klisura
 */
@RunWith(EasyMockRunner.class)
public class ChromeLauncherTest extends EasyMockSupport {

  @Mock private ProcessLauncher processLauncher;

  @Mock private Environment environment;

  @Mock private ShutdownHookRegistry shutdownHookRegistry;

  @Mock private Process process;

  private ChromeLauncher launcher;

  @Before
  public void setUp() throws Exception {
    launcher =
        new ChromeLauncher(
            processLauncher, environment, shutdownHookRegistry, new ChromeLauncherConfiguration());
  }

  @Test(expected = RuntimeException.class)
  public void testGetChromeBinaryPathThrowsExceptionWhenNoBinaryFound() {
    expect(environment.getEnv("CHROME_PATH")).andReturn("test");

    expect(processLauncher.isExecutable("test")).andReturn(false);

    expect(processLauncher.isExecutable("/usr/bin/chromium")).andReturn(false);
    expect(processLauncher.isExecutable("/usr/bin/chromium-browser")).andReturn(false);
    expect(processLauncher.isExecutable("/usr/bin/google-chrome-stable")).andReturn(false);
    expect(processLauncher.isExecutable("/usr/bin/google-chrome")).andReturn(false);
    expect(processLauncher.isExecutable("/Applications/Chromium.app/Contents/MacOS/Chromium"))
        .andReturn(false);
    expect(
            processLauncher.isExecutable(
                "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome"))
        .andReturn(false);
    expect(
            processLauncher.isExecutable(
                "/Applications/Google Chrome Canary.app/Contents/MacOS/Google Chrome Canary"))
        .andReturn(false);
    expect(
            processLauncher.isExecutable(
                "C:/Program Files (x86)/Google/Chrome/Application/chrome.exe"))
        .andReturn(false);

    replayAll();

    launcher.getChromeBinaryPath();
  }

  @Test
  public void testGetChromeBinaryPathReturnsSomePath() {
    expect(environment.getEnv("CHROME_PATH")).andReturn(null);

    expect(processLauncher.isExecutable("/usr/bin/chromium")).andReturn(false);
    expect(processLauncher.isExecutable("/usr/bin/chromium-browser")).andReturn(false);
    expect(processLauncher.isExecutable("/usr/bin/google-chrome-stable")).andReturn(false);
    expect(processLauncher.isExecutable("/usr/bin/google-chrome")).andReturn(true);

    replayAll();

    Path chromeBinaryPath = launcher.getChromeBinaryPath();

    assertNotNull(chromeBinaryPath);
    assertNotNull("/usr/bin/google-chrome", chromeBinaryPath.toString());

    verifyAll();
  }

  @Test
  public void testGetChromeBinaryPathReturnsPathFromEnv() {
    expect(environment.getEnv("CHROME_PATH")).andReturn("test/env/path");

    expect(processLauncher.isExecutable("test/env/path")).andReturn(true);

    replayAll();

    Path chromeBinaryPath = launcher.getChromeBinaryPath();

    assertNotNull(chromeBinaryPath);
    assertNotNull("test/env/path", chromeBinaryPath.toString());

    verifyAll();
  }

  @Test
  public void testLaunchWithBinaryAndArguments() throws IOException, InterruptedException {
    final Path binaryPath = Paths.get("test-binary-path");

    final ChromeArguments chromeArguments =
        ChromeArguments.builder()
            .noFirstRun(Boolean.FALSE)
            .incognito()
            .remoteDebuggingPort(null)
            .disableBackgroundNetworking()
            .disableDefaultApps()
            .userDataDir("user-data-dir-param")
            .build();

    shutdownHookRegistry.register(anyObject());

    final String trigger = "\r\n\r\nDevTools listening on ws://127.0.0.1:9123/";
    expect(process.getInputStream()).andReturn(new ByteArrayInputStream(trigger.getBytes()));

    Capture<List<String>> captureArguments = Capture.newInstance();
    expect(processLauncher.launch(eq("test-binary-path"), capture(captureArguments)))
        .andReturn(process);

    replayAll();

    ChromeService launch = launcher.launch(binaryPath, chromeArguments);

    verifyAll();

    assertNotNull(launch);
    assertTrue(launch instanceof ChromeServiceImpl);

    assertEquals(9123, ((ChromeServiceImpl) launch).getPort());

    List<String> arguments = captureArguments.getValue();

    assertEquals(5, arguments.size());
    assertTrue(arguments.contains("--incognito"));
    assertTrue(arguments.contains("--disable-background-networking"));
    assertTrue(arguments.contains("--disable-default-apps"));
    assertTrue(arguments.contains("--remote-debugging-port=0"));
    assertTrue(arguments.contains("--user-data-dir=user-data-dir-param"));

    try {
      launcher.launch(binaryPath, chromeArguments);
      fail(
          "IllegalStateException should be thrown from launch when launching already active process.");
    } catch (IllegalStateException e) {
      // Ignore this exception.
    }

    // Test closing
    resetAll();

    process.destroy();
    expect(process.waitFor(60, TimeUnit.SECONDS)).andReturn(true);

    shutdownHookRegistry.remove(anyObject());

    replayAll();

    launcher.close();
    launcher.close();

    verify();
  }

  @Test
  public void testLaunchWithBinaryAndArgumentsAndCloseWithInterruptedException()
      throws IOException, InterruptedException {
    final Path binaryPath = Paths.get("test-binary-path");

    final ChromeArguments chromeArguments =
        ChromeArguments.builder().incognito().userDataDir("user-data-dir-param").build();

    shutdownHookRegistry.register(anyObject());

    final String trigger = "\r\n\r\nDevTools listening on ws://127.0.0.1:9123/";
    expect(process.getInputStream()).andReturn(new ByteArrayInputStream(trigger.getBytes()));

    Capture<List<String>> captureArguments = Capture.newInstance();
    expect(processLauncher.launch(eq("test-binary-path"), capture(captureArguments)))
        .andReturn(process);

    replayAll();

    ChromeService launch = launcher.launch(binaryPath, chromeArguments);

    verifyAll();

    assertNotNull(launch);
    assertTrue(launch instanceof ChromeServiceImpl);

    assertEquals(9123, ((ChromeServiceImpl) launch).getPort());

    List<String> arguments = captureArguments.getValue();

    assertEquals(3, arguments.size());
    assertTrue(arguments.contains("--incognito"));
    assertTrue(arguments.contains("--remote-debugging-port=0"));
    assertTrue(arguments.contains("--user-data-dir=user-data-dir-param"));

    try {
      launcher.launch(binaryPath, chromeArguments);
      fail(
          "IllegalStateException should be thrown from launch when launching already active process.");
    } catch (IllegalStateException e) {
      // Ignore this exception.
    }

    // Test closing
    resetAll();

    process.destroy();
    expect(process.waitFor(60, TimeUnit.SECONDS)).andThrow(new InterruptedException());

    expect(process.destroyForcibly()).andReturn(process);

    shutdownHookRegistry.remove(anyObject());

    replayAll();

    launcher.close();

    verify();
  }

  @Test
  public void testLaunchHeadlessWithBinary() throws IOException, InterruptedException {
    expect(environment.getEnv("CHROME_PATH")).andReturn("/test-binary-path");
    expect(processLauncher.isExecutable("/test-binary-path")).andReturn(true);

    shutdownHookRegistry.register(anyObject());

    final String trigger = "\r\n\r\nDevTools listening on ws://127.0.0.1:9123/";
    expect(process.getInputStream()).andReturn(new ByteArrayInputStream(trigger.getBytes()));

    Capture<List<String>> captureArguments = Capture.newInstance();
    expect(processLauncher.launch(eq("/test-binary-path"), capture(captureArguments)))
        .andReturn(process);

    replayAll();

    ChromeService launch = launcher.launch();

    verifyAll();

    assertNotNull(launch);
    assertTrue(launch instanceof ChromeServiceImpl);

    assertEquals(9123, ((ChromeServiceImpl) launch).getPort());

    List<String> arguments = captureArguments.getValue();
    assertEquals(20, arguments.size());
    assertTrue(arguments.contains("--no-first-run"));
    assertTrue(arguments.contains("--remote-debugging-port=0"));
    assertTrue(arguments.contains("--mute-audio"));
    assertTrue(arguments.contains("--disable-client-side-phishing-detection"));
    assertTrue(arguments.contains("--disable-popup-blocking"));
    assertTrue(arguments.contains("--disable-default-apps"));
    assertTrue(arguments.contains("--disable-extensions"));
    assertTrue(arguments.contains("--metrics-recording-only"));
    assertTrue(arguments.contains("--no-default-browser-check"));
    assertTrue(arguments.contains("--disable-background-timer-throttling"));
    assertTrue(arguments.contains("--disable-translate"));
    assertTrue(arguments.contains("--safebrowsing-disable-auto-update"));
    assertTrue(arguments.contains("--headless"));
    assertTrue(arguments.contains("--hide-scrollbars"));
    assertTrue(arguments.contains("--disable-background-networking"));
    assertTrue(arguments.contains("--disable-prompt-on-repost"));
    assertTrue(arguments.contains("--disable-hang-monitor"));
    assertTrue(arguments.contains("--disable-sync"));
    assertTrue(arguments.contains("--disable-gpu"));

    assertUserDataDir(arguments);
  }

  @Test(expected = RuntimeException.class)
  public void testLaunchWithBinaryAndArgumentsFailsOnReading()
      throws IOException, InterruptedException {
    final ChromeArguments chromeArguments =
        ChromeArguments.builder()
            .incognito()
            .remoteDebuggingPort(null)
            .disableBackgroundNetworking()
            .disableDefaultApps()
            .userDataDir("user-data-dir-param")
            .build();

    shutdownHookRegistry.register(anyObject());

    expect(environment.getEnv("CHROME_PATH")).andReturn("/test-binary-path");

    expect(processLauncher.isExecutable("/test-binary-path")).andReturn(true);

    expect(process.getInputStream()).andThrow(new RuntimeException("test exception"));

    Capture<List<String>> captureArguments = Capture.newInstance();
    expect(processLauncher.launch(eq("/test-binary-path"), capture(captureArguments)))
        .andReturn(process);

    process.destroy();
    expect(process.waitFor(60, TimeUnit.SECONDS)).andReturn(true);

    shutdownHookRegistry.remove(anyObject());

    replayAll();

    launcher.launch(chromeArguments);
  }

  @Test
  public void testLaunchWithBinaryAndArgumentsThrowsExceptionOnTimeout()
      throws IOException, InterruptedException {
    final Path binaryPath = Paths.get("test-binary-path");

    final ChromeArguments chromeArguments =
        ChromeArguments.builder()
            .incognito()
            .disableBackgroundNetworking()
            .disableDefaultApps()
            .userDataDir("user-data-dir-param")
            .build();

    Capture<Thread> addCaptureShutdownThread = Capture.newInstance();
    shutdownHookRegistry.register(capture(addCaptureShutdownThread));

    final String trigger = "\r\n\r\n";
    expect(process.getInputStream()).andReturn(new ByteArrayInputStream(trigger.getBytes()));

    Capture<List<String>> captureArguments = Capture.newInstance();
    expect(processLauncher.launch(eq("test-binary-path"), capture(captureArguments)))
        .andReturn(process);

    process.destroy();
    expect(process.waitFor(60, TimeUnit.SECONDS)).andReturn(true);

    Capture<Thread> removeCaptureShutdownThread = Capture.newInstance();
    shutdownHookRegistry.remove(capture(removeCaptureShutdownThread));

    replayAll();

    ChromeLauncherConfiguration configuration = new ChromeLauncherConfiguration();
    configuration.setStartupWaitTime(1);
    launcher =
        new ChromeLauncher(processLauncher, environment, shutdownHookRegistry, configuration);

    try {
      launcher.launch(binaryPath, chromeArguments);
      fail("Exception not thrown on timeout.");
    } catch (RuntimeException e) {
      assertEquals("Failed while waiting for chrome to start. Timeout expired!", e.getMessage());
    }

    assertEquals(removeCaptureShutdownThread.getValue(), addCaptureShutdownThread.getValue());
  }

  @Test
  public void testLaunchWithBinaryAndArgumentsThrowsExceptionOnTimeoutForciblyClosingProcess()
      throws IOException, InterruptedException {
    final Path binaryPath = Paths.get("test-binary-path");

    final ChromeArguments chromeArguments =
        ChromeArguments.builder()
            .incognito()
            .disableBackgroundNetworking()
            .disableDefaultApps()
            .userDataDir("user-data-dir-param")
            .build();

    Capture<Thread> addCaptureShutdownThread = Capture.newInstance();
    shutdownHookRegistry.register(capture(addCaptureShutdownThread));

    final String trigger = "\r\n\r\n";
    expect(process.getInputStream()).andReturn(new ByteArrayInputStream(trigger.getBytes()));

    Capture<List<String>> captureArguments = Capture.newInstance();
    expect(processLauncher.launch(eq("test-binary-path"), capture(captureArguments)))
        .andReturn(process);

    process.destroy();
    expect(process.waitFor(60, TimeUnit.SECONDS)).andReturn(false);
    expect(process.destroyForcibly()).andReturn(process);
    expect(process.waitFor(60, TimeUnit.SECONDS)).andReturn(true);

    Capture<Thread> removeCaptureShutdownThread = Capture.newInstance();
    shutdownHookRegistry.remove(capture(removeCaptureShutdownThread));

    replayAll();

    ChromeLauncherConfiguration configuration = new ChromeLauncherConfiguration();
    configuration.setStartupWaitTime(1);
    launcher =
        new ChromeLauncher(processLauncher, environment, shutdownHookRegistry, configuration);

    try {
      launcher.launch(binaryPath, chromeArguments);
      fail("Exception not thrown on timeout.");
    } catch (RuntimeException e) {
      assertEquals("Failed while waiting for chrome to start. Timeout expired!", e.getMessage());
    }

    assertEquals(removeCaptureShutdownThread.getValue(), addCaptureShutdownThread.getValue());
  }

  private static void assertUserDataDir(List<String> arguments) {
    boolean hasUserDataDir = false;
    for (String argument : arguments) {
      if (argument.startsWith("--user-data-dir=")) {
        hasUserDataDir = true;
        break;
      }
    }

    assertTrue(hasUserDataDir);
  }
}
