package com.github.kklisura.cdt.services.invocation;

/*-
 * #%L
 * cdt-java-client
 * %%
 * Copyright (C) 2018 - 2023 Kenan Klisura
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.github.kklisura.cdt.protocol.support.annotations.EventName;
import com.github.kklisura.cdt.protocol.support.annotations.ParamName;
import com.github.kklisura.cdt.protocol.support.annotations.ReturnTypeParameter;
import com.github.kklisura.cdt.protocol.support.annotations.Returns;
import com.github.kklisura.cdt.protocol.support.types.EventHandler;
import com.github.kklisura.cdt.protocol.support.types.EventListener;
import com.github.kklisura.cdt.services.ChromeDevToolsService;
import com.github.kklisura.cdt.services.types.MethodInvocation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Command invocation handler intercepts command requests and invokes the command using
 * DevToolsService.
 *
 * @author Kenan Klisura
 */
public class CommandInvocationHandler implements InvocationHandler {
  private static final String EVENT_LISTENER_PREFIX = "on";

  private static final AtomicLong ID_SUPPLIER = new AtomicLong(1L);

  private ChromeDevToolsService chromeDevToolsService;

  /**
   * Sets dev tools service.
   *
   * @param chromeDevToolsService Chrome dev tools service.
   */
  public void setChromeDevToolsService(ChromeDevToolsService chromeDevToolsService) {
    this.chromeDevToolsService = chromeDevToolsService;
  }

  @Override
  public Object invoke(Object unused, Method method, Object[] args) throws Throwable {
    if (isEventSubscription(method)) {
      String domainName = method.getDeclaringClass().getSimpleName();
      String eventName = getEventName(method);

      Class<?> eventHandlerType = getEventHandlerType(method);
      return chromeDevToolsService.addEventListener(
          domainName, eventName, (EventHandler) args[0], eventHandlerType);
    }

    Class<?> returnType = method.getReturnType();

    Class<?>[] returnTypeClasses = null;
    ReturnTypeParameter returnTypeParameter = method.getAnnotation(ReturnTypeParameter.class);
    if (returnTypeParameter != null) {
      returnTypeClasses = returnTypeParameter.value();
    }

    String returnProperty = null;
    Returns returnsAnnotation = method.getAnnotation(Returns.class);
    if (returnsAnnotation != null) {
      returnProperty = returnsAnnotation.value();
    }

    MethodInvocation methodInvocation = createMethodInvocation(method, args);
    return chromeDevToolsService.invoke(
        returnProperty, returnType, returnTypeClasses, methodInvocation);
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

  /**
   * Returns event name given an event subscription method.
   *
   * @param method Method.
   * @return Event name.
   */
  private static String getEventName(Method method) {
    return method.getAnnotation(EventName.class).value();
  }

  private static Class<?> getEventHandlerType(Method method) {
    return (Class<?>)
        ((ParameterizedType) method.getGenericParameterTypes()[0]).getActualTypeArguments()[0];
  }

  /**
   * Checks if given method has signature of event subscription.
   *
   * @param method Method to check.
   * @return True if this is event subscription method that is: EventListener on*(EventHandler)
   */
  public static boolean isEventSubscription(Method method) {
    String name = method.getName();
    Parameter[] parameters = method.getParameters();

    return name.startsWith(EVENT_LISTENER_PREFIX)
        && EventListener.class.equals(method.getReturnType())
        && (parameters != null
            && parameters.length == 1
            && EventHandler.class.isAssignableFrom(parameters[0].getType()));
  }
}
