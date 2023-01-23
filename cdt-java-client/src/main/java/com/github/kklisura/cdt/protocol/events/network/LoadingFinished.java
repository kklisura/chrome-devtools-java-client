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

import com.github.kklisura.cdt.protocol.support.annotations.Optional;

/** Fired when HTTP request has finished loading. */
public class LoadingFinished {

  private String requestId;

  private Double timestamp;

  private Double encodedDataLength;

  @Optional private Boolean shouldReportCorbBlocking;

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

  /** Total number of bytes received for this request. */
  public Double getEncodedDataLength() {
    return encodedDataLength;
  }

  /** Total number of bytes received for this request. */
  public void setEncodedDataLength(Double encodedDataLength) {
    this.encodedDataLength = encodedDataLength;
  }

  /**
   * Set when 1) response was blocked by Cross-Origin Read Blocking and also 2) this needs to be
   * reported to the DevTools console.
   */
  public Boolean getShouldReportCorbBlocking() {
    return shouldReportCorbBlocking;
  }

  /**
   * Set when 1) response was blocked by Cross-Origin Read Blocking and also 2) this needs to be
   * reported to the DevTools console.
   */
  public void setShouldReportCorbBlocking(Boolean shouldReportCorbBlocking) {
    this.shouldReportCorbBlocking = shouldReportCorbBlocking;
  }
}
