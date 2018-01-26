package com.github.kklisura.cdtp.protocol.types.network;

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
