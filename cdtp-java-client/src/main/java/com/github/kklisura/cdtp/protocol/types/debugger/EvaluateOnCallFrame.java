package com.github.kklisura.cdtp.protocol.types.debugger;

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

import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import com.github.kklisura.cdtp.protocol.types.runtime.ExceptionDetails;
import com.github.kklisura.cdtp.protocol.types.runtime.RemoteObject;

public class EvaluateOnCallFrame {

  private RemoteObject result;

  @Optional private ExceptionDetails exceptionDetails;

  /** Object wrapper for the evaluation result. */
  public RemoteObject getResult() {
    return result;
  }

  /** Object wrapper for the evaluation result. */
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
