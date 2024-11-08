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

import com.github.kklisura.cdt.launch.support.annotations.ChromeArgument;
import java.util.HashMap;
import java.util.Map;

/**
 * Chrome arguments.
 *
 * @author Kenan Klisura
 */
public class ChromeArguments {
  public static final String USER_DATA_DIR_ARGUMENT = "user-data-dir";

  @ChromeArgument("headless")
  private Boolean headless;

  @ChromeArgument("remote-debugging-port")
  private Integer remoteDebuggingPort = 0;

  @ChromeArgument("no-default-browser-check")
  private Boolean noDefaultBrowserCheck;

  @ChromeArgument("no-first-run")
  private Boolean noFirstRun;

  @ChromeArgument(USER_DATA_DIR_ARGUMENT)
  private String userDataDir;

  @ChromeArgument("incognito")
  private Boolean incognito;

  @ChromeArgument("disable-gpu")
  private Boolean disableGpu;

  @ChromeArgument("hide-scrollbars")
  private Boolean hideScrollbars;

  @ChromeArgument("mute-audio")
  private Boolean muteAudio;

  @ChromeArgument("disable-background-networking")
  private Boolean disableBackgroundNetworking;

  @ChromeArgument("disable-background-timer-throttling")
  private Boolean disableBackgroundTimerThrottling;

  @ChromeArgument("disable-client-side-phishing-detection")
  private Boolean disableClientSidePhishingDetection;

  @ChromeArgument("disable-default-apps")
  private Boolean disableDefaultApps;

  @ChromeArgument("disable-extensions")
  private Boolean disableExtensions;

  @ChromeArgument("disable-hang-monitor")
  private Boolean disableHangMonitor;

  @ChromeArgument("disable-popup-blocking")
  private Boolean disablePopupBlocking;

  @ChromeArgument("disable-prompt-on-repost")
  private Boolean disablePromptOnRepost;

  @ChromeArgument("disable-sync")
  private Boolean disableSync;

  @ChromeArgument("disable-translate")
  private Boolean disableTranslate;

  @ChromeArgument("metrics-recording-only")
  private Boolean metricsRecordingOnly;

  @ChromeArgument("safebrowsing-disable-auto-update")
  private Boolean safebrowsingDisableAutoUpdate;

  @ChromeArgument("enable-logging")
  private String enableLogging;

  private Map<String, Object> additionalArguments;

  /**
   * Is headless.
   *
   * @return True if chrome should start in headless mode.
   */
  public Boolean getHeadless() {
    return headless;
  }

  /**
   * Gets additional arguments.
   *
   * @return Additional arguments to run chrome.
   */
  public Map<String, Object> getAdditionalArguments() {
    if (additionalArguments == null) {
      additionalArguments = new HashMap<>();
    }

    return additionalArguments;
  }

  /**
   * Gets no default browser check.
   *
   * @return the no default browser check
   */
  public Boolean getNoDefaultBrowserCheck() {
    return noDefaultBrowserCheck;
  }

  /**
   * Gets no first run param.
   *
   * @return No first run param.
   */
  public Boolean getNoFirstRun() {
    return noFirstRun;
  }

  /**
   * Gets user data dir.
   *
   * @return User data dir.
   */
  public String getUserDataDir() {
    return userDataDir;
  }

  /**
   * Gets remote debugging port.
   *
   * @return Remote debugging port.
   */
  public Integer getRemoteDebuggingPort() {
    return remoteDebuggingPort;
  }

  /**
   * Gets the incognito flag.
   *
   * @return Incognito flag.
   */
  public Boolean getIncognito() {
    return incognito;
  }

  /**
   * Gets disable gpu flag.
   *
   * @return Disable gpu flag.
   */
  public Boolean getDisableGpu() {
    return disableGpu;
  }

  /**
   * Gets hide scrollbars flag.
   *
   * @return Hide scrollbars flag.
   */
  public Boolean getHideScrollbars() {
    return hideScrollbars;
  }

  /**
   * Gets mute audio flag.
   *
   * @return Mute audio flag.
   */
  public Boolean getMuteAudio() {
    return muteAudio;
  }

  /**
   * Gets disable background networking.
   *
   * @return the disable background networking
   */
  public Boolean getDisableBackgroundNetworking() {
    return disableBackgroundNetworking;
  }

  /**
   * Gets disable background timer throttling.
   *
   * @return the disable background timer throttling
   */
  public Boolean getDisableBackgroundTimerThrottling() {
    return disableBackgroundTimerThrottling;
  }

