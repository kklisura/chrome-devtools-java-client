package com.github.kklisura.cdtp.protocol.events.runtime;

/** Issued when unhandled exception was revoked. */
public class ExceptionRevoked {

  private String reason;

  private Integer exceptionId;

  /** Reason describing why exception was revoked. */
  public String getReason() {
    return reason;
  }

  /** Reason describing why exception was revoked. */
  public void setReason(String reason) {
    this.reason = reason;
  }

  /** The id of revoked exception, as reported in <code>exceptionUnhandled</code>. */
  public Integer getExceptionId() {
    return exceptionId;
  }

  /** The id of revoked exception, as reported in <code>exceptionUnhandled</code>. */
  public void setExceptionId(Integer exceptionId) {
    this.exceptionId = exceptionId;
  }
}
