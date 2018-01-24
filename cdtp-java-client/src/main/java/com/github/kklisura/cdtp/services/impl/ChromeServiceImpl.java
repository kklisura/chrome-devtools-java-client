package com.github.kklisura.cdtp.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kklisura.cdtp.services.ChromeDevToolsService;
import com.github.kklisura.cdtp.services.ChromeService;
import com.github.kklisura.cdtp.services.WebSocketService;
import com.github.kklisura.cdtp.services.exceptions.ChromeServiceException;
import com.github.kklisura.cdtp.services.exceptions.WebSocketServiceException;
import com.github.kklisura.cdtp.services.factory.WebSocketServiceFactory;
import com.github.kklisura.cdtp.services.invocation.CommandInvocationHandler;
import com.github.kklisura.cdtp.services.types.ChromeTab;
import com.github.kklisura.cdtp.services.types.ChromeVersion;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.github.kklisura.cdtp.services.utils.ProxyUtils.createProxy;
import static com.github.kklisura.cdtp.services.utils.ProxyUtils.createProxyFromAbstract;

/**
 * Chrome service implementation.
 *
 * @author Kenan Klisura
 */
public class ChromeServiceImpl implements ChromeService {
	public static final String ABOUT_BLANK_PAGE = "about:blank";

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	private static final String EMPTY_STRING = "";

	private static final String LOCALHOST = "localhost";

	private static final String LIST_TABS = "json/list";
	private static final String CREATE_TAB = "json/new";
	private static final String ACTIVATE_TAB = "json/activate";
	private static final String CLOSE_TAB = "json/close";
	private static final String VERSION = "json/version";

	private String host;
	private int port;

	private WebSocketServiceFactory webSocketServiceFactory;
	private Map<String, ChromeDevToolsService> chromeDevToolServiceCache = new ConcurrentHashMap<>();

	/**
	 * Creates a new chrome service given a host, port and web service socket factory.
	 *
	 * @param host Chrome host.
	 * @param port Chrome debugging port.
	 * @param webSocketServiceFactory Web socket service factory.
	 */
	public ChromeServiceImpl(String host, int port, WebSocketServiceFactory webSocketServiceFactory) {
		this.host = host;
		this.port = port;
		this.webSocketServiceFactory = webSocketServiceFactory;
	}

	/**
	 * Creates a new chrome service given a host and a port.
	 *
	 * @param host Chrome host.
	 * @param port Chrome debugging port.
	 */
	public ChromeServiceImpl(String host, int port) {
		this(host, port, (wsUrl -> WebSocketServiceImpl.create(URI.create(wsUrl))));
	}

	/**
	 * Creates new chrome service given a port. Host is assumed to be localhost.
	 *
	 * @param port Chrome debugging port.
	 */
	public ChromeServiceImpl(int port) {
		this(LOCALHOST, port);
	}

	/**
	 * Creates new chrome service given a port. Host is assumed to be localhost.
	 *
	 * @param port Chrome debugging port.
	 * @param webSocketServiceFactory Web socket service factory.
	 */
	public ChromeServiceImpl(int port, WebSocketServiceFactory webSocketServiceFactory) {
		this(LOCALHOST, port, webSocketServiceFactory);
	}

	/**
	 * Sets web socket service factory.
	 *
	 * @param webSocketServiceFactory Web socket service factory.
	 */
	public void setWebSocketServiceFactory(WebSocketServiceFactory webSocketServiceFactory) {
		this.webSocketServiceFactory = webSocketServiceFactory;
	}

	@Override
	public List<ChromeTab> getTabs() throws ChromeServiceException {
		return Arrays.asList(request(ChromeTab[].class, "http://%s:%d/%s", host, port, LIST_TABS));
	}

	@Override
	public ChromeTab createTab() throws ChromeServiceException {
		return createTab(ABOUT_BLANK_PAGE);
	}

	@Override
	public ChromeTab createTab(String tab) throws ChromeServiceException {
		return request(ChromeTab.class, "http://%s:%d/%s?%s", host, port, CREATE_TAB, tab);
	}

	@Override
	public void activateTab(ChromeTab tab) throws ChromeServiceException {
		request(Void.class, "http://%s:%d/%s/%s", host, port, ACTIVATE_TAB, tab.getId());
	}

	@Override
	public void closeTab(ChromeTab tab) throws ChromeServiceException {
		request(Void.class, "http://%s:%d/%s/%s", host, port, CLOSE_TAB, tab.getId());

		// Remove dev tools from cache.
		clearChromeDevToolsServiceCache(tab);
	}