  /**
   * Gets disable client side phishing detection.
   *
   * @return the disable client side phishing detection
   */
  public Boolean getDisableClientSidePhishingDetection() {
    return disableClientSidePhishingDetection;
  }

  /**
   * Gets disable default apps.
   *
   * @return the disable default apps
   */
  public Boolean getDisableDefaultApps() {
    return disableDefaultApps;
  }

  /**
   * Gets disable extensions.
   *
   * @return the disable extensions
   */
  public Boolean getDisableExtensions() {
    return disableExtensions;
  }

  /**
   * Gets disable hang monitor.
   *
   * @return the disable hang monitor
   */
  public Boolean getDisableHangMonitor() {
    return disableHangMonitor;
  }

  /**
   * Gets disable popup blocking.
   *
   * @return the disable popup blocking
   */
  public Boolean getDisablePopupBlocking() {
    return disablePopupBlocking;
  }

  /**
   * Gets disable prompt on repost.
   *
   * @return the disable prompt on repost
   */
  public Boolean getDisablePromptOnRepost() {
    return disablePromptOnRepost;
  }

  /**
   * Gets disable sync.
   *
   * @return the disable sync
   */
  public Boolean getDisableSync() {
    return disableSync;
  }

  /**
   * Gets disable translate.
   *
   * @return the disable translate
   */
  public Boolean getDisableTranslate() {
    return disableTranslate;
  }

  /**
   * Gets metrics recording only.
   *
   * @return the metrics recording only
   */
  public Boolean getMetricsRecordingOnly() {
    return metricsRecordingOnly;
  }

  /**
   * Gets safebrowsing disable auto update.
   *
   * @return the safebrowsing disable auto update
   */
  public Boolean getSafebrowsingDisableAutoUpdate() {
    return safebrowsingDisableAutoUpdate;
  }

  /**
   * Gets the enable-logging argument.
   *
   * @return Enable logging argument.
   */
  public String getEnableLogging() {
    return enableLogging;
  }

  /**
   * Builder builder.
   *
   * @return Builder. builder
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Creates a builder with default values.
   *
   * @param headless Headless.
   * @return Builder. builder
   * @throws RuntimeException If it fails to create temp directory for user-data-dir.
   */
  public static Builder defaults(boolean headless) {
    Builder builder =
        new Builder()
            .noFirstRun()
            .noDefaultBrowserCheck()
            .disableBackgroundNetworking()
            .disableBackgroundTimerThrottling()
            .disableClientSidePhishingDetection()
            .disableDefaultApps()
            .disableExtensions()
            .disableHangMonitor()
            .disablePopupBlocking()
            .disablePromptOnRepost()
            .disableSync()
            .disableTranslate()
            .metricsRecordingOnly()
            .safebrowsingDisableAutoUpdate();

    if (headless) {
      builder.headless().disableGpu().hideScrollbars().muteAudio();
    }

    return builder;
  }

  /** The type Builder. */
  public static class Builder {
    private ChromeArguments arguments;

    private Builder() {
      arguments = new ChromeArguments();
    }

    /**
     * Launches chrome with headless flag.
     *
     * @param headless Headless flag.
     * @return This builder.
     */
    public Builder headless(Boolean headless) {
      arguments.headless = headless;
      return this;
    }

    /**
     * Launches chrome with headless flag.
     *
     * @return This builder.
     */
    public Builder headless() {
      return headless(Boolean.TRUE);
    }

    /**
     * Launches chrome with remote debugging port.
     *
     * @param remoteDebuggingPort Remote debugging port.
     * @return This builder.
     */
    public Builder remoteDebuggingPort(Integer remoteDebuggingPort) {
      if (remoteDebuggingPort == null) {
        remoteDebuggingPort = 0;
      }

      arguments.remoteDebuggingPort = remoteDebuggingPort;
      return this;
    }

    /**
     * Adds an additional argument.
     *
     * @param key Argument key.
     * @param value Argument value.
     * @return This builder.
     */
    public Builder additionalArguments(String key, Object value) {
      arguments.getAdditionalArguments().put(key, value);
      return this;
    }

    /**
     * Launches chrome with additional arguments.
     *
     * @param additionalArguments Additional arguments.
     * @return This builder.
     */
    public Builder additionalArguments(Map<String, Object> additionalArguments) {
      arguments.additionalArguments = additionalArguments;
      return this;
    }

