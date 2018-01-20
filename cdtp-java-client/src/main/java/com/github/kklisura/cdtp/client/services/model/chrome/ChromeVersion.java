package com.github.kklisura.cdtp.client.services.model.chrome;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Chrome version model.
 *
 * @author Kenan Klisura
 */
public class ChromeVersion {
	@JsonProperty("Browser")
	private String browser;

	@JsonProperty("Protocol-Version")
	private String protocolVersion;

	@JsonProperty("User-Agent")
	private String userAgent;

	@JsonProperty("V8-Version")
	private String v8Version;

	@JsonProperty("WebKit-Version")
	private String webKitVersion;

	@JsonProperty("webSocketDebuggerUrl")
	private String webSocketDebuggerUrl;

	/**
	 * Gets browser.
	 *
	 * @return the browser
	 */
	public String getBrowser() {
		return browser;
	}

	/**
	 * Gets protocol version.
	 *
	 * @return the protocol version
	 */
	public String getProtocolVersion() {
		return protocolVersion;
	}

	/**
	 * Gets user agent.
	 *
	 * @return the user agent
	 */
	public String getUserAgent() {
		return userAgent;
	}

	/**
	 * Gets v 8 version.
	 *
	 * @return the v 8 version
	 */
	public String getV8Version() {
		return v8Version;
	}

	/**
	 * Gets web kit version.
	 *
	 * @return the web kit version
	 */
	public String getWebKitVersion() {
		return webKitVersion;
	}

	/**
	 * Gets web socket debugger url.
	 *
	 * @return the web socket debugger url
	 */
	public String getWebSocketDebuggerUrl() {
		return webSocketDebuggerUrl;
	}
}
