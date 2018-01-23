package com.github.kklisura.cdtp.services.utils;

import javassist.Modifier;
import javassist.util.proxy.ProxyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Proxy utils.
 *
 * @author Kenan Klisura
 */
public final class ProxyUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProxyUtils.class);

	/**
	 * Empty ctor.
	 */
	private ProxyUtils() {
		// Empty ctor.
	}

	/**
	 * Creates a proxy class to a given interface clazz supplied with invocation handler.
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

	/**
	 * Creates a proxy class to a given abstract clazz supplied with invocation handler for un-implemented/abstrat
	 * methods.
	 *
	 * @param clazz Proxy to class.
	 * @param paramTypes Ctor param types.
	 * @param args Ctor args.
	 * @param invocationHandler Invocation handler.
	 * @param <T> Class type.
	 * @return Proxy instance.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T createProxyFromAbstract(Class<T> clazz, Class[] paramTypes, Object[] args,
												InvocationHandler invocationHandler) {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setSuperclass(clazz);
		proxyFactory.setFilter(method -> Modifier.isAbstract(method.getModifiers()));
		try {
			return (T) proxyFactory.create(paramTypes, args, (o, method, method1, objects) ->
					invocationHandler.invoke(o, method, objects));
		} catch (Exception e) {
			LOGGER.error("Failed creating proxy from abstract class", e);
			throw new RuntimeException("Failed creating proxy from abstract class", e);
		}
	}
}