    /**
     * Launches chrome with no-default-browser-check param.
     *
     * @param noDefaultBrowserCheck Argument value.
     * @return This builder.
     */
    public Builder noDefaultBrowserCheck(Boolean noDefaultBrowserCheck) {
      arguments.noDefaultBrowserCheck = noDefaultBrowserCheck;
      return this;
    }

    /**
     * Launches chrome with no-default-browser-check param.
     *
     * @return This builder.
     */
    public Builder noDefaultBrowserCheck() {
      return noDefaultBrowserCheck(Boolean.TRUE);
    }

    /**
     * Launches chrome with no-first-fun param.
     *
     * @param noFirstRun Argument value.
     * @return This builder.
     */
    public Builder noFirstRun(Boolean noFirstRun) {
      arguments.noFirstRun = noFirstRun;
      return this;
    }

    /**
     * Launches chrome with no-first-fun param.
     *
     * @return This builder.
     */
    public Builder noFirstRun() {
      return noFirstRun(Boolean.TRUE);
    }

    /**
     * Launches chrome with user-data-dir param.
     *
     * @param userDataDir User data directory.
     * @return This builder.
     */
    public Builder userDataDir(String userDataDir) {
      arguments.userDataDir = userDataDir;
      return this;
    }

    /**
     * Incognito builder.
     *
     * @param incognito the incognito
     * @return This builder.
     */
    public Builder incognito(Boolean incognito) {
      arguments.incognito = incognito;
      return this;
    }

    /**
     * Incognito builder.
     *
     * @return This builder.
     */
    public Builder incognito() {
      return incognito(Boolean.TRUE);
    }

    /**
     * Disables gpu.
     *
     * @param disableGpu Argument value.
     * @return This builder.
     */
    public Builder disableGpu(Boolean disableGpu) {
      arguments.disableGpu = disableGpu;
      return this;
    }

    /**
     * Disable gpu builder.
     *
     * @return This builder.
     */
    public Builder disableGpu() {
      return disableGpu(Boolean.TRUE);
    }

    /**
     * Hide scrollbars.
     *
     * @param hideScrollbars Argument value.
     * @return This builder.
     */
    public Builder hideScrollbars(Boolean hideScrollbars) {
      arguments.hideScrollbars = hideScrollbars;
      return this;
    }

    /**
     * Mute audio.
     *
     * @return This builder.
     */
    public Builder hideScrollbars() {
      return hideScrollbars(Boolean.TRUE);
    }

    /**
     * Mute audio.
     *
     * @param muteAudio Argument value.
     * @return This builder.
     */
    public Builder muteAudio(Boolean muteAudio) {
      arguments.muteAudio = muteAudio;
      return this;
    }

    /**
     * Mute audio.
     *
     * @return This builder.
     */
    public Builder muteAudio() {
      return muteAudio(Boolean.TRUE);
    }

    /**
     * Disable background networking.
     *
     * @param disableBackgroundNetworking Argument value.
     * @return This builder.
     */
    public Builder disableBackgroundNetworking(Boolean disableBackgroundNetworking) {
      arguments.disableBackgroundNetworking = disableBackgroundNetworking;
      return this;
    }

    /**
     * Disable background networking.
     *
     * @return This builder.
     */
    public Builder disableBackgroundNetworking() {
      return disableBackgroundNetworking(Boolean.TRUE);
    }

    /**
     * Disable background timer throttling.
     *
     * @param disableBackgroundTimerThrottling Argument value.
     * @return This builder.
     */
    public Builder disableBackgroundTimerThrottling(Boolean disableBackgroundTimerThrottling) {
      arguments.disableBackgroundTimerThrottling = disableBackgroundTimerThrottling;
      return this;
    }

    /**
     * Disable background timer throttling.
     *
     * @return This builder.
     */
    public Builder disableBackgroundTimerThrottling() {
      return disableBackgroundTimerThrottling(Boolean.TRUE);
    }

    /**
     * Disable client side phishing detection.
     *
     * @param disableClientSidePhishingDetection Argument value.
     * @return This builder.
     */
    public Builder disableClientSidePhishingDetection(Boolean disableClientSidePhishingDetection) {
      arguments.disableClientSidePhishingDetection = disableClientSidePhishingDetection;
      return this;
    }

    /**
     * Disable client side phishing detection.
     *
     * @return This builder.
     */
    public Builder disableClientSidePhishingDetection() {
      return disableClientSidePhishingDetection(Boolean.TRUE);
    }

