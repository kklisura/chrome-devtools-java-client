package com.github.kklisura.cdtp.protocol.types.page;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;

/**
 * Error while paring app manifest.
 */
@Experimental
public class AppManifestError {

	private String message;

	private Integer critical;

	private Integer line;

	private Integer column;

	/**
	 * Error message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Error message.
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * If criticial, this is a non-recoverable parse error.
	 */
	public Integer getCritical() {
		return critical;
	}

	/**
	 * If criticial, this is a non-recoverable parse error.
	 */
	public void setCritical(Integer critical) {
		this.critical = critical;
	}

	/**
	 * Error line.
	 */
	public Integer getLine() {
		return line;
	}

	/**
	 * Error line.
	 */
	public void setLine(Integer line) {
		this.line = line;
	}

	/**
	 * Error column.
	 */
	public Integer getColumn() {
		return column;
	}

	/**
	 * Error column.
	 */
	public void setColumn(Integer column) {
		this.column = column;
	}
}
