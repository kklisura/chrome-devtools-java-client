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
public class AttributionReportingTriggerRegistration {

  private AttributionReportingFilterPair filters;

  @Optional private String debugKey;

  private List<AttributionReportingAggregatableDedupKey> aggregatableDedupKeys;

  private List<AttributionReportingEventTriggerData> eventTriggerData;

  private List<AttributionReportingAggregatableTriggerData> aggregatableTriggerData;

  private List<AttributionReportingAggregatableValueEntry> aggregatableValues;

  private Integer aggregatableFilteringIdMaxBytes;

  private Boolean debugReporting;

  @Optional private String aggregationCoordinatorOrigin;

  private AttributionReportingSourceRegistrationTimeConfig sourceRegistrationTimeConfig;

  @Optional private String triggerContextId;

  private AttributionReportingAggregatableDebugReportingConfig aggregatableDebugReportingConfig;

  private List<String> scopes;

  public AttributionReportingFilterPair getFilters() {
    return filters;
  }

  public void setFilters(AttributionReportingFilterPair filters) {
    this.filters = filters;
  }

  public String getDebugKey() {
    return debugKey;
  }

  public void setDebugKey(String debugKey) {
    this.debugKey = debugKey;
  }

  public List<AttributionReportingAggregatableDedupKey> getAggregatableDedupKeys() {
    return aggregatableDedupKeys;
  }

  public void setAggregatableDedupKeys(
      List<AttributionReportingAggregatableDedupKey> aggregatableDedupKeys) {
    this.aggregatableDedupKeys = aggregatableDedupKeys;
  }

  public List<AttributionReportingEventTriggerData> getEventTriggerData() {
    return eventTriggerData;
  }

  public void setEventTriggerData(List<AttributionReportingEventTriggerData> eventTriggerData) {
    this.eventTriggerData = eventTriggerData;
  }

  public List<AttributionReportingAggregatableTriggerData> getAggregatableTriggerData() {
    return aggregatableTriggerData;
  }

  public void setAggregatableTriggerData(
      List<AttributionReportingAggregatableTriggerData> aggregatableTriggerData) {
    this.aggregatableTriggerData = aggregatableTriggerData;
  }

  public List<AttributionReportingAggregatableValueEntry> getAggregatableValues() {
    return aggregatableValues;
  }

  public void setAggregatableValues(
      List<AttributionReportingAggregatableValueEntry> aggregatableValues) {
    this.aggregatableValues = aggregatableValues;
  }

  public Integer getAggregatableFilteringIdMaxBytes() {
    return aggregatableFilteringIdMaxBytes;
  }

  public void setAggregatableFilteringIdMaxBytes(Integer aggregatableFilteringIdMaxBytes) {
    this.aggregatableFilteringIdMaxBytes = aggregatableFilteringIdMaxBytes;
  }

  public Boolean getDebugReporting() {
    return debugReporting;
  }

  public void setDebugReporting(Boolean debugReporting) {
    this.debugReporting = debugReporting;
  }

  public String getAggregationCoordinatorOrigin() {
    return aggregationCoordinatorOrigin;
  }

  public void setAggregationCoordinatorOrigin(String aggregationCoordinatorOrigin) {
    this.aggregationCoordinatorOrigin = aggregationCoordinatorOrigin;
  }

  public AttributionReportingSourceRegistrationTimeConfig getSourceRegistrationTimeConfig() {
    return sourceRegistrationTimeConfig;
  }

  public void setSourceRegistrationTimeConfig(
      AttributionReportingSourceRegistrationTimeConfig sourceRegistrationTimeConfig) {
    this.sourceRegistrationTimeConfig = sourceRegistrationTimeConfig;
  }

  public String getTriggerContextId() {
    return triggerContextId;
  }

  public void setTriggerContextId(String triggerContextId) {
    this.triggerContextId = triggerContextId;
  }

  public AttributionReportingAggregatableDebugReportingConfig
      getAggregatableDebugReportingConfig() {
    return aggregatableDebugReportingConfig;
  }

  public void setAggregatableDebugReportingConfig(
      AttributionReportingAggregatableDebugReportingConfig aggregatableDebugReportingConfig) {
    this.aggregatableDebugReportingConfig = aggregatableDebugReportingConfig;
  }

  public List<String> getScopes() {
    return scopes;
  }

  public void setScopes(List<String> scopes) {
    this.scopes = scopes;
  }
}
