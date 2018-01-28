package com.github.kklisura.cdtp.protocol.types.heapprofiler;

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

import com.github.kklisura.cdtp.protocol.types.runtime.CallFrame;
import java.util.List;

/**
 * Sampling Heap Profile node. Holds callsite information, allocation statistics and child nodes.
 */
public class SamplingHeapProfileNode {

  private CallFrame callFrame;

  private Double selfSize;

  private List<com.github.kklisura.cdtp.protocol.types.heapprofiler.SamplingHeapProfileNode>
      children;

  /** Function location. */
  public CallFrame getCallFrame() {
    return callFrame;
  }

  /** Function location. */
  public void setCallFrame(CallFrame callFrame) {
    this.callFrame = callFrame;
  }

  /** Allocations size in bytes for the node excluding children. */
  public Double getSelfSize() {
    return selfSize;
  }

  /** Allocations size in bytes for the node excluding children. */
  public void setSelfSize(Double selfSize) {
    this.selfSize = selfSize;
  }

  /** Child nodes. */
  public List<com.github.kklisura.cdtp.protocol.types.heapprofiler.SamplingHeapProfileNode>
      getChildren() {
    return children;
  }

  /** Child nodes. */
  public void setChildren(
      List<com.github.kklisura.cdtp.protocol.types.heapprofiler.SamplingHeapProfileNode> children) {
    this.children = children;
  }
}
