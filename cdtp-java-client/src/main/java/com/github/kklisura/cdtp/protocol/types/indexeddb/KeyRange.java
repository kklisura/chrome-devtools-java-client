package com.github.kklisura.cdtp.protocol.types.indexeddb;

import com.github.kklisura.cdtp.protocol.annotations.Optional;

/**
 * Key range.
 */
public class KeyRange {

	@Optional
	private Key lower;

	@Optional
	private Key upper;

	private Boolean lowerOpen;

	private Boolean upperOpen;

	/**
	 * Lower bound.
	 */
	public Key getLower() {
		return lower;
	}

	/**
	 * Lower bound.
	 */
	public void setLower(Key lower) {
		this.lower = lower;
	}

	/**
	 * Upper bound.
	 */
	public Key getUpper() {
		return upper;
	}

	/**
	 * Upper bound.
	 */
	public void setUpper(Key upper) {
		this.upper = upper;
	}

	/**
	 * If true lower bound is open.
	 */
	public Boolean getLowerOpen() {
		return lowerOpen;
	}

	/**
	 * If true lower bound is open.
	 */
	public void setLowerOpen(Boolean lowerOpen) {
		this.lowerOpen = lowerOpen;
	}

	/**
	 * If true upper bound is open.
	 */
	public Boolean getUpperOpen() {
		return upperOpen;
	}

	/**
	 * If true upper bound is open.
	 */
	public void setUpperOpen(Boolean upperOpen) {
		this.upperOpen = upperOpen;
	}
}
