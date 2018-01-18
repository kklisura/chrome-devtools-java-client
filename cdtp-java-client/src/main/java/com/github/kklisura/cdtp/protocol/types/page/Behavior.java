package com.github.kklisura.cdtp.protocol.types.page;

/**
 * Whether to allow all or deny all download requests, or use default Chrome behavior if available (otherwise deny).
 */
public enum Behavior {

	DENY("deny"), ALLOW("allow"), DEFAULT("default");

	final String propertyName;

	Behavior(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
