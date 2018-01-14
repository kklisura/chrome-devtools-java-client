package com.github.kklisura.cdtp.protocol.types.page;

/**
 * Transition type.
 */
public enum TransitionType {

	LINK("link"), TYPED("typed"), AUTO_BOOKMARK("auto_bookmark"), AUTO_SUBFRAME("auto_subframe"), MANUAL_SUBFRAME("manual_subframe"), GENERATED("generated"), AUTO_TOPLEVEL("auto_toplevel"), FORM_SUBMIT("form_submit"), RELOAD("reload"), KEYWORD("keyword"), KEYWORD_GENERATED("keyword_generated"), OTHER("other");

	final String propertyName;

	TransitionType(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
