package com.github.kklisura.cdtp.protocol.types.log;

/**
 * Violation type.
 */
public enum Name {

	LONG_TASK("longTask"), LONG_LAYOUT("longLayout"), BLOCKED_EVENT("blockedEvent"), BLOCKED_PARSER("blockedParser"), DISCOURAGED_API_USE("discouragedAPIUse"), HANDLER("handler"), RECURRING_HANDLER("recurringHandler");

	final String propertyName;

	Name(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
