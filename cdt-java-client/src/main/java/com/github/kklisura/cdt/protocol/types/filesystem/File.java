package com.github.kklisura.cdt.protocol.types.filesystem;

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

public class File {

  private String name;

  private Double lastModified;

  private Double size;

  private String type;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /** Timestamp */
  public Double getLastModified() {
    return lastModified;
  }

  /** Timestamp */
  public void setLastModified(Double lastModified) {
    this.lastModified = lastModified;
  }

  /** Size in bytes */
  public Double getSize() {
    return size;
  }

  /** Size in bytes */
  public void setSize(Double size) {
    this.size = size;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