	@Override
	public ChromeVersion getVersion() throws ChromeServiceException {
		return request(ChromeVersion.class, "http://%s:%d/%s", host, port, VERSION);
	}

	@Override
	public synchronized ChromeDevToolsService createDevToolsService(ChromeTab tab) throws ChromeServiceException {
		try {
			if (isChromeDevToolsServiceCached(tab)) {
				return getCachedChromeDevToolsService(tab);
			}

			// Connect to a tab via web socket
			String webSocketDebuggerUrl = tab.getWebSocketDebuggerUrl();
			WebSocketService webSocketService = webSocketServiceFactory.createWebSocketService(webSocketDebuggerUrl);

			// Create invocation handler
			CommandInvocationHandler commandInvocationHandler = new CommandInvocationHandler();

			// Setup command cache for this session
			Map<Method, Object> commandsCache = new ConcurrentHashMap<>();

			// Create dev tools service.
			ChromeDevToolsServiceImpl chromeDevToolsService = createProxyFromAbstract(ChromeDevToolsServiceImpl.class,
					new Class[] { WebSocketService.class }, new Object[] { webSocketService },
					(unused, method, args) ->
						commandsCache.computeIfAbsent(method, key -> {
							Class<?> returnType = method.getReturnType();
							return createProxy(returnType, commandInvocationHandler);
						}));

			// Register dev tools service with invocation handler.
			commandInvocationHandler.setChromeDevToolsService(chromeDevToolsService);

			// Cache it up.
			cacheChromeDevToolsService(tab, chromeDevToolsService);

			return chromeDevToolsService;
		} catch (WebSocketServiceException ex) {
			throw new ChromeServiceException("Failed connecting to tab web socket.", ex);
		}
	}

	/**
	 * Clears the chrome dev tool service cache given a tab.
	 *
	 * @param tab Chrome tab.
	 */
	public void clearChromeDevToolsServiceCache(ChromeTab tab) {
		ChromeDevToolsService chromeDevToolsService = chromeDevToolServiceCache.remove(tab.getId());

		if (chromeDevToolsService != null) {
			chromeDevToolsService.close();
		}
	}

	private boolean isChromeDevToolsServiceCached(ChromeTab tab) {
		return chromeDevToolServiceCache.get(tab.getId()) != null;
	}

	private ChromeDevToolsService getCachedChromeDevToolsService(ChromeTab tab) {
		return chromeDevToolServiceCache.get(tab.getId());
	}

	private void cacheChromeDevToolsService(ChromeTab tab, ChromeDevToolsService chromeDevToolsService) {
		chromeDevToolServiceCache.put(tab.getId(), chromeDevToolsService);
	}

	/**
	 * Sends a request and parses json response as type T.
	 *
	 * @param responseType Resulting class type.
	 * @param path Path with optional params similar to String.formats params.
	 * @param params Path params.
	 * @param <T> Type of response type.
	 * @return Response object.
	 * @throws ChromeServiceException If sending request fails due to any reason.
	 */
	private static <T> T request(Class<T> responseType, String path, Object...params) throws ChromeServiceException {
		HttpURLConnection connection = null;
		InputStream inputStream = null;

		try {
			URL uri = new URL(String.format(path, params));
			connection = (HttpURLConnection) uri.openConnection();

			int responseCode = connection.getResponseCode();
			if (HttpURLConnection.HTTP_OK == responseCode) {
				if (Void.class.equals(responseType)) {
					return null;
				}

				inputStream = connection.getInputStream();
				return OBJECT_MAPPER.readerFor(responseType).readValue(inputStream);
			}

			inputStream = connection.getErrorStream();
			final String responseBody = inputStreamToString(inputStream);

			String message = MessageFormat.format("Server responded with non-200 code: {0} - {1}. {2}",
					responseCode, connection.getResponseMessage(), responseBody);
			throw new ChromeServiceException(message);
		} catch (IOException ex) {
			throw new ChromeServiceException("Failed sending HTTP request.", ex);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// We can ignore this.
				}
			}

			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	/**
	 * Converts input stream to string. If input string is null, it returns empty string.
	 *
	 * @param inputStream Input stream.
	 * @return String
	 * @throws IOException If conversion fails.
	 */
	public static String inputStreamToString(InputStream inputStream) throws IOException {
		if (inputStream == null) {
			return EMPTY_STRING;
		}

		int length;
		byte[] buffer = new byte[1024];
		ByteArrayOutputStream result = new ByteArrayOutputStream();

		while ((length = inputStream.read(buffer)) != -1) {
			result.write(buffer, 0, length);
		}

		return result.toString("UTF-8");
	}
}
