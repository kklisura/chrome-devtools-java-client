package com.github.kklisura.cdtp.protocol.types.network;

/**
 * The referrer policy of the request, as defined in https://www.w3.org/TR/referrer-policy/
 */
public enum ReferrerPolicy {

	UNSAFE_URL("unsafe-url"), NO_REFERRER_WHEN_DOWNGRADE("no-referrer-when-downgrade"), NO_REFERRER("no-referrer"), ORIGIN("origin"), ORIGIN_WHEN_CROSS_ORIGIN("origin-when-cross-origin"), SAME_ORIGIN("same-origin"), STRICT_ORIGIN("strict-origin"), STRICT_ORIGIN_WHEN_CROSS_ORIGIN("strict-origin-when-cross-origin");

	final String propertyName;

	ReferrerPolicy(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
