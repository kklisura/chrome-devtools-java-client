package com.github.kklisura.cdtp.services.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Proxy utils.
 *
 * @author Kenan Klisura
 */
public final class ProxyUtils {
	/**
	 * Creates a proxy class to a given clazz supplied with invocation handler.
	 *
	 * @param clazz Proxy to class.
	 * @param invocationHandler Invocation handler.
	 * @param <T> Class type.
	 * @return Proxy instance.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T createProxy(Class<T> clazz, InvocationHandler invocationHandler) {
		return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] { clazz }, invocationHandler);
	}
}
