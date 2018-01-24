package com.github.kklisura.cdtp.protocol.types.css;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;

/**
 * Information about amount of glyphs that were rendered with given font.
 */
@Experimental
public class PlatformFontUsage {

	private String familyName;

	private Boolean isCustomFont;

	private Double glyphCount;

	/**
	 * Font's family name reported by platform.
	 */
	public String getFamilyName() {
		return familyName;
	}

	/**
	 * Font's family name reported by platform.
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	/**
	 * Indicates if the font was downloaded or resolved locally.
	 */
	public Boolean getIsCustomFont() {
		return isCustomFont;
	}

	/**
	 * Indicates if the font was downloaded or resolved locally.
	 */
	public void setIsCustomFont(Boolean isCustomFont) {
		this.isCustomFont = isCustomFont;
	}

	/**
	 * Amount of glyphs that were rendered with this font.
	 */
	public Double getGlyphCount() {
		return glyphCount;
	}

	/**
	 * Amount of glyphs that were rendered with this font.
	 */
	public void setGlyphCount(Double glyphCount) {
		this.glyphCount = glyphCount;
	}
}
