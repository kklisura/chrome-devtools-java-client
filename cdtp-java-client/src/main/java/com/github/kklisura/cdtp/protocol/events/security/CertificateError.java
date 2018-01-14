package com.github.kklisura.cdtp.protocol.events.security;

/**
 * There is a certificate error. If overriding certificate errors is enabled, then it should be handled with the handleCertificateError command. Note: this event does not fire if the certificate error has been allowed internally.
 */
public class CertificateError {

	private Integer eventId;

	private String errorType;

	private String requestURL;

	/**
	 * The ID of the event.
	 */
	public Integer getEventId() {
		return eventId;
	}

	/**
	 * The ID of the event.
	 */
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	/**
	 * The type of the error.
	 */
	public String getErrorType() {
		return errorType;
	}

	/**
	 * The type of the error.
	 */
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	/**
	 * The url that was requested.
	 */
	public String getRequestURL() {
		return requestURL;
	}

	/**
	 * The url that was requested.
	 */
	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}
}
