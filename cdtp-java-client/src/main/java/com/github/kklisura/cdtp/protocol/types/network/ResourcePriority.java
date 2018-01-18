package com.github.kklisura.cdtp.protocol.types.network;

/**
 * Loading priority of a resource request.
 */
public enum ResourcePriority {

	VERY_LOW("VeryLow"), LOW("Low"), MEDIUM("Medium"), HIGH("High"), VERY_HIGH("VeryHigh");

	final String propertyName;

	ResourcePriority(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
