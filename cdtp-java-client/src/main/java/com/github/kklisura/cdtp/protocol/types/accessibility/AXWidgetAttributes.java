package com.github.kklisura.cdtp.protocol.types.accessibility;

/**
 * Attributes which apply to widgets.
 */
public enum AXWidgetAttributes {

	AUTOCOMPLETE("autocomplete"), HASPOPUP("haspopup"), LEVEL("level"), MULTISELECTABLE("multiselectable"), ORIENTATION("orientation"), MULTILINE("multiline"), READONLY("readonly"), REQUIRED("required"), VALUEMIN("valuemin"), VALUEMAX("valuemax"), VALUETEXT("valuetext");

	final String propertyName;

	AXWidgetAttributes(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
