package com.github.kklisura.cdtp.protocol.types.accessibility;

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

public class AXProperty {

  private String name;

  private AXValue value;

  /** The name of this property. */
  public String getName() {
    return name;
  }

  /** The name of this property. */
  public void setName(String name) {
    this.name = name;
  }

  /** The value of this property. */
  public AXValue getValue() {
    return value;
  }

  /** The value of this property. */
  public void setValue(AXValue value) {
    this.value = value;
  }
}
