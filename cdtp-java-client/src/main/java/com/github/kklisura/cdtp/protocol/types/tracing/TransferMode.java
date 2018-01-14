package com.github.kklisura.cdtp.protocol.types.tracing;

/**
 * Whether to report trace events as series of dataCollected events or to save trace to a stream (defaults to <code>ReportEvents</code>).
 */
public enum TransferMode {

	REPORT_EVENTS("ReportEvents"), RETURN_AS_STREAM("ReturnAsStream");

	final String propertyName;

	TransferMode(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
