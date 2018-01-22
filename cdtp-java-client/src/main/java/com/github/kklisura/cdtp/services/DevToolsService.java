package com.github.kklisura.cdtp.services;

import com.github.kklisura.cdtp.services.exceptions.ChromeDevToolsException;
import com.github.kklisura.cdtp.services.model.chrome.MethodInvocation;

/**
 * Created by Kenan Klisura on 20/01/2018.
 *
 * @author Kenan Klisura
 */
public interface DevToolsService {
	/**
	 * Invokes a dev tools method.
	 *
	 * @param returnProperty   Return property.
	 * @param clazz            Return class type.
	 * @param methodInvocation Method invocation definition.
	 * @param <T> Type of a return class.
	 * @return Return object.
	 */
	<T> T invoke(String returnProperty, Class<T> clazz, MethodInvocation methodInvocation) throws ChromeDevToolsException;
}
