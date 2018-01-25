package com.github.kklisura.cdtp.services;

import com.github.kklisura.cdtp.protocol.ChromeDevTools;
import com.github.kklisura.cdtp.protocol.support.types.EventHandler;
import com.github.kklisura.cdtp.protocol.support.types.EventListener;
import com.github.kklisura.cdtp.services.exceptions.ChromeDevToolsInvocationException;
import com.github.kklisura.cdtp.services.types.MethodInvocation;

/**
 * Chrome dev tools service.
 *
 * @author Kenan Klisura
 */
public interface ChromeDevToolsService extends ChromeDevTools, AutoCloseable {
	/**
	 * Invokes a dev tools method.
	 *
	 * @param returnProperty   Return property.
	 * @param clazz            Return class type.
	 * @param methodInvocation Method invocation definition.
	 * @param <T> Type of a return class.
	 * @return Return object.
	 * @throws ChromeDevToolsInvocationException If invocation fails.
	 */
	<T> T invoke(String returnProperty, Class<T> clazz, MethodInvocation methodInvocation);

	/**
	 * Closes the dev tools service.
	 */
	void close();

	/**
	 * Adds an event listener on a given event name belonging to some domain.
	 *
	 * @param domainName Domain.
	 * @param eventName Event.
	 * @param eventHandler Event handler.
	 * @param eventType Event type.
	 * @return Event listener.
	 */
	EventListener addEventListener(String domainName, String eventName, EventHandler eventHandler, Class<?> eventType);

	/**
	 * Removes an event listener.
	 *
	 * @param eventListener Event listener.
	 */
	void removeEventListener(EventListener eventListener);
}
