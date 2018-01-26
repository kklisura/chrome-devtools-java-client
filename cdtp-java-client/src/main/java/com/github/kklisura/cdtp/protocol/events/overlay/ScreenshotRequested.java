package com.github.kklisura.cdtp.protocol.events.overlay;

import com.github.kklisura.cdtp.protocol.types.page.Viewport;

/** Fired when user asks to capture screenshot of some area on the page. */
public class ScreenshotRequested {

  private Viewport viewport;

  /** Viewport to capture, in CSS. */
  public Viewport getViewport() {
    return viewport;
  }

  /** Viewport to capture, in CSS. */
  public void setViewport(Viewport viewport) {
    this.viewport = viewport;
  }
}
