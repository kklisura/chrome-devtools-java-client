package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.Optional;
import com.github.kklisura.cdtp.protocol.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.annotations.Returns;
import com.github.kklisura.cdtp.protocol.types.network.AuthChallengeResponse;
import com.github.kklisura.cdtp.protocol.types.network.ConnectionType;
import com.github.kklisura.cdtp.protocol.types.network.Cookie;
import com.github.kklisura.cdtp.protocol.types.network.CookieParam;
import com.github.kklisura.cdtp.protocol.types.network.CookieSameSite;
import com.github.kklisura.cdtp.protocol.types.network.ErrorReason;
import com.github.kklisura.cdtp.protocol.types.network.Headers;
import com.github.kklisura.cdtp.protocol.types.network.ResponseBody;
import java.util.List;

/**
 * Network domain allows tracking network activities of the page. It exposes information about http, file, data and other requests and responses, their headers, bodies, timing, etc.
 */
public interface Network {

	/**
	 * Enables network tracking, network events will now be delivered to the client.
	 */
	void enable();

	/**
	 * Enables network tracking, network events will now be delivered to the client.
	 */
	void enable(@Experimental @Optional @ParamName("maxTotalBufferSize") Integer maxTotalBufferSize, @Experimental @Optional @ParamName("maxResourceBufferSize") Integer maxResourceBufferSize);

	/**
	 * Disables network tracking, prevents network events from being sent to the client.
	 */
	void disable();

	/**
	 * Allows overriding user agent with the given string.
	 */
	void setUserAgentOverride(@ParamName("userAgent") String userAgent);

	/**
	 * Specifies whether to always send extra HTTP headers with the requests from this page.
	 */
	void setExtraHTTPHeaders(@ParamName("headers") Headers headers);

	/**
	 * Returns content served for the given request.
	 */
	ResponseBody getResponseBody(@ParamName("requestId") String requestId);

	/**
	 * Blocks URLs from loading.
	 */
	@Experimental
	void setBlockedURLs(@ParamName("urls") List<String> urls);

	/**
	 * This method sends a new XMLHttpRequest which is identical to the original one. The following parameters should be identical: method, url, async, request body, extra headers, withCredentials attribute, user, password.
	 */
	@Experimental
	void replayXHR(@ParamName("requestId") String requestId);

	/**
	 * Tells whether clearing browser cache is supported.
	 */
	@Returns("result")
	Boolean canClearBrowserCache();

	/**
	 * Clears browser cache.
	 */
	void clearBrowserCache();

	/**
	 * Tells whether clearing browser cookies is supported.
	 */
	@Returns("result")
	Boolean canClearBrowserCookies();

	/**
	 * Clears browser cookies.
	 */
	void clearBrowserCookies();

	/**
	 * Returns all browser cookies for the current URL. Depending on the backend support, will return detailed cookie information in the <code>cookies</code> field.
	 */
	@Experimental
	@Returns("cookies")
	List<Cookie> getCookies();

	/**
	 * Returns all browser cookies for the current URL. Depending on the backend support, will return detailed cookie information in the <code>cookies</code> field.
	 */
	@Experimental
	@Returns("cookies")
	List<Cookie> getCookies(@Optional @ParamName("urls") List<String> urls);

	/**
	 * Returns all browser cookies. Depending on the backend support, will return detailed cookie information in the <code>cookies</code> field.
	 */
	@Experimental
	@Returns("cookies")
	List<Cookie> getAllCookies();

	/**
	 * Deletes browser cookies with matching name and url or domain/path pair.
	 */
	@Experimental
	void deleteCookies(@ParamName("name") String name);

	/**
	 * Deletes browser cookies with matching name and url or domain/path pair.
	 */
	@Experimental
	void deleteCookies(@ParamName("name") String name, @Optional @ParamName("url") String url, @Optional @ParamName("domain") String domain, @Optional @ParamName("path") String path);

