package com.github.kklisura.cdtp.protocol.types.accessibility;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * States which apply to every AX node.
 */
public enum AXGlobalStates {

	@JsonProperty("busy")
	BUSY, @JsonProperty("disabled")
	DISABLED, @JsonProperty("hidden")
	HIDDEN, @JsonProperty("hiddenRoot")
	HIDDEN_ROOT, @JsonProperty("invalid")
	INVALID, @JsonProperty("keyshortcuts")
	KEYSHORTCUTS, @JsonProperty("roledescription")
	ROLEDESCRIPTION
}
