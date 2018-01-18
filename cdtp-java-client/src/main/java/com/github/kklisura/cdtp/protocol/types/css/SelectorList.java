package com.github.kklisura.cdtp.protocol.types.css;

import java.util.List;

/**
 * Selector list data.
 */
public class SelectorList {

	private List<Value> selectors;

	private String text;

	/**
	 * Selectors in the list.
	 */
	public List<Value> getSelectors() {
		return selectors;
	}

	/**
	 * Selectors in the list.
	 */
	public void setSelectors(List<Value> selectors) {
		this.selectors = selectors;
	}

	/**
	 * Rule selector text.
	 */
	public String getText() {
		return text;
	}

	/**
	 * Rule selector text.
	 */
	public void setText(String text) {
		this.text = text;
	}
}
