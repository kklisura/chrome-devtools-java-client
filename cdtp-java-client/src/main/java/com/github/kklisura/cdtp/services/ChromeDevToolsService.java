package com.github.kklisura.cdtp.services;

/*-
 * #%L
 * cdpt-java-client
 * %%
 * Copyright (C) 2018 Kenan Klisura
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
   * @param returnProperty Return property.
   * @param clazz Return class type.
   * @param methodInvocation Method invocation definition.
   * @param <T> Type of a return class.
   * @return Return object.
   * @throws ChromeDevToolsInvocationException If invocation fails.
   */
  <T> T invoke(String returnProperty, Class<T> clazz, MethodInvocation methodInvocation);

  /** Closes the dev tools service. */
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
  EventListener addEventListener(
      String domainName, String eventName, EventHandler eventHandler, Class<?> eventType);

  /**
   * Removes an event listener.
   *
   * @param eventListener Event listener.
   */
  void removeEventListener(EventListener eventListener);
}
