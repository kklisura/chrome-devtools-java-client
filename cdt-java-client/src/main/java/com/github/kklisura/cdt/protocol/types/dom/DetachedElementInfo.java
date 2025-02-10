package com.github.kklisura.cdt.protocol.types.dom;

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

import java.util.List;

/**
 * A structure to hold the top-level node of a detached tree and an array of its retained
 * descendants.
 */
public class DetachedElementInfo {

  private Node treeNode;

  private List<Integer> retainedNodeIds;

  public Node getTreeNode() {
    return treeNode;
  }

  public void setTreeNode(Node treeNode) {
    this.treeNode = treeNode;
  }

  public List<Integer> getRetainedNodeIds() {
    return retainedNodeIds;
  }

  public void setRetainedNodeIds(List<Integer> retainedNodeIds) {
    this.retainedNodeIds = retainedNodeIds;
  }
}
