package com.github.kklisura.cdt.protocol.events.network;

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

/** Fired when data chunk was received over the network. */
public class DataReceived {

  private String requestId;

  private Double timestamp;

  private Integer dataLength;

  private Integer encodedDataLength;

  @Experimental @Optional private String data;

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

  /** Data chunk length. */
  public Integer getDataLength() {
    return dataLength;
  }

  /** Data chunk length. */
  public void setDataLength(Integer dataLength) {
    this.dataLength = dataLength;
  }

  /** Actual bytes received (might be less than dataLength for compressed encodings). */
  public Integer getEncodedDataLength() {
    return encodedDataLength;
  }

  /** Actual bytes received (might be less than dataLength for compressed encodings). */
  public void setEncodedDataLength(Integer encodedDataLength) {
    this.encodedDataLength = encodedDataLength;
  }

  /** Data that was received. (Encoded as a base64 string when passed over JSON) */
  public String getData() {
    return data;
  }

  /** Data that was received. (Encoded as a base64 string when passed over JSON) */
  public void setData(String data) {
    this.data = data;
  }
}
