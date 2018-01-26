package com.github.kklisura.cdtp.protocol.events.dom;

import com.github.kklisura.cdtp.protocol.types.dom.Node;

/** Mirrors <code>DOMNodeInserted</code> event. */
public class ChildNodeInserted {

  private Integer parentNodeId;

  private Integer previousNodeId;

  private Node node;

  /** Id of the node that has changed. */
  public Integer getParentNodeId() {
    return parentNodeId;
  }

  /** Id of the node that has changed. */
  public void setParentNodeId(Integer parentNodeId) {
    this.parentNodeId = parentNodeId;
  }

  /** If of the previous siblint. */
  public Integer getPreviousNodeId() {
    return previousNodeId;
  }

  /** If of the previous siblint. */
  public void setPreviousNodeId(Integer previousNodeId) {
    this.previousNodeId = previousNodeId;
  }

  /** Inserted node data. */
  public Node getNode() {
    return node;
  }

  /** Inserted node data. */
  public void setNode(Node node) {
    this.node = node;
  }
}
