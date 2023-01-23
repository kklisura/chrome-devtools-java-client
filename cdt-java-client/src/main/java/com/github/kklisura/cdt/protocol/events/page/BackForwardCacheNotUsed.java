package com.github.kklisura.cdt.protocol.events.page;

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

import com.github.kklisura.cdt.protocol.support.annotations.Experimental;

/**
 * Fired for failed bfcache history navigations if BackForwardCache feature is enabled. Do not
 * assume any ordering with the Page.frameNavigated event. This event is fired only for main-frame
 * history navigation where the document changes (non-same-document navigations), when bfcache
 * navigation fails.
 */
@Experimental
public class BackForwardCacheNotUsed {

  private String loaderId;

  private String frameId;

  /** The loader id for the associated navgation. */
  public String getLoaderId() {
    return loaderId;
  }

  /** The loader id for the associated navgation. */
  public void setLoaderId(String loaderId) {
    this.loaderId = loaderId;
  }

  /** The frame id of the associated frame. */
  public String getFrameId() {
    return frameId;
  }

  /** The frame id of the associated frame. */
  public void setFrameId(String frameId) {
    this.frameId = frameId;
  }
}
