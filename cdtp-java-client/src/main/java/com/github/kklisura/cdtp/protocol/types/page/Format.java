package com.github.kklisura.cdtp.protocol.types.page;

/**
 * Image compression format.
 */
public enum Format {

	JPEG("jpeg"), PNG("png");

	final String propertyName;

	Format(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
