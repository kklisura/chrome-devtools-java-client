package com.github.kklisura.cdtp.protocol.types.network;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.Optional;

/**
 * WebSocket response data.
 */
@Experimental
public class WebSocketResponse {

	private Double status;

	private String statusText;

	private Headers headers;

	@Optional
	private String headersText;

	@Optional
	private Headers requestHeaders;

	@Optional
	private String requestHeadersText;

	/**
	 * HTTP response status code.
	 */
	public Double getStatus() {
		return status;
	}

	/**
	 * HTTP response status code.
	 */
	public void setStatus(Double status) {
		this.status = status;
	}

	/**
	 * HTTP response status text.
	 */
	public String getStatusText() {
		return statusText;
	}

	/**
	 * HTTP response status text.
	 */
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	/**
	 * HTTP response headers.
	 */
	public Headers getHeaders() {
		return headers;
	}

	/**
	 * HTTP response headers.
	 */
	public void setHeaders(Headers headers) {
		this.headers = headers;
	}

	/**
	 * HTTP response headers text.
	 */
	public String getHeadersText() {
		return headersText;
	}

	/**
	 * HTTP response headers text.
	 */
	public void setHeadersText(String headersText) {
		this.headersText = headersText;
	}

	/**
	 * HTTP request headers.
	 */
	public Headers getRequestHeaders() {
		return requestHeaders;
	}

	/**
	 * HTTP request headers.
	 */
	public void setRequestHeaders(Headers requestHeaders) {
		this.requestHeaders = requestHeaders;
	}

	/**
	 * HTTP request headers text.
	 */
	public String getRequestHeadersText() {
		return requestHeadersText;
	}

	/**
	 * HTTP request headers text.
	 */
	public void setRequestHeadersText(String requestHeadersText) {
		this.requestHeadersText = requestHeadersText;
	}
}
