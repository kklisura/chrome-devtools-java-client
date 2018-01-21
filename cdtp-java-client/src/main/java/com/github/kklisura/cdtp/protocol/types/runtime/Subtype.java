package com.github.kklisura.cdtp.protocol.types.runtime;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Object subtype hint. Specified for <code>object</code> type values only.
 */
public enum Subtype {

	@JsonProperty("array")
	ARRAY, @JsonProperty("null")
	NULL, @JsonProperty("node")
	NODE, @JsonProperty("regexp")
	REGEXP, @JsonProperty("date")
	DATE, @JsonProperty("map")
	MAP, @JsonProperty("set")
	SET, @JsonProperty("weakmap")
	WEAKMAP, @JsonProperty("weakset")
	WEAKSET, @JsonProperty("iterator")
	ITERATOR, @JsonProperty("generator")
	GENERATOR, @JsonProperty("error")
	ERROR
}
