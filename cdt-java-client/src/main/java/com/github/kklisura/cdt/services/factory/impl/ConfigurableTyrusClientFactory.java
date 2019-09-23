package com.github.kklisura.cdt.services.factory.impl;

/*-
 * #%L
 * cdt-java-client
 * %%
 * Copyright (C) 2018 - 2019 Kenan Klisura
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

import com.github.kklisura.cdt.services.factory.WebSocketContainerFactory;
import javax.websocket.WebSocketContainer;
import org.glassfish.tyrus.client.ClientManager;
import org.glassfish.tyrus.container.grizzly.client.GrizzlyClientContainer;

/**
 * Configurable tyrus client WebSocketContainer factory. This demonstrates how to increase incoming
 * buffer size in tyrus client. It increases the incoming buffer to 120MB instead of 4MB which is
 * used as default in Tyrus.
 *
 * <p>To install this factory add the following to your main application class:
 *
 * <pre>
 *   static {
 *     System.setProperty("com.github.kklisura.cdt.services.config.webSocketContainerFactory",
 *                         ConfigurableTyrusClientFactory.class.getName());
 *   }
 * </pre>
 *
 * <p>This sets system property to a ConfigurableTyrusClientFactory class.
 *
 * <p>Use this class as template for any configuration changes to underlying web-socket client.
 *
 * <p><a href="https://tyrus-project.github.io/documentation/1.13.1/user-guide.html#d0e1197">8.4.
 * Incoming buffer size on tyrus</a>
 *
 * @author Kenan Klisura
 */
public class ConfigurableTyrusClientFactory implements WebSocketContainerFactory {
  public static final String INCOMING_BUFFER_SIZE_PROPERTY =
      "org.glassfish.tyrus.incomingBufferSize";

  public static final int KB = 1024;
  public static final int MB = 1024 * KB;

  private static final int INCOMING_BUFFER_SIZE = 120 * MB;

  @Override
  public WebSocketContainer getWebSocketContainer() {
    final ClientManager client = ClientManager.createClient(GrizzlyClientContainer.class.getName());
    // Tyrus configuration goes here.
    client.getProperties().put(INCOMING_BUFFER_SIZE_PROPERTY, INCOMING_BUFFER_SIZE);
    return client;
  }
}
