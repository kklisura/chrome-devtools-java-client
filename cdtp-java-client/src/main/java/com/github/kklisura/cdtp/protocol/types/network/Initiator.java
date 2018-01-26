package com.github.kklisura.cdtp.protocol.types.network;

import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import com.github.kklisura.cdtp.protocol.types.runtime.StackTrace;

/** Information about the request initiator. */
public class Initiator {

  private Type type;

  @Optional private StackTrace stack;

  @Optional private String url;

  @Optional private Double lineNumber;

  /** Type of this initiator. */
  public Type getType() {
    return type;
  }

  /** Type of this initiator. */
  public void setType(Type type) {
    this.type = type;
  }

  /** Initiator JavaScript stack trace, set for Script only. */
  public StackTrace getStack() {
    return stack;
  }

  /** Initiator JavaScript stack trace, set for Script only. */
  public void setStack(StackTrace stack) {
    this.stack = stack;
  }

  /** Initiator URL, set for Parser type or for Script type (when script is importing module). */
  public String getUrl() {
    return url;
  }

  /** Initiator URL, set for Parser type or for Script type (when script is importing module). */
  public void setUrl(String url) {
    this.url = url;
  }

  /**
   * Initiator line number, set for Parser type or for Script type (when script is importing module)
   * (0-based).
   */
  public Double getLineNumber() {
    return lineNumber;
  }

  /**
   * Initiator line number, set for Parser type or for Script type (when script is importing module)
   * (0-based).
   */
  public void setLineNumber(Double lineNumber) {
    this.lineNumber = lineNumber;
  }
}
