package com.github.kklisura.cdtp.protocol.types.accessibility;

/**
 * Relationships between elements other than parent/child/sibling.
 */
public enum AXRelationshipAttributes {

	ACTIVEDESCENDANT("activedescendant"), CONTROLS("controls"), DESCRIBEDBY("describedby"), DETAILS("details"), ERRORMESSAGE("errormessage"), FLOWTO("flowto"), LABELLEDBY("labelledby"), OWNS("owns");

	final String propertyName;

	AXRelationshipAttributes(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
