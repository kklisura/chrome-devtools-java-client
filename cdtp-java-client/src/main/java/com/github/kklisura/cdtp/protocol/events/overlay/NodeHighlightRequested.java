package com.github.kklisura.cdtp.protocol.events.overlay;

/**
 * Fired when the node should be highlighted. This happens after call to <code>setInspectMode</code>
 * .
 */
public class NodeHighlightRequested {

  private Integer nodeId;

  public Integer getNodeId() {
    return nodeId;
  }

  public void setNodeId(Integer nodeId) {
    this.nodeId = nodeId;
  }
}
