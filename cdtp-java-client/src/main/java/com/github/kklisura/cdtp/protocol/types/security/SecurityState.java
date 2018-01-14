package com.github.kklisura.cdtp.protocol.types.security;

/**
 * The security level of a page or resource.
 */
public enum SecurityState {

	UNKNOWN("unknown"), NEUTRAL("neutral"), INSECURE("insecure"), SECURE("secure"), INFO("info");

	final String propertyName;

	SecurityState(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
