package com.github.kklisura.cdtp.protocol.types.overlay;

public enum InspectMode {

	SEARCH_FOR_NODE("searchForNode"), SEARCH_FOR_UA_SHADOW_DOM("searchForUAShadowDOM"), NONE("none");

	final String propertyName;

	InspectMode(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
