package com.github.kklisura.cdt.services.utils;

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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import javassist.Modifier;
import javassist.util.proxy.ProxyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Proxy utils.
 *
 * @author Kenan Klisura
 */
public final class ProxyUtils {
  private static final Logger LOGGER = LoggerFactory.getLogger(ProxyUtils.class);

  /** Empty ctor. */
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
    return (T)
        Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] {clazz}, invocationHandler);
  }

  /**
   * Creates a proxy class to a given abstract clazz supplied with invocation handler for
   * un-implemented/abstrat methods.
   *
   * @param clazz Proxy to class.
   * @param paramTypes Ctor param types.
   * @param args Ctor args.
   * @param invocationHandler Invocation handler.
   * @param <T> Class type.
   * @return Proxy instance.
   */
  @SuppressWarnings("unchecked")
  public static <T> T createProxyFromAbstract(
      Class<T> clazz, Class[] paramTypes, Object[] args, InvocationHandler invocationHandler) {
    ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.setSuperclass(clazz);
    proxyFactory.setFilter(method -> Modifier.isAbstract(method.getModifiers()));
    try {
      return (T)
          proxyFactory.create(
              paramTypes,
              args,
              (o, method, method1, objects) -> invocationHandler.invoke(o, method, objects));
    } catch (Exception e) {
      LOGGER.error("Failed creating proxy from abstract class", e);
      throw new RuntimeException("Failed creating proxy from abstract class", e);
    }
  }
}
