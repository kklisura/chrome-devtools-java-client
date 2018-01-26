package com.github.kklisura.cdtp.protocol.events.console;

import com.github.kklisura.cdtp.protocol.types.console.ConsoleMessage;

/** Issued when new console message is added. */
public class MessageAdded {

  private ConsoleMessage message;

  /** Console message that has been added. */
  public ConsoleMessage getMessage() {
    return message;
  }

  /** Console message that has been added. */
  public void setMessage(ConsoleMessage message) {
    this.message = message;
  }
}
