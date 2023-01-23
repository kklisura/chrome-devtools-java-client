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

/** Emitted only when `page.interceptFileChooser` is enabled. */
public class FileChooserOpened {

  @Experimental private String frameId;

  @Experimental private Integer backendNodeId;

  private FileChooserOpenedMode mode;

  /** Id of the frame containing input node. */
  public String getFrameId() {
    return frameId;
  }

  /** Id of the frame containing input node. */
  public void setFrameId(String frameId) {
    this.frameId = frameId;
  }

  /** Input node id. */
  public Integer getBackendNodeId() {
    return backendNodeId;
  }

  /** Input node id. */
  public void setBackendNodeId(Integer backendNodeId) {
    this.backendNodeId = backendNodeId;
  }

  /** Input mode. */
  public FileChooserOpenedMode getMode() {
    return mode;
  }

  /** Input mode. */
  public void setMode(FileChooserOpenedMode mode) {
    this.mode = mode;
  }
}
