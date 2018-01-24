package com.github.kklisura.cdtp.protocol.types.page;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;

/**
 * Visual viewport position, dimensions, and scale.
 */
@Experimental
public class VisualViewport {

	private Double offsetX;

	private Double offsetY;

	private Double pageX;

	private Double pageY;

	private Double clientWidth;

	private Double clientHeight;

	private Double scale;

	/**
	 * Horizontal offset relative to the layout viewport (CSS pixels).
	 */
	public Double getOffsetX() {
		return offsetX;
	}

	/**
	 * Horizontal offset relative to the layout viewport (CSS pixels).
	 */
	public void setOffsetX(Double offsetX) {
		this.offsetX = offsetX;
	}

	/**
	 * Vertical offset relative to the layout viewport (CSS pixels).
	 */
	public Double getOffsetY() {
		return offsetY;
	}

	/**
	 * Vertical offset relative to the layout viewport (CSS pixels).
	 */
	public void setOffsetY(Double offsetY) {
		this.offsetY = offsetY;
	}

	/**
	 * Horizontal offset relative to the document (CSS pixels).
	 */
	public Double getPageX() {
		return pageX;
	}

	/**
	 * Horizontal offset relative to the document (CSS pixels).
	 */
	public void setPageX(Double pageX) {
		this.pageX = pageX;
	}

	/**
	 * Vertical offset relative to the document (CSS pixels).
	 */
	public Double getPageY() {
		return pageY;
	}

	/**
	 * Vertical offset relative to the document (CSS pixels).
	 */
	public void setPageY(Double pageY) {
		this.pageY = pageY;
	}

	/**
	 * Width (CSS pixels), excludes scrollbar if present.
	 */
	public Double getClientWidth() {
		return clientWidth;
	}

	/**
	 * Width (CSS pixels), excludes scrollbar if present.
	 */
	public void setClientWidth(Double clientWidth) {
		this.clientWidth = clientWidth;
	}

	/**
	 * Height (CSS pixels), excludes scrollbar if present.
	 */
	public Double getClientHeight() {
		return clientHeight;
	}

	/**
	 * Height (CSS pixels), excludes scrollbar if present.
	 */
	public void setClientHeight(Double clientHeight) {
		this.clientHeight = clientHeight;
	}

	/**
	 * Scale relative to the ideal viewport (size at width=device-width).
	 */
	public Double getScale() {
		return scale;
	}

	/**
	 * Scale relative to the ideal viewport (size at width=device-width).
	 */
	public void setScale(Double scale) {
		this.scale = scale;
	}
}
