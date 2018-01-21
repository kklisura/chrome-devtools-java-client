package com.github.kklisura.cdtp.protocol.types.accessibility;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Attributes which apply to widgets.
 */
public enum AXWidgetAttributes {

	@JsonProperty("autocomplete")
	AUTOCOMPLETE, @JsonProperty("haspopup")
	HASPOPUP, @JsonProperty("level")
	LEVEL, @JsonProperty("multiselectable")
	MULTISELECTABLE, @JsonProperty("orientation")
	ORIENTATION, @JsonProperty("multiline")
	MULTILINE, @JsonProperty("readonly")
	READONLY, @JsonProperty("required")
	REQUIRED, @JsonProperty("valuemin")
	VALUEMIN, @JsonProperty("valuemax")
	VALUEMAX, @JsonProperty("valuetext")
	VALUETEXT
}
