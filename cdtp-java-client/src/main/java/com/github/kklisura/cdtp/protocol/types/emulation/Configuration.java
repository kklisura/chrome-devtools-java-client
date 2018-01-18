package com.github.kklisura.cdtp.protocol.types.emulation;

/**
 * Touch/gesture events configuration. Default: current platform.
 */
public enum Configuration {

	MOBILE("mobile"), DESKTOP("desktop");

	final String propertyName;

	Configuration(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
