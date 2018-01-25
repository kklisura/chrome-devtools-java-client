package com.github.kklisura.cdtp.services.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kklisura.cdtp.protocol.support.types.EventHandler;
import com.github.kklisura.cdtp.protocol.support.types.EventListener;
import com.github.kklisura.cdtp.services.ChromeDevToolsService;
import com.github.kklisura.cdtp.services.WebSocketService;
import com.github.kklisura.cdtp.services.exceptions.ChromeDevToolsInvocationException;
import com.github.kklisura.cdtp.services.exceptions.WebSocketServiceException;
import com.github.kklisura.cdtp.services.types.ChromeTab;
import com.github.kklisura.cdtp.services.types.EventListenerImpl;
import com.github.kklisura.cdtp.services.types.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import static com.github.kklisura.cdtp.services.utils.ConfigurationUtils.systemProperty;

/**
 * Dev tools service implementation.
 *
 * @author Kenan Klisura
 */
public abstract class ChromeDevToolsServiceImpl implements ChromeDevToolsService, Consumer<String>, AutoCloseable {
	private static final Logger LOGGER = LoggerFactory.getLogger(ChromeDevToolsServiceImpl.class);

	private static final String TIMEOUT_ENV_PROPERTY = "com.github.kklisura.cdtp.websocket.readTimeout";

	// Default of 10 seconds read timeout.
	private static final long DEFAULT_READ_TIMEOUT = 10_000;
	private static final long READ_TIMEOUT = systemProperty(TIMEOUT_ENV_PROPERTY, DEFAULT_READ_TIMEOUT);

	private static final String ID_PROPERTY = "id";
	private static final String ERROR_PROPERTY = "error";
	private static final String RESULT_PROPERTY = "result";
	private static final String METHOD_PROPERTY = "method";
	private static final String PARAMS_PROPERTY = "params";

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
			.setSerializationInclusion(JsonInclude.Include.NON_NULL);

	private WebSocketService webSocketService;

	private Map<Long, InvocationResult> invocationResultMap = new ConcurrentHashMap<>();

	private long readTimeout;

	private ChromeTab chromeTab;
	private ChromeServiceImpl chromeService;

	private Map<String, Set<EventListenerImpl>> eventNameToHandlersMap = new HashMap<>();

	/**
	 * Instantiates a new Dev tools service.
	 *
	 * @param webSocketService Web socket service.
	 * @param readTimeout      Read timeout in milliseconds.
	 * @throws WebSocketServiceException Web socket service exception.
	 */
	public ChromeDevToolsServiceImpl(WebSocketService webSocketService, long readTimeout)
			throws WebSocketServiceException {
		this.webSocketService = webSocketService;
		this.webSocketService.addMessageHandler(this);
		this.readTimeout = readTimeout;
	}

	/**
	 * Instantiates a new Chrome dev tools service. This is used during proxy building phase.
	 *
	 * See {@link ChromeServiceImpl#createDevToolsService(ChromeTab)}
	 * See {@link com.github.kklisura.cdtp.services.utils.ProxyUtils#createProxyFromAbstract(Class, Class[], Object[], InvocationHandler)}
	 *
	 * @param webSocketService Web socket service.
	 * @throws WebSocketServiceException Web socket service exception
	 */
	public ChromeDevToolsServiceImpl(WebSocketService webSocketService) throws WebSocketServiceException {
		this(webSocketService, READ_TIMEOUT);
	}

	/**
	 * Sets the chrome service container.
	 *
	 * @param chromeService Chrome service.
	 */
	public void setChromeService(ChromeServiceImpl chromeService) {
		this.chromeService = chromeService;
	}

	/**
	 * Sets the chrome tab for this service.
	 *
	 * @param chromeTab the tab
	 */
	public void setChromeTab(ChromeTab chromeTab) {
		this.chromeTab = chromeTab;
	}

	@Override
	public <T> T invoke(String returnProperty, Class<T> clazz, MethodInvocation methodInvocation) {
		try {
			InvocationResult invocationResult = new InvocationResult(returnProperty);
			invocationResultMap.put(methodInvocation.getId(), invocationResult);

			webSocketService.send(OBJECT_MAPPER.writeValueAsString(methodInvocation));

			boolean hasReceivedResponse = invocationResult.waitForResult(readTimeout, TimeUnit.MILLISECONDS);
			invocationResultMap.remove(methodInvocation.getId());

			if (!hasReceivedResponse) {
				throw new ChromeDevToolsInvocationException("Timeout expired while waiting for server response.");
			}

			if (invocationResult.isSuccess()) {
				if (Void.TYPE.equals(clazz)) {
					return null;
				}

				return readJsonObject(clazz, invocationResult.getResult());
			} else {
				ErrorObject error = readJsonObject(ErrorObject.class, invocationResult.getResult());
				StringBuilder errorMessageBuilder = new StringBuilder(error.getMessage());
				if (error.getData() != null) {
					errorMessageBuilder.append(": ");
					errorMessageBuilder.append(error.getData());
				}

				throw new ChromeDevToolsInvocationException(error.getCode(), errorMessageBuilder.toString());
			}
		} catch (WebSocketServiceException e) {
			throw new ChromeDevToolsInvocationException("Failed sending web socket message.", e);
		} catch (InterruptedException e) {
			throw new ChromeDevToolsInvocationException("Interrupted while waiting response.", e);
		} catch (IOException ex) {
			throw new ChromeDevToolsInvocationException("Failed reading response message.", ex);
		}
	}

