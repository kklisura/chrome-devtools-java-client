package com.github.kklisura.cdtp.protocol.events.overlay;

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

import com.github.kklisura.cdtp.protocol.types.page.Viewport;

/** Fired when user asks to capture screenshot of some area on the page. */
public class ScreenshotRequested {

  private Viewport viewport;

  /** Viewport to capture, in CSS. */
  public Viewport getViewport() {
    return viewport;
  }

  /** Viewport to capture, in CSS. */
  public void setViewport(Viewport viewport) {
    this.viewport = viewport;
  }
}
