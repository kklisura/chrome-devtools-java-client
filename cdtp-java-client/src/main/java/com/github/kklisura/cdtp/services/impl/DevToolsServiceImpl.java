package com.github.kklisura.cdtp.services.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kklisura.cdtp.services.DevToolsService;
import com.github.kklisura.cdtp.services.WebSocketService;
import com.github.kklisura.cdtp.services.exceptions.ChromeDevToolsException;
import com.github.kklisura.cdtp.services.exceptions.WebSocketServiceException;
import com.github.kklisura.cdtp.services.model.chrome.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

/**
 * Dev tools service implementation.
 *
 * @author Kenan Klisura
 */
public class DevToolsServiceImpl implements DevToolsService, Consumer<String> {
	private static final Logger LOGGER = LoggerFactory.getLogger(DevToolsServiceImpl.class);

	private static final String ID_PROPERTY = "id";
	private static final String ERROR_PROPERTY = "error";
	private static final String RESULT_PROPERTY = "result";
	private static final String METHOD_PROPERTY = "method";
	private static final String PARAMS_PROPERTY = "params";

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
			.setSerializationInclusion(JsonInclude.Include.NON_NULL);

	private WebSocketService webSocketService;

	private Map<Long, InvocationResult> invocationResultMap = new ConcurrentHashMap<>();

	/**
	 * Instantiates a new Dev tools service.
	 *
	 * @param webSocketService Web socket service.
	 * @throws WebSocketServiceException Web socket service exception.
	 */
	public DevToolsServiceImpl(WebSocketService webSocketService) throws WebSocketServiceException {
		this.webSocketService = webSocketService;
		this.webSocketService.addMessageHandler(this);
	}

	@Override
	public <T> T invoke(String returnProperty, Class<T> clazz, MethodInvocation methodInvocation)
			throws ChromeDevToolsException {
		try {
			InvocationResult invocationResult = new InvocationResult(returnProperty);
			invocationResultMap.put(methodInvocation.getId(), invocationResult);

			webSocketService.send(OBJECT_MAPPER.writeValueAsString(methodInvocation));

			invocationResult.waitForResult();
			invocationResultMap.remove(methodInvocation.getId());

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

				throw new ChromeDevToolsException(error.getCode(), errorMessageBuilder.toString());
			}
		} catch (WebSocketServiceException ex) {
			throw new ChromeDevToolsException("Failed sending web socket message.", ex);
		} catch (InterruptedException ex) {
			throw new ChromeDevToolsException("Interrupted while waiting response.", ex);
		} catch (IOException ex) {
			throw new ChromeDevToolsException("Failed reading response message.", ex);
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
						invocationResult.signalResultReady(false, errorNode.toString());
					} else {
						if (invocationResult.getReturnProperty() != null) {
							if (resultNode != null) {
								resultNode = resultNode.get(invocationResult.getReturnProperty());
							}
						}

						if (resultNode != null) {
							invocationResult.signalResultReady(true, resultNode.toString());
						} else {
							invocationResult.signalResultReady(true, null);
						}
					}
				} else {
					LOGGER.warn("Received result response with unknown invocation id {}. {}", id, jsonNode.asText());
				}
			} else {
				// TODO(kklisura): Handle dev tools events.
//				JsonNode methodNode = jsonNode.get(METHOD_PROPERTY);
//				JsonNode paramsNode = jsonNode.get(PARAMS_PROPERTY);
			}
		} catch (IOException ex) {
			LOGGER.error("Failed reading web socket message!", ex);
		} catch (Exception ex) {
			LOGGER.error("Failed receiving web socket message!", ex);
		}
	}

	private <T> T readJsonObject(Class<T> clazz, String json) throws IOException {
		return OBJECT_MAPPER.readerFor(clazz).readValue(json);
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
		private String result;
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
		public String getResult() {
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
		public void signalResultReady(boolean isSuccess, String result) {
			this.isSuccess = isSuccess;
			this.result = result;

			countDownLatch.countDown();
		}

		/**
		 * Waits until result is available.
		 *
		 * @throws InterruptedException If wait is interrupted.
		 */
		public void waitForResult() throws InterruptedException {
			countDownLatch.await();
		}
	}
}
