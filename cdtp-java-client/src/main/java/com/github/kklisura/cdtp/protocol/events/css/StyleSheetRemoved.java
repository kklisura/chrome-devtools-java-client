package com.github.kklisura.cdtp.protocol.events.css;

/** Fired whenever an active document stylesheet is removed. */
public class StyleSheetRemoved {

  private String styleSheetId;

  /** Identifier of the removed stylesheet. */
  public String getStyleSheetId() {
    return styleSheetId;
  }

  /** Identifier of the removed stylesheet. */
  public void setStyleSheetId(String styleSheetId) {
    this.styleSheetId = styleSheetId;
  }
}
