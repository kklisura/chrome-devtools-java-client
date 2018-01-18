package com.github.kklisura.cdtp.protocol.types.network;

/**
 * Source of the authentication challenge.
 */
public enum Source {

	SERVER("Server"), PROXY("Proxy");

	final String propertyName;

	Source(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
