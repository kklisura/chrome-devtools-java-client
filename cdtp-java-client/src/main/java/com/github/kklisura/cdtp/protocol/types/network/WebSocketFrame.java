package com.github.kklisura.cdtp.protocol.types.network;

/*-
 * #%L
 * cdpt-java-client
 * %%
 * Copyright (C) 2018 Kenan Klisura
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

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;

/** WebSocket frame data. */
@Experimental
public class WebSocketFrame {

  private Double opcode;

  private Boolean mask;

  private String payloadData;

  /** WebSocket frame opcode. */
  public Double getOpcode() {
    return opcode;
  }

  /** WebSocket frame opcode. */
  public void setOpcode(Double opcode) {
    this.opcode = opcode;
  }

  /** WebSocke frame mask. */
  public Boolean getMask() {
    return mask;
  }

  /** WebSocke frame mask. */
  public void setMask(Boolean mask) {
    this.mask = mask;
  }

  /** WebSocke frame payload data. */
  public String getPayloadData() {
    return payloadData;
  }

  /** WebSocke frame payload data. */
  public void setPayloadData(String payloadData) {
    this.payloadData = payloadData;
  }
}
