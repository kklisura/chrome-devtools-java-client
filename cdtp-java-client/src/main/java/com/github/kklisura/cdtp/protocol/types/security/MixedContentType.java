package com.github.kklisura.cdtp.protocol.types.security;

/**
 * A description of mixed content (HTTP resources on HTTPS pages), as defined by https://www.w3.org/TR/mixed-content/#categories
 */
public enum MixedContentType {

	BLOCKABLE("blockable"), OPTIONALLY_BLOCKABLE("optionally-blockable"), NONE("none");

	final String propertyName;

	MixedContentType(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
