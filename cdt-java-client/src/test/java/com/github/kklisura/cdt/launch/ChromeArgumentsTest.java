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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import org.easymock.EasyMockSupport;
import org.junit.Test;

/**
 * Test chrome arguments.
 *
 * @author Kenan Klisura
 */
public class ChromeArgumentsTest extends EasyMockSupport {
  @Test
  public void testBuilder() {
    assertNotNull(ChromeArguments.builder());
  }

  @Test
  public void testDefaults() {
    ChromeArguments build = ChromeArguments.defaults(false).build();

    assertDefaults(build);

    assertNull(build.getHeadless());
    assertNull(build.getDisableGpu());
    assertNull(build.getHideScrollbars());
    assertNull(build.getMuteAudio());
  }

  @Test
  public void testDefaultsWithHeadless() {
    ChromeArguments build = ChromeArguments.defaults(true).build();

    assertDefaults(build);

    assertTrue(build.getHeadless());
    assertTrue(build.getDisableGpu());
    assertTrue(build.getHideScrollbars());
    assertTrue(build.getMuteAudio());
  }

  @Test
  public void testDefaultsWithHeadlessWithRemoteDebuggingPort() {
    ChromeArguments build = ChromeArguments.defaults(true).remoteDebuggingPort(9123).build();

    assertEquals(9123, build.getRemoteDebuggingPort().intValue());
  }

  @Test
  public void testDefaultsWithHeadlessAndSomeParams() {
    ChromeArguments build =
        ChromeArguments.defaults(true)
            .incognito()
            .remoteDebuggingPort(null)
            .additionalArguments(
                new HashMap<String, Object>() {
                  {
                    put("some-arg", "v0");
                    put("some-arg1", "v1");
                  }
                })
            .additionalArguments("some-arg", "v1")
            .build();

    assertDefaults(build);

    assertTrue(build.getHeadless());
    assertTrue(build.getDisableGpu());
    assertTrue(build.getHideScrollbars());
    assertTrue(build.getMuteAudio());

    assertTrue(build.getIncognito());

    assertFalse(build.getAdditionalArguments().isEmpty());
    assertEquals("v1", build.getAdditionalArguments().get("some-arg"));
    assertEquals("v1", build.getAdditionalArguments().get("some-arg1"));
  }

  private void assertDefaults(ChromeArguments args) {
    assertTrue(args.getNoFirstRun());
    assertTrue(args.getNoDefaultBrowserCheck());
    assertTrue(args.getDisableBackgroundNetworking());
    assertTrue(args.getDisableBackgroundTimerThrottling());
    assertTrue(args.getDisableClientSidePhishingDetection());
    assertTrue(args.getDisableDefaultApps());
    assertTrue(args.getDisableExtensions());
    assertTrue(args.getDisableHangMonitor());
    assertTrue(args.getDisablePromptOnRepost());
    assertTrue(args.getDisableSync());
    assertTrue(args.getDisableTranslate());
    assertTrue(args.getMetricsRecordingOnly());
    assertTrue(args.getSafebrowsingDisableAutoUpdate());
    assertTrue(args.getDisablePopupBlocking());

    assertNull(args.getUserDataDir());

    assertEquals(0, args.getRemoteDebuggingPort().intValue());
  }

  public static void assertTrue(Boolean value) {
    assertEquals(Boolean.TRUE, value);
  }
}
