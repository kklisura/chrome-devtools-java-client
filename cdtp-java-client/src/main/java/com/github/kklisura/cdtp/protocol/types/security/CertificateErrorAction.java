package com.github.kklisura.cdtp.protocol.types.security;

/**
 * The action to take when a certificate error occurs. continue will continue processing the request and cancel will cancel the request.
 */
public enum CertificateErrorAction {

	CONTINUE("continue"), CANCEL("cancel");

	final String propertyName;

	CertificateErrorAction(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
