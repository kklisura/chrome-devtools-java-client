package com.github.kklisura.cdtp.protocol.types.accessibility;

/**
 * Attributes which apply to nodes in live regions.
 */
public enum AXLiveRegionAttributes {

	LIVE("live"), ATOMIC("atomic"), RELEVANT("relevant"), ROOT("root");

	final String propertyName;

	AXLiveRegionAttributes(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
