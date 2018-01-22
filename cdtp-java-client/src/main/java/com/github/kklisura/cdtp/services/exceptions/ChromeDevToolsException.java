package com.github.kklisura.cdtp.services.exceptions;

/**
 * Chrome dev tools exception. Info consists of code and a message.
 *
 * @author Kenan Klisura
 */
public class ChromeDevToolsException extends Exception {
	private Long code = -1L;

	/**
	 * Instantiates a new Chrome dev tools exception.
	 *
	 * @param code    Error code.
	 * @param message Error message.
	 */
	public ChromeDevToolsException(Long code, String message) {
		super(message);
		this.code = code;
	}

	/**
	 * Instantiates a new Chrome dev tools exception.
	 *
	 * @param message Error message.
	 * @param cause   Exception cause.
	 */
	public ChromeDevToolsException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Gets the error code.
	 *
	 * @return Error code.
	 */
	public Long getCode() {
		return code;
	}
}
