package com.github.kklisura.cdtp.services.exceptions;

/**
 * Chrome service exception.
 *
 * @author Kenan Klisura
 */
public class ChromeServiceException extends Exception {
	public ChromeServiceException(String message) {
		super(message);
	}

	public ChromeServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
