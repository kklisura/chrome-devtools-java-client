package com.github.kklisura.cdt.protocol.types.storage;

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
public class AttributionReportingEventTriggerData {

  private String data;

  private String priority;

  @Optional private String dedupKey;

  private AttributionReportingFilterPair filters;

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public String getPriority() {
    return priority;
  }

  public void setPriority(String priority) {
    this.priority = priority;
  }

  public String getDedupKey() {
    return dedupKey;
  }

  public void setDedupKey(String dedupKey) {
    this.dedupKey = dedupKey;
  }

  public AttributionReportingFilterPair getFilters() {
    return filters;
  }

  public void setFilters(AttributionReportingFilterPair filters) {
    this.filters = filters;
  }
}
