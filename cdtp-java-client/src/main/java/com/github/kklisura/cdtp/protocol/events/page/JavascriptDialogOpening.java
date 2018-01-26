package com.github.kklisura.cdtp.protocol.events.page;

import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import com.github.kklisura.cdtp.protocol.types.page.DialogType;

/**
 * Fired when a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload) is about to
 * open.
 */
public class JavascriptDialogOpening {

  private String url;

  private String message;

  private DialogType type;

  @Optional private String defaultPrompt;

  /** Frame url. */
  public String getUrl() {
    return url;
  }

  /** Frame url. */
  public void setUrl(String url) {
    this.url = url;
  }

  /** Message that will be displayed by the dialog. */
  public String getMessage() {
    return message;
  }

  /** Message that will be displayed by the dialog. */
  public void setMessage(String message) {
    this.message = message;
  }

  /** Dialog type. */
  public DialogType getType() {
    return type;
  }

  /** Dialog type. */
  public void setType(DialogType type) {
    this.type = type;
  }

  /** Default dialog prompt. */
  public String getDefaultPrompt() {
    return defaultPrompt;
  }

  /** Default dialog prompt. */
  public void setDefaultPrompt(String defaultPrompt) {
    this.defaultPrompt = defaultPrompt;
  }
}
