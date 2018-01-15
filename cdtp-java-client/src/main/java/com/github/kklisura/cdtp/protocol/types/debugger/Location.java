package com.github.kklisura.cdtp.protocol.types.debugger;

import com.github.kklisura.cdtp.protocol.annotations.Optional;

/**
 * Location in the source code.
 */
public class Location {

	private String scriptId;

	private Integer lineNumber;

	@Optional
	private Integer columnNumber;

	/**
	 * Script identifier as reported in the `Debugger.scriptParsed`.
	 */
	public String getScriptId() {
		return scriptId;
	}

	/**
	 * Script identifier as reported in the `Debugger.scriptParsed`.
	 */
	public void setScriptId(String scriptId) {
		this.scriptId = scriptId;
	}

	/**
	 * Line number in the script (0-based).
	 */
	public Integer getLineNumber() {
		return lineNumber;
	}

	/**
	 * Line number in the script (0-based).
	 */
	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}

	/**
	 * Column number in the script (0-based).
	 */
	public Integer getColumnNumber() {
		return columnNumber;
	}

	/**
	 * Column number in the script (0-based).
	 */
	public void setColumnNumber(Integer columnNumber) {
		this.columnNumber = columnNumber;
	}
}
