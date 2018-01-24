package com.github.kklisura.cdtp.protocol.types.runtime;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.Optional;

@Experimental
public class PropertyPreview {

	private String name;

	private Type type;

	@Optional
	private String value;

	@Optional
	private ObjectPreview valuePreview;

	@Optional
	private Subtype subtype;

	/**
	 * Property name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Property name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Object type. Accessor means that the property itself is an accessor property.
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Object type. Accessor means that the property itself is an accessor property.
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * User-friendly property value string.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * User-friendly property value string.
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Nested value preview.
	 */
	public ObjectPreview getValuePreview() {
		return valuePreview;
	}

	/**
	 * Nested value preview.
	 */
	public void setValuePreview(ObjectPreview valuePreview) {
		this.valuePreview = valuePreview;
	}

	/**
	 * Object subtype hint. Specified for <code>object</code> type values only.
	 */
	public Subtype getSubtype() {
		return subtype;
	}

	/**
	 * Object subtype hint. Specified for <code>object</code> type values only.
	 */
	public void setSubtype(Subtype subtype) {
		this.subtype = subtype;
	}
}
