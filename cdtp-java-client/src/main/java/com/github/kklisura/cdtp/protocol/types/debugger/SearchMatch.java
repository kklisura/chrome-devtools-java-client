package com.github.kklisura.cdtp.protocol.types.debugger;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;

/**
 * Search match for resource.
 */
@Experimental
public class SearchMatch {

	private Double lineNumber;

	private String lineContent;

	/**
	 * Line number in resource content.
	 */
	public Double getLineNumber() {
		return lineNumber;
	}

	/**
	 * Line number in resource content.
	 */
	public void setLineNumber(Double lineNumber) {
		this.lineNumber = lineNumber;
	}

	/**
	 * Line with match content.
	 */
	public String getLineContent() {
		return lineContent;
	}

	/**
	 * Line with match content.
	 */
	public void setLineContent(String lineContent) {
		this.lineContent = lineContent;
	}
}
