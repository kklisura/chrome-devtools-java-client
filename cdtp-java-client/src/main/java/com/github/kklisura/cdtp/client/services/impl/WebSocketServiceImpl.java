package com.github.kklisura.cdtp.client.services.impl;

import com.github.kklisura.cdtp.client.services.WebSocketService;
import com.github.kklisura.cdtp.client.services.exceptions.WebSocketServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

/**
 * Web socket service implementation.
 *
 * @author Kenan Klisura
 */
public class WebSocketServiceImpl implements WebSocketService {
	private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketServiceImpl.class);

	private static final WebSocketContainer WEB_SOCKET_CONTAINER = ContainerProvider.getWebSocketContainer();

	private Session session;

	/**
	 * Private ctor.
	 */
	private WebSocketServiceImpl() {
		// Empty body.
	}

	/**
	 * Creates a web socket service implementation factory method.
	 *
	 * Creates a WebSocketService and connects to a specified uri.
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
		final CountDownLatch countDownLatch = new CountDownLatch(1);

		LOGGER.info("Connecting to ws server {}", uri);

		try {
			session = WEB_SOCKET_CONTAINER.connectToServer(new Endpoint() {
				@Override
				public void onOpen(Session session, EndpointConfig config) {
					LOGGER.debug("Connected to ws server {}", uri);
					countDownLatch.countDown();
				}

				// TODO(kklisura): Add close handler.
			}, uri);
		} catch (DeploymentException | IOException e) {
			LOGGER.warn("Failed connecting to ws server {}...", uri, e);
			throw new WebSocketServiceException("Failed connecting to ws server {}", e);
		}

		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			LOGGER.warn("Interrupted while connecting to ws server {}...", uri, e);
			throw new WebSocketServiceException("Failed connecting to ws server.", e);
		}
	}

	@Override
	public void send(String message) throws WebSocketServiceException {
		try {
			LOGGER.info("Sending message {} on {}", message, session.getRequestURI());
			session.getBasicRemote().sendText(message);
		} catch (IOException e) {
			LOGGER.error("Failed sending data to ws server {}...", session.getRequestURI(), e);
			throw new WebSocketServiceException("Failed sending data to ws server.", e);
		}
	}

	@Override
	public void addMessageHandler(Consumer<String> consumer) throws WebSocketServiceException {
		if (session == null) {
			throw new WebSocketServiceException("You first must connect to ws server in order to receive messages.");
		}

		if (!session.getMessageHandlers().isEmpty()) {
			throw new WebSocketServiceException("You are already subscribed to this web socket service.");
		}

		session.addMessageHandler(new MessageHandler.Whole<String>() {
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
		} catch (IOException e) {
			LOGGER.error("Failed closing ws session on {}...", session.getRequestURI(), e);
		}
	}
}
