package com.github.kklisura.cdtp.protocol.types.memory;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Memory pressure level.
 */
public enum PressureLevel {

	@JsonProperty("moderate")
	MODERATE, @JsonProperty("critical")
	CRITICAL
}
