package com.github.kklisura.cdtp.protocol.types.input;

/**
 * Type of the mouse event.
 */
public enum Type {

	MOUSE_PRESSED("mousePressed"), MOUSE_RELEASED("mouseReleased"), MOUSE_MOVED("mouseMoved"), MOUSE_WHEEL("mouseWheel");

	final String propertyName;

	Type(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
