package com.github.kklisura.cdtp.services.impl;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import com.github.kklisura.cdtp.services.WebSocketService;
import com.github.kklisura.cdtp.services.exceptions.WebSocketServiceException;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.websocket.DeploymentException;
import javax.websocket.MessageHandler;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.easymock.Capture;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
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

  private static final String PING = "PING";
  private static final String PONG = "PONG";

  @Test
  public void testConnectionAndMessageSending()
      throws WebSocketServiceException, InterruptedException {
    final AtomicBoolean isSuccess = new AtomicBoolean(Boolean.FALSE);
    final CountDownLatch countDownLatch = new CountDownLatch(1);
    final int port = randomPort();
    final Server server = startServer(port);

    final WebSocketService webSocketService = WebSocketServiceImpl.create(createURI(port));

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
  public void testConnectionAndMessageSendFails() throws WebSocketServiceException {
    final int port = randomPort();
    final Server server = startServer(port);

    final WebSocketService webSocketService = WebSocketServiceImpl.create(createURI(port));
    server.stop();

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

  private static Server startServer(int port) {
    Server server;
    while (true) {
      server = new Server("localhost", port, "/ws", new HashMap<>(), SimpleEndpoint.class);
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
    public void onMessage(String message, Session session) throws IOException {
      if (PING.equals(message)) {
        session.getBasicRemote().sendText(PONG);
      } else {
        session.getBasicRemote().sendText(PING);
      }
    }
  }
}
