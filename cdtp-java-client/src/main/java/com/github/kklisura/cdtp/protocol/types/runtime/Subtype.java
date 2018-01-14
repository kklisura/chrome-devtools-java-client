package com.github.kklisura.cdtp.protocol.types.runtime;

/**
 * Object subtype hint. Specified for <code>object</code> type values only.
 */
public enum Subtype {

	ARRAY("array"), NULL("null"), NODE("node"), REGEXP("regexp"), DATE("date"), MAP("map"), SET("set"), WEAKMAP("weakmap"), WEAKSET("weakset"), ITERATOR("iterator"), GENERATOR("generator"), ERROR("error");

	final String propertyName;

	Subtype(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
