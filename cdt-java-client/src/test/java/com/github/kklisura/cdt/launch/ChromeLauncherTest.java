package com.github.kklisura.cdt.launch;

/*-
 * #%L
 * cdt-java-client
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

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.capture;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replay;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.github.kklisura.cdt.launch.ChromeLauncher.Environment;
import com.github.kklisura.cdt.launch.ChromeLauncher.ShutdownHookRegistry;
import com.github.kklisura.cdt.launch.config.ChromeLauncherConfiguration;
import com.github.kklisura.cdt.launch.exceptions.ChromeProcessTimeoutException;
import com.github.kklisura.cdt.launch.support.ProcessLauncher;
import com.github.kklisura.cdt.launch.utils.LogCollector;
import com.github.kklisura.cdt.services.ChromeService;
import com.github.kklisura.cdt.services.impl.ChromeServiceImpl;
import com.github.kklisura.cdt.utils.FilesUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.easymock.Capture;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.LoggerFactory;

/**
 * Chrome launcher test.
 *
 * @author Kenan Klisura
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ChromeLauncher.class, FilesUtils.class})
public class ChromeLauncherTest extends EasyMockSupport {

  @Mock private ProcessLauncher processLauncher;

  @Mock private Environment environment;

  @Mock private ShutdownHookRegistry shutdownHookRegistry;

  @Mock private Process process;

  private ChromeLauncher launcher;

  @Before
  public void setUp() throws Exception {
    mockStatic(FilesUtils.class);

    launcher =
        new ChromeLauncher(
            processLauncher, environment, shutdownHookRegistry, new ChromeLauncherConfiguration());
  }

  @Test(expected = RuntimeException.class)
  public void testGetChromeBinaryPathThrowsExceptionWhenNoBinaryFoundOnChromePath() {
    expect(environment.getEnv("CHROME_PATH")).andReturn("test");

    expect(processLauncher.isExecutable("test")).andReturn(false);

    replayAll();

    launcher.getChromeBinaryPath();
  }

  @Test(expected = RuntimeException.class)
  public void testGetChromeBinaryPathThrowsExceptionWhenNoBinaryFound() {
    expect(environment.getEnv("CHROME_PATH")).andReturn(null);

    expect(processLauncher.isExecutable("/snap/bin/chromium")).andReturn(false);
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
    expect(processLauncher.isExecutable("C:/Program Files/Google/Chrome/Application/chrome.exe"))
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
  public void testLaunchWithBinaryAndArgumentsAndDefaultUserDataDir()
      throws IOException, InterruptedException, ChromeProcessTimeoutException {
    final Path binaryPath = Paths.get("test-binary-path");

    final ChromeArguments chromeArguments =
        ChromeArguments.builder()
            .noFirstRun(Boolean.FALSE)
            .incognito()
            .remoteDebuggingPort(null)
            .disableBackgroundNetworking()
            .disableDefaultApps()
            .build();

    shutdownHookRegistry.register(anyObject());

    final String trigger = "\r\n\r\nDevTools listening on ws://127.0.0.1:9123/";
    expect(process.getInputStream()).andReturn(new ByteArrayInputStream(trigger.getBytes()));
    expect(process.isAlive()).andReturn(true);

    Capture<List<String>> captureArguments = Capture.newInstance();
    expect(processLauncher.launch(eq("test-binary-path"), capture(captureArguments)))
        .andReturn(process);

    expect(FilesUtils.randomTempDir("cdt-user-data-dir")).andReturn("temp-user-data-dir");

    replayAll();
    PowerMock.replay(FilesUtils.class);

    ChromeService launch = launcher.launch(binaryPath, chromeArguments);

    PowerMock.verify(FilesUtils.class);

    assertNotNull(launch);
    assertTrue(launch instanceof ChromeServiceImpl);

    assertEquals(9123, ((ChromeServiceImpl) launch).getPort());

    List<String> arguments = captureArguments.getValue();

    assertEquals(5, arguments.size());
    assertTrue(arguments.contains("--incognito"));
    assertTrue(arguments.contains("--disable-background-networking"));
    assertTrue(arguments.contains("--disable-default-apps"));
    assertTrue(arguments.contains("--remote-debugging-port=0"));
    assertTrue(arguments.contains("--user-data-dir=temp-user-data-dir"));

    try {
      launcher.launch(binaryPath, chromeArguments);
      fail(
          "IllegalStateException should be thrown from launch when launching already active process.");
    } catch (IllegalStateException e) {
      // Ignore this exception.
    }

    verifyAll();

    // Test closing
    resetAll();
    PowerMock.resetAll(FilesUtils.class);

    process.destroy();
    expect(process.waitFor(60, TimeUnit.SECONDS)).andReturn(true);
    expect(process.isAlive()).andReturn(true);
    expect(process.isAlive()).andReturn(false);

    shutdownHookRegistry.remove(anyObject());

    Capture<Path> deletePathCapture = Capture.newInstance();
    FilesUtils.deleteQuietly(capture(deletePathCapture));

    replayAll();
    replay(FilesUtils.class);

    launcher.close();
    launcher.close();

    verify();
    PowerMock.verify(FilesUtils.class);

    assertEquals("temp-user-data-dir", deletePathCapture.getValue().toString());
  }

  @Test
  public void testLaunchWithBinaryAndArgumentsAndCustomUserDataDir()
      throws IOException, InterruptedException, ChromeProcessTimeoutException {
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
    expect(process.isAlive()).andReturn(true);

    replayAll();

    ChromeService launch = launcher.launch(binaryPath, chromeArguments);

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

    verifyAll();

    // Test closing
    resetAll();

    process.destroy();
    expect(process.waitFor(60, TimeUnit.SECONDS)).andReturn(true);

    expect(process.isAlive()).andReturn(true);
    expect(process.isAlive()).andReturn(false);

    shutdownHookRegistry.remove(anyObject());

    FilesUtils.deleteQuietly(null);

    replayAll();
    replay(FilesUtils.class);

    launcher.close();
    launcher.close();

    verify();
    PowerMock.verify(FilesUtils.class);
  }

  @Test
  public void testLaunchWithBinaryAndArgumentsAndCloseWithInterruptedException()
      throws IOException, InterruptedException, ChromeProcessTimeoutException {
    final Path binaryPath = Paths.get("test-binary-path");

    final ChromeArguments chromeArguments =
        ChromeArguments.builder().incognito().userDataDir("user-data-dir-param").build();

    shutdownHookRegistry.register(anyObject());

    final String trigger = "\r\n\r\nDevTools listening on ws://127.0.0.1:9123/";
    expect(process.getInputStream()).andReturn(new ByteArrayInputStream(trigger.getBytes()));
    expect(process.isAlive()).andReturn(true);

    Capture<List<String>> captureArguments = Capture.newInstance();
    expect(processLauncher.launch(eq("test-binary-path"), capture(captureArguments)))
        .andReturn(process);

    replayAll();

    ChromeService launch = launcher.launch(binaryPath, chromeArguments);

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

    verifyAll();

    // Test closing
    resetAll();

    process.destroy();
    expect(process.waitFor(60, TimeUnit.SECONDS)).andThrow(new InterruptedException());

    expect(process.destroyForcibly()).andReturn(process);

    expect(process.isAlive()).andReturn(true);

    shutdownHookRegistry.remove(anyObject());

    FilesUtils.deleteQuietly(null);

    replayAll();
    PowerMock.replay(FilesUtils.class);

    launcher.close();

    verify();
    PowerMock.verify(FilesUtils.class);
  }

  @Test
  public void testLaunchHeadlessWithBinary()
      throws IOException, InterruptedException, ChromeProcessTimeoutException {
    expect(environment.getEnv("CHROME_PATH")).andReturn("/test-binary-path");
    expect(processLauncher.isExecutable("/test-binary-path")).andReturn(true);

    shutdownHookRegistry.register(anyObject());

    final String trigger = "\r\n\r\nDevTools listening on ws://127.0.0.1:9123/";
    expect(process.getInputStream()).andReturn(new ByteArrayInputStream(trigger.getBytes()));

    Capture<List<String>> captureArguments = Capture.newInstance();
    expect(processLauncher.launch(eq("/test-binary-path"), capture(captureArguments)))
        .andReturn(process);

    expect(FilesUtils.randomTempDir("cdt-user-data-dir")).andReturn("temp-user-data-dir");

    replayAll();
    PowerMock.replay(FilesUtils.class);

    ChromeService launch = launcher.launch();

    verifyAll();
    PowerMock.verify(FilesUtils.class);

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
    assertTrue(arguments.contains("--user-data-dir=temp-user-data-dir"));
  }

  @Test(expected = RuntimeException.class)
  public void testLaunchWithBinaryAndArgumentsFailsOnReading()
      throws IOException, InterruptedException, ChromeProcessTimeoutException {
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
    expect(process.isAlive()).andReturn(true);

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

    final String trigger = "test\r\ntest";
    expect(process.getInputStream()).andReturn(new ByteArrayInputStream(trigger.getBytes()));
    expect(process.isAlive()).andReturn(true);

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
    } catch (ChromeProcessTimeoutException e) {
      assertEquals(
          "Failed while waiting for chrome to start: Timeout expired! Chrome output: test\ntest",
          e.getMessage());
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

    final String trigger = "test\r\n\r\n";
    expect(process.getInputStream()).andReturn(new ByteArrayInputStream(trigger.getBytes()));
    expect(process.isAlive()).andReturn(true);

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
    } catch (ChromeProcessTimeoutException e) {
      assertEquals(
          "Failed while waiting for chrome to start: Timeout expired! Chrome output: test\n",
          e.getMessage());
    }

    assertEquals(removeCaptureShutdownThread.getValue(), addCaptureShutdownThread.getValue());
  }

  @Test
  public void testIsAlive() throws IOException {
    assertFalse(launcher.isAlive());

    final Path binaryPath = Paths.get("test-binary-path");

    final ChromeArguments chromeArguments =
        ChromeArguments.builder().incognito().userDataDir("user-data-dir-param").build();

    shutdownHookRegistry.register(anyObject());

    final String trigger = "\r\n\r\nDevTools listening on ws://127.0.0.1:9123/";
    expect(process.getInputStream()).andReturn(new ByteArrayInputStream(trigger.getBytes()));

    expect(process.isAlive()).andReturn(true);

    Capture<List<String>> captureArguments = Capture.newInstance();
    expect(processLauncher.launch(eq("test-binary-path"), capture(captureArguments)))
        .andReturn(process);

    replayAll();

    launcher.launch(binaryPath, chromeArguments);

    assertTrue(launcher.isAlive());

    verifyAll();
  }

  @Test(expected = IllegalStateException.class)
  public void testExitValueThrowsExceptionWhenProcessNotStarted() throws IOException {
    launcher.exitValue();
  }

  @Test
  public void testExitValue() throws IOException {
    final Path binaryPath = Paths.get("test-binary-path");

    final ChromeArguments chromeArguments =
        ChromeArguments.builder().incognito().userDataDir("user-data-dir-param").build();

    shutdownHookRegistry.register(anyObject());

    final String trigger = "\r\n\r\nDevTools listening on ws://127.0.0.1:9123/";
    expect(process.getInputStream()).andReturn(new ByteArrayInputStream(trigger.getBytes()));

    expect(process.exitValue()).andReturn(123);

    Capture<List<String>> captureArguments = Capture.newInstance();
    expect(processLauncher.launch(eq("test-binary-path"), capture(captureArguments)))
        .andReturn(process);

    replayAll();

    launcher.launch(binaryPath, chromeArguments);

    assertEquals(123, launcher.exitValue());

    verifyAll();
  }

  /**
   * Registers appender that adds logging events to the argument loggingEvents. This also sets the
   * level of the logger to DEBUG.
   *
   * @param name Name of the logger.
   * @param loggingEvents Logging events.
   */
  private static void registerAppenderOnDebugLogger(
      String name, Level level, List<String> loggingEvents) {
    LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

    final Logger logger = loggerContext.getLogger(name);
    logger.setLevel(level);
    logger.addAppender(new LogCollector(loggingEvents));
  }

  @Test
  public void testLaunchWithDebugLogging()
      throws IOException, ChromeProcessTimeoutException, InterruptedException {
    List<String> loggingEvents = new ArrayList<>();
    registerAppenderOnDebugLogger(
        "com.github.kklisura.cdt.launch.chrome.output", Level.DEBUG, loggingEvents);

    expect(environment.getEnv("CHROME_PATH")).andReturn("/test-binary-path");
    expect(processLauncher.isExecutable("/test-binary-path")).andReturn(true);

    shutdownHookRegistry.register(anyObject());

    final String trigger =
        "first-line\r\nsecond-line\r\nDevTools listening on ws://127.0.0.1:9123/\r\nthird-line\r\nforth-line\r\n";
    expect(process.getInputStream()).andReturn(new ByteArrayInputStream(trigger.getBytes()));

    expect(processLauncher.launch(eq("/test-binary-path"), anyObject())).andReturn(process);

    expect(FilesUtils.randomTempDir("cdt-user-data-dir")).andReturn("temp-user-data-dir");

    replayAll();
    PowerMock.replay(FilesUtils.class);

    ChromeService launch = launcher.launch();

    verifyAll();
    PowerMock.verify(FilesUtils.class);

    assertNotNull(launch);
    assertTrue(launch instanceof ChromeServiceImpl);

    assertEquals(9123, ((ChromeServiceImpl) launch).getPort());

    // Give a thread in waitForDevToolsServer method a bit more chance to collect logging events.
    Thread.sleep(100);

    assertEquals(5, loggingEvents.size());
    assertEquals("[DEBUG] first-line", loggingEvents.get(0));
    assertEquals("[DEBUG] second-line", loggingEvents.get(1));
    assertEquals("[DEBUG] DevTools listening on ws://127.0.0.1:9123/", loggingEvents.get(2));
    assertEquals("[DEBUG] third-line", loggingEvents.get(3));
    assertEquals("[DEBUG] forth-line", loggingEvents.get(4));
  }

  @Test
  public void testLaunchWithErrorLoggingDoesNotLogAnything()
      throws IOException, ChromeProcessTimeoutException {
    List<String> loggingEvents = new ArrayList<>();
    registerAppenderOnDebugLogger(
        "com.github.kklisura.cdt.launch.chrome.output", Level.ERROR, loggingEvents);

    expect(environment.getEnv("CHROME_PATH")).andReturn("/test-binary-path");
    expect(processLauncher.isExecutable("/test-binary-path")).andReturn(true);

    shutdownHookRegistry.register(anyObject());

    final String trigger =
        "first-line\r\nsecond-line\r\nDevTools listening on ws://127.0.0.1:9123/\r\nthird-line\r\nforth-line\r\n";
    expect(process.getInputStream()).andReturn(new ByteArrayInputStream(trigger.getBytes()));

    expect(processLauncher.launch(eq("/test-binary-path"), anyObject())).andReturn(process);

    expect(FilesUtils.randomTempDir("cdt-user-data-dir")).andReturn("temp-user-data-dir");

    replayAll();
    PowerMock.replay(FilesUtils.class);

    ChromeService launch = launcher.launch();

    verifyAll();
    PowerMock.verify(FilesUtils.class);

    assertNotNull(launch);
    assertTrue(launch instanceof ChromeServiceImpl);

    assertEquals(9123, ((ChromeServiceImpl) launch).getPort());

    assertEquals(0, loggingEvents.size());
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
