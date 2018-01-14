package com.github.kklisura.cdtp.protocol.types.debugger;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;

/**
 * Location in the source code.
 */
@Experimental
public class ScriptPosition {

	private Integer lineNumber;

	private Integer columnNumber;

	public Integer getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}

	public Integer getColumnNumber() {
		return columnNumber;
	}

	public void setColumnNumber(Integer columnNumber) {
		this.columnNumber = columnNumber;
	}
}
