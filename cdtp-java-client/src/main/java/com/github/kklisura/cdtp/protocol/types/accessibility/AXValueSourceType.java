package com.github.kklisura.cdtp.protocol.types.accessibility;

/**
 * Enum of possible property sources.
 */
public enum AXValueSourceType {

	ATTRIBUTE("attribute"), IMPLICIT("implicit"), STYLE("style"), CONTENTS("contents"), PLACEHOLDER("placeholder"), RELATED_ELEMENT("relatedElement");

	final String propertyName;

	AXValueSourceType(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
