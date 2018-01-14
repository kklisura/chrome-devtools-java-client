package com.github.kklisura.cdtp.protocol.types.network;

/**
 * Represents the cookie's 'SameSite' status: https://tools.ietf.org/html/draft-west-first-party-cookies
 */
public enum CookieSameSite {

	STRICT("Strict"), LAX("Lax");

	final String propertyName;

	CookieSameSite(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
