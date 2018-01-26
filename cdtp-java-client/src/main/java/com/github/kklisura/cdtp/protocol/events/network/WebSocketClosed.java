package com.github.kklisura.cdtp.protocol.events.network;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;

/** Fired when WebSocket is closed. */
@Experimental
public class WebSocketClosed {

  private String requestId;

  private Double timestamp;

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
}
