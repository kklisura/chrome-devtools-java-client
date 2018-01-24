package com.github.kklisura.cdtp.protocol.types.overlay;

import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import com.github.kklisura.cdtp.protocol.types.dom.RGBA;

/**
 * Configuration data for the highlighting of page elements.
 */
public class HighlightConfig {

	@Optional
	private Boolean showInfo;

	@Optional
	private Boolean showRulers;

	@Optional
	private Boolean showExtensionLines;

	@Optional
	private Boolean displayAsMaterial;

	@Optional
	private RGBA contentColor;

	@Optional
	private RGBA paddingColor;

	@Optional
	private RGBA borderColor;

	@Optional
	private RGBA marginColor;

	@Optional
	private RGBA eventTargetColor;

	@Optional
	private RGBA shapeColor;

	@Optional
	private RGBA shapeMarginColor;

	@Optional
	private String selectorList;

	@Optional
	private RGBA cssGridColor;

	/**
	 * Whether the node info tooltip should be shown (default: false).
	 */
	public Boolean getShowInfo() {
		return showInfo;
	}

	/**
	 * Whether the node info tooltip should be shown (default: false).
	 */
	public void setShowInfo(Boolean showInfo) {
		this.showInfo = showInfo;
	}

	/**
	 * Whether the rulers should be shown (default: false).
	 */
	public Boolean getShowRulers() {
		return showRulers;
	}

	/**
	 * Whether the rulers should be shown (default: false).
	 */
	public void setShowRulers(Boolean showRulers) {
		this.showRulers = showRulers;
	}

	/**
	 * Whether the extension lines from node to the rulers should be shown (default: false).
	 */
	public Boolean getShowExtensionLines() {
		return showExtensionLines;
	}

	/**
	 * Whether the extension lines from node to the rulers should be shown (default: false).
	 */
	public void setShowExtensionLines(Boolean showExtensionLines) {
		this.showExtensionLines = showExtensionLines;
	}

	public Boolean getDisplayAsMaterial() {
		return displayAsMaterial;
	}

	public void setDisplayAsMaterial(Boolean displayAsMaterial) {
		this.displayAsMaterial = displayAsMaterial;
	}

	/**
	 * The content box highlight fill color (default: transparent).
	 */
	public RGBA getContentColor() {
		return contentColor;
	}

	/**
	 * The content box highlight fill color (default: transparent).
	 */
	public void setContentColor(RGBA contentColor) {
		this.contentColor = contentColor;
	}

	/**
	 * The padding highlight fill color (default: transparent).
	 */
	public RGBA getPaddingColor() {
		return paddingColor;
	}

	/**
	 * The padding highlight fill color (default: transparent).
	 */
	public void setPaddingColor(RGBA paddingColor) {
		this.paddingColor = paddingColor;
	}

	/**
	 * The border highlight fill color (default: transparent).
	 */
	public RGBA getBorderColor() {
		return borderColor;
	}

	/**
	 * The border highlight fill color (default: transparent).
	 */
	public void setBorderColor(RGBA borderColor) {
		this.borderColor = borderColor;
	}

	/**
	 * The margin highlight fill color (default: transparent).
	 */
	public RGBA getMarginColor() {
		return marginColor;
	}

	/**
	 * The margin highlight fill color (default: transparent).
	 */
	public void setMarginColor(RGBA marginColor) {
		this.marginColor = marginColor;
	}

	/**
	 * The event target element highlight fill color (default: transparent).
	 */
	public RGBA getEventTargetColor() {
		return eventTargetColor;
	}

	/**
	 * The event target element highlight fill color (default: transparent).
	 */
	public void setEventTargetColor(RGBA eventTargetColor) {
		this.eventTargetColor = eventTargetColor;
	}

	/**
	 * The shape outside fill color (default: transparent).
	 */
	public RGBA getShapeColor() {
		return shapeColor;
	}

	/**
	 * The shape outside fill color (default: transparent).
	 */
	public void setShapeColor(RGBA shapeColor) {
		this.shapeColor = shapeColor;
	}

	/**
	 * The shape margin fill color (default: transparent).
	 */
	public RGBA getShapeMarginColor() {
		return shapeMarginColor;
	}

	/**
	 * The shape margin fill color (default: transparent).
	 */
	public void setShapeMarginColor(RGBA shapeMarginColor) {
		this.shapeMarginColor = shapeMarginColor;
	}

	/**
	 * Selectors to highlight relevant nodes.
	 */
	public String getSelectorList() {
		return selectorList;
	}

	/**
	 * Selectors to highlight relevant nodes.
	 */
	public void setSelectorList(String selectorList) {
		this.selectorList = selectorList;
	}

	/**
	 * The grid layout color (default: transparent).
	 */
	public RGBA getCssGridColor() {
		return cssGridColor;
	}

	/**
	 * The grid layout color (default: transparent).
	 */
	public void setCssGridColor(RGBA cssGridColor) {
		this.cssGridColor = cssGridColor;
	}
}
