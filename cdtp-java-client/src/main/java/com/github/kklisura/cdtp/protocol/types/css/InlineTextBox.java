package com.github.kklisura.cdtp.protocol.types.css;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.types.dom.Rect;

/**
 * Details of post layout rendered text positions. The exact layout should not be regarded as stable
 * and may change between versions.
 */
@Experimental
public class InlineTextBox {

  private Rect boundingBox;

  private Integer startCharacterIndex;

  private Integer numCharacters;

  /** The absolute position bounding box. */
  public Rect getBoundingBox() {
    return boundingBox;
  }

  /** The absolute position bounding box. */
  public void setBoundingBox(Rect boundingBox) {
    this.boundingBox = boundingBox;
  }

  /** The starting index in characters, for this post layout textbox substring. */
  public Integer getStartCharacterIndex() {
    return startCharacterIndex;
  }

  /** The starting index in characters, for this post layout textbox substring. */
  public void setStartCharacterIndex(Integer startCharacterIndex) {
    this.startCharacterIndex = startCharacterIndex;
  }

  /** The number of characters in this post layout textbox substring. */
  public Integer getNumCharacters() {
    return numCharacters;
  }

  /** The number of characters in this post layout textbox substring. */
  public void setNumCharacters(Integer numCharacters) {
    this.numCharacters = numCharacters;
  }
}
