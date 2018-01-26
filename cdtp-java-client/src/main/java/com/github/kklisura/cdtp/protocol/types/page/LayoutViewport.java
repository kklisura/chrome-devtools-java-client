package com.github.kklisura.cdtp.protocol.types.page;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;

/** Layout viewport position and dimensions. */
@Experimental
public class LayoutViewport {

  private Integer pageX;

  private Integer pageY;

  private Integer clientWidth;

  private Integer clientHeight;

  /** Horizontal offset relative to the document (CSS pixels). */
  public Integer getPageX() {
    return pageX;
  }

  /** Horizontal offset relative to the document (CSS pixels). */
  public void setPageX(Integer pageX) {
    this.pageX = pageX;
  }

  /** Vertical offset relative to the document (CSS pixels). */
  public Integer getPageY() {
    return pageY;
  }

  /** Vertical offset relative to the document (CSS pixels). */
  public void setPageY(Integer pageY) {
    this.pageY = pageY;
  }

  /** Width (CSS pixels), excludes scrollbar if present. */
  public Integer getClientWidth() {
    return clientWidth;
  }

  /** Width (CSS pixels), excludes scrollbar if present. */
  public void setClientWidth(Integer clientWidth) {
    this.clientWidth = clientWidth;
  }

  /** Height (CSS pixels), excludes scrollbar if present. */
  public Integer getClientHeight() {
    return clientHeight;
  }

  /** Height (CSS pixels), excludes scrollbar if present. */
  public void setClientHeight(Integer clientHeight) {
    this.clientHeight = clientHeight;
  }
}
