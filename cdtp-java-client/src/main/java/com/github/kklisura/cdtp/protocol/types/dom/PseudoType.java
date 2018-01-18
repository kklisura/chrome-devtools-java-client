package com.github.kklisura.cdtp.protocol.types.dom;

/**
 * Pseudo element type.
 */
public enum PseudoType {

	FIRST_LINE("first-line"), FIRST_LETTER("first-letter"), BEFORE("before"), AFTER("after"), BACKDROP("backdrop"), SELECTION("selection"), FIRST_LINE_INHERITED("first-line-inherited"), SCROLLBAR("scrollbar"), SCROLLBAR_THUMB("scrollbar-thumb"), SCROLLBAR_BUTTON("scrollbar-button"), SCROLLBAR_TRACK("scrollbar-track"), SCROLLBAR_TRACK_PIECE("scrollbar-track-piece"), SCROLLBAR_CORNER("scrollbar-corner"), RESIZER("resizer"), INPUT_LIST_BUTTON("input-list-button");

	final String propertyName;

	PseudoType(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
