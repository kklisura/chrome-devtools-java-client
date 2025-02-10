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
import java.util.List;

@Experimental
public class AttributionReportingAggregatableValueEntry {

  private List<AttributionReportingAggregatableValueDictEntry> values;

  private AttributionReportingFilterPair filters;

  public List<AttributionReportingAggregatableValueDictEntry> getValues() {
    return values;
  }

  public void setValues(List<AttributionReportingAggregatableValueDictEntry> values) {
    this.values = values;
  }

  public AttributionReportingFilterPair getFilters() {
    return filters;
  }

  public void setFilters(AttributionReportingFilterPair filters) {
    this.filters = filters;
  }
}
