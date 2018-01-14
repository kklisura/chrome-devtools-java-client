package com.github.kklisura.cdtp.protocol.types.memory;

/**
 * Memory pressure level.
 */
public enum PressureLevel {

	MODERATE("moderate"), CRITICAL("critical");

	final String propertyName;

	PressureLevel(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
