package com.github.kklisura.cdtp.protocol.types.network;

/**
 * Type of this initiator.
 */
public enum Type {

	PARSER("parser"), SCRIPT("script"), PRELOAD("preload"), OTHER("other");

	final String propertyName;

	Type(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
