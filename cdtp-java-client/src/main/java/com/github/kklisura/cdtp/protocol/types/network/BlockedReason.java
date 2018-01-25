package com.github.kklisura.cdtp.protocol.types.network;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The reason why request was blocked.
 */
public enum BlockedReason {

	@JsonProperty("csp")
	CSP,
	@JsonProperty("mixed-content")
	MIXED_CONTENT,
	@JsonProperty("origin")
	ORIGIN,
	@JsonProperty("inspector")
	INSPECTOR,
	@JsonProperty("subresource-filter")
	SUBRESOURCE_FILTER,
	@JsonProperty("other")
	OTHER
}
