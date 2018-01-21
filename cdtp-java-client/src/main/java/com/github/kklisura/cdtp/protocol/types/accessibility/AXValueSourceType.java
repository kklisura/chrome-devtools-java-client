package com.github.kklisura.cdtp.protocol.types.accessibility;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Enum of possible property sources.
 */
public enum AXValueSourceType {

	@JsonProperty("attribute")
	ATTRIBUTE, @JsonProperty("implicit")
	IMPLICIT, @JsonProperty("style")
	STYLE, @JsonProperty("contents")
	CONTENTS, @JsonProperty("placeholder")
	PLACEHOLDER, @JsonProperty("relatedElement")
	RELATED_ELEMENT
}
