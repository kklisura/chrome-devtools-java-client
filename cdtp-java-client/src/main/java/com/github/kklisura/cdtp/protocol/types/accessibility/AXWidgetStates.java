package com.github.kklisura.cdtp.protocol.types.accessibility;

/**
 * States which apply to widgets.
 */
public enum AXWidgetStates {

	CHECKED("checked"), EXPANDED("expanded"), MODAL("modal"), PRESSED("pressed"), SELECTED("selected");

	final String propertyName;

	AXWidgetStates(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
