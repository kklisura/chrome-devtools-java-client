package com.github.kklisura.cdtp.protocol.types.heapprofiler;

/** Profile. */
public class SamplingHeapProfile {

  private SamplingHeapProfileNode head;

  public SamplingHeapProfileNode getHead() {
    return head;
  }

  public void setHead(SamplingHeapProfileNode head) {
    this.head = head;
  }
}