	@Override
	public void close() {
		webSocketService.close();

		if (chromeService != null) {
			chromeService.clearChromeDevToolsServiceCache(chromeTab);
		}
	}

	@Override
	public EventListener addEventListener(String domainName, String eventName, EventHandler eventHandler,
										  Class<?> eventType) {
		String name = domainName + "." + eventName;

		EventListenerImpl eventListener = new EventListenerImpl(name, eventHandler, eventType, this);
		eventNameToHandlersMap.computeIfAbsent(name, this::createEventHandlerSet).add(eventListener);

		return eventListener;
	}

	@Override
	public void removeEventListener(EventListener eventListener) {
		EventListenerImpl eventListenerImpl = (EventListenerImpl) eventListener;

		String name = eventListenerImpl.getKey();
		EventHandler eventHandler = eventListenerImpl.getHandler();

		Set<EventListenerImpl> listeners = eventNameToHandlersMap.computeIfAbsent(name, this::createEventHandlerSet);
		synchronized (listeners) {
			listeners.removeIf(next -> eventHandler.equals(next.getHandler()));
		}
	}

	@Override
	public void accept(String message) {
		try {
			JsonNode jsonNode = OBJECT_MAPPER.readTree(message);

			JsonNode idNode = jsonNode.get(ID_PROPERTY);
			if (idNode != null) {
				Long id = idNode.asLong();
				InvocationResult invocationResult = invocationResultMap.get(id);

				if (invocationResult != null) {
					JsonNode resultNode = jsonNode.get(RESULT_PROPERTY);
					JsonNode errorNode = jsonNode.get(ERROR_PROPERTY);

					if (errorNode != null) {
						invocationResult.signalResultReady(false, errorNode);
					} else {
						if (invocationResult.getReturnProperty() != null) {
							if (resultNode != null) {
								resultNode = resultNode.get(invocationResult.getReturnProperty());
							}
						}

						if (resultNode != null) {
							invocationResult.signalResultReady(true, resultNode);
						} else {
							invocationResult.signalResultReady(true, null);
						}
					}
				} else {
					LOGGER.warn("Received result response with unknown invocation id {}. {}", id, jsonNode.asText());
				}
			} else {
				JsonNode methodNode = jsonNode.get(METHOD_PROPERTY);
				JsonNode paramsNode = jsonNode.get(PARAMS_PROPERTY);

				if (methodNode != null) {
					handleEvent(methodNode.asText(), paramsNode);
				}
			}
		} catch (IOException ex) {
			LOGGER.error("Failed reading web socket message!", ex);
		} catch (Exception ex) {
			LOGGER.error("Failed receiving web socket message!", ex);
		}
	}

	@SuppressWarnings("unchecked")
	private void handleEvent(String name, JsonNode params) {
		Set<EventListenerImpl> listeners = eventNameToHandlersMap.get(name);

		if (listeners != null) {
			synchronized (listeners) {
				if (!listeners.isEmpty()) {
					Object event = null;

					for (EventListenerImpl listener : listeners) {
						try {
							if (event == null) {
								event = readJsonObject(listener.getParamType(), params);
							}

							listener.getHandler().onEvent(event);
						} catch (Exception e) {
							LOGGER.error("Error while processing event {}", name, e);
						}
					}
				}
			}
		}
	}

	private <T> T readJsonObject(Class<T> clazz, JsonNode jsonNode) throws IOException {
		if (jsonNode == null) {
			throw new ChromeDevToolsInvocationException("Failed converting null response to clazz " + clazz.getName());
		}

		return OBJECT_MAPPER.readerFor(clazz).readValue(jsonNode);
	}

	private Set<EventListenerImpl> createEventHandlerSet(String unused) {
		return Collections.synchronizedSet(new HashSet<>());
	}

	/**
	 * Error object returned from dev tools.
	 */
	private static class ErrorObject {
		private Long code;
		private String message;
		private String data;

		public String getData() {
			return data;
		}

		public Long getCode() {
			return code;
		}

		public String getMessage() {
			return message;
		}
	}

	/**
	 * Invocation result wrapper.
	 */
	private static class InvocationResult {
		private String returnProperty;
		private JsonNode result;
		private boolean isSuccess;
		private CountDownLatch countDownLatch = new CountDownLatch(1);

		/**
		 * Creates an invocation result given a return property.
		 *
		 * @param returnProperty Return property. Could be null.
		 */
		public InvocationResult(String returnProperty) {
			this.returnProperty = returnProperty;
		}

		/**
		 * Gets the return property. Could be null.
		 *
		 * @return Return property.
		 */
		public String getReturnProperty() {
			return returnProperty;
		}

		/**
		 * Gets the JSON result.
		 *
		 * @return the result
		 */
		public JsonNode getResult() {
			return result;
		}

		/**
		 * Is invocation successful.
		 *
		 * @return True if invocation is successful.
		 */
		public boolean isSuccess() {
			return isSuccess;
		}

		/**
		 * Signals result is ready for consumption.
		 */
		public void signalResultReady(boolean isSuccess, JsonNode result) {
			this.isSuccess = isSuccess;
			this.result = result;

			countDownLatch.countDown();
		}

		/**
		 * Waits until result is available.
		 *
		 * @return False if timeout is expired.
		 * @throws InterruptedException If wait is interrupted.
		 */
		public boolean waitForResult(long timeout, TimeUnit timeUnit) throws InterruptedException {
			return countDownLatch.await(timeout, timeUnit);
		}
	}
}
