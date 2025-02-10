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

import com.github.kklisura.cdt.protocol.types.storage.InterestGroupAuctionFetchType;
import java.util.List;

/**
 * Specifies which auctions a particular network fetch may be related to, and in what role. Note
 * that it is not ordered with respect to Network.requestWillBeSent (but will happen before
 * loadingFinished loadingFailed).
 */
public class InterestGroupAuctionNetworkRequestCreated {

  private InterestGroupAuctionFetchType type;

  private String requestId;

  private List<String> auctions;

  public InterestGroupAuctionFetchType getType() {
    return type;
  }

  public void setType(InterestGroupAuctionFetchType type) {
    this.type = type;
  }

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  /**
   * This is the set of the auctions using the worklet that issued this request. In the case of
   * trusted signals, it's possible that only some of them actually care about the keys being
   * queried.
   */
  public List<String> getAuctions() {
    return auctions;
  }

  /**
   * This is the set of the auctions using the worklet that issued this request. In the case of
   * trusted signals, it's possible that only some of them actually care about the keys being
   * queried.
   */
  public void setAuctions(List<String> auctions) {
    this.auctions = auctions;
  }
}
