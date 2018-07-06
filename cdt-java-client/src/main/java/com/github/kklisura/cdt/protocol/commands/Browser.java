package com.github.kklisura.cdt.protocol.commands;

/*-
 * #%L
 * cdt-java-client
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

import com.github.kklisura.cdt.protocol.support.annotations.Experimental;
import com.github.kklisura.cdt.protocol.support.annotations.Optional;
import com.github.kklisura.cdt.protocol.support.annotations.ParamName;
import com.github.kklisura.cdt.protocol.support.annotations.Returns;
import com.github.kklisura.cdt.protocol.types.browser.Bounds;
import com.github.kklisura.cdt.protocol.types.browser.Histogram;
import com.github.kklisura.cdt.protocol.types.browser.Version;
import com.github.kklisura.cdt.protocol.types.browser.WindowForTarget;
import java.util.List;

/** The Browser domain defines methods and events for browser managing. */
public interface Browser {

  /** Close browser gracefully. */
  void close();

  /** Returns version information. */
  Version getVersion();

  /**
   * Returns the command line switches for the browser process if, and only if --enable-automation
   * is on the commandline.
   */
  @Experimental
  @Returns("arguments")
  List<String> getBrowserCommandLine();

  /** Get Chrome histograms. */
  @Experimental
  @Returns("histograms")
  List<Histogram> getHistograms();

  /**
   * Get Chrome histograms.
   *
   * @param query Requested substring in name. Only histograms which have query as a substring in
   *     their name are extracted. An empty or absent query returns all histograms.
   * @param delta If true, retrieve delta since last call.
   */
  @Experimental
  @Returns("histograms")
  List<Histogram> getHistograms(
      @Optional @ParamName("query") String query, @Optional @ParamName("delta") Boolean delta);

  /**
   * Get a Chrome histogram by name.
   *
   * @param name Requested histogram name.
   */
  @Experimental
  @Returns("histogram")
  Histogram getHistogram(@ParamName("name") String name);

  /**
   * Get a Chrome histogram by name.
   *
   * @param name Requested histogram name.
   * @param delta If true, retrieve delta since last call.
   */
  @Experimental
  @Returns("histogram")
  Histogram getHistogram(
      @ParamName("name") String name, @Optional @ParamName("delta") Boolean delta);

  /**
   * Get position and size of the browser window.
   *
   * @param windowId Browser window id.
   */
  @Experimental
  @Returns("bounds")
  Bounds getWindowBounds(@ParamName("windowId") Integer windowId);

  /**
   * Get the browser window that contains the devtools target.
   *
   * @param targetId Devtools agent host id.
   */
  @Experimental
  WindowForTarget getWindowForTarget(@ParamName("targetId") String targetId);

  /**
   * Set position and/or size of the browser window.
   *
   * @param windowId Browser window id.
   * @param bounds New window bounds. The 'minimized', 'maximized' and 'fullscreen' states cannot be
   *     combined with 'left', 'top', 'width' or 'height'. Leaves unspecified fields unchanged.
   */
  @Experimental
  void setWindowBounds(@ParamName("windowId") Integer windowId, @ParamName("bounds") Bounds bounds);
}
