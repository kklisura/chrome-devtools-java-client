package com.github.kklisura.cdt.protocol.types.memory;

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

/** DOM object counter data. */
public class DOMCounter {

  private String name;

  private Integer count;

  /**
   * Object name. Note: object names should be presumed volatile and clients should not expect the
   * returned names to be consistent across runs.
   */
  public String getName() {
    return name;
  }

  /**
   * Object name. Note: object names should be presumed volatile and clients should not expect the
   * returned names to be consistent across runs.
   */
  public void setName(String name) {
    this.name = name;
  }

  /** Object count. */
  public Integer getCount() {
    return count;
  }

  /** Object count. */
  public void setCount(Integer count) {
    this.count = count;
  }
}
