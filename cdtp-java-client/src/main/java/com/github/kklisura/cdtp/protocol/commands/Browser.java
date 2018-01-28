package com.github.kklisura.cdtp.protocol.commands;

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

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.support.annotations.Returns;
import com.github.kklisura.cdtp.protocol.types.browser.Bounds;
import com.github.kklisura.cdtp.protocol.types.browser.Version;
import com.github.kklisura.cdtp.protocol.types.browser.WindowForTarget;

/** The Browser domain defines methods and events for browser managing. */
@Experimental
public interface Browser {

  /**
   * Get the browser window that contains the devtools target.
   *
   * @param targetId Devtools agent host id.
   */
  WindowForTarget getWindowForTarget(@ParamName("targetId") String targetId);

  /** Returns version information. */
  Version getVersion();

  /**
   * Set position and/or size of the browser window.
   *
   * @param windowId Browser window id.
   * @param bounds New window bounds. The 'minimized', 'maximized' and 'fullscreen' states cannot be
   *     combined with 'left', 'top', 'width' or 'height'. Leaves unspecified fields unchanged.
   */
  void setWindowBounds(@ParamName("windowId") Integer windowId, @ParamName("bounds") Bounds bounds);

  /**
   * Get position and size of the browser window.
   *
   * @param windowId Browser window id.
   */
  @Returns("bounds")
  Bounds getWindowBounds(@ParamName("windowId") Integer windowId);
}
