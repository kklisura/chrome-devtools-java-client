package com.github.kklisura.cdtp.services.exceptions;

/**
 * Invocation exception.
 *
 * @author Kenan Klisura
 */
public class ChromeDevToolsInvocationException extends RuntimeException {
	private Long code = -1L;

	/**
	 * Instantiates a new Chrome dev tools invocation exception.
	 *
	 * @param message Exception message.
	 */
	public ChromeDevToolsInvocationException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new Chrome dev tools invocation exception.
	 *
	 * @param code    Exception code.
	 * @param message Exception message.
	 */
	public ChromeDevToolsInvocationException(Long code, String message) {
		super(message);
		this.code = code;
	}

	/**
	 * Instantiates a new Chrome dev tools invocation exception.
	 *
	 * @param message Exception message.
	 * @param cause   Exception cause.
	 */
	public ChromeDevToolsInvocationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Gets code. -1 for 'local' exceptions.
	 *
	 * @return the code
	 */
	public Long getCode() {
		return code;
	}
}
