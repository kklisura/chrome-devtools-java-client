package com.github.kklisura.cdtp.protocol.types.tracing;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Whether to report trace events as series of dataCollected events or to save trace to a stream (defaults to <code>ReportEvents</code>).
 */
public enum TransferMode {

	@JsonProperty("ReportEvents")
	REPORT_EVENTS, @JsonProperty("ReturnAsStream")
	RETURN_AS_STREAM
}
