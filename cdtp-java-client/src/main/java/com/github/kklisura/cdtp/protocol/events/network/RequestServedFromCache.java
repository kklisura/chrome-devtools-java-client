package com.github.kklisura.cdtp.protocol.events.network;

/**
 * Fired if request ended up loading from cache.
 */
public class RequestServedFromCache {

	private String requestId;

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
}
