package com.github.kklisura.cdtp.protocol.types.debugger;

/**
 * Pause on exceptions mode.
 */
public enum State {

	NONE("none"), UNCAUGHT("uncaught"), ALL("all");

	final String propertyName;

	State(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
