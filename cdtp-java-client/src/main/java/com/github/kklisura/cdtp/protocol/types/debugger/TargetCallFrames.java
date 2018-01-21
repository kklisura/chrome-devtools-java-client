package com.github.kklisura.cdtp.protocol.types.debugger;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TargetCallFrames {

	@JsonProperty("any")
	ANY, @JsonProperty("current")
	CURRENT
}
