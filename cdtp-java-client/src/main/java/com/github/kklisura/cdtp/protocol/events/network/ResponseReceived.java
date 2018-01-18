package com.github.kklisura.cdtp.protocol.events.network;

import com.github.kklisura.cdtp.protocol.types.page.ResourceType;
import com.github.kklisura.cdtp.protocol.types.network.Response;
import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.Optional;

/**
 * Fired when HTTP response is available.
 */
public class ResponseReceived {

	private String requestId;

	private String loaderId;

	private Double timestamp;

	private ResourceType type;

	private Response response;

	@Experimental
	@Optional
	private String frameId;

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
	 * Loader identifier. Empty string if the request is fetched form worker.
	 */
	public String getLoaderId() {
		return loaderId;
	}

	/**
	 * Loader identifier. Empty string if the request is fetched form worker.
	 */
	public void setLoaderId(String loaderId) {
		this.loaderId = loaderId;
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
	 * Resource type.
	 */
	public ResourceType getType() {
		return type;
	}

	/**
	 * Resource type.
	 */
	public void setType(ResourceType type) {
		this.type = type;
	}

	/**
	 * Response data.
	 */
	public Response getResponse() {
		return response;
	}

	/**
	 * Response data.
	 */
	public void setResponse(Response response) {
		this.response = response;
	}

	/**
	 * Frame identifier.
	 */
	public String getFrameId() {
		return frameId;
	}

	/**
	 * Frame identifier.
	 */
	public void setFrameId(String frameId) {
		this.frameId = frameId;
	}
}
