package com.github.kklisura.cdtp.protocol.events.dom;

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
import com.github.kklisura.cdtp.protocol.types.dom.BackendNode;
import java.util.List;

/** Called when distrubution is changed. */
@Experimental
public class DistributedNodesUpdated {

  private Integer insertionPointId;

  private List<BackendNode> distributedNodes;

  /** Insertion point where distrubuted nodes were updated. */
  public Integer getInsertionPointId() {
    return insertionPointId;
  }

  /** Insertion point where distrubuted nodes were updated. */
  public void setInsertionPointId(Integer insertionPointId) {
    this.insertionPointId = insertionPointId;
  }

  /** Distributed nodes for given insertion point. */
  public List<BackendNode> getDistributedNodes() {
    return distributedNodes;
  }

  /** Distributed nodes for given insertion point. */
  public void setDistributedNodes(List<BackendNode> distributedNodes) {
    this.distributedNodes = distributedNodes;
  }
}
