package com.github.kklisura.cdtp.protocol.types.runtime;

/*-
 * #%L
 * cdpt-java-client
 * %%
 * Copyright (C) 2018 Kenan Klisura
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

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
