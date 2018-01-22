package com.github.kklisura.cdtp.services.impl;

import com.github.kklisura.cdtp.services.WebSocketService;
import com.github.kklisura.cdtp.services.exceptions.WebSocketServiceException;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.glassfish.tyrus.server.Server;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.websocket.DeploymentException;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.assertTrue;

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
	public void testConnectionAndMessageSending() throws WebSocketServiceException, InterruptedException {
		final AtomicBoolean isSuccess = new AtomicBoolean(Boolean.FALSE);
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		final int port = randomPort();
		final Server server = startServer(port);

		final WebSocketService webSocketService = WebSocketServiceImpl.create(createURI(port));

		webSocketService.addMessageHandler(message -> {
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
	public void testConnectionOnNonExistentServer() throws WebSocketServiceException, InterruptedException {
		final int port = randomPort();
		WebSocketServiceImpl.create(createURI(port));
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