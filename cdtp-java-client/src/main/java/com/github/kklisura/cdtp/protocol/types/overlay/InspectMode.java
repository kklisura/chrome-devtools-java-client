package com.github.kklisura.cdtp.protocol.types.overlay;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum InspectMode {

	@JsonProperty("searchForNode")
	SEARCH_FOR_NODE, @JsonProperty("searchForUAShadowDOM")
	SEARCH_FOR_UA_SHADOW_DOM, @JsonProperty("none")
	NONE
}
