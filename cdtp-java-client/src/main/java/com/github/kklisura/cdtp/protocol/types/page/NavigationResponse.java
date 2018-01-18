package com.github.kklisura.cdtp.protocol.types.page;

/**
 * Proceed: allow the navigation; Cancel: cancel the navigation; CancelAndIgnore: cancels the navigation and makes the requester of the navigation acts like the request was never made.
 */
public enum NavigationResponse {

	PROCEED("Proceed"), CANCEL("Cancel"), CANCEL_AND_IGNORE("CancelAndIgnore");

	final String propertyName;

	NavigationResponse(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
