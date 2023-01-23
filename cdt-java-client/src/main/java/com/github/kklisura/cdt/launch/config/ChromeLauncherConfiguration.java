package com.github.kklisura.cdt.launch.config;

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

/**
 * Chrome launcher configuration.
 *
 * @author Kenan Klisura
 */
public class ChromeLauncherConfiguration {
  /** Default startup wait time in seconds. */
  private static final int DEFAULT_STARTUP_WAIT_TIME = 60;

  /** Default shutdown wait time in seconds. */
  private static final int DEFAULT_SHUTDOWN_WAIT_TIME = 60;

  /** 5 seconds wait time for threads to stop. */
  private static final int THREAD_JOIN_WAIT_TIME = 5;

  /** Startup wait time in seconds. */
  private int startupWaitTime = DEFAULT_STARTUP_WAIT_TIME;

  /** Shutdown wait time in seconds. */
  private int shutdownWaitTime = DEFAULT_SHUTDOWN_WAIT_TIME;

  /** Waits for threads to quite in seconds. */
  private int threadWaitTime = THREAD_JOIN_WAIT_TIME;

  /**
   * Gets startup wait time.
   *
   * @return Startup wait time in seconds.
   */
  public int getStartupWaitTime() {
    return startupWaitTime;
  }

  /**
   * Sets startup wait time.
   *
   * @param startupWaitTime startup wait time in seconds.
   */
  public void setStartupWaitTime(int startupWaitTime) {
    this.startupWaitTime = startupWaitTime;
  }

  /**
   * Gets shutdown wait time in seconds.
   *
   * @return the shutdown wait time in secnds.
   */
  public int getShutdownWaitTime() {
    return shutdownWaitTime;
  }

  /**
   * Sets shutdown wait time in seconds.
   *
   * @param shutdownWaitTime the shutdown wait time in seconds.
   */
  public void setShutdownWaitTime(int shutdownWaitTime) {
    this.shutdownWaitTime = shutdownWaitTime;
  }

  /**
   * Gets thread wait time in seconds.
   *
   * @return the thread wait time in seconds.
   */
  public int getThreadWaitTime() {
    return threadWaitTime;
  }

  /**
   * Sets thread wait time in seconds.
   *
   * @param threadWaitTime the thread wait time in seconds.
   */
  public void setThreadWaitTime(int threadWaitTime) {
    this.threadWaitTime = threadWaitTime;
  }
}
