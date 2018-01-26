package com.github.kklisura.cdtp.protocol.events.runtime;

/** Issued when execution context is destroyed. */
public class ExecutionContextDestroyed {

  private Integer executionContextId;

  /** Id of the destroyed context */
  public Integer getExecutionContextId() {
    return executionContextId;
  }

  /** Id of the destroyed context */
  public void setExecutionContextId(Integer executionContextId) {
    this.executionContextId = executionContextId;
  }
}
