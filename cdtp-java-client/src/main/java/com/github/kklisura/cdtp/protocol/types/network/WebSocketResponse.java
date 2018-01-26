package com.github.kklisura.cdtp.protocol.types.network;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import java.util.Map;

/** WebSocket response data. */
@Experimental
public class WebSocketResponse {

  private Double status;

  private String statusText;

  private Map<String, Object> headers;

  @Optional private String headersText;

  @Optional private Map<String, Object> requestHeaders;

  @Optional private String requestHeadersText;

  /** HTTP response status code. */
  public Double getStatus() {
    return status;
  }

  /** HTTP response status code. */
  public void setStatus(Double status) {
    this.status = status;
  }

  /** HTTP response status text. */
  public String getStatusText() {
    return statusText;
  }

  /** HTTP response status text. */
  public void setStatusText(String statusText) {
    this.statusText = statusText;
  }

  /** HTTP response headers. */
  public Map<String, Object> getHeaders() {
    return headers;
  }

  /** HTTP response headers. */
  public void setHeaders(Map<String, Object> headers) {
    this.headers = headers;
  }

  /** HTTP response headers text. */
  public String getHeadersText() {
    return headersText;
  }

  /** HTTP response headers text. */
  public void setHeadersText(String headersText) {
    this.headersText = headersText;
  }

  /** HTTP request headers. */
  public Map<String, Object> getRequestHeaders() {
    return requestHeaders;
  }

  /** HTTP request headers. */
  public void setRequestHeaders(Map<String, Object> requestHeaders) {
    this.requestHeaders = requestHeaders;
  }

  /** HTTP request headers text. */
  public String getRequestHeadersText() {
    return requestHeadersText;
  }

  /** HTTP request headers text. */
  public void setRequestHeadersText(String requestHeadersText) {
    this.requestHeadersText = requestHeadersText;
  }
}
