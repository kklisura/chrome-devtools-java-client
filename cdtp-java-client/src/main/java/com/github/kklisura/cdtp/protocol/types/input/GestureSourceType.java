package com.github.kklisura.cdtp.protocol.types.input;

public enum GestureSourceType {

	DEFAULT("default"), TOUCH("touch"), MOUSE("mouse");

	final String propertyName;

	GestureSourceType(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
