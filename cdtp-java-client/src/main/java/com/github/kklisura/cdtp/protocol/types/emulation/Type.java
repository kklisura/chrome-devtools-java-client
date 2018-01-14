package com.github.kklisura.cdtp.protocol.types.emulation;

/**
 * Orientation type.
 */
public enum Type {

	PORTRAIT_PRIMARY("portraitPrimary"), PORTRAIT_SECONDARY("portraitSecondary"), LANDSCAPE_PRIMARY("landscapePrimary"), LANDSCAPE_SECONDARY("landscapeSecondary");

	final String propertyName;

	Type(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
