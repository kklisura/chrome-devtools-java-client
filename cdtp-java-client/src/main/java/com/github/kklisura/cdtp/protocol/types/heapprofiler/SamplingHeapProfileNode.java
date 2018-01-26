package com.github.kklisura.cdtp.protocol.types.heapprofiler;

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
