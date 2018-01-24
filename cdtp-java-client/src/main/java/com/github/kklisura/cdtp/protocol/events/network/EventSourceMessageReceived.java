package com.github.kklisura.cdtp.protocol.events.network;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;

/**
 * Fired when EventSource message is received.
 */
@Experimental
public class EventSourceMessageReceived {

	private String requestId;

	private Double timestamp;

	private String eventName;

	private String eventId;

	private String data;

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
	 * Message type.
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * Message type.
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	/**
	 * Message identifier.
	 */
	public String getEventId() {
		return eventId;
	}

	/**
	 * Message identifier.
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	/**
	 * Message content.
	 */
	public String getData() {
		return data;
	}

	/**
	 * Message content.
	 */
	public void setData(String data) {
		this.data = data;
	}
}
