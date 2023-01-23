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
 * Details for issues around "Attribution Reporting API" usage. Explainer:
 * https://github.com/WICG/conversion-measurement-api
 */
public class AttributionReportingIssueDetails {

  private AttributionReportingIssueType violationType;

  @Optional private AffectedFrame frame;

  @Optional private AffectedRequest request;

  @Optional private Integer violatingNodeId;

  @Optional private String invalidParameter;

  public AttributionReportingIssueType getViolationType() {
    return violationType;
  }

  public void setViolationType(AttributionReportingIssueType violationType) {
    this.violationType = violationType;
  }

  public AffectedFrame getFrame() {
    return frame;
  }

  public void setFrame(AffectedFrame frame) {
    this.frame = frame;
  }

  public AffectedRequest getRequest() {
    return request;
  }

  public void setRequest(AffectedRequest request) {
    this.request = request;
  }

  public Integer getViolatingNodeId() {
    return violatingNodeId;
  }

  public void setViolatingNodeId(Integer violatingNodeId) {
    this.violatingNodeId = violatingNodeId;
  }

  public String getInvalidParameter() {
    return invalidParameter;
  }

  public void setInvalidParameter(String invalidParameter) {
    this.invalidParameter = invalidParameter;
  }
}
