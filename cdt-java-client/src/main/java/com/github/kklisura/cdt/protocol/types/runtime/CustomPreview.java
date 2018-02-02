package com.github.kklisura.cdt.protocol.types.runtime;

/*-
 * #%L
 * cdt-java-client
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

import com.github.kklisura.cdt.protocol.support.annotations.Experimental;
import com.github.kklisura.cdt.protocol.support.annotations.Optional;

@Experimental
public class CustomPreview {

  private String header;

  private Boolean hasBody;

  private String formatterObjectId;

  private String bindRemoteObjectFunctionId;

  @Optional private String configObjectId;

  public String getHeader() {
    return header;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  public Boolean getHasBody() {
    return hasBody;
  }

  public void setHasBody(Boolean hasBody) {
    this.hasBody = hasBody;
  }

  public String getFormatterObjectId() {
    return formatterObjectId;
  }

  public void setFormatterObjectId(String formatterObjectId) {
    this.formatterObjectId = formatterObjectId;
  }

  public String getBindRemoteObjectFunctionId() {
    return bindRemoteObjectFunctionId;
  }

  public void setBindRemoteObjectFunctionId(String bindRemoteObjectFunctionId) {
    this.bindRemoteObjectFunctionId = bindRemoteObjectFunctionId;
  }

  public String getConfigObjectId() {
    return configObjectId;
  }

  public void setConfigObjectId(String configObjectId) {
    this.configObjectId = configObjectId;
  }
}
