package com.github.kklisura.cdtp.protocol.types.accessibility;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Relationships between elements other than parent/child/sibling.
 */
public enum AXRelationshipAttributes {

	@JsonProperty("activedescendant")
	ACTIVEDESCENDANT,
	@JsonProperty("controls")
	CONTROLS,
	@JsonProperty("describedby")
	DESCRIBEDBY,
	@JsonProperty("details")
	DETAILS,
	@JsonProperty("errormessage")
	ERRORMESSAGE,
	@JsonProperty("flowto")
	FLOWTO,
	@JsonProperty("labelledby")
	LABELLEDBY,
	@JsonProperty("owns")
	OWNS
}
