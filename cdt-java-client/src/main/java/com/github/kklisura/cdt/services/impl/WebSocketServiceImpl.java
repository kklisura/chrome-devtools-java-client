package com.github.kklisura.cdt.services.impl;

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

import static com.github.kklisura.cdt.services.impl.utils.WebSocketUtils.isTyrusBufferOverflowCloseReason;
import static com.github.kklisura.cdt.services.utils.ConfigurationUtils.systemProperty;

import com.github.kklisura.cdt.services.WebSocketService;
import com.github.kklisura.cdt.services.exceptions.WebSocketServiceException;
import com.github.kklisura.cdt.services.factory.WebSocketContainerFactory;
import com.github.kklisura.cdt.services.factory.impl.DefaultWebSocketContainerFactory;
import jakarta.websocket.CloseReason;
import jakarta.websocket.DeploymentException;
import jakarta.websocket.Endpoint;
import jakarta.websocket.EndpointConfig;
import jakarta.websocket.MessageHandler;
import jakarta.websocket.Session;
import jakarta.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Web socket service implementation.
 *
 * @author Kenan Klisura
 */
public class WebSocketServiceImpl implements WebSocketService {
  public static final String WEB_SOCKET_CONTAINER_FACTORY_PROPERTY =
      "com.github.kklisura.cdt.services.config.webSocketContainerFactory";

  private static final String DEFAULT_WEB_SOCKET_CONTAINER_FACTORY =
      DefaultWebSocketContainerFactory.class.getName();

  private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketServiceImpl.class);

  private static final WebSocketContainer WEB_SOCKET_CONTAINER = getWebSocketContainer();

  private Session session;

  /**
   * Instantiates a new Web socket service.
   *
   * @param session Session.
   */
  public WebSocketServiceImpl(Session session) {
    this.session = session;
  }

  /** Private ctor. */
  private WebSocketServiceImpl() {
    // Empty body.
  }

  /**
   * Creates a web socket service implementation factory method.
   *
   * <p>Creates a WebSocketService and connects to a specified uri.
   *
   * @param uri URI to connect to.
   * @return WebSocketService implementation.
   * @throws WebSocketServiceException If it fails to connect.
   */
  public static WebSocketService create(URI uri) throws WebSocketServiceException {
    WebSocketServiceImpl webSocketService = new WebSocketServiceImpl();
    webSocketService.connect(uri);
    return webSocketService;
  }

  @Override
  public void connect(URI uri) throws WebSocketServiceException {
    LOGGER.debug("Connecting to ws server {}", uri);

    final WebSocketServiceImpl webSocketService = this;

    try {
      session =
          WEB_SOCKET_CONTAINER.connectToServer(
              new Endpoint() {
                @Override
                public void onOpen(Session session, EndpointConfig config) {
                  webSocketService.onOpen(session, config);
                }

                @Override
                public void onClose(Session session, CloseReason closeReason) {
                  super.onClose(session, closeReason);
                  webSocketService.onClose(session, closeReason);
                }

                @Override
                public void onError(Session session, Throwable thr) {
                  super.onError(session, thr);
                  webSocketService.onError(session, thr);
                }
              },
              uri);
    } catch (DeploymentException | IOException e) {
      LOGGER.warn("Failed connecting to ws server {}...", uri, e);
      throw new WebSocketServiceException("Failed connecting to ws server {}", e);
    }
  }

  @Override
  public void send(String message) throws WebSocketServiceException {
    try {
      LOGGER.debug("Sending message {} on {}", message, session.getRequestURI());
      session.getBasicRemote().sendText(message);
    } catch (Exception e) {
      LOGGER.error("Failed sending data to ws server {}...", session.getRequestURI(), e);
      throw new WebSocketServiceException("Failed sending data to ws server.", e);
    }
  }

  @Override
  public void addMessageHandler(Consumer<String> consumer) throws WebSocketServiceException {
    if (session == null) {
      throw new WebSocketServiceException(
          "You first must connect to ws server in order to receive messages.");
    }

    if (!session.getMessageHandlers().isEmpty()) {
      throw new WebSocketServiceException("You are already subscribed to this web socket service.");
    }

    session.addMessageHandler(
        new MessageHandler.Whole<String>() {
          @Override
          public void onMessage(String message) {
            LOGGER.debug("Received message {} on {}", message, session.getRequestURI());
            consumer.accept(message);
          }
        });
  }

  @Override
  public void close() {
    try {
      session.close();
      session = null;
    } catch (IOException e) {
      LOGGER.error("Failed closing ws session on {}...", session.getRequestURI(), e);
    }
  }

  @Override
  public boolean closed() {
    return session == null || !session.isOpen();
  }

  private void onOpen(Session session, EndpointConfig config) {
    LOGGER.info("Connected to ws {}", session.getRequestURI());
  }

  private void onClose(Session session, CloseReason closeReason) {
    LOGGER.info(
        "Web socket connection closed {}, {}",
        closeReason.getCloseCode(),
        closeReason.getReasonPhrase());

    if (isTyrusBufferOverflowCloseReason(closeReason)) {
      LOGGER.error(
          "Web socket connection closed due to BufferOverflow raised by Tyrus client. This indicates the message "
              + "about to be received is larger than the incoming buffer in Tyrus client. "
              + "See DefaultWebSocketContainerFactory class source on how to increase the incoming buffer size in Tyrus or visit https://github.com/kklisura/chrome-devtools-java-client/blob/master/cdt-examples/src/main/java/com/github/kklisura/cdt/examples/IncreasedIncomingBufferInTyrusExample.java");
    }
  }

  private void onError(Session session, Throwable thr) {
    LOGGER.error("Error in web socket session.", thr);
  }

  /**
   * Returns a WebSocketContainer retrieved from class defined in system property
   * com.github.kklisura.cdt.services.config.webSocketContainerProvider. The default value for this
   * property is GrizzlyContainerProvider class FQN.
   *
   * @return WebSocketContainer.
   */
  @SuppressWarnings("unchecked")
  public static WebSocketContainer getWebSocketContainer() {
    String containerFactoryClassName =
        systemProperty(WEB_SOCKET_CONTAINER_FACTORY_PROPERTY, DEFAULT_WEB_SOCKET_CONTAINER_FACTORY);
    if (containerFactoryClassName == null || containerFactoryClassName.isEmpty()) {
      throw new RuntimeException(WEB_SOCKET_CONTAINER_FACTORY_PROPERTY + " property not set");
    }

    try {
      Class<WebSocketContainerFactory> containerFactoryClass =
          (Class<WebSocketContainerFactory>) Class.forName(containerFactoryClassName);

      if (WebSocketContainerFactory.class.isAssignableFrom(containerFactoryClass)) {
        WebSocketContainerFactory containerFactory = containerFactoryClass.newInstance();
        return containerFactory.getWebSocketContainer();
      }

      throw new RuntimeException(
          containerFactoryClassName
              + " does not implement com.github.kklisura.cdt.services.factory.WebSocketContainerFactory interface.");
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(containerFactoryClassName + " class not found.", e);
    } catch (IllegalAccessException | InstantiationException e) {
      throw new RuntimeException(
          "Could not create instance of " + containerFactoryClassName + " class");
    }
  }
}
