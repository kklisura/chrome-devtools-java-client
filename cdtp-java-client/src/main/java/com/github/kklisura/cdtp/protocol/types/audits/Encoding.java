package com.github.kklisura.cdtp.protocol.types.audits;

/**
 * The encoding to use.
 */
public enum Encoding {

	WEBP("webp"), JPEG("jpeg"), PNG("png");

	final String propertyName;

	Encoding(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
