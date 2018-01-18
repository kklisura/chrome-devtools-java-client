package com.github.kklisura.cdtp.protocol.types.database;

/**
 * Database error.
 */
public class Error {

	private String message;

	private Integer code;

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
	 * Error code.
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * Error code.
	 */
	public void setCode(Integer code) {
		this.code = code;
	}
}
