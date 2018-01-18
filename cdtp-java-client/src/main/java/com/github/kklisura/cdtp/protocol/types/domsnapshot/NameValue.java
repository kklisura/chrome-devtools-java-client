package com.github.kklisura.cdtp.protocol.types.domsnapshot;

/**
 * A name/value pair.
 */
public class NameValue {

	private String name;

	private String value;

	/**
	 * Attribute/property name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Attribute/property name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Attribute/property value.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Attribute/property value.
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
