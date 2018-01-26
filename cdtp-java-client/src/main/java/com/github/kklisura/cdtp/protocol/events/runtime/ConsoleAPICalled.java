package com.github.kklisura.cdtp.protocol.events.runtime;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import com.github.kklisura.cdtp.protocol.types.runtime.RemoteObject;
import com.github.kklisura.cdtp.protocol.types.runtime.StackTrace;
import java.util.List;

/** Issued when console API was called. */
public class ConsoleAPICalled {

  private Type type;

  private List<RemoteObject> args;

  private Integer executionContextId;

  private Double timestamp;

  @Optional private StackTrace stackTrace;

  @Experimental @Optional private String context;

  /** Type of the call. */
  public Type getType() {
    return type;
  }

  /** Type of the call. */
  public void setType(Type type) {
    this.type = type;
  }

  /** Call arguments. */
  public List<RemoteObject> getArgs() {
    return args;
  }

  /** Call arguments. */
  public void setArgs(List<RemoteObject> args) {
    this.args = args;
  }

  /** Identifier of the context where the call was made. */
  public Integer getExecutionContextId() {
    return executionContextId;
  }

  /** Identifier of the context where the call was made. */
  public void setExecutionContextId(Integer executionContextId) {
    this.executionContextId = executionContextId;
  }

  /** Call timestamp. */
  public Double getTimestamp() {
    return timestamp;
  }

  /** Call timestamp. */
  public void setTimestamp(Double timestamp) {
    this.timestamp = timestamp;
  }

  /** Stack trace captured when the call was made. */
  public StackTrace getStackTrace() {
    return stackTrace;
  }

  /** Stack trace captured when the call was made. */
  public void setStackTrace(StackTrace stackTrace) {
    this.stackTrace = stackTrace;
  }

  /**
   * Console context descriptor for calls on non-default console context (not console.*):
   * 'anonymous#unique-logger-id' for call on unnamed context, 'name#unique-logger-id' for call on
   * named context.
   */
  public String getContext() {
    return context;
  }

  /**
   * Console context descriptor for calls on non-default console context (not console.*):
   * 'anonymous#unique-logger-id' for call on unnamed context, 'name#unique-logger-id' for call on
   * named context.
   */
  public void setContext(String context) {
    this.context = context;
  }
}
