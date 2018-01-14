package com.github.kklisura.cdtp.protocol.types.indexeddb;

/**
 * Key path type.
 */
public enum Type {

	NULL("null"), STRING("string"), ARRAY("array");

	final String propertyName;

	Type(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
