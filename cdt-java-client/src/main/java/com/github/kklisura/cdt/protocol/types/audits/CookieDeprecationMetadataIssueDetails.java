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

import java.util.List;

/**
 * This issue warns about third-party sites that are accessing cookies on the current page, and have
 * been permitted due to having a global metadata grant. Note that in this context 'site' means
 * eTLD+1. For example, if the URL `https://example.test:80/web_page` was accessing cookies, the
 * site reported would be `example.test`.
 */
public class CookieDeprecationMetadataIssueDetails {

  private List<String> allowedSites;

  private Double optOutPercentage;

  private Boolean isOptOutTopLevel;

  private CookieOperation operation;

  public List<String> getAllowedSites() {
    return allowedSites;
  }

  public void setAllowedSites(List<String> allowedSites) {
    this.allowedSites = allowedSites;
  }

  public Double getOptOutPercentage() {
    return optOutPercentage;
  }

  public void setOptOutPercentage(Double optOutPercentage) {
    this.optOutPercentage = optOutPercentage;
  }

  public Boolean getIsOptOutTopLevel() {
    return isOptOutTopLevel;
  }

  public void setIsOptOutTopLevel(Boolean isOptOutTopLevel) {
    this.isOptOutTopLevel = isOptOutTopLevel;
  }

  public CookieOperation getOperation() {
    return operation;
  }

  public void setOperation(CookieOperation operation) {
    this.operation = operation;
  }
}
