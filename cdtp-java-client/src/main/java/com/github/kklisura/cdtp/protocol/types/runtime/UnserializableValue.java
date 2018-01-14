package com.github.kklisura.cdtp.protocol.types.runtime;

/**
 * Primitive value which cannot be JSON-stringified.
 */
public enum UnserializableValue {

	INFINITY("Infinity"), NAN("NaN"), NEGATIVE_INFINITY("-Infinity"), NEGATIVE_0("-0");

	final String propertyName;

	UnserializableValue(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
