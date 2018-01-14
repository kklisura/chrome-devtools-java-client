package com.github.kklisura.cdtp.protocol.types.indexeddb;

import com.github.kklisura.cdtp.protocol.types.runtime.RemoteObject;

/**
 * Data entry.
 */
public class DataEntry {

	private RemoteObject key;

	private RemoteObject primaryKey;

	private RemoteObject value;

	/**
	 * Key object.
	 */
	public RemoteObject getKey() {
		return key;
	}

	/**
	 * Key object.
	 */
	public void setKey(RemoteObject key) {
		this.key = key;
	}

	/**
	 * Primary key object.
	 */
	public RemoteObject getPrimaryKey() {
		return primaryKey;
	}

	/**
	 * Primary key object.
	 */
	public void setPrimaryKey(RemoteObject primaryKey) {
		this.primaryKey = primaryKey;
	}

	/**
	 * Value object.
	 */
	public RemoteObject getValue() {
		return value;
	}

	/**
	 * Value object.
	 */
	public void setValue(RemoteObject value) {
		this.value = value;
	}
}
