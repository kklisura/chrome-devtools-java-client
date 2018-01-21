package com.github.kklisura.cdtp.protocol.types.accessibility;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Enum of possible native property sources (as a subtype of a particular AXValueSourceType).
 */
public enum AXValueNativeSourceType {

	@JsonProperty("figcaption")
	FIGCAPTION, @JsonProperty("label")
	LABEL, @JsonProperty("labelfor")
	LABELFOR, @JsonProperty("labelwrapped")
	LABELWRAPPED, @JsonProperty("legend")
	LEGEND, @JsonProperty("tablecaption")
	TABLECAPTION, @JsonProperty("title")
	TITLE, @JsonProperty("other")
	OTHER
}
