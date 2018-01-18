package com.github.kklisura.cdtp.protocol.types.layertree;

/**
 * Reason for rectangle to force scrolling on the main thread
 */
public enum Type {

	REPAINTS_ON_SCROLL("RepaintsOnScroll"), TOUCH_EVENT_HANDLER("TouchEventHandler"), WHEEL_EVENT_HANDLER("WheelEventHandler");

	final String propertyName;

	Type(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
