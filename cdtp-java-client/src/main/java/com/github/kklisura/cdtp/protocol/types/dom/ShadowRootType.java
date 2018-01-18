package com.github.kklisura.cdtp.protocol.types.dom;

/**
 * Shadow root type.
 */
public enum ShadowRootType {

	USER_AGENT("user-agent"), OPEN("open"), CLOSED("closed");

	final String propertyName;

	ShadowRootType(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
