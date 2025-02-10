package com.github.kklisura.cdt.protocol.types.animation;

/*-
 * #%L
 * cdt-java-client
 * %%
 * Copyright (C) 2018 - 2025 Kenan Klisura
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
import com.github.kklisura.cdt.protocol.types.dom.ScrollOrientation;

/** Timeline instance */
public class ViewOrScrollTimeline {

  @Optional private Integer sourceNodeId;

  @Optional private Double startOffset;

  @Optional private Double endOffset;

  @Optional private Integer subjectNodeId;

  private ScrollOrientation axis;

  /** Scroll container node */
  public Integer getSourceNodeId() {
    return sourceNodeId;
  }

  /** Scroll container node */
  public void setSourceNodeId(Integer sourceNodeId) {
    this.sourceNodeId = sourceNodeId;
  }

  /**
   * Represents the starting scroll position of the timeline as a length offset in pixels from
   * scroll origin.
   */
  public Double getStartOffset() {
    return startOffset;
  }

  /**
   * Represents the starting scroll position of the timeline as a length offset in pixels from
   * scroll origin.
   */
  public void setStartOffset(Double startOffset) {
    this.startOffset = startOffset;
  }

  /**
   * Represents the ending scroll position of the timeline as a length offset in pixels from scroll
   * origin.
   */
  public Double getEndOffset() {
    return endOffset;
  }

  /**
   * Represents the ending scroll position of the timeline as a length offset in pixels from scroll
   * origin.
   */
  public void setEndOffset(Double endOffset) {
    this.endOffset = endOffset;
  }

  /**
   * The element whose principal box's visibility in the scrollport defined the progress of the
   * timeline. Does not exist for animations with ScrollTimeline
   */
  public Integer getSubjectNodeId() {
    return subjectNodeId;
  }

  /**
   * The element whose principal box's visibility in the scrollport defined the progress of the
   * timeline. Does not exist for animations with ScrollTimeline
   */
  public void setSubjectNodeId(Integer subjectNodeId) {
    this.subjectNodeId = subjectNodeId;
  }

  /** Orientation of the scroll */
  public ScrollOrientation getAxis() {
    return axis;
  }

  /** Orientation of the scroll */
  public void setAxis(ScrollOrientation axis) {
    this.axis = axis;
  }
}
