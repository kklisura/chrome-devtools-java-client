package com.github.kklisura.cdt.protocol.types.audits;

/*-
 * #%L
 * cdt-java-client
 * %%
 * Copyright (C) 2018 - 2023 Kenan Klisura
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
 * A unique identifier for the type of issue. Each type may use one of the optional fields in
 * InspectorIssueDetails to convey more specific information about the kind of issue.
 */
public enum InspectorIssueCode {
  @JsonProperty("SameSiteCookieIssue")
  SAME_SITE_COOKIE_ISSUE,
  @JsonProperty("MixedContentIssue")
  MIXED_CONTENT_ISSUE,
  @JsonProperty("BlockedByResponseIssue")
  BLOCKED_BY_RESPONSE_ISSUE,
  @JsonProperty("HeavyAdIssue")
  HEAVY_AD_ISSUE,
  @JsonProperty("ContentSecurityPolicyIssue")
  CONTENT_SECURITY_POLICY_ISSUE,
  @JsonProperty("SharedArrayBufferIssue")
  SHARED_ARRAY_BUFFER_ISSUE,
  @JsonProperty("TrustedWebActivityIssue")
  TRUSTED_WEB_ACTIVITY_ISSUE,
  @JsonProperty("LowTextContrastIssue")
  LOW_TEXT_CONTRAST_ISSUE,
  @JsonProperty("CorsIssue")
  CORS_ISSUE,
  @JsonProperty("AttributionReportingIssue")
  ATTRIBUTION_REPORTING_ISSUE
}
