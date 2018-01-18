package com.github.kklisura.cdtp.protocol.types.network;

/**
 * The reason why request was blocked.
 */
public enum BlockedReason {

	CSP("csp"), MIXED_CONTENT("mixed-content"), ORIGIN("origin"), INSPECTOR("inspector"), SUBRESOURCE_FILTER("subresource-filter"), OTHER("other");

	final String propertyName;

	BlockedReason(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
