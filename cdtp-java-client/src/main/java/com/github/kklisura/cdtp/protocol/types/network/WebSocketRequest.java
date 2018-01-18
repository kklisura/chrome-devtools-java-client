package com.github.kklisura.cdtp.protocol.types.network;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;

/**
 * WebSocket request data.
 */
@Experimental
public class WebSocketRequest {

	private Headers headers;

	/**
	 * HTTP request headers.
	 */
	public Headers getHeaders() {
		return headers;
	}

	/**
	 * HTTP request headers.
	 */
	public void setHeaders(Headers headers) {
		this.headers = headers;
	}
}
