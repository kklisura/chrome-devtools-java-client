package com.github.kklisura.cdtp.protocol.types.accessibility;

public class AXProperty {

	private String name;

	private AXValue value;

	/**
	 * The name of this property.
	 */
	public String getName() {
		return name;
	}

	/**
	 * The name of this property.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * The value of this property.
	 */
	public AXValue getValue() {
		return value;
	}

	/**
	 * The value of this property.
	 */
	public void setValue(AXValue value) {
		this.value = value;
	}
}
