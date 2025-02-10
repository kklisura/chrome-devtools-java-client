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

import com.github.kklisura.cdt.protocol.support.annotations.Experimental;
import com.github.kklisura.cdt.protocol.support.annotations.Optional;

@Experimental
public class ServiceWorkerRouterInfo {

  @Optional private Integer ruleIdMatched;

  @Optional private ServiceWorkerRouterSource matchedSourceType;

  @Optional private ServiceWorkerRouterSource actualSourceType;

  /**
   * ID of the rule matched. If there is a matched rule, this field will be set, otherwiser no value
   * will be set.
   */
  public Integer getRuleIdMatched() {
    return ruleIdMatched;
  }

  /**
   * ID of the rule matched. If there is a matched rule, this field will be set, otherwiser no value
   * will be set.
   */
  public void setRuleIdMatched(Integer ruleIdMatched) {
    this.ruleIdMatched = ruleIdMatched;
  }

  /**
   * The router source of the matched rule. If there is a matched rule, this field will be set,
   * otherwise no value will be set.
   */
  public ServiceWorkerRouterSource getMatchedSourceType() {
    return matchedSourceType;
  }

  /**
   * The router source of the matched rule. If there is a matched rule, this field will be set,
   * otherwise no value will be set.
   */
  public void setMatchedSourceType(ServiceWorkerRouterSource matchedSourceType) {
    this.matchedSourceType = matchedSourceType;
  }

  /** The actual router source used. */
  public ServiceWorkerRouterSource getActualSourceType() {
    return actualSourceType;
  }

  /** The actual router source used. */
  public void setActualSourceType(ServiceWorkerRouterSource actualSourceType) {
    this.actualSourceType = actualSourceType;
  }
}
