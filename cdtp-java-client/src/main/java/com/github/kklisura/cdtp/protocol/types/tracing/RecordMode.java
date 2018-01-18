package com.github.kklisura.cdtp.protocol.types.tracing;

/**
 * Controls how the trace buffer stores data.
 */
public enum RecordMode {

	RECORD_UNTIL_FULL("recordUntilFull"), RECORD_CONTINUOUSLY("recordContinuously"), RECORD_AS_MUCH_AS_POSSIBLE("recordAsMuchAsPossible"), ECHO_TO_CONSOLE("echoToConsole");

	final String propertyName;

	RecordMode(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
