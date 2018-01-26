package com.github.kklisura.cdtp.protocol.types.dom;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import java.util.List;

/** Box model. */
@Experimental
public class BoxModel {

  private List<Double> content;

  private List<Double> padding;

  private List<Double> border;

  private List<Double> margin;

  private Integer width;

  private Integer height;

  @Optional private ShapeOutsideInfo shapeOutside;

  /** Content box */
  public List<Double> getContent() {
    return content;
  }

  /** Content box */
  public void setContent(List<Double> content) {
    this.content = content;
  }

  /** Padding box */
  public List<Double> getPadding() {
    return padding;
  }

  /** Padding box */
  public void setPadding(List<Double> padding) {
    this.padding = padding;
  }

  /** Border box */
  public List<Double> getBorder() {
    return border;
  }

  /** Border box */
  public void setBorder(List<Double> border) {
    this.border = border;
  }

  /** Margin box */
  public List<Double> getMargin() {
    return margin;
  }

  /** Margin box */
  public void setMargin(List<Double> margin) {
    this.margin = margin;
  }

  /** Node width */
  public Integer getWidth() {
    return width;
  }

  /** Node width */
  public void setWidth(Integer width) {
    this.width = width;
  }

  /** Node height */
  public Integer getHeight() {
    return height;
  }

  /** Node height */
  public void setHeight(Integer height) {
    this.height = height;
  }

  /** Shape outside coordinates */
  public ShapeOutsideInfo getShapeOutside() {
    return shapeOutside;
  }

  /** Shape outside coordinates */
  public void setShapeOutside(ShapeOutsideInfo shapeOutside) {
    this.shapeOutside = shapeOutside;
  }
}
