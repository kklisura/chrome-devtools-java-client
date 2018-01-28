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

@Experimental
public class PropertyPreview {

  private String name;

  private Type type;

  @Optional private String value;

  @Optional private ObjectPreview valuePreview;

  @Optional private Subtype subtype;

  /** Property name. */
  public String getName() {
    return name;
  }

  /** Property name. */
  public void setName(String name) {
    this.name = name;
  }

  /** Object type. Accessor means that the property itself is an accessor property. */
  public Type getType() {
    return type;
  }

  /** Object type. Accessor means that the property itself is an accessor property. */
  public void setType(Type type) {
    this.type = type;
  }

  /** User-friendly property value string. */
  public String getValue() {
    return value;
  }

  /** User-friendly property value string. */
  public void setValue(String value) {
    this.value = value;
  }

  /** Nested value preview. */
  public ObjectPreview getValuePreview() {
    return valuePreview;
  }

  /** Nested value preview. */
  public void setValuePreview(ObjectPreview valuePreview) {
    this.valuePreview = valuePreview;
  }

  /** Object subtype hint. Specified for <code>object</code> type values only. */
  public Subtype getSubtype() {
    return subtype;
  }

  /** Object subtype hint. Specified for <code>object</code> type values only. */
  public void setSubtype(Subtype subtype) {
    this.subtype = subtype;
  }
}
