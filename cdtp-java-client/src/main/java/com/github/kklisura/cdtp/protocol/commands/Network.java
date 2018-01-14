package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.Optional;
import com.github.kklisura.cdtp.protocol.types.network.Headers;
import com.github.kklisura.cdtp.protocol.types.network.ResponseBody;
import java.util.List;
import com.github.kklisura.cdtp.protocol.types.network.Cookie;
import com.github.kklisura.cdtp.protocol.types.network.CookieSameSite;
import com.github.kklisura.cdtp.protocol.types.network.CookieParam;
import com.github.kklisura.cdtp.protocol.types.network.ConnectionType;
import com.github.kklisura.cdtp.protocol.types.network.ErrorReason;
import com.github.kklisura.cdtp.protocol.types.network.AuthChallengeResponse;

/**
 * Network domain allows tracking network activities of the page. It exposes information about http, file, data and other requests and responses, their headers, bodies, timing, etc.
 */
public interface Network {

	/**
	 * Enables network tracking, network events will now be delivered to the client.
	 */
	void enable(@Experimental @Optional Integer maxTotalBufferSize, @Experimental @Optional Integer maxResourceBufferSize);

	/**
	 * Disables network tracking, prevents network events from being sent to the client.
	 */
	void disable();

	/**
	 * Allows overriding user agent with the given string.
	 */
	void setUserAgentOverride(String userAgent);

	/**
	 * Specifies whether to always send extra HTTP headers with the requests from this page.
	 */
	void setExtraHTTPHeaders(Headers headers);

	/**
	 * Returns content served for the given request.
	 */
	ResponseBody getResponseBody(String requestId);

	/**
	 * Blocks URLs from loading.
	 */
	@Experimental
	void setBlockedURLs(List<String> urls);

	/**
	 * This method sends a new XMLHttpRequest which is identical to the original one. The following parameters should be identical: method, url, async, request body, extra headers, withCredentials attribute, user, password.
	 */
	@Experimental
	void replayXHR(String requestId);

	/**
	 * Tells whether clearing browser cache is supported.
	 */
	Boolean canClearBrowserCache();

	/**
	 * Clears browser cache.
	 */
	void clearBrowserCache();

	/**
	 * Tells whether clearing browser cookies is supported.
	 */
	Boolean canClearBrowserCookies();

	/**
	 * Clears browser cookies.
	 */
	void clearBrowserCookies();

	/**
	 * Returns all browser cookies for the current URL. Depending on the backend support, will return detailed cookie information in the <code>cookies</code> field.
	 */
	@Experimental
	List<Cookie> getCookies(@Optional List<String> urls);

	/**
	 * Returns all browser cookies. Depending on the backend support, will return detailed cookie information in the <code>cookies</code> field.
	 */
	@Experimental
	List<Cookie> getAllCookies();

	/**
	 * Deletes browser cookies with matching name and url or domain/path pair.
	 */
	@Experimental
	void deleteCookies(String name, @Optional String url, @Optional String domain, @Optional String path);

	/**
	 * Sets a cookie with the given cookie data; may overwrite equivalent cookies if they exist.
	 */
	@Experimental
	Boolean setCookie(String name, String value, @Optional String url, @Optional String domain, @Optional String path, @Optional Boolean secure, @Optional Boolean httpOnly, @Optional CookieSameSite sameSite, @Optional Double expires);

	/**
	 * Sets given cookies.
	 */
	@Experimental
	void setCookies(List<CookieParam> cookies);

	/**
	 * Tells whether emulation of network conditions is supported.
	 */
	@Experimental
	Boolean canEmulateNetworkConditions();

	/**
	 * Activates emulation of network conditions.
	 */
	void emulateNetworkConditions(Boolean offline, Double latency, Double downloadThroughput, Double uploadThroughput, @Optional ConnectionType connectionType);

	/**
	 * Toggles ignoring cache for each request. If <code>true</code>, cache will not be used.
	 */
	void setCacheDisabled(Boolean cacheDisabled);

	/**
	 * Toggles ignoring of service worker for each request.
	 */
	@Experimental
	void setBypassServiceWorker(Boolean bypass);

	/**
	 * For testing.
	 */
	@Experimental
	void setDataSizeLimitsForTest(Integer maxTotalSize, Integer maxResourceSize);

	/**
	 * Returns the DER-encoded certificate.
	 */
	@Experimental
	List<String> getCertificate(String origin);

	/**
	 * Sets the requests to intercept that match a the provided patterns.
	 */
	@Experimental
	void setRequestInterceptionEnabled(Boolean enabled, @Optional List<String> patterns);

	/**
	 * Response to Network.requestIntercepted which either modifies the request to continue with any modifications, or blocks it, or completes it with the provided response bytes. If a network fetch occurs as a result which encounters a redirect an additional Network.requestIntercepted event will be sent with the same InterceptionId.
	 */
	@Experimental
	void continueInterceptedRequest(String interceptionId, @Optional ErrorReason errorReason, @Optional String rawResponse, @Optional String url, @Optional String method, @Optional String postData, @Optional Headers headers, @Optional AuthChallengeResponse authChallengeResponse);
}
