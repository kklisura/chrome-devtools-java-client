package com.github.kklisura.cdtp.protocol.types.console;

/**
 * Message severity.
 */
public enum Level {

	LOG("log"), WARNING("warning"), ERROR("error"), DEBUG("debug"), INFO("info");

	final String propertyName;

	Level(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
