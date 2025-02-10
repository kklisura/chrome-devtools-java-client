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

public enum AttributionReportingSourceRegistrationResult {
  @JsonProperty("success")
  SUCCESS,
  @JsonProperty("internalError")
  INTERNAL_ERROR,
  @JsonProperty("insufficientSourceCapacity")
  INSUFFICIENT_SOURCE_CAPACITY,
  @JsonProperty("insufficientUniqueDestinationCapacity")
  INSUFFICIENT_UNIQUE_DESTINATION_CAPACITY,
  @JsonProperty("excessiveReportingOrigins")
  EXCESSIVE_REPORTING_ORIGINS,
  @JsonProperty("prohibitedByBrowserPolicy")
  PROHIBITED_BY_BROWSER_POLICY,
  @JsonProperty("successNoised")
  SUCCESS_NOISED,
  @JsonProperty("destinationReportingLimitReached")
  DESTINATION_REPORTING_LIMIT_REACHED,
  @JsonProperty("destinationGlobalLimitReached")
  DESTINATION_GLOBAL_LIMIT_REACHED,
  @JsonProperty("destinationBothLimitsReached")
  DESTINATION_BOTH_LIMITS_REACHED,
  @JsonProperty("reportingOriginsPerSiteLimitReached")
  REPORTING_ORIGINS_PER_SITE_LIMIT_REACHED,
  @JsonProperty("exceedsMaxChannelCapacity")
  EXCEEDS_MAX_CHANNEL_CAPACITY,
  @JsonProperty("exceedsMaxScopesChannelCapacity")
  EXCEEDS_MAX_SCOPES_CHANNEL_CAPACITY,
  @JsonProperty("exceedsMaxTriggerStateCardinality")
  EXCEEDS_MAX_TRIGGER_STATE_CARDINALITY,
  @JsonProperty("exceedsMaxEventStatesLimit")
  EXCEEDS_MAX_EVENT_STATES_LIMIT,
  @JsonProperty("destinationPerDayReportingLimitReached")
  DESTINATION_PER_DAY_REPORTING_LIMIT_REACHED
}
