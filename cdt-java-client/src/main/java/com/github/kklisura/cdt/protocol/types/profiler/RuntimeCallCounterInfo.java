package com.github.kklisura.cdt.protocol.types.profiler;

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

import com.github.kklisura.cdt.protocol.support.annotations.Experimental;

/** Runtime call counter information. */
@Experimental
public class RuntimeCallCounterInfo {

  private String name;

  private Double value;

  private Double time;

  /** Counter name. */
  public String getName() {
    return name;
  }

  /** Counter name. */
  public void setName(String name) {
    this.name = name;
  }

  /** Counter value. */
  public Double getValue() {
    return value;
  }

  /** Counter value. */
  public void setValue(Double value) {
    this.value = value;
  }

  /** Counter time in seconds. */
  public Double getTime() {
    return time;
  }

  /** Counter time in seconds. */
  public void setTime(Double time) {
    this.time = time;
  }
}
