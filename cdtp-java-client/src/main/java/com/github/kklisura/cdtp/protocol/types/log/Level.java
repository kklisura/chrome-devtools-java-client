package com.github.kklisura.cdtp.protocol.types.log;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Log entry severity.
 */
public enum Level {

	@JsonProperty("verbose")
	VERBOSE, @JsonProperty("info")
	INFO, @JsonProperty("warning")
	WARNING, @JsonProperty("error")
	ERROR
}
