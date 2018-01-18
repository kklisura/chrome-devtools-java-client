package com.github.kklisura.cdtp.protocol.types.debugger;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.Optional;

@Experimental
public class BreakLocation {

	private String scriptId;

	private Integer lineNumber;

	@Optional
	private Integer columnNumber;

	@Optional
	private Type type;

	/**
	 * Script identifier as reported in the <code>Debugger.scriptParsed</code>.
	 */
	public String getScriptId() {
		return scriptId;
	}

	/**
	 * Script identifier as reported in the <code>Debugger.scriptParsed</code>.
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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}
