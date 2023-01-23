package com.github.kklisura.cdt.protocol.types.page;

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

import com.github.kklisura.cdt.protocol.support.annotations.Optional;

public class Navigate {

  private String frameId;

  @Optional private String loaderId;

  @Optional private String errorText;

  /** Frame id that has navigated (or failed to navigate) */
  public String getFrameId() {
    return frameId;
  }

  /** Frame id that has navigated (or failed to navigate) */
  public void setFrameId(String frameId) {
    this.frameId = frameId;
  }

  /** Loader identifier. */
  public String getLoaderId() {
    return loaderId;
  }

  /** Loader identifier. */
  public void setLoaderId(String loaderId) {
    this.loaderId = loaderId;
  }

  /** User friendly error message, present if and only if navigation has failed. */
  public String getErrorText() {
    return errorText;
  }

  /** User friendly error message, present if and only if navigation has failed. */
  public void setErrorText(String errorText) {
    this.errorText = errorText;
  }
}
