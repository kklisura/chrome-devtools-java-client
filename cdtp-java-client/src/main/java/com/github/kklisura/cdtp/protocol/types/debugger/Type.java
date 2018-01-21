package com.github.kklisura.cdtp.protocol.types.debugger;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Type {

	@JsonProperty("debuggerStatement")
	DEBUGGER_STATEMENT, @JsonProperty("call")
	CALL, @JsonProperty("return")
	RETURN
}
