package com.github.kklisura.cdtp.protocol.types.input;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Mouse button.
 */
public enum Button {

	@JsonProperty("none")
	NONE, @JsonProperty("left")
	LEFT, @JsonProperty("middle")
	MIDDLE, @JsonProperty("right")
	RIGHT
}
