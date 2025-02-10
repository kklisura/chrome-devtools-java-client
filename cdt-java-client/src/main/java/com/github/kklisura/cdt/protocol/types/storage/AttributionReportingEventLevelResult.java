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

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AttributionReportingEventLevelResult {
  @JsonProperty("success")
  SUCCESS,
  @JsonProperty("successDroppedLowerPriority")
  SUCCESS_DROPPED_LOWER_PRIORITY,
  @JsonProperty("internalError")
  INTERNAL_ERROR,
  @JsonProperty("noCapacityForAttributionDestination")
  NO_CAPACITY_FOR_ATTRIBUTION_DESTINATION,
  @JsonProperty("noMatchingSources")
  NO_MATCHING_SOURCES,
  @JsonProperty("deduplicated")
  DEDUPLICATED,
  @JsonProperty("excessiveAttributions")
  EXCESSIVE_ATTRIBUTIONS,
  @JsonProperty("priorityTooLow")
  PRIORITY_TOO_LOW,
  @JsonProperty("neverAttributedSource")
  NEVER_ATTRIBUTED_SOURCE,
  @JsonProperty("excessiveReportingOrigins")
  EXCESSIVE_REPORTING_ORIGINS,
  @JsonProperty("noMatchingSourceFilterData")
  NO_MATCHING_SOURCE_FILTER_DATA,
  @JsonProperty("prohibitedByBrowserPolicy")
  PROHIBITED_BY_BROWSER_POLICY,
  @JsonProperty("noMatchingConfigurations")
  NO_MATCHING_CONFIGURATIONS,
  @JsonProperty("excessiveReports")
  EXCESSIVE_REPORTS,
  @JsonProperty("falselyAttributedSource")
  FALSELY_ATTRIBUTED_SOURCE,
  @JsonProperty("reportWindowPassed")
  REPORT_WINDOW_PASSED,
  @JsonProperty("notRegistered")
  NOT_REGISTERED,
  @JsonProperty("reportWindowNotStarted")
  REPORT_WINDOW_NOT_STARTED,
  @JsonProperty("noMatchingTriggerData")
  NO_MATCHING_TRIGGER_DATA
}
