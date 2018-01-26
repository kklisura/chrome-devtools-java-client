package com.github.kklisura.cdtp.protocol.events.runtime;

import com.github.kklisura.cdtp.protocol.types.runtime.ExceptionDetails;

/** Issued when exception was thrown and unhandled. */
public class ExceptionThrown {

  private Double timestamp;

  private ExceptionDetails exceptionDetails;

  /** Timestamp of the exception. */
  public Double getTimestamp() {
    return timestamp;
  }

  /** Timestamp of the exception. */
  public void setTimestamp(Double timestamp) {
    this.timestamp = timestamp;
  }

  public ExceptionDetails getExceptionDetails() {
    return exceptionDetails;
  }

  public void setExceptionDetails(ExceptionDetails exceptionDetails) {
    this.exceptionDetails = exceptionDetails;
  }
}
