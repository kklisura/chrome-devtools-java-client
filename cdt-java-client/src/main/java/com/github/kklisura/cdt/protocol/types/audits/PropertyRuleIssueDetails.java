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

/**
 * This issue warns about errors in property rules that lead to property registrations being
 * ignored.
 */
public class PropertyRuleIssueDetails {

  private SourceCodeLocation sourceCodeLocation;

  private PropertyRuleIssueReason propertyRuleIssueReason;

  @Optional private String propertyValue;

  /** Source code position of the property rule. */
  public SourceCodeLocation getSourceCodeLocation() {
    return sourceCodeLocation;
  }

  /** Source code position of the property rule. */
  public void setSourceCodeLocation(SourceCodeLocation sourceCodeLocation) {
    this.sourceCodeLocation = sourceCodeLocation;
  }

  /** Reason why the property rule was discarded. */
  public PropertyRuleIssueReason getPropertyRuleIssueReason() {
    return propertyRuleIssueReason;
  }

  /** Reason why the property rule was discarded. */
  public void setPropertyRuleIssueReason(PropertyRuleIssueReason propertyRuleIssueReason) {
    this.propertyRuleIssueReason = propertyRuleIssueReason;
  }

  /** The value of the property rule property that failed to parse */
  public String getPropertyValue() {
    return propertyValue;
  }

  /** The value of the property rule property that failed to parse */
  public void setPropertyValue(String propertyValue) {
    this.propertyValue = propertyValue;
  }
}
