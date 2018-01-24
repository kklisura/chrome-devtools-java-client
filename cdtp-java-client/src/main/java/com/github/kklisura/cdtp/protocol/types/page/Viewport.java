package com.github.kklisura.cdtp.protocol.types.page;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;

/**
 * Viewport for capturing screenshot.
 */
@Experimental
public class Viewport {

	private Double x;

	private Double y;

	private Double width;

	private Double height;

	private Double scale;

	/**
	 * X offset in CSS pixels.
	 */
	public Double getX() {
		return x;
	}

	/**
	 * X offset in CSS pixels.
	 */
	public void setX(Double x) {
		this.x = x;
	}

	/**
	 * Y offset in CSS pixels
	 */
	public Double getY() {
		return y;
	}

	/**
	 * Y offset in CSS pixels
	 */
	public void setY(Double y) {
		this.y = y;
	}

	/**
	 * Rectangle width in CSS pixels
	 */
	public Double getWidth() {
		return width;
	}

	/**
	 * Rectangle width in CSS pixels
	 */
	public void setWidth(Double width) {
		this.width = width;
	}

	/**
	 * Rectangle height in CSS pixels
	 */
	public Double getHeight() {
		return height;
	}

	/**
	 * Rectangle height in CSS pixels
	 */
	public void setHeight(Double height) {
		this.height = height;
	}

	/**
	 * Page scale factor.
	 */
	public Double getScale() {
		return scale;
	}

	/**
	 * Page scale factor.
	 */
	public void setScale(Double scale) {
		this.scale = scale;
	}
}
