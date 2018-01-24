package com.github.kklisura.cdtp.protocol.types.indexeddb;

import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import java.util.List;

/**
 * Key.
 */
public class Key {

	private Type type;

	@Optional
	private Double number;

	@Optional
	private String string;

	@Optional
	private Double date;

	@Optional
	private List<com.github.kklisura.cdtp.protocol.types.indexeddb.Key> array;

	/**
	 * Key type.
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Key type.
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * Number value.
	 */
	public Double getNumber() {
		return number;
	}

	/**
	 * Number value.
	 */
	public void setNumber(Double number) {
		this.number = number;
	}

	/**
	 * String value.
	 */
	public String getString() {
		return string;
	}

	/**
	 * String value.
	 */
	public void setString(String string) {
		this.string = string;
	}

	/**
	 * Date value.
	 */
	public Double getDate() {
		return date;
	}

	/**
	 * Date value.
	 */
	public void setDate(Double date) {
		this.date = date;
	}

	/**
	 * Array value.
	 */
	public List<com.github.kklisura.cdtp.protocol.types.indexeddb.Key> getArray() {
		return array;
	}

	/**
	 * Array value.
	 */
	public void setArray(List<com.github.kklisura.cdtp.protocol.types.indexeddb.Key> array) {
		this.array = array;
	}
}
