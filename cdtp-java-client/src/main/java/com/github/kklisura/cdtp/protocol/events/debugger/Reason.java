package com.github.kklisura.cdtp.protocol.events.debugger;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Pause reason.
 */
public enum Reason {

	@JsonProperty("XHR")
	XHR, @JsonProperty("DOM")
	DOM, @JsonProperty("EventListener")
	EVENT_LISTENER, @JsonProperty("exception")
	EXCEPTION, @JsonProperty("assert")
	ASSERT, @JsonProperty("debugCommand")
	DEBUG_COMMAND, @JsonProperty("promiseRejection")
	PROMISE_REJECTION, @JsonProperty("OOM")
	OOM, @JsonProperty("other")
	OTHER, @JsonProperty("ambiguous")
	AMBIGUOUS
}
