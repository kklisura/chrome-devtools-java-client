package com.github.kklisura.cdt.protocol.events.page;

/*-
 * #%L
 * cdt-java-client
 * %%
 * Copyright (C) 2018 - 2025 Kenan Klisura
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
 * Fired when a navigation starts. This event is fired for both renderer-initiated and
 * browser-initiated navigations. For renderer-initiated navigations, the event is fired after
 * `frameRequestedNavigation`. Navigation may still be cancelled after the event is issued. Multiple
 * events can be fired for a single navigation, for example, when a same-document navigation becomes
 * a cross-document navigation (such as in the case of a frameset).
 */
@Experimental
public class FrameStartedNavigating {

  private String frameId;

  private String url;

  private String loaderId;

  private FrameStartedNavigatingNavigationType navigationType;

  /** ID of the frame that is being navigated. */
  public String getFrameId() {
    return frameId;
  }

  /** ID of the frame that is being navigated. */
  public void setFrameId(String frameId) {
    this.frameId = frameId;
  }

  /** The URL the navigation started with. The final URL can be different. */
  public String getUrl() {
    return url;
  }

  /** The URL the navigation started with. The final URL can be different. */
  public void setUrl(String url) {
    this.url = url;
  }

  /**
   * Loader identifier. Even though it is present in case of same-document navigation, the
   * previously committed loaderId would not change unless the navigation changes from a
   * same-document to a cross-document navigation.
   */
  public String getLoaderId() {
    return loaderId;
  }

  /**
   * Loader identifier. Even though it is present in case of same-document navigation, the
   * previously committed loaderId would not change unless the navigation changes from a
   * same-document to a cross-document navigation.
   */
  public void setLoaderId(String loaderId) {
    this.loaderId = loaderId;
  }

  public FrameStartedNavigatingNavigationType getNavigationType() {
    return navigationType;
  }

  public void setNavigationType(FrameStartedNavigatingNavigationType navigationType) {
    this.navigationType = navigationType;
  }
}
