package com.github.kklisura.cdtp.protocol.events.css;

/** Fired whenever a stylesheet is changed as a result of the client operation. */
public class StyleSheetChanged {

  private String styleSheetId;

  public String getStyleSheetId() {
    return styleSheetId;
  }

  public void setStyleSheetId(String styleSheetId) {
    this.styleSheetId = styleSheetId;
  }
}
