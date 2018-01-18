package com.github.kklisura.cdtp.protocol.types.layertree;

import com.github.kklisura.cdtp.protocol.types.dom.Rect;
import com.github.kklisura.cdtp.protocol.annotations.Optional;

/**
 * Sticky position constraints.
 */
public class StickyPositionConstraint {

	private Rect stickyBoxRect;

	private Rect containingBlockRect;

	@Optional
	private String nearestLayerShiftingStickyBox;

	@Optional
	private String nearestLayerShiftingContainingBlock;

	/**
	 * Layout rectangle of the sticky element before being shifted
	 */
	public Rect getStickyBoxRect() {
		return stickyBoxRect;
	}

	/**
	 * Layout rectangle of the sticky element before being shifted
	 */
	public void setStickyBoxRect(Rect stickyBoxRect) {
		this.stickyBoxRect = stickyBoxRect;
	}

	/**
	 * Layout rectangle of the containing block of the sticky element
	 */
	public Rect getContainingBlockRect() {
		return containingBlockRect;
	}

	/**
	 * Layout rectangle of the containing block of the sticky element
	 */
	public void setContainingBlockRect(Rect containingBlockRect) {
		this.containingBlockRect = containingBlockRect;
	}

	/**
	 * The nearest sticky layer that shifts the sticky box
	 */
	public String getNearestLayerShiftingStickyBox() {
		return nearestLayerShiftingStickyBox;
	}

	/**
	 * The nearest sticky layer that shifts the sticky box
	 */
	public void setNearestLayerShiftingStickyBox(String nearestLayerShiftingStickyBox) {
		this.nearestLayerShiftingStickyBox = nearestLayerShiftingStickyBox;
	}

	/**
	 * The nearest sticky layer that shifts the containing block
	 */
	public String getNearestLayerShiftingContainingBlock() {
		return nearestLayerShiftingContainingBlock;
	}

	/**
	 * The nearest sticky layer that shifts the containing block
	 */
	public void setNearestLayerShiftingContainingBlock(String nearestLayerShiftingContainingBlock) {
		this.nearestLayerShiftingContainingBlock = nearestLayerShiftingContainingBlock;
	}
}
