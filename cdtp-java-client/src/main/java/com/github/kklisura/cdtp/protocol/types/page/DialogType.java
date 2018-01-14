package com.github.kklisura.cdtp.protocol.types.page;

/**
 * Javascript dialog type.
 */
public enum DialogType {

	ALERT("alert"), CONFIRM("confirm"), PROMPT("prompt"), BEFOREUNLOAD("beforeunload");

	final String propertyName;

	DialogType(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
