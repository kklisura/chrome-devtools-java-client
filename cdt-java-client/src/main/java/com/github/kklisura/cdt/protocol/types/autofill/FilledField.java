package com.github.kklisura.cdt.protocol.types.autofill;

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

public class FilledField {

  private String htmlType;

  private String id;

  private String name;

  private String value;

  private String autofillType;

  private FillingStrategy fillingStrategy;

  private String frameId;

  private Integer fieldId;

  /** The type of the field, e.g text, password etc. */
  public String getHtmlType() {
    return htmlType;
  }

  /** The type of the field, e.g text, password etc. */
  public void setHtmlType(String htmlType) {
    this.htmlType = htmlType;
  }

  /** the html id */
  public String getId() {
    return id;
  }

  /** the html id */
  public void setId(String id) {
    this.id = id;
  }

  /** the html name */
  public String getName() {
    return name;
  }

  /** the html name */
  public void setName(String name) {
    this.name = name;
  }

  /** the field value */
  public String getValue() {
    return value;
  }

  /** the field value */
  public void setValue(String value) {
    this.value = value;
  }

  /** The actual field type, e.g FAMILY_NAME */
  public String getAutofillType() {
    return autofillType;
  }

  /** The actual field type, e.g FAMILY_NAME */
  public void setAutofillType(String autofillType) {
    this.autofillType = autofillType;
  }

  /** The filling strategy */
  public FillingStrategy getFillingStrategy() {
    return fillingStrategy;
  }

  /** The filling strategy */
  public void setFillingStrategy(FillingStrategy fillingStrategy) {
    this.fillingStrategy = fillingStrategy;
  }

  /** The frame the field belongs to */
  public String getFrameId() {
    return frameId;
  }

  /** The frame the field belongs to */
  public void setFrameId(String frameId) {
    this.frameId = frameId;
  }

  /** The form field's DOM node */
  public Integer getFieldId() {
    return fieldId;
  }

  /** The form field's DOM node */
  public void setFieldId(Integer fieldId) {
    this.fieldId = fieldId;
  }
}
