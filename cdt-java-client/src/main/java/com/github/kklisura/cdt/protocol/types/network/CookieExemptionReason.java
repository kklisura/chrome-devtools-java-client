package com.github.kklisura.cdt.protocol.types.network;

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

/**
 * Types of reasons why a cookie should have been blocked by 3PCD but is exempted for the request.
 */
public enum CookieExemptionReason {
  @JsonProperty("None")
  NONE,
  @JsonProperty("UserSetting")
  USER_SETTING,
  @JsonProperty("TPCDMetadata")
  TPCD_METADATA,
  @JsonProperty("TPCDDeprecationTrial")
  TPCD_DEPRECATION_TRIAL,
  @JsonProperty("TopLevelTPCDDeprecationTrial")
  TOP_LEVEL_TPCD_DEPRECATION_TRIAL,
  @JsonProperty("TPCDHeuristics")
  TPCD_HEURISTICS,
  @JsonProperty("EnterprisePolicy")
  ENTERPRISE_POLICY,
  @JsonProperty("StorageAccess")
  STORAGE_ACCESS,
  @JsonProperty("TopLevelStorageAccess")
  TOP_LEVEL_STORAGE_ACCESS,
  @JsonProperty("Scheme")
  SCHEME,
  @JsonProperty("SameSiteNoneCookiesInSandbox")
  SAME_SITE_NONE_COOKIES_IN_SANDBOX
}
