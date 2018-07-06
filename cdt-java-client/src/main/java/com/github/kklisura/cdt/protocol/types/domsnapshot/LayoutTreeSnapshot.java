package com.github.kklisura.cdt.protocol.types.domsnapshot;

/*-
 * #%L
 * cdt-java-client
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

import java.util.List;

/** Details of an element in the DOM tree with a LayoutObject. */
public class LayoutTreeSnapshot {

  private List<Integer> nodeIndex;

  private List<List<Integer>> styles;

  private List<List<Double>> bounds;

  private List<Integer> text;

  private TextBoxSnapshot textBoxes;

  /** The index of the related DOM node in the `domNodes` array returned by `getSnapshot`. */
  public List<Integer> getNodeIndex() {
    return nodeIndex;
  }

  /** The index of the related DOM node in the `domNodes` array returned by `getSnapshot`. */
  public void setNodeIndex(List<Integer> nodeIndex) {
    this.nodeIndex = nodeIndex;
  }

  /** Index into the `computedStyles` array returned by `captureSnapshot`. */
  public List<List<Integer>> getStyles() {
    return styles;
  }

  /** Index into the `computedStyles` array returned by `captureSnapshot`. */
  public void setStyles(List<List<Integer>> styles) {
    this.styles = styles;
  }

  /** The absolute position bounding box. */
  public List<List<Double>> getBounds() {
    return bounds;
  }

  /** The absolute position bounding box. */
  public void setBounds(List<List<Double>> bounds) {
    this.bounds = bounds;
  }

  /** Contents of the LayoutText, if any. */
  public List<Integer> getText() {
    return text;
  }

  /** Contents of the LayoutText, if any. */
  public void setText(List<Integer> text) {
    this.text = text;
  }

  /** The post-layout inline text nodes */
  public TextBoxSnapshot getTextBoxes() {
    return textBoxes;
  }

  /** The post-layout inline text nodes */
  public void setTextBoxes(TextBoxSnapshot textBoxes) {
    this.textBoxes = textBoxes;
  }
}
