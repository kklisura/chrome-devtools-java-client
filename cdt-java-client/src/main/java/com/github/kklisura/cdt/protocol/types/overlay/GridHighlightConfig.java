package com.github.kklisura.cdt.protocol.types.overlay;

/*-
 * #%L
 * cdt-java-client
 * %%
 * Copyright (C) 2018 - 2019 Kenan Klisura
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
import com.github.kklisura.cdt.protocol.types.dom.RGBA;

/** Configuration data for the highlighting of Grid elements. */
public class GridHighlightConfig {

  @Optional private Boolean showGridExtensionLines;

  @Optional private RGBA gridBorderColor;

  @Optional private RGBA cellBorderColor;

  @Optional private Boolean gridBorderDash;

  @Optional private Boolean cellBorderDash;

  @Optional private RGBA rowGapColor;

  @Optional private RGBA rowHatchColor;

  @Optional private RGBA columnGapColor;

  @Optional private RGBA columnHatchColor;

  /** Whether the extension lines from grid cells to the rulers should be shown (default: false). */
  public Boolean getShowGridExtensionLines() {
    return showGridExtensionLines;
  }

  /** Whether the extension lines from grid cells to the rulers should be shown (default: false). */
  public void setShowGridExtensionLines(Boolean showGridExtensionLines) {
    this.showGridExtensionLines = showGridExtensionLines;
  }

  /** The grid container border highlight color (default: transparent). */
  public RGBA getGridBorderColor() {
    return gridBorderColor;
  }

  /** The grid container border highlight color (default: transparent). */
  public void setGridBorderColor(RGBA gridBorderColor) {
    this.gridBorderColor = gridBorderColor;
  }

  /** The cell border color (default: transparent). */
  public RGBA getCellBorderColor() {
    return cellBorderColor;
  }

  /** The cell border color (default: transparent). */
  public void setCellBorderColor(RGBA cellBorderColor) {
    this.cellBorderColor = cellBorderColor;
  }

  /** Whether the grid border is dashed (default: false). */
  public Boolean getGridBorderDash() {
    return gridBorderDash;
  }

  /** Whether the grid border is dashed (default: false). */
  public void setGridBorderDash(Boolean gridBorderDash) {
    this.gridBorderDash = gridBorderDash;
  }

  /** Whether the cell border is dashed (default: false). */
  public Boolean getCellBorderDash() {
    return cellBorderDash;
  }

  /** Whether the cell border is dashed (default: false). */
  public void setCellBorderDash(Boolean cellBorderDash) {
    this.cellBorderDash = cellBorderDash;
  }

  /** The row gap highlight fill color (default: transparent). */
  public RGBA getRowGapColor() {
    return rowGapColor;
  }

  /** The row gap highlight fill color (default: transparent). */
  public void setRowGapColor(RGBA rowGapColor) {
    this.rowGapColor = rowGapColor;
  }

  /** The row gap hatching fill color (default: transparent). */
  public RGBA getRowHatchColor() {
    return rowHatchColor;
  }

  /** The row gap hatching fill color (default: transparent). */
  public void setRowHatchColor(RGBA rowHatchColor) {
    this.rowHatchColor = rowHatchColor;
  }

  /** The column gap highlight fill color (default: transparent). */
  public RGBA getColumnGapColor() {
    return columnGapColor;
  }

  /** The column gap highlight fill color (default: transparent). */
  public void setColumnGapColor(RGBA columnGapColor) {
    this.columnGapColor = columnGapColor;
  }

  /** The column gap hatching fill color (default: transparent). */
  public RGBA getColumnHatchColor() {
    return columnHatchColor;
  }

  /** The column gap hatching fill color (default: transparent). */
  public void setColumnHatchColor(RGBA columnHatchColor) {
    this.columnHatchColor = columnHatchColor;
  }
}
