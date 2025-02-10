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
import java.util.List;

@Experimental
public class AttributionReportingSourceRegistration {

  private Double time;

  private Integer expiry;

  private List<AttributionReportingTriggerSpec> triggerSpecs;

  private Integer aggregatableReportWindow;

  private AttributionReportingSourceType type;

  private String sourceOrigin;

  private String reportingOrigin;

  private List<String> destinationSites;

  private String eventId;

  private String priority;

  private List<AttributionReportingFilterDataEntry> filterData;

  private List<AttributionReportingAggregationKeysEntry> aggregationKeys;

  @Optional private String debugKey;

  private AttributionReportingTriggerDataMatching triggerDataMatching;

  private String destinationLimitPriority;

  private AttributionReportingAggregatableDebugReportingConfig aggregatableDebugReportingConfig;

  @Optional private AttributionScopesData scopesData;

  private Integer maxEventLevelReports;

  public Double getTime() {
    return time;
  }

  public void setTime(Double time) {
    this.time = time;
  }

  /** duration in seconds */
  public Integer getExpiry() {
    return expiry;
  }

  /** duration in seconds */
  public void setExpiry(Integer expiry) {
    this.expiry = expiry;
  }

  public List<AttributionReportingTriggerSpec> getTriggerSpecs() {
    return triggerSpecs;
  }

  public void setTriggerSpecs(List<AttributionReportingTriggerSpec> triggerSpecs) {
    this.triggerSpecs = triggerSpecs;
  }

  /** duration in seconds */
  public Integer getAggregatableReportWindow() {
    return aggregatableReportWindow;
  }

  /** duration in seconds */
  public void setAggregatableReportWindow(Integer aggregatableReportWindow) {
    this.aggregatableReportWindow = aggregatableReportWindow;
  }

  public AttributionReportingSourceType getType() {
    return type;
  }

  public void setType(AttributionReportingSourceType type) {
    this.type = type;
  }

  public String getSourceOrigin() {
    return sourceOrigin;
  }

  public void setSourceOrigin(String sourceOrigin) {
    this.sourceOrigin = sourceOrigin;
  }

  public String getReportingOrigin() {
    return reportingOrigin;
  }

  public void setReportingOrigin(String reportingOrigin) {
    this.reportingOrigin = reportingOrigin;
  }

  public List<String> getDestinationSites() {
    return destinationSites;
  }

  public void setDestinationSites(List<String> destinationSites) {
    this.destinationSites = destinationSites;
  }

  public String getEventId() {
    return eventId;
  }

  public void setEventId(String eventId) {
    this.eventId = eventId;
  }

  public String getPriority() {
    return priority;
  }

  public void setPriority(String priority) {
    this.priority = priority;
  }

  public List<AttributionReportingFilterDataEntry> getFilterData() {
    return filterData;
  }

  public void setFilterData(List<AttributionReportingFilterDataEntry> filterData) {
    this.filterData = filterData;
  }

  public List<AttributionReportingAggregationKeysEntry> getAggregationKeys() {
    return aggregationKeys;
  }

  public void setAggregationKeys(List<AttributionReportingAggregationKeysEntry> aggregationKeys) {
    this.aggregationKeys = aggregationKeys;
  }

  public String getDebugKey() {
    return debugKey;
  }

  public void setDebugKey(String debugKey) {
    this.debugKey = debugKey;
  }

  public AttributionReportingTriggerDataMatching getTriggerDataMatching() {
    return triggerDataMatching;
  }

  public void setTriggerDataMatching(AttributionReportingTriggerDataMatching triggerDataMatching) {
    this.triggerDataMatching = triggerDataMatching;
  }

  public String getDestinationLimitPriority() {
    return destinationLimitPriority;
  }

  public void setDestinationLimitPriority(String destinationLimitPriority) {
    this.destinationLimitPriority = destinationLimitPriority;
  }

  public AttributionReportingAggregatableDebugReportingConfig
      getAggregatableDebugReportingConfig() {
    return aggregatableDebugReportingConfig;
  }

  public void setAggregatableDebugReportingConfig(
      AttributionReportingAggregatableDebugReportingConfig aggregatableDebugReportingConfig) {
    this.aggregatableDebugReportingConfig = aggregatableDebugReportingConfig;
  }

  public AttributionScopesData getScopesData() {
    return scopesData;
  }

  public void setScopesData(AttributionScopesData scopesData) {
    this.scopesData = scopesData;
  }

  public Integer getMaxEventLevelReports() {
    return maxEventLevelReports;
  }

  public void setMaxEventLevelReports(Integer maxEventLevelReports) {
    this.maxEventLevelReports = maxEventLevelReports;
  }
}
