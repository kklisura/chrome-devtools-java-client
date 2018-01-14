package com.github.kklisura.cdtp.protocol.types.accessibility;

/**
 * Enum of possible native property sources (as a subtype of a particular AXValueSourceType).
 */
public enum AXValueNativeSourceType {

	FIGCAPTION("figcaption"), LABEL("label"), LABELFOR("labelfor"), LABELWRAPPED("labelwrapped"), LEGEND("legend"), TABLECAPTION("tablecaption"), TITLE("title"), OTHER("other");

	final String propertyName;

	AXValueNativeSourceType(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
