package com.github.kklisura.cdtp.protocol.types.dom;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;

/**
 * Rectangle.
 */
@Experimental
public class Rect {

	private Double x;

	private Double y;

	private Double width;

	private Double height;

	/**
	 * X coordinate
	 */
	public Double getX() {
		return x;
	}

	/**
	 * X coordinate
	 */
	public void setX(Double x) {
		this.x = x;
	}

	/**
	 * Y coordinate
	 */
	public Double getY() {
		return y;
	}

	/**
	 * Y coordinate
	 */
	public void setY(Double y) {
		this.y = y;
	}

	/**
	 * Rectangle width
	 */
	public Double getWidth() {
		return width;
	}

	/**
	 * Rectangle width
	 */
	public void setWidth(Double width) {
		this.width = width;
	}

	/**
	 * Rectangle height
	 */
	public Double getHeight() {
		return height;
	}

	/**
	 * Rectangle height
	 */
	public void setHeight(Double height) {
		this.height = height;
	}
}
