package com.github.kklisura.cdtp.protocol.types.css;

/**
 * A descriptor of operation to mutate style declaration text.
 */
public class StyleDeclarationEdit {

	private String styleSheetId;

	private SourceRange range;

	private String text;

	/**
	 * The css style sheet identifier.
	 */
	public String getStyleSheetId() {
		return styleSheetId;
	}

	/**
	 * The css style sheet identifier.
	 */
	public void setStyleSheetId(String styleSheetId) {
		this.styleSheetId = styleSheetId;
	}

	/**
	 * The range of the style text in the enclosing stylesheet.
	 */
	public SourceRange getRange() {
		return range;
	}

	/**
	 * The range of the style text in the enclosing stylesheet.
	 */
	public void setRange(SourceRange range) {
		this.range = range;
	}

	/**
	 * New style text.
	 */
	public String getText() {
		return text;
	}

	/**
	 * New style text.
	 */
	public void setText(String text) {
		this.text = text;
	}
}
