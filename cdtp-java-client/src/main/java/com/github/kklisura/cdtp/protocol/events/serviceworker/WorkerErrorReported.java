package com.github.kklisura.cdtp.protocol.events.serviceworker;

import com.github.kklisura.cdtp.protocol.types.serviceworker.ServiceWorkerErrorMessage;

public class WorkerErrorReported {

  private ServiceWorkerErrorMessage errorMessage;

  public ServiceWorkerErrorMessage getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(ServiceWorkerErrorMessage errorMessage) {
    this.errorMessage = errorMessage;
  }
}
