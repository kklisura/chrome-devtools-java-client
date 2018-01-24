package com.github.kklisura.cdtp.protocol.types.dom;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import java.util.List;

/**
 * CSS Shape Outside details.
 */
@Experimental
public class ShapeOutsideInfo {

	private List<Double> bounds;

	private List<Object> shape;

	private List<Object> marginShape;

	/**
	 * Shape bounds
	 */
	public List<Double> getBounds() {
		return bounds;
	}

	/**
	 * Shape bounds
	 */
	public void setBounds(List<Double> bounds) {
		this.bounds = bounds;
	}

	/**
	 * Shape coordinate details
	 */
	public List<Object> getShape() {
		return shape;
	}

	/**
	 * Shape coordinate details
	 */
	public void setShape(List<Object> shape) {
		this.shape = shape;
	}

	/**
	 * Margin shape bounds
	 */
	public List<Object> getMarginShape() {
		return marginShape;
	}

	/**
	 * Margin shape bounds
	 */
	public void setMarginShape(List<Object> marginShape) {
		this.marginShape = marginShape;
	}
}
