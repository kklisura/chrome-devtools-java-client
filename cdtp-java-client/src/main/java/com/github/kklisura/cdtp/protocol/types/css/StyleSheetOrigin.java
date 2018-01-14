package com.github.kklisura.cdtp.protocol.types.css;

/**
 * Stylesheet type: "injected" for stylesheets injected via extension, "user-agent" for user-agent stylesheets, "inspector" for stylesheets created by the inspector (i.e. those holding the "via inspector" rules), "regular" for regular stylesheets.
 */
public enum StyleSheetOrigin {

	INJECTED("injected"), USER_AGENT("user-agent"), INSPECTOR("inspector"), REGULAR("regular");

	final String propertyName;

	StyleSheetOrigin(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
