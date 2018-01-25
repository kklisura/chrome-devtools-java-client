package com.github.kklisura.cdtp.protocol.types.network;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Network level fetch failure reason.
 */
public enum ErrorReason {

	@JsonProperty("Failed")
	FAILED,
	@JsonProperty("Aborted")
	ABORTED,
	@JsonProperty("TimedOut")
	TIMED_OUT,
	@JsonProperty("AccessDenied")
	ACCESS_DENIED,
	@JsonProperty("ConnectionClosed")
	CONNECTION_CLOSED,
	@JsonProperty("ConnectionReset")
	CONNECTION_RESET,
	@JsonProperty("ConnectionRefused")
	CONNECTION_REFUSED,
	@JsonProperty("ConnectionAborted")
	CONNECTION_ABORTED,
	@JsonProperty("ConnectionFailed")
	CONNECTION_FAILED,
	@JsonProperty("NameNotResolved")
	NAME_NOT_RESOLVED,
	@JsonProperty("InternetDisconnected")
	INTERNET_DISCONNECTED,
	@JsonProperty("AddressUnreachable")
	ADDRESS_UNREACHABLE
}
