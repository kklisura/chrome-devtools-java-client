package com.github.kklisura.cdtp.protocol.types.runtime;

import com.github.kklisura.cdtp.protocol.support.annotations.Optional;

public class CallFunctionOn {

  private RemoteObject result;

  @Optional private ExceptionDetails exceptionDetails;

  /** Call result. */
  public RemoteObject getResult() {
    return result;
  }

  /** Call result. */
  public void setResult(RemoteObject result) {
    this.result = result;
  }

  /** Exception details. */
  public ExceptionDetails getExceptionDetails() {
    return exceptionDetails;
  }

  /** Exception details. */
  public void setExceptionDetails(ExceptionDetails exceptionDetails) {
    this.exceptionDetails = exceptionDetails;
  }
}
