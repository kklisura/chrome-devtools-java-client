package com.github.kklisura.cdtp.protocol.types.css;

import com.github.kklisura.cdtp.protocol.support.annotations.Optional;

/**
 * Data for a simple selector (these are delimited by commas in a selector list).
 */
public class Value {

	private String text;

	@Optional
	private SourceRange range;

	/**
	 * Value text.
	 */
	public String getText() {
		return text;
	}

	/**
	 * Value text.
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Value range in the underlying resource (if available).
	 */
	public SourceRange getRange() {
		return range;
	}

	/**
	 * Value range in the underlying resource (if available).
	 */
	public void setRange(SourceRange range) {
		this.range = range;
	}
}
