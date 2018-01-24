package com.github.kklisura.cdtp.protocol.types.css;

import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import java.util.List;

public class BackgroundColors {

	@Optional
	private List<String> backgroundColors;

	@Optional
	private String computedFontSize;

	@Optional
	private String computedFontWeight;

	@Optional
	private String computedBodyFontSize;

	/**
	 * The range of background colors behind this element, if it contains any visible text. If no visible text is present, this will be undefined. In the case of a flat background color, this will consist of simply that color. In the case of a gradient, this will consist of each of the color stops. For anything more complicated, this will be an empty array. Images will be ignored (as if the image had failed to load).
	 */
	public List<String> getBackgroundColors() {
		return backgroundColors;
	}

	/**
	 * The range of background colors behind this element, if it contains any visible text. If no visible text is present, this will be undefined. In the case of a flat background color, this will consist of simply that color. In the case of a gradient, this will consist of each of the color stops. For anything more complicated, this will be an empty array. Images will be ignored (as if the image had failed to load).
	 */
	public void setBackgroundColors(List<String> backgroundColors) {
		this.backgroundColors = backgroundColors;
	}

	/**
	 * The computed font size for this node, as a CSS computed value string (e.g. '12px').
	 */
	public String getComputedFontSize() {
		return computedFontSize;
	}

	/**
	 * The computed font size for this node, as a CSS computed value string (e.g. '12px').
	 */
	public void setComputedFontSize(String computedFontSize) {
		this.computedFontSize = computedFontSize;
	}

	/**
	 * The computed font weight for this node, as a CSS computed value string (e.g. 'normal' or '100').
	 */
	public String getComputedFontWeight() {
		return computedFontWeight;
	}

	/**
	 * The computed font weight for this node, as a CSS computed value string (e.g. 'normal' or '100').
	 */
	public void setComputedFontWeight(String computedFontWeight) {
		this.computedFontWeight = computedFontWeight;
	}

	/**
	 * The computed font size for the document body, as a computed CSS value string (e.g. '16px').
	 */
	public String getComputedBodyFontSize() {
		return computedBodyFontSize;
	}

	/**
	 * The computed font size for the document body, as a computed CSS value string (e.g. '16px').
	 */
	public void setComputedBodyFontSize(String computedBodyFontSize) {
		this.computedBodyFontSize = computedBodyFontSize;
	}
}
