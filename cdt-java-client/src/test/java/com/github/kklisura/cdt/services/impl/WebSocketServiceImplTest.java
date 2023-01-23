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

import static java.lang.Thread.sleep;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import com.github.kklisura.cdt.services.WebSocketService;
import com.github.kklisura.cdt.services.exceptions.WebSocketServiceException;
import com.github.kklisura.cdt.services.factory.WebSocketContainerFactory;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import org.easymock.Capture;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.glassfish.tyrus.client.ClientManager;
import org.glassfish.tyrus.server.Server;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Web socket service implementation test. It's more integration test than a unit test.
 *
 * @author Kenan Klisura
 */
@RunWith(EasyMockRunner.class)
public class WebSocketServiceImplTest extends EasyMockSupport {

  private static final Random RANDOM_PORT = new Random();

  private static final int MAX_PORT = 65535;
  private static final int RESERVED_PORTS = 1025;

  private static final int KB = 1024;
  private static final int MB = 1024 * KB;
  private static final int LARGE_MESSAGE_SIZE = 9 * MB;

  private static final String PING = "PING";
  private static final String PONG = "PONG";
  private static final String EMIT_LARGE_MESSAGE = "EMIT_LARGE_MESSAGE";

  @Test
  public void testTyrusMessageLimit() throws WebSocketServiceException, InterruptedException {
    final Server server = startServer();

    final WebSocketService webSocketService =
        WebSocketServiceImpl.create(createURI(server.getPort()));

    webSocketService.send(EMIT_LARGE_MESSAGE);
    sleep(500);

    assertTrue(webSocketService.closed());

    server.stop();
  }

  @Test
  public void testConnectionAndMessageSending()
      throws WebSocketServiceException, InterruptedException {
    final AtomicBoolean isSuccess = new AtomicBoolean(Boolean.FALSE);
    final CountDownLatch countDownLatch = new CountDownLatch(1);
    final Server server = startServer();

    final WebSocketService webSocketService =
        WebSocketServiceImpl.create(createURI(server.getPort()));

    webSocketService.addMessageHandler(
        message -> {
          if (PONG.equals(message)) {
            isSuccess.set(Boolean.TRUE);
          }
          countDownLatch.countDown();
          webSocketService.close();
        });

    webSocketService.send(PING);

    countDownLatch.await();

    webSocketService.close();
    server.stop();

    assertTrue("Failed sending/receiving ws messages.", isSuccess.get());
  }

  @Test(expected = WebSocketServiceException.class)
  public void testConnectionAndMessageSendFails()
      throws WebSocketServiceException, InterruptedException {
    final Server server = startServer();

    final WebSocketService webSocketService =
        WebSocketServiceImpl.create(createURI(server.getPort()));
    server.stop();

    sleep(500);

    try {
      webSocketService.send(PING);
    } finally {
      webSocketService.close();
    }
  }

  @Test(expected = WebSocketServiceException.class)
  public void testConnectionOnNonExistentServer()
      throws WebSocketServiceException, InterruptedException {
    final int port = randomPort();
    WebSocketServiceImpl.create(createURI(port));
  }

  @Test(expected = WebSocketServiceException.class)
  public void testAddMessageHandlerThrowsExceptionIfNotConnected()
      throws WebSocketServiceException {
    WebSocketServiceImpl socketService = new WebSocketServiceImpl(null);
    socketService.addMessageHandler(message -> {});
  }

  @Test(expected = WebSocketServiceException.class)
  public void testAddMessageHandlerThrowsExceptionIfHandlerAlreadyAdded()
      throws WebSocketServiceException {
    Session session = mock(Session.class);

    Set<MessageHandler> messageHandlerSet = new HashSet<>();
    messageHandlerSet.add(null);

    expect(session.getMessageHandlers()).andReturn(messageHandlerSet);

    replay(session);

    WebSocketServiceImpl socketService = new WebSocketServiceImpl(session);
    socketService.addMessageHandler(message -> {});

    verify(session);
  }

