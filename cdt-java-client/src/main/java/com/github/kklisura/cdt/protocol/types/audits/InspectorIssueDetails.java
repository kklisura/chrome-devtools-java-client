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

import com.github.kklisura.cdt.protocol.support.annotations.Optional;

/**
 * This struct holds a list of optional fields with additional information specific to the kind of
 * issue. When adding a new issue code, please also add a new optional field to this type.
 */
public class InspectorIssueDetails {

  @Optional private SameSiteCookieIssueDetails sameSiteCookieIssueDetails;

  @Optional private MixedContentIssueDetails mixedContentIssueDetails;

  @Optional private BlockedByResponseIssueDetails blockedByResponseIssueDetails;

  @Optional private HeavyAdIssueDetails heavyAdIssueDetails;

  @Optional private ContentSecurityPolicyIssueDetails contentSecurityPolicyIssueDetails;

  @Optional private SharedArrayBufferIssueDetails sharedArrayBufferIssueDetails;

  @Optional private TrustedWebActivityIssueDetails twaQualityEnforcementDetails;

  @Optional private LowTextContrastIssueDetails lowTextContrastIssueDetails;

  @Optional private CorsIssueDetails corsIssueDetails;

  @Optional private AttributionReportingIssueDetails attributionReportingIssueDetails;

  public SameSiteCookieIssueDetails getSameSiteCookieIssueDetails() {
    return sameSiteCookieIssueDetails;
  }

  public void setSameSiteCookieIssueDetails(SameSiteCookieIssueDetails sameSiteCookieIssueDetails) {
    this.sameSiteCookieIssueDetails = sameSiteCookieIssueDetails;
  }

  public MixedContentIssueDetails getMixedContentIssueDetails() {
    return mixedContentIssueDetails;
  }

  public void setMixedContentIssueDetails(MixedContentIssueDetails mixedContentIssueDetails) {
    this.mixedContentIssueDetails = mixedContentIssueDetails;
  }

  public BlockedByResponseIssueDetails getBlockedByResponseIssueDetails() {
    return blockedByResponseIssueDetails;
  }

  public void setBlockedByResponseIssueDetails(
      BlockedByResponseIssueDetails blockedByResponseIssueDetails) {
    this.blockedByResponseIssueDetails = blockedByResponseIssueDetails;
  }

  public HeavyAdIssueDetails getHeavyAdIssueDetails() {
    return heavyAdIssueDetails;
  }

  public void setHeavyAdIssueDetails(HeavyAdIssueDetails heavyAdIssueDetails) {
    this.heavyAdIssueDetails = heavyAdIssueDetails;
  }

  public ContentSecurityPolicyIssueDetails getContentSecurityPolicyIssueDetails() {
    return contentSecurityPolicyIssueDetails;
  }

  public void setContentSecurityPolicyIssueDetails(
      ContentSecurityPolicyIssueDetails contentSecurityPolicyIssueDetails) {
    this.contentSecurityPolicyIssueDetails = contentSecurityPolicyIssueDetails;
  }

  public SharedArrayBufferIssueDetails getSharedArrayBufferIssueDetails() {
    return sharedArrayBufferIssueDetails;
  }

  public void setSharedArrayBufferIssueDetails(
      SharedArrayBufferIssueDetails sharedArrayBufferIssueDetails) {
    this.sharedArrayBufferIssueDetails = sharedArrayBufferIssueDetails;
  }

  public TrustedWebActivityIssueDetails getTwaQualityEnforcementDetails() {
    return twaQualityEnforcementDetails;
  }

  public void setTwaQualityEnforcementDetails(
      TrustedWebActivityIssueDetails twaQualityEnforcementDetails) {
    this.twaQualityEnforcementDetails = twaQualityEnforcementDetails;
  }

  public LowTextContrastIssueDetails getLowTextContrastIssueDetails() {
    return lowTextContrastIssueDetails;
  }

  public void setLowTextContrastIssueDetails(
      LowTextContrastIssueDetails lowTextContrastIssueDetails) {
    this.lowTextContrastIssueDetails = lowTextContrastIssueDetails;
  }

  public CorsIssueDetails getCorsIssueDetails() {
    return corsIssueDetails;
  }

  public void setCorsIssueDetails(CorsIssueDetails corsIssueDetails) {
    this.corsIssueDetails = corsIssueDetails;
  }

  public AttributionReportingIssueDetails getAttributionReportingIssueDetails() {
    return attributionReportingIssueDetails;
  }

  public void setAttributionReportingIssueDetails(
      AttributionReportingIssueDetails attributionReportingIssueDetails) {
    this.attributionReportingIssueDetails = attributionReportingIssueDetails;
  }
}
