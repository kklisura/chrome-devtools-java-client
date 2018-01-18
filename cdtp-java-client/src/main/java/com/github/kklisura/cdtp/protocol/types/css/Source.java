package com.github.kklisura.cdtp.protocol.types.css;

/**
 * Source of the media query: "mediaRule" if specified by a @media rule, "importRule" if specified by an @import rule, "linkedSheet" if specified by a "media" attribute in a linked stylesheet's LINK tag, "inlineSheet" if specified by a "media" attribute in an inline stylesheet's STYLE tag.
 */
public enum Source {

	MEDIA_RULE("mediaRule"), IMPORT_RULE("importRule"), LINKED_SHEET("linkedSheet"), INLINE_SHEET("inlineSheet");

	final String propertyName;

	Source(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
