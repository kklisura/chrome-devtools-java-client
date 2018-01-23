package com.github.kklisura.cdtp.services.factory;

import com.github.kklisura.cdtp.services.WebSocketService;
import com.github.kklisura.cdtp.services.exceptions.WebSocketServiceException;

/**
 * Web service socket factory.
 *
 * @author Kenan Klisura
 */
@FunctionalInterface
public interface WebSocketServiceFactory {
	/**
	 * Creates a web socket service given a web socket url.
	 *
	 * @param wsUrl Web socket url.
	 * @return Web socket service.
	 */
	WebSocketService createWebSocketService(String wsUrl) throws WebSocketServiceException;
}
