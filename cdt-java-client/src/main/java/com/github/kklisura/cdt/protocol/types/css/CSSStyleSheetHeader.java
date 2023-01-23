package com.github.kklisura.cdt.protocol.types.css;

/*-
 * #%L
 * cdt-java-client
 * %%
 * Copyright (C) 2018 - 2023 Kenan Klisura
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

/** CSS stylesheet metainformation. */
public class CSSStyleSheetHeader {

  private String styleSheetId;

  private String frameId;

  private String sourceURL;

  @Optional private String sourceMapURL;

  private StyleSheetOrigin origin;

  private String title;

  @Optional private Integer ownerNode;

  private Boolean disabled;

  @Optional private Boolean hasSourceURL;

  private Boolean isInline;

  private Boolean isMutable;

  private Boolean isConstructed;

  private Double startLine;

  private Double startColumn;

  private Double length;

  private Double endLine;

  private Double endColumn;

  /** The stylesheet identifier. */
  public String getStyleSheetId() {
    return styleSheetId;
  }

  /** The stylesheet identifier. */
  public void setStyleSheetId(String styleSheetId) {
    this.styleSheetId = styleSheetId;
  }

  /** Owner frame identifier. */
  public String getFrameId() {
    return frameId;
  }

  /** Owner frame identifier. */
  public void setFrameId(String frameId) {
    this.frameId = frameId;
  }

  /** Stylesheet resource URL. */
  public String getSourceURL() {
    return sourceURL;
  }

  /** Stylesheet resource URL. */
  public void setSourceURL(String sourceURL) {
    this.sourceURL = sourceURL;
  }

  /** URL of source map associated with the stylesheet (if any). */
  public String getSourceMapURL() {
    return sourceMapURL;
  }

  /** URL of source map associated with the stylesheet (if any). */
  public void setSourceMapURL(String sourceMapURL) {
    this.sourceMapURL = sourceMapURL;
  }

  /** Stylesheet origin. */
  public StyleSheetOrigin getOrigin() {
    return origin;
  }

  /** Stylesheet origin. */
  public void setOrigin(StyleSheetOrigin origin) {
    this.origin = origin;
  }

  /** Stylesheet title. */
  public String getTitle() {
    return title;
  }

  /** Stylesheet title. */
  public void setTitle(String title) {
    this.title = title;
  }

  /** The backend id for the owner node of the stylesheet. */
  public Integer getOwnerNode() {
    return ownerNode;
  }

  /** The backend id for the owner node of the stylesheet. */
  public void setOwnerNode(Integer ownerNode) {
    this.ownerNode = ownerNode;
  }

  /** Denotes whether the stylesheet is disabled. */
  public Boolean getDisabled() {
    return disabled;
  }

  /** Denotes whether the stylesheet is disabled. */
  public void setDisabled(Boolean disabled) {
    this.disabled = disabled;
  }

  /** Whether the sourceURL field value comes from the sourceURL comment. */
  public Boolean getHasSourceURL() {
    return hasSourceURL;
  }

  /** Whether the sourceURL field value comes from the sourceURL comment. */
  public void setHasSourceURL(Boolean hasSourceURL) {
    this.hasSourceURL = hasSourceURL;
  }

  /**
   * Whether this stylesheet is created for STYLE tag by parser. This flag is not set for
   * document.written STYLE tags.
   */
  public Boolean getIsInline() {
    return isInline;
  }

  /**
   * Whether this stylesheet is created for STYLE tag by parser. This flag is not set for
   * document.written STYLE tags.
   */
  public void setIsInline(Boolean isInline) {
    this.isInline = isInline;
  }

  /**
   * Whether this stylesheet is mutable. Inline stylesheets become mutable after they have been
   * modified via CSSOM API. <link> element's stylesheets become mutable only if DevTools modifies
   * them. Constructed stylesheets (new CSSStyleSheet()) are mutable immediately after creation.
   */
  public Boolean getIsMutable() {
    return isMutable;
  }

  /**
   * Whether this stylesheet is mutable. Inline stylesheets become mutable after they have been
   * modified via CSSOM API. <link> element's stylesheets become mutable only if DevTools modifies
   * them. Constructed stylesheets (new CSSStyleSheet()) are mutable immediately after creation.
   */
  public void setIsMutable(Boolean isMutable) {
    this.isMutable = isMutable;
  }

  /** Whether this stylesheet is a constructed stylesheet (created using new CSSStyleSheet()). */
  public Boolean getIsConstructed() {
    return isConstructed;
  }

  /** Whether this stylesheet is a constructed stylesheet (created using new CSSStyleSheet()). */
  public void setIsConstructed(Boolean isConstructed) {
    this.isConstructed = isConstructed;
  }

  /** Line offset of the stylesheet within the resource (zero based). */
  public Double getStartLine() {
    return startLine;
  }

  /** Line offset of the stylesheet within the resource (zero based). */
  public void setStartLine(Double startLine) {
    this.startLine = startLine;
  }

  /** Column offset of the stylesheet within the resource (zero based). */
  public Double getStartColumn() {
    return startColumn;
  }

  /** Column offset of the stylesheet within the resource (zero based). */
  public void setStartColumn(Double startColumn) {
    this.startColumn = startColumn;
  }

  /** Size of the content (in characters). */
  public Double getLength() {
    return length;
  }

  /** Size of the content (in characters). */
  public void setLength(Double length) {
    this.length = length;
  }

  /** Line offset of the end of the stylesheet within the resource (zero based). */
  public Double getEndLine() {
    return endLine;
  }

  /** Line offset of the end of the stylesheet within the resource (zero based). */
  public void setEndLine(Double endLine) {
    this.endLine = endLine;
  }

  /** Column offset of the end of the stylesheet within the resource (zero based). */
  public Double getEndColumn() {
    return endColumn;
  }

  /** Column offset of the end of the stylesheet within the resource (zero based). */
  public void setEndColumn(Double endColumn) {
    this.endColumn = endColumn;
  }
}
