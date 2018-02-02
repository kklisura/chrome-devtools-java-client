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

import com.github.kklisura.cdt.protocol.support.annotations.Optional;
import com.github.kklisura.cdt.protocol.types.css.InlineTextBox;
import com.github.kklisura.cdt.protocol.types.dom.Rect;
import java.util.List;

/** Details of an element in the DOM tree with a LayoutObject. */
public class LayoutTreeNode {

  private Integer domNodeIndex;

  private Rect boundingBox;

  @Optional private String layoutText;

  @Optional private List<InlineTextBox> inlineTextNodes;

  @Optional private Integer styleIndex;

  /**
   * The index of the related DOM node in the <code>domNodes</code> array returned by <code>
   * getSnapshot</code>.
   */
  public Integer getDomNodeIndex() {
    return domNodeIndex;
  }

  /**
   * The index of the related DOM node in the <code>domNodes</code> array returned by <code>
   * getSnapshot</code>.
   */
  public void setDomNodeIndex(Integer domNodeIndex) {
    this.domNodeIndex = domNodeIndex;
  }

  /** The absolute position bounding box. */
  public Rect getBoundingBox() {
    return boundingBox;
  }

  /** The absolute position bounding box. */
  public void setBoundingBox(Rect boundingBox) {
    this.boundingBox = boundingBox;
  }

  /** Contents of the LayoutText, if any. */
  public String getLayoutText() {
    return layoutText;
  }

  /** Contents of the LayoutText, if any. */
  public void setLayoutText(String layoutText) {
    this.layoutText = layoutText;
  }

  /** The post-layout inline text nodes, if any. */
  public List<InlineTextBox> getInlineTextNodes() {
    return inlineTextNodes;
  }

  /** The post-layout inline text nodes, if any. */
  public void setInlineTextNodes(List<InlineTextBox> inlineTextNodes) {
    this.inlineTextNodes = inlineTextNodes;
  }

  /** Index into the <code>computedStyles</code> array returned by <code>getSnapshot</code>. */
  public Integer getStyleIndex() {
    return styleIndex;
  }

  /** Index into the <code>computedStyles</code> array returned by <code>getSnapshot</code>. */
  public void setStyleIndex(Integer styleIndex) {
    this.styleIndex = styleIndex;
  }
}
