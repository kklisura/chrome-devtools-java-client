package com.github.kklisura.cdt.protocol.events.storage;

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
import com.github.kklisura.cdt.protocol.types.storage.InterestGroupAuctionEventType;
import java.util.Map;

/** An auction involving interest groups is taking place. These events are target-specific. */
public class InterestGroupAuctionEventOccurred {

  private Double eventTime;

  private InterestGroupAuctionEventType type;

  private String uniqueAuctionId;

  @Optional private String parentAuctionId;

  @Optional private Map<String, Object> auctionConfig;

  public Double getEventTime() {
    return eventTime;
  }

  public void setEventTime(Double eventTime) {
    this.eventTime = eventTime;
  }

  public InterestGroupAuctionEventType getType() {
    return type;
  }

  public void setType(InterestGroupAuctionEventType type) {
    this.type = type;
  }

  public String getUniqueAuctionId() {
    return uniqueAuctionId;
  }

  public void setUniqueAuctionId(String uniqueAuctionId) {
    this.uniqueAuctionId = uniqueAuctionId;
  }

  /** Set for child auctions. */
  public String getParentAuctionId() {
    return parentAuctionId;
  }

  /** Set for child auctions. */
  public void setParentAuctionId(String parentAuctionId) {
    this.parentAuctionId = parentAuctionId;
  }

  /** Set for started and configResolved */
  public Map<String, Object> getAuctionConfig() {
    return auctionConfig;
  }

  /** Set for started and configResolved */
  public void setAuctionConfig(Map<String, Object> auctionConfig) {
    this.auctionConfig = auctionConfig;
  }
}
