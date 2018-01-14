package com.github.kklisura.cdtp.protocol.types.runtime;

/**
 * Object type. Accessor means that the property itself is an accessor property.
 */
public enum Type {

	OBJECT("object"), FUNCTION("function"), UNDEFINED("undefined"), STRING("string"), NUMBER("number"), BOOLEAN("boolean"), SYMBOL("symbol"), ACCESSOR("accessor");

	final String propertyName;

	Type(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
