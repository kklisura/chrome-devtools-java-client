package com.github.kklisura.cdtp.client.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kklisura.cdtp.client.services.ChromeService;
import com.github.kklisura.cdtp.client.services.exceptions.ChromeServiceException;
import com.github.kklisura.cdtp.client.services.model.chrome.ChromeTab;
import com.github.kklisura.cdtp.client.services.model.chrome.ChromeVersion;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

/**
 * Chrome service implementation.
 *
 * @author Kenan Klisura
 */
public class ChromeServiceImpl implements ChromeService {
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	private static final String EMPTY_STRING = "";

	private static final String LOCALHOST = "localhost";

	private static final String LIST_TABS = "json/list";
	private static final String CREATE_TAB = "json/new";
	private static final String ACTIVATE_TAB = "json/activate";
	private static final String CLOSE_TAB = "json/close";
	private static final String VERSION_TAB = "json/version";

	private String host;
	private int port;

	/**
	 * Creates a new chrome service given a host and a port.
	 *
	 * @param host Chrome host.
	 * @param port Chrome debugging port.
	 */
	public ChromeServiceImpl(String host, int port) {
		this.host = host;
		this.port = port;
	}

	/**
	 * Creates new chrome service given a port. Host is assumed to be localhost.
	 *
	 * @param port Chrome debugging port.
	 */
	public ChromeServiceImpl(int port) {
		this.host = LOCALHOST;
		this.port = port;
	}

	@Override
	public List<ChromeTab> getTabs() throws ChromeServiceException {
		return Arrays.asList(request(ChromeTab[].class, "http://%s:%d/%s", host, port, LIST_TABS));
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
	}

	@Override
	public ChromeVersion getVersion() throws ChromeServiceException {
		return request(ChromeVersion.class, "http://%s:%d/%s", host, port, VERSION_TAB);
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
		} catch (MalformedURLException ex) {
			throw new ChromeServiceException("Bad url provided.", ex);
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
