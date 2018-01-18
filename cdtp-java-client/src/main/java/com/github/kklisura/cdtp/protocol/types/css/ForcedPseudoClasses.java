package com.github.kklisura.cdtp.protocol.types.css;

public enum ForcedPseudoClasses {

	ACTIVE("active"), FOCUS("focus"), HOVER("hover"), VISITED("visited");

	final String propertyName;

	ForcedPseudoClasses(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
