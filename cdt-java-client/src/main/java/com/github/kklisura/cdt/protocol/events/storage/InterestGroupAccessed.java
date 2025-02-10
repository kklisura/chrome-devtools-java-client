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
import com.github.kklisura.cdt.protocol.types.storage.InterestGroupAccessType;

/**
 * One of the interest groups was accessed. Note that these events are global to all targets sharing
 * an interest group store.
 */
public class InterestGroupAccessed {

  private Double accessTime;

  private InterestGroupAccessType type;

  private String ownerOrigin;

  private String name;

  @Optional private String componentSellerOrigin;

  @Optional private Double bid;

  @Optional private String bidCurrency;

  @Optional private String uniqueAuctionId;

  public Double getAccessTime() {
    return accessTime;
  }

  public void setAccessTime(Double accessTime) {
    this.accessTime = accessTime;
  }

  public InterestGroupAccessType getType() {
    return type;
  }

  public void setType(InterestGroupAccessType type) {
    this.type = type;
  }

  public String getOwnerOrigin() {
    return ownerOrigin;
  }

  public void setOwnerOrigin(String ownerOrigin) {
    this.ownerOrigin = ownerOrigin;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /** For topLevelBid/topLevelAdditionalBid, and when appropriate, win and additionalBidWin */
  public String getComponentSellerOrigin() {
    return componentSellerOrigin;
  }

  /** For topLevelBid/topLevelAdditionalBid, and when appropriate, win and additionalBidWin */
  public void setComponentSellerOrigin(String componentSellerOrigin) {
    this.componentSellerOrigin = componentSellerOrigin;
  }

  /** For bid or somethingBid event, if done locally and not on a server. */
  public Double getBid() {
    return bid;
  }

  /** For bid or somethingBid event, if done locally and not on a server. */
  public void setBid(Double bid) {
    this.bid = bid;
  }

  public String getBidCurrency() {
    return bidCurrency;
  }

  public void setBidCurrency(String bidCurrency) {
    this.bidCurrency = bidCurrency;
  }

  /** For non-global events --- links to interestGroupAuctionEvent */
  public String getUniqueAuctionId() {
    return uniqueAuctionId;
  }

  /** For non-global events --- links to interestGroupAuctionEvent */
  public void setUniqueAuctionId(String uniqueAuctionId) {
    this.uniqueAuctionId = uniqueAuctionId;
  }
}
