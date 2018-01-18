package com.github.kklisura.cdtp.protocol.types.css;

import com.github.kklisura.cdtp.protocol.annotations.Optional;

public class InlineStylesForNode {

	@Optional
	private CSSStyle inlineStyle;

	@Optional
	private CSSStyle attributesStyle;

	/**
	 * Inline style for the specified DOM node.
	 */
	public CSSStyle getInlineStyle() {
		return inlineStyle;
	}

	/**
	 * Inline style for the specified DOM node.
	 */
	public void setInlineStyle(CSSStyle inlineStyle) {
		this.inlineStyle = inlineStyle;
	}

	/**
	 * Attribute-defined element style (e.g. resulting from "width=20 height=100%").
	 */
	public CSSStyle getAttributesStyle() {
		return attributesStyle;
	}

	/**
	 * Attribute-defined element style (e.g. resulting from "width=20 height=100%").
	 */
	public void setAttributesStyle(CSSStyle attributesStyle) {
		this.attributesStyle = attributesStyle;
	}
}
