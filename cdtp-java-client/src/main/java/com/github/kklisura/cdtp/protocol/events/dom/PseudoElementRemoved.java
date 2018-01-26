package com.github.kklisura.cdtp.protocol.events.dom;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;

/** Called when a pseudo element is removed from an element. */
@Experimental
public class PseudoElementRemoved {

  private Integer parentId;

  private Integer pseudoElementId;

  /** Pseudo element's parent element id. */
  public Integer getParentId() {
    return parentId;
  }

  /** Pseudo element's parent element id. */
  public void setParentId(Integer parentId) {
    this.parentId = parentId;
  }

  /** The removed pseudo element id. */
  public Integer getPseudoElementId() {
    return pseudoElementId;
  }

  /** The removed pseudo element id. */
  public void setPseudoElementId(Integer pseudoElementId) {
    this.pseudoElementId = pseudoElementId;
  }
}
