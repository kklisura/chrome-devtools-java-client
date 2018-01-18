package com.github.kklisura.cdtp.protocol.events.css;

import com.github.kklisura.cdtp.protocol.types.css.CSSStyleSheetHeader;

/**
 * Fired whenever an active document stylesheet is added.
 */
public class StyleSheetAdded {

	private CSSStyleSheetHeader header;

	/**
	 * Added stylesheet metainfo.
	 */
	public CSSStyleSheetHeader getHeader() {
		return header;
	}

	/**
	 * Added stylesheet metainfo.
	 */
	public void setHeader(CSSStyleSheetHeader header) {
		this.header = header;
	}
}
