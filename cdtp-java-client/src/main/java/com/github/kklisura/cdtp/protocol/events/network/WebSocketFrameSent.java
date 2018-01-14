package com.github.kklisura.cdtp.protocol.events.network;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.types.network.WebSocketFrame;

/**
 * Fired when WebSocket frame is sent.
 */
@Experimental
public class WebSocketFrameSent {

	private String requestId;

	private Double timestamp;

	private WebSocketFrame response;

	/**
	 * Request identifier.
	 */
	public String getRequestId() {
		return requestId;
	}

	/**
	 * Request identifier.
	 */
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	/**
	 * Timestamp.
	 */
	public Double getTimestamp() {
		return timestamp;
	}

	/**
	 * Timestamp.
	 */
	public void setTimestamp(Double timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * WebSocket response data.
	 */
	public WebSocketFrame getResponse() {
		return response;
	}

	/**
	 * WebSocket response data.
	 */
	public void setResponse(WebSocketFrame response) {
		this.response = response;
	}
}
