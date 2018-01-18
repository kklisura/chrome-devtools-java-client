package com.github.kklisura.cdtp.protocol.types.network;

/**
 * Network level fetch failure reason.
 */
public enum ErrorReason {

	FAILED("Failed"), ABORTED("Aborted"), TIMED_OUT("TimedOut"), ACCESS_DENIED("AccessDenied"), CONNECTION_CLOSED("ConnectionClosed"), CONNECTION_RESET("ConnectionReset"), CONNECTION_REFUSED("ConnectionRefused"), CONNECTION_ABORTED("ConnectionAborted"), CONNECTION_FAILED("ConnectionFailed"), NAME_NOT_RESOLVED("NameNotResolved"), INTERNET_DISCONNECTED("InternetDisconnected"), ADDRESS_UNREACHABLE("AddressUnreachable");

	final String propertyName;

	ErrorReason(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
