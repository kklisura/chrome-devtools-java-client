package com.github.kklisura.cdt.protocol.types.domsnapshot;

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

import java.util.List;

public class CaptureSnapshot {

  private DOMTreeSnapshot nodes;

  private LayoutTreeSnapshot layout;

  private List<String> strings;

  /** The nodes in the DOM tree. The DOMNode at index 0 corresponds to the root document. */
  public DOMTreeSnapshot getNodes() {
    return nodes;
  }

  /** The nodes in the DOM tree. The DOMNode at index 0 corresponds to the root document. */
  public void setNodes(DOMTreeSnapshot nodes) {
    this.nodes = nodes;
  }

  /** The nodes in the layout tree. */
  public LayoutTreeSnapshot getLayout() {
    return layout;
  }

  /** The nodes in the layout tree. */
  public void setLayout(LayoutTreeSnapshot layout) {
    this.layout = layout;
  }

  /** Shared string table that all string properties refer to with indexes. */
  public List<String> getStrings() {
    return strings;
  }

  /** Shared string table that all string properties refer to with indexes. */
  public void setStrings(List<String> strings) {
    this.strings = strings;
  }
}
