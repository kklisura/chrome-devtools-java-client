package com.github.kklisura.cdt.protocol.types.audits;

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

import com.github.kklisura.cdt.protocol.support.annotations.Optional;

/** Information about the suggested solution to a cookie issue. */
public class CookieIssueInsight {

  private InsightType type;

  @Optional private String tableEntryUrl;

  public InsightType getType() {
    return type;
  }

  public void setType(InsightType type) {
    this.type = type;
  }

  /** Link to table entry in third-party cookie migration readiness list. */
  public String getTableEntryUrl() {
    return tableEntryUrl;
  }

  /** Link to table entry in third-party cookie migration readiness list. */
  public void setTableEntryUrl(String tableEntryUrl) {
    this.tableEntryUrl = tableEntryUrl;
  }
}
