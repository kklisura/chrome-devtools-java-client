package com.github.kklisura.cdtp.protocol.types.runtime;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import java.util.List;

/** Call frames for assertions or error messages. */
public class StackTrace {

  @Optional private String description;

  private List<CallFrame> callFrames;

  @Optional private com.github.kklisura.cdtp.protocol.types.runtime.StackTrace parent;

  @Experimental @Optional private CallFrame promiseCreationFrame;

  /**
   * String label of this stack trace. For async traces this may be a name of the function that
   * initiated the async call.
   */
  public String getDescription() {
    return description;
  }

  /**
   * String label of this stack trace. For async traces this may be a name of the function that
   * initiated the async call.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /** JavaScript function name. */
  public List<CallFrame> getCallFrames() {
    return callFrames;
  }

  /** JavaScript function name. */
  public void setCallFrames(List<CallFrame> callFrames) {
    this.callFrames = callFrames;
  }

  /** Asynchronous JavaScript stack trace that preceded this stack, if available. */
  public com.github.kklisura.cdtp.protocol.types.runtime.StackTrace getParent() {
    return parent;
  }

  /** Asynchronous JavaScript stack trace that preceded this stack, if available. */
  public void setParent(com.github.kklisura.cdtp.protocol.types.runtime.StackTrace parent) {
    this.parent = parent;
  }

  /**
   * Creation frame of the Promise which produced the next synchronous trace when resolved, if
   * available.
   */
  public CallFrame getPromiseCreationFrame() {
    return promiseCreationFrame;
  }

  /**
   * Creation frame of the Promise which produced the next synchronous trace when resolved, if
   * available.
   */
  public void setPromiseCreationFrame(CallFrame promiseCreationFrame) {
    this.promiseCreationFrame = promiseCreationFrame;
  }
}
