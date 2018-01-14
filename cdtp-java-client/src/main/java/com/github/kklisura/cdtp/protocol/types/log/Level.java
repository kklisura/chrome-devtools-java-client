package com.github.kklisura.cdtp.protocol.types.log;

/**
 * Log entry severity.
 */
public enum Level {

	VERBOSE("verbose"), INFO("info"), WARNING("warning"), ERROR("error");

	final String propertyName;

	Level(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