	/**
	 * Sets a cookie with the given cookie data; may overwrite equivalent cookies if they exist.
	 */
	@Experimental
	@Returns("success")
	Boolean setCookie(@ParamName("name") String name, @ParamName("value") String value);

	/**
	 * Sets a cookie with the given cookie data; may overwrite equivalent cookies if they exist.
	 */
	@Experimental
	@Returns("success")
	Boolean setCookie(@ParamName("name") String name, @ParamName("value") String value, @Optional @ParamName("url") String url, @Optional @ParamName("domain") String domain, @Optional @ParamName("path") String path, @Optional @ParamName("secure") Boolean secure, @Optional @ParamName("httpOnly") Boolean httpOnly, @Optional @ParamName("sameSite") CookieSameSite sameSite, @Optional @ParamName("expires") Double expires);

	/**
	 * Sets given cookies.
	 */
	@Experimental
	void setCookies(@ParamName("cookies") List<CookieParam> cookies);

	/**
	 * Tells whether emulation of network conditions is supported.
	 */
	@Experimental
	@Returns("result")
	Boolean canEmulateNetworkConditions();

	/**
	 * Activates emulation of network conditions.
	 */
	void emulateNetworkConditions(@ParamName("offline") Boolean offline, @ParamName("latency") Double latency, @ParamName("downloadThroughput") Double downloadThroughput, @ParamName("uploadThroughput") Double uploadThroughput);

	/**
	 * Activates emulation of network conditions.
	 */
	void emulateNetworkConditions(@ParamName("offline") Boolean offline, @ParamName("latency") Double latency, @ParamName("downloadThroughput") Double downloadThroughput, @ParamName("uploadThroughput") Double uploadThroughput, @Optional @ParamName("connectionType") ConnectionType connectionType);

	/**
	 * Toggles ignoring cache for each request. If <code>true</code>, cache will not be used.
	 */
	void setCacheDisabled(@ParamName("cacheDisabled") Boolean cacheDisabled);

	/**
	 * Toggles ignoring of service worker for each request.
	 */
	@Experimental
	void setBypassServiceWorker(@ParamName("bypass") Boolean bypass);

	/**
	 * For testing.
	 */
	@Experimental
	void setDataSizeLimitsForTest(@ParamName("maxTotalSize") Integer maxTotalSize, @ParamName("maxResourceSize") Integer maxResourceSize);

	/**
	 * Returns the DER-encoded certificate.
	 */
	@Experimental
	@Returns("tableNames")
	List<String> getCertificate(@ParamName("origin") String origin);

	/**
	 * Sets the requests to intercept that match a the provided patterns.
	 */
	@Experimental
	void setRequestInterceptionEnabled(@ParamName("enabled") Boolean enabled);

	/**
	 * Sets the requests to intercept that match a the provided patterns.
	 */
	@Experimental
	void setRequestInterceptionEnabled(@ParamName("enabled") Boolean enabled, @Optional @ParamName("patterns") List<String> patterns);

	/**
	 * Response to Network.requestIntercepted which either modifies the request to continue with any modifications, or blocks it, or completes it with the provided response bytes. If a network fetch occurs as a result which encounters a redirect an additional Network.requestIntercepted event will be sent with the same InterceptionId.
	 */
	@Experimental
	void continueInterceptedRequest(@ParamName("interceptionId") String interceptionId);

	/**
	 * Response to Network.requestIntercepted which either modifies the request to continue with any modifications, or blocks it, or completes it with the provided response bytes. If a network fetch occurs as a result which encounters a redirect an additional Network.requestIntercepted event will be sent with the same InterceptionId.
	 */
	@Experimental
	void continueInterceptedRequest(@ParamName("interceptionId") String interceptionId, @Optional @ParamName("errorReason") ErrorReason errorReason, @Optional @ParamName("rawResponse") String rawResponse, @Optional @ParamName("url") String url, @Optional @ParamName("method") String method, @Optional @ParamName("postData") String postData, @Optional @ParamName("headers") Headers headers, @Optional @ParamName("authChallengeResponse") AuthChallengeResponse authChallengeResponse);
}
