package com.github.kklisura.cdtp.protocol.events.network;

/** Fired when HTTP request has finished loading. */
public class LoadingFinished {

  private String requestId;

  private Double timestamp;

  private Double encodedDataLength;

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
}
