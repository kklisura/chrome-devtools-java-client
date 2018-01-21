package com.github.kklisura.cdtp.protocol.types.indexeddb;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Key path type.
 */
public enum Type {

	@JsonProperty("null")
	NULL, @JsonProperty("string")
	STRING, @JsonProperty("array")
	ARRAY
}
