package com.github.kklisura.cdtp.protocol.types.indexeddb;

import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import java.util.List;

/**
 * Key path.
 */
public class KeyPath {

	private Type type;

	@Optional
	private String string;

	@Optional
	private List<String> array;

	/**
	 * Key path type.
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Key path type.
	 */
	public void setType(Type type) {
		this.type = type;
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
	 * Array value.
	 */
	public List<String> getArray() {
		return array;
	}

	/**
	 * Array value.
	 */
	public void setArray(List<String> array) {
		this.array = array;
	}
}
