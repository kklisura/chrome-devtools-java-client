package com.github.kklisura.cdt.services.exceptions;

/*-
 * #%L
 * cdt-java-client
 * %%
 * Copyright (C) 2018 - 2023 Kenan Klisura
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

/**
 * Invocation exception.
 *
 * @author Kenan Klisura
 */
public class ChromeDevToolsInvocationException extends RuntimeException {
  private Long code = -1L;

  /**
   * Instantiates a new Chrome dev tools invocation exception.
   *
   * @param message Exception message.
   */
  public ChromeDevToolsInvocationException(String message) {
    super(message);
  }

  /**
   * Instantiates a new Chrome dev tools invocation exception.
   *
   * @param code Exception code.
   * @param message Exception message.
   */
  public ChromeDevToolsInvocationException(Long code, String message) {
    super(message);
    this.code = code;
  }

  /**
   * Instantiates a new Chrome dev tools invocation exception.
   *
   * @param message Exception message.
   * @param cause Exception cause.
   */
  public ChromeDevToolsInvocationException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Gets code. -1 for 'local' exceptions.
   *
   * @return the code
   */
  public Long getCode() {
    return code;
  }
}
