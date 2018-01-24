package com.github.kklisura.cdtp.protocol.types.network;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import java.util.Map;

/**
 * WebSocket request data.
 */
@Experimental
public class WebSocketRequest {

	private Map<String, Object> headers;

	/**
	 * HTTP request headers.
	 */
	public Map<String, Object> getHeaders() {
		return headers;
	}

	/**
	 * HTTP request headers.
	 */
	public void setHeaders(Map<String, Object> headers) {
		this.headers = headers;
	}
}
