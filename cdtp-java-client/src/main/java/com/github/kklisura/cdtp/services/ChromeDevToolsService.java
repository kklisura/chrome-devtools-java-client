package com.github.kklisura.cdtp.services;

import com.github.kklisura.cdtp.protocol.ChromeDevTools;
import com.github.kklisura.cdtp.services.exceptions.ChromeDevToolsInvocationException;
import com.github.kklisura.cdtp.services.model.chrome.MethodInvocation;

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
}
