package com.github.kklisura.cdtp.protocol.types.log;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Log entry source.
 */
public enum Source {

	@JsonProperty("xml")
	XML, @JsonProperty("javascript")
	JAVASCRIPT, @JsonProperty("network")
	NETWORK, @JsonProperty("storage")
	STORAGE, @JsonProperty("appcache")
	APPCACHE, @JsonProperty("rendering")
	RENDERING, @JsonProperty("security")
	SECURITY, @JsonProperty("deprecation")
	DEPRECATION, @JsonProperty("worker")
	WORKER, @JsonProperty("violation")
	VIOLATION, @JsonProperty("intervention")
	INTERVENTION, @JsonProperty("other")
	OTHER
}
