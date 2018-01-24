package com.github.kklisura.cdtp.protocol.types.css;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.Optional;

/**
 * Media query expression descriptor.
 */
@Experimental
public class MediaQueryExpression {

	private Double value;

	private String unit;

	private String feature;

	@Optional
	private SourceRange valueRange;

	@Optional
	private Double computedLength;

	/**
	 * Media query expression value.
	 */
	public Double getValue() {
		return value;
	}

	/**
	 * Media query expression value.
	 */
	public void setValue(Double value) {
		this.value = value;
	}

	/**
	 * Media query expression units.
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * Media query expression units.
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * Media query expression feature.
	 */
	public String getFeature() {
		return feature;
	}

	/**
	 * Media query expression feature.
	 */
	public void setFeature(String feature) {
		this.feature = feature;
	}

	/**
	 * The associated range of the value text in the enclosing stylesheet (if available).
	 */
	public SourceRange getValueRange() {
		return valueRange;
	}

	/**
	 * The associated range of the value text in the enclosing stylesheet (if available).
	 */
	public void setValueRange(SourceRange valueRange) {
		this.valueRange = valueRange;
	}

	/**
	 * Computed length of media query expression (if applicable).
	 */
	public Double getComputedLength() {
		return computedLength;
	}

	/**
	 * Computed length of media query expression (if applicable).
	 */
	public void setComputedLength(Double computedLength) {
		this.computedLength = computedLength;
	}
}
