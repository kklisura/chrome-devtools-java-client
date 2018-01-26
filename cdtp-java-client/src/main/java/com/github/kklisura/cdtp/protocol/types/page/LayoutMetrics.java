package com.github.kklisura.cdtp.protocol.types.page;

import com.github.kklisura.cdtp.protocol.types.dom.Rect;

public class LayoutMetrics {

  private LayoutViewport layoutViewport;

  private VisualViewport visualViewport;

  private Rect contentSize;

  /** Metrics relating to the layout viewport. */
  public LayoutViewport getLayoutViewport() {
    return layoutViewport;
  }

  /** Metrics relating to the layout viewport. */
  public void setLayoutViewport(LayoutViewport layoutViewport) {
    this.layoutViewport = layoutViewport;
  }

  /** Metrics relating to the visual viewport. */
  public VisualViewport getVisualViewport() {
    return visualViewport;
  }

  /** Metrics relating to the visual viewport. */
  public void setVisualViewport(VisualViewport visualViewport) {
    this.visualViewport = visualViewport;
  }

  /** Size of scrollable area. */
  public Rect getContentSize() {
    return contentSize;
  }

  /** Size of scrollable area. */
  public void setContentSize(Rect contentSize) {
    this.contentSize = contentSize;
  }
}
