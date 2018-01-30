package com.github.kklisura.cdtp.protocol.types.dom;

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

/** Backend node with a friendly name. */
@Experimental
public class BackendNode {

  private Integer nodeType;

  private String nodeName;

  private Integer backendNodeId;

  /** <code>Node</code>'s nodeType. */
  public Integer getNodeType() {
    return nodeType;
  }

  /** <code>Node</code>'s nodeType. */
  public void setNodeType(Integer nodeType) {
    this.nodeType = nodeType;
  }

  /** <code>Node</code>'s nodeName. */
  public String getNodeName() {
    return nodeName;
  }

  /** <code>Node</code>'s nodeName. */
  public void setNodeName(String nodeName) {
    this.nodeName = nodeName;
  }

  public Integer getBackendNodeId() {
    return backendNodeId;
  }

  public void setBackendNodeId(Integer backendNodeId) {
    this.backendNodeId = backendNodeId;
  }
}
