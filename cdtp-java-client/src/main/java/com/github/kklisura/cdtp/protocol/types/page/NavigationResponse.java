package com.github.kklisura.cdtp.protocol.types.page;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Proceed: allow the navigation; Cancel: cancel the navigation; CancelAndIgnore: cancels the navigation and makes the requester of the navigation acts like the request was never made.
 */
public enum NavigationResponse {

	@JsonProperty("Proceed")
	PROCEED, @JsonProperty("Cancel")
	CANCEL, @JsonProperty("CancelAndIgnore")
	CANCEL_AND_IGNORE
}