  @Test
  public void testAddMessageHandlerHandlerIsAdded() throws WebSocketServiceException {
    final String message = "MESSAGE";

    Session session = mock(Session.class);

    Set<MessageHandler> messageHandlerSet = new HashSet<>();

    expect(session.getMessageHandlers()).andReturn(messageHandlerSet);

    expect(session.getRequestURI()).andReturn(URI.create("http://github/com"));

    Capture<MessageHandler.Whole<String>> handlerCapture = Capture.newInstance();
    session.addMessageHandler(capture(handlerCapture));

    Capture<String> messageCapture = Capture.newInstance();

    replay(session);

    WebSocketServiceImpl socketService = new WebSocketServiceImpl(session);
    socketService.addMessageHandler(messageCapture::setValue);

    assertNotNull(handlerCapture.getValue());
    handlerCapture.getValue().onMessage(message);

    verify(session);

    assertEquals(message, messageCapture.getValue());
  }

  @Test
  public void testGetWebSocketContainerReturnsDefaultContainerFactory() {
    System.setProperty(WebSocketServiceImpl.WEB_SOCKET_CONTAINER_FACTORY_PROPERTY, "");

    WebSocketContainer webSocketContainer;

    webSocketContainer = WebSocketServiceImpl.getWebSocketContainer();
    assertTrue(webSocketContainer instanceof ClientManager);

    System.setProperty(
        WebSocketServiceImpl.WEB_SOCKET_CONTAINER_FACTORY_PROPERTY,
        CustomWebSocketContainerFactory.class.getName());
    webSocketContainer = WebSocketServiceImpl.getWebSocketContainer();
    assertNull(webSocketContainer);
  }

  @Test(expected = RuntimeException.class)
  public void testGetWebSocketContainerFailsOnUnknownFactoryClass() {
    System.setProperty(WebSocketServiceImpl.WEB_SOCKET_CONTAINER_FACTORY_PROPERTY, "non-existing");
    WebSocketServiceImpl.getWebSocketContainer();
  }

  @Test(expected = RuntimeException.class)
  public void testGetWebSocketContainerFailsOnNonImplementingFactoryClass() {
    System.setProperty(
        WebSocketServiceImpl.WEB_SOCKET_CONTAINER_FACTORY_PROPERTY,
        CustomNonImplementingWebSocketContainerFactory.class.getName());
    WebSocketServiceImpl.getWebSocketContainer();
  }

  private static Server startServer() {
    Server server;
    while (true) {
      server = new Server("localhost", randomPort(), "/ws", new HashMap<>(), SimpleEndpoint.class);
      try {
        server.start();
        break;
      } catch (DeploymentException e) {
        // Ignore...
      }
    }

    return server;
  }

  private static URI createURI(int port) {
    return URI.create(String.format("ws://%s:%s/ws/test", "localhost", port));
  }

  private static int randomPort() {
    return RESERVED_PORTS + (Math.abs(RANDOM_PORT.nextInt()) % (MAX_PORT - RESERVED_PORTS));
  }

  @ServerEndpoint(value = "/test")
  public static class SimpleEndpoint {
    @OnMessage
    public void onMessage(String message, Session session) {
      try {
        if (PING.equals(message)) {
          session.getBasicRemote().sendText(PONG);
        } else {
          if (EMIT_LARGE_MESSAGE.equals(message)) {
            final char[] chars = new char[LARGE_MESSAGE_SIZE];
            Arrays.fill(chars, 'K');
            session.getBasicRemote().sendText(new String(chars));
          }
        }
      } catch (IOException e) {
        // Ignore this exception.
      }
    }
  }

  public static class CustomWebSocketContainerFactory implements WebSocketContainerFactory {
    @Override
    public WebSocketContainer getWebSocketContainer() {
      return null;
    }
  }

  public static class CustomNonImplementingWebSocketContainerFactory {
    public WebSocketContainer getWebSocketContainer() {
      return null;
    }
  }
}
