package com.github.kklisura.cdtp.services.exceptions;

/**
 * Web socket service exception.
 *
 * @author Kenan Klisura
 */
public class WebSocketServiceException extends Exception {
	public WebSocketServiceException(String message) {
		super(message);
	}

	public WebSocketServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
