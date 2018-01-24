package com.github.kklisura.cdtp.protocol.types.dom;

import com.github.kklisura.cdtp.protocol.support.annotations.Optional;

/**
 * A structure holding an RGBA color.
 */
public class RGBA {

	private Integer r;

	private Integer g;

	private Integer b;

	@Optional
	private Double a;

	/**
	 * The red component, in the [0-255] range.
	 */
	public Integer getR() {
		return r;
	}

	/**
	 * The red component, in the [0-255] range.
	 */
	public void setR(Integer r) {
		this.r = r;
	}

	/**
	 * The green component, in the [0-255] range.
	 */
	public Integer getG() {
		return g;
	}

	/**
	 * The green component, in the [0-255] range.
	 */
	public void setG(Integer g) {
		this.g = g;
	}

	/**
	 * The blue component, in the [0-255] range.
	 */
	public Integer getB() {
		return b;
	}

	/**
	 * The blue component, in the [0-255] range.
	 */
	public void setB(Integer b) {
		this.b = b;
	}

	/**
	 * The alpha component, in the [0-1] range (default: 1).
	 */
	public Double getA() {
		return a;
	}

	/**
	 * The alpha component, in the [0-1] range (default: 1).
	 */
	public void setA(Double a) {
		this.a = a;
	}
}
