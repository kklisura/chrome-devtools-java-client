package com.github.kklisura.cdt.protocol.events.network;

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

import com.github.kklisura.cdt.protocol.types.network.WebSocketRequest;

/** Fired when WebSocket is about to initiate handshake. */
public class WebSocketWillSendHandshakeRequest {

  private String requestId;

  private Double timestamp;

  private Double wallTime;

  private WebSocketRequest request;

  /** Request identifier. */
  public String getRequestId() {
    return requestId;
  }

  /** Request identifier. */
  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  /** Timestamp. */
  public Double getTimestamp() {
    return timestamp;
  }

  /** Timestamp. */
  public void setTimestamp(Double timestamp) {
    this.timestamp = timestamp;
  }

  /** UTC Timestamp. */
  public Double getWallTime() {
    return wallTime;
  }

  /** UTC Timestamp. */
  public void setWallTime(Double wallTime) {
    this.wallTime = wallTime;
  }

  /** WebSocket request data. */
  public WebSocketRequest getRequest() {
    return request;
  }

  /** WebSocket request data. */
  public void setRequest(WebSocketRequest request) {
    this.request = request;
  }
}
