package com.github.kklisura.cdtp.protocol.types.network;

/**
 * The decision on what to do in response to the authorization challenge.  Default means deferring to the default behavior of the net stack, which will likely either the Cancel authentication or display a popup dialog box.
 */
public enum Response {

	DEFAULT("Default"), CANCEL_AUTH("CancelAuth"), PROVIDE_CREDENTIALS("ProvideCredentials");

	final String propertyName;

	Response(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
