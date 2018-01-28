package com.github.kklisura.cdtp.protocol.types.database;

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

/** Database object. */
@Experimental
public class Database {

  private String id;

  private String domain;

  private String name;

  private String version;

  /** Database ID. */
  public String getId() {
    return id;
  }

  /** Database ID. */
  public void setId(String id) {
    this.id = id;
  }

  /** Database domain. */
  public String getDomain() {
    return domain;
  }

  /** Database domain. */
  public void setDomain(String domain) {
    this.domain = domain;
  }

  /** Database name. */
  public String getName() {
    return name;
  }

  /** Database name. */
  public void setName(String name) {
    this.name = name;
  }

  /** Database version. */
  public String getVersion() {
    return version;
  }

  /** Database version. */
  public void setVersion(String version) {
    this.version = version;
  }
}
