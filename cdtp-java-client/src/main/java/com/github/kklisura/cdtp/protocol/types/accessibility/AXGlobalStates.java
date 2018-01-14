package com.github.kklisura.cdtp.protocol.types.accessibility;

/**
 * States which apply to every AX node.
 */
public enum AXGlobalStates {

	BUSY("busy"), DISABLED("disabled"), HIDDEN("hidden"), HIDDEN_ROOT("hiddenRoot"), INVALID("invalid"), KEYSHORTCUTS("keyshortcuts"), ROLEDESCRIPTION("roledescription");

	final String propertyName;

	AXGlobalStates(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
