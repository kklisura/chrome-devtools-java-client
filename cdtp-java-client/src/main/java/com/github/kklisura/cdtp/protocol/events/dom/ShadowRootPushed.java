package com.github.kklisura.cdtp.protocol.events.dom;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.types.dom.Node;

/** Called when shadow root is pushed into the element. */
@Experimental
public class ShadowRootPushed {

  private Integer hostId;

  private Node root;

  /** Host element id. */
  public Integer getHostId() {
    return hostId;
  }

  /** Host element id. */
  public void setHostId(Integer hostId) {
    this.hostId = hostId;
  }

  /** Shadow root. */
  public Node getRoot() {
    return root;
  }

  /** Shadow root. */
  public void setRoot(Node root) {
    this.root = root;
  }
}
