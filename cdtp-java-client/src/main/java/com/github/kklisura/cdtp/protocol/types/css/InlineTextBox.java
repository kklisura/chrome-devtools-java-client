package com.github.kklisura.cdtp.protocol.types.css;

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