    /**
     * Disable default apps.
     *
     * @param disableDefaultApps Argument value.
     * @return This builder.
     */
    public Builder disableDefaultApps(Boolean disableDefaultApps) {
      arguments.disableDefaultApps = disableDefaultApps;
      return this;
    }

    /**
     * Disable client side phishing detection.
     *
     * @return This builder.
     */
    public Builder disableDefaultApps() {
      return disableDefaultApps(Boolean.TRUE);
    }

    /**
     * Disable extensions.
     *
     * @param disableExtensions Argument value.
     * @return This builder.
     */
    public Builder disableExtensions(Boolean disableExtensions) {
      arguments.disableExtensions = disableExtensions;
      return this;
    }

    /**
     * Disable extensions.
     *
     * @return This builder.
     */
    public Builder disableExtensions() {
      return disableExtensions(Boolean.TRUE);
    }

    /**
     * Disable hang monitor.
     *
     * @param disableHangMonitor Argument value.
     * @return This builder.
     */
    public Builder disableHangMonitor(Boolean disableHangMonitor) {
      arguments.disableHangMonitor = disableHangMonitor;
      return this;
    }

    /**
     * Disable hang monitor.
     *
     * @return This builder.
     */
    public Builder disableHangMonitor() {
      return disableHangMonitor(Boolean.TRUE);
    }

    /**
     * Disable popup blocking.
     *
     * @param disablePopupBlocking Argument value.
     * @return This builder.
     */
    public Builder disablePopupBlocking(Boolean disablePopupBlocking) {
      arguments.disablePopupBlocking = disablePopupBlocking;
      return this;
    }

    /**
     * Disable hang monitor.
     *
     * @return This builder.
     */
    public Builder disablePopupBlocking() {
      return disablePopupBlocking(Boolean.TRUE);
    }

    /**
     * Disable prompt on repost.
     *
     * @param disablePromptOnRepost Argument value.
     * @return This builder.
     */
    public Builder disablePromptOnRepost(Boolean disablePromptOnRepost) {
      arguments.disablePromptOnRepost = disablePromptOnRepost;
      return this;
    }

    /**
     * Disable prompt on repost.
     *
     * @return This builder.
     */
    public Builder disablePromptOnRepost() {
      return disablePromptOnRepost(Boolean.TRUE);
    }

    /**
     * Disable sync.
     *
     * @param disableSync Argument value.
     * @return This builder.
     */
    public Builder disableSync(Boolean disableSync) {
      arguments.disableSync = disableSync;
      return this;
    }

    /**
     * Disable prompt on repost.
     *
     * @return This builder.
     */
    public Builder disableSync() {
      return disableSync(Boolean.TRUE);
    }

    /**
     * Disable translate.
     *
     * @param disableTranslate Argument value.
     * @return This builder.
     */
    public Builder disableTranslate(Boolean disableTranslate) {
      arguments.disableTranslate = disableTranslate;
      return this;
    }

    /**
     * Disable translate.
     *
     * @return This builder.
     */
    public Builder disableTranslate() {
      return disableTranslate(Boolean.TRUE);
    }

    /**
     * Metrics recording.
     *
     * @param metricsRecordingOnly Argument value.
     * @return This builder.
     */
    public Builder metricsRecordingOnly(Boolean metricsRecordingOnly) {
      arguments.metricsRecordingOnly = metricsRecordingOnly;
      return this;
    }

    /**
     * Metrics recording.
     *
     * @return This builder.
     */
    public Builder metricsRecordingOnly() {
      return metricsRecordingOnly(Boolean.TRUE);
    }

    /**
     * Safebrowsing disable auto update.
     *
     * @param safebrowsingDisableAutoUpdate Argument value.
     * @return This builder.
     */
    public Builder safebrowsingDisableAutoUpdate(Boolean safebrowsingDisableAutoUpdate) {
      arguments.safebrowsingDisableAutoUpdate = safebrowsingDisableAutoUpdate;
      return this;
    }

    /**
     * Safebrowsing disable auto update.
     *
     * @return This builder.
     */
    public Builder safebrowsingDisableAutoUpdate() {
      return safebrowsingDisableAutoUpdate(Boolean.TRUE);
    }

    /**
     * Sets the enable-logging argument.
     *
     * @return This builder.
     */
    public Builder enableLogging(String enableLogging) {
      arguments.enableLogging = enableLogging;
      return this;
    }

    /**
     * Build chrome arguments.
     *
     * @return Chrome arguments.
     */
    public ChromeArguments build() {
      return arguments;
    }
  }
}
