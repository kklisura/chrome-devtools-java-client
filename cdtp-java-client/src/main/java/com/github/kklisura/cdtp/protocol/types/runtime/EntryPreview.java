package com.github.kklisura.cdtp.protocol.types.runtime;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.Optional;

@Experimental
public class EntryPreview {

	@Optional
	private ObjectPreview key;

	private ObjectPreview value;

	/**
	 * Preview of the key. Specified for map-like collection entries.
	 */
	public ObjectPreview getKey() {
		return key;
	}

	/**
	 * Preview of the key. Specified for map-like collection entries.
	 */
	public void setKey(ObjectPreview key) {
		this.key = key;
	}

	/**
	 * Preview of the value.
	 */
	public ObjectPreview getValue() {
		return value;
	}

	/**
	 * Preview of the value.
	 */
	public void setValue(ObjectPreview value) {
		this.value = value;
	}
}
