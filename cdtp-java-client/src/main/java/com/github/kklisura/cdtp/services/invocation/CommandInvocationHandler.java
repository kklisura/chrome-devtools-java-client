package com.github.kklisura.cdtp.services.invocation;

import com.github.kklisura.cdtp.protocol.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.annotations.Returns;
import com.github.kklisura.cdtp.services.DevToolsService;
import com.github.kklisura.cdtp.services.model.chrome.MethodInvocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Command invocation handler intercepts command requests and invokes the command using DevToolsService.
 *
 * @author Kenan Klisura
 */
public class CommandInvocationHandler implements InvocationHandler {
	private static final AtomicLong ID_SUPPLIER = new AtomicLong(1L);

	private DevToolsService devToolsService;

	/**
	 * Instantiates a new Command invocation handler with a dev tool service implementation.
	 *
	 * @param devToolsService DevTools service implementation.
	 */
	public CommandInvocationHandler(DevToolsService devToolsService) {
		this.devToolsService = devToolsService;
	}

	@Override
	public Object invoke(Object unused, Method method, Object[] args) throws Throwable {
		Class<?> returnType = method.getReturnType();

		String returnProperty = null;
		Returns returnsAnnotation = method.getAnnotation(Returns.class);
		if (returnsAnnotation != null) {
			returnProperty = returnsAnnotation.value();
		}

		MethodInvocation methodInvocation = createMethodInvocation(method, args);
		return devToolsService.invoke(returnProperty, returnType, methodInvocation);
	}

	/**
	 * Creates the method invocation object given a method and its args.
	 *
	 * @param method Method.
	 * @param args Method args.
	 * @return Chrome method invocation object.
	 */
	private MethodInvocation createMethodInvocation(Method method, Object[] args) {
		String domainName = method.getDeclaringClass().getSimpleName();
		String methodName = method.getName();

		MethodInvocation methodInvocation = new MethodInvocation();
		methodInvocation.setId(ID_SUPPLIER.getAndIncrement());
		methodInvocation.setMethod(domainName + "." + methodName);
		methodInvocation.setParams(buildMethodParams(method, args));

		return methodInvocation;
	}

	/**
	 * Builds method params given a method and its args.
	 *
	 * @param method Method.
	 * @param args Method args.
	 * @return Map of params.
	 */
	private Map<String, Object> buildMethodParams(Method method, Object[] args) {
		Map<String, Object> params = new HashMap<>();
		Parameter[] parameters = method.getParameters();

		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				params.put(parameters[i].getAnnotation(ParamName.class).value(), args[i]);
			}
		}

		return params;
	}
}
