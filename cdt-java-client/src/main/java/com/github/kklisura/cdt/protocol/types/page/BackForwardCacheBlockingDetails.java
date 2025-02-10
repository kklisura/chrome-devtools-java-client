package com.github.kklisura.cdt.protocol.types.page;

/*-
 * #%L
 * cdt-java-client
 * %%
 * Copyright (C) 2018 - 2025 Kenan Klisura
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

import com.github.kklisura.cdt.protocol.support.annotations.Experimental;
import com.github.kklisura.cdt.protocol.support.annotations.Optional;

@Experimental
public class BackForwardCacheBlockingDetails {

  @Optional private String url;

  @Optional private String function;

  private Integer lineNumber;

  private Integer columnNumber;

  /** Url of the file where blockage happened. Optional because of tests. */
  public String getUrl() {
    return url;
  }

  /** Url of the file where blockage happened. Optional because of tests. */
  public void setUrl(String url) {
    this.url = url;
  }

  /** Function name where blockage happened. Optional because of anonymous functions and tests. */
  public String getFunction() {
    return function;
  }

  /** Function name where blockage happened. Optional because of anonymous functions and tests. */
  public void setFunction(String function) {
    this.function = function;
  }

  /** Line number in the script (0-based). */
  public Integer getLineNumber() {
    return lineNumber;
  }

  /** Line number in the script (0-based). */
  public void setLineNumber(Integer lineNumber) {
    this.lineNumber = lineNumber;
  }

  /** Column number in the script (0-based). */
  public Integer getColumnNumber() {
    return columnNumber;
  }

  /** Column number in the script (0-based). */
  public void setColumnNumber(Integer columnNumber) {
    this.columnNumber = columnNumber;
  }
}
