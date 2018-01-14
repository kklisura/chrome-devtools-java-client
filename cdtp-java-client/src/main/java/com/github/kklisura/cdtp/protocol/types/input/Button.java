package com.github.kklisura.cdtp.protocol.types.input;

/**
 * Mouse button.
 */
public enum Button {

	NONE("none"), LEFT("left"), MIDDLE("middle"), RIGHT("right");

	final String propertyName;

	Button(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
