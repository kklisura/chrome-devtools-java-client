package com.github.kklisura.cdtp.protocol.types.debugger;

public enum TargetCallFrames {

	ANY("any"), CURRENT("current");

	final String propertyName;

	TargetCallFrames(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
