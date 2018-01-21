package com.github.kklisura.cdtp.protocol.types.audits;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The encoding to use.
 */
public enum Encoding {

	@JsonProperty("webp")
	WEBP, @JsonProperty("jpeg")
	JPEG, @JsonProperty("png")
	PNG
}
