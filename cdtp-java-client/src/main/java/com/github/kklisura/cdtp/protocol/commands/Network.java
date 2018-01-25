package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.events.network.DataReceived;
import com.github.kklisura.cdtp.protocol.events.network.EventSourceMessageReceived;
import com.github.kklisura.cdtp.protocol.events.network.LoadingFailed;
import com.github.kklisura.cdtp.protocol.events.network.LoadingFinished;
import com.github.kklisura.cdtp.protocol.events.network.RequestIntercepted;
import com.github.kklisura.cdtp.protocol.events.network.RequestServedFromCache;
import com.github.kklisura.cdtp.protocol.events.network.RequestWillBeSent;
import com.github.kklisura.cdtp.protocol.events.network.ResourceChangedPriority;
import com.github.kklisura.cdtp.protocol.events.network.ResponseReceived;
import com.github.kklisura.cdtp.protocol.events.network.WebSocketClosed;
import com.github.kklisura.cdtp.protocol.events.network.WebSocketCreated;
import com.github.kklisura.cdtp.protocol.events.network.WebSocketFrameError;
import com.github.kklisura.cdtp.protocol.events.network.WebSocketFrameReceived;
import com.github.kklisura.cdtp.protocol.events.network.WebSocketFrameSent;
import com.github.kklisura.cdtp.protocol.events.network.WebSocketHandshakeResponseReceived;
import com.github.kklisura.cdtp.protocol.events.network.WebSocketWillSendHandshakeRequest;
import com.github.kklisura.cdtp.protocol.support.annotations.EventName;
import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import com.github.kklisura.cdtp.protocol.support.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.support.annotations.Returns;
import com.github.kklisura.cdtp.protocol.support.types.EventHandler;
import com.github.kklisura.cdtp.protocol.support.types.EventListener;
import com.github.kklisura.cdtp.protocol.types.network.AuthChallengeResponse;
import com.github.kklisura.cdtp.protocol.types.network.ConnectionType;
import com.github.kklisura.cdtp.protocol.types.network.Cookie;
import com.github.kklisura.cdtp.protocol.types.network.CookieParam;
import com.github.kklisura.cdtp.protocol.types.network.CookieSameSite;
import com.github.kklisura.cdtp.protocol.types.network.ErrorReason;
import com.github.kklisura.cdtp.protocol.types.network.ResponseBody;
import java.util.List;
import java.util.Map;

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
	 *
	 * @param maxTotalBufferSize Buffer size in bytes to use when preserving network payloads (XHRs, etc).
	 * @param maxResourceBufferSize Per-resource buffer size in bytes to use when preserving network payloads (XHRs, etc).
	 */
	void enable(@Experimental @Optional @ParamName("maxTotalBufferSize") Integer maxTotalBufferSize, @Experimental @Optional @ParamName("maxResourceBufferSize") Integer maxResourceBufferSize);

	/**
	 * Disables network tracking, prevents network events from being sent to the client.
	 */
	void disable();

	/**
	 * Allows overriding user agent with the given string.
	 *
	 * @param userAgent User agent to use.
	 */
	void setUserAgentOverride(@ParamName("userAgent") String userAgent);

	/**
	 * Specifies whether to always send extra HTTP headers with the requests from this page.
	 *
	 * @param headers Map with extra HTTP headers.
	 */
	void setExtraHTTPHeaders(@ParamName("headers") Map<String, Object> headers);

	/**
	 * Returns content served for the given request.
	 *
	 * @param requestId Identifier of the network request to get content for.
	 */
	ResponseBody getResponseBody(@ParamName("requestId") String requestId);

	/**
	 * Blocks URLs from loading.
	 *
	 * @param urls URL patterns to block. Wildcards ('*') are allowed.
	 */
	@Experimental
	void setBlockedURLs(@ParamName("urls") List<String> urls);

	/**
	 * This method sends a new XMLHttpRequest which is identical to the original one. The following parameters should be identical: method, url, async, request body, extra headers, withCredentials attribute, user, password.
	 *
	 * @param requestId Identifier of XHR to replay.
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
	 *
	 * @param urls The list of URLs for which applicable cookies will be fetched
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
	 *
	 * @param name Name of the cookies to remove.
	 */
	@Experimental
	void deleteCookies(@ParamName("name") String name);

	/**
	 * Deletes browser cookies with matching name and url or domain/path pair.
	 *
	 * @param name Name of the cookies to remove.
	 * @param url If specified, deletes all the cookies with the given name where domain and path match provided URL.
	 * @param domain If specified, deletes only cookies with the exact domain.
	 * @param path If specified, deletes only cookies with the exact path.
	 */
	@Experimental
	void deleteCookies(@ParamName("name") String name, @Optional @ParamName("url") String url, @Optional @ParamName("domain") String domain, @Optional @ParamName("path") String path);

	/**
	 * Sets a cookie with the given cookie data; may overwrite equivalent cookies if they exist.
	 *
	 * @param name Cookie name.
	 * @param value Cookie value.
	 */
	@Experimental
	@Returns("success")
	Boolean setCookie(@ParamName("name") String name, @ParamName("value") String value);

	/**
	 * Sets a cookie with the given cookie data; may overwrite equivalent cookies if they exist.
	 *
	 * @param name Cookie name.
	 * @param value Cookie value.
	 * @param url The request-URI to associate with the setting of the cookie. This value can affect the default domain and path values of the created cookie.
	 * @param domain Cookie domain.
	 * @param path Cookie path.
	 * @param secure True if cookie is secure.
	 * @param httpOnly True if cookie is http-only.
	 * @param sameSite Cookie SameSite type.
	 * @param expires Cookie expiration date, session cookie if not set
	 */
	@Experimental
	@Returns("success")
	Boolean setCookie(@ParamName("name") String name, @ParamName("value") String value, @Optional @ParamName("url") String url, @Optional @ParamName("domain") String domain, @Optional @ParamName("path") String path, @Optional @ParamName("secure") Boolean secure, @Optional @ParamName("httpOnly") Boolean httpOnly, @Optional @ParamName("sameSite") CookieSameSite sameSite, @Optional @ParamName("expires") Double expires);

	/**
	 * Sets given cookies.
	 *
	 * @param cookies Cookies to be set.
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
	 *
	 * @param offline True to emulate internet disconnection.
	 * @param latency Minimum latency from request sent to response headers received (ms).
	 * @param downloadThroughput Maximal aggregated download throughput (bytes/sec). -1 disables download throttling.
	 * @param uploadThroughput Maximal aggregated upload throughput (bytes/sec).  -1 disables upload throttling.
	 */
	void emulateNetworkConditions(@ParamName("offline") Boolean offline, @ParamName("latency") Double latency, @ParamName("downloadThroughput") Double downloadThroughput, @ParamName("uploadThroughput") Double uploadThroughput);

	/**
	 * Activates emulation of network conditions.
	 *
	 * @param offline True to emulate internet disconnection.
	 * @param latency Minimum latency from request sent to response headers received (ms).
	 * @param downloadThroughput Maximal aggregated download throughput (bytes/sec). -1 disables download throttling.
	 * @param uploadThroughput Maximal aggregated upload throughput (bytes/sec).  -1 disables upload throttling.
	 * @param connectionType Connection type if known.
	 */
	void emulateNetworkConditions(@ParamName("offline") Boolean offline, @ParamName("latency") Double latency, @ParamName("downloadThroughput") Double downloadThroughput, @ParamName("uploadThroughput") Double uploadThroughput, @Optional @ParamName("connectionType") ConnectionType connectionType);

	/**
	 * Toggles ignoring cache for each request. If <code>true</code>, cache will not be used.
	 *
	 * @param cacheDisabled Cache disabled state.
	 */
	void setCacheDisabled(@ParamName("cacheDisabled") Boolean cacheDisabled);

	/**
	 * Toggles ignoring of service worker for each request.
	 *
	 * @param bypass Bypass service worker and load from network.
	 */
	@Experimental
	void setBypassServiceWorker(@ParamName("bypass") Boolean bypass);

	/**
	 * For testing.
	 *
	 * @param maxTotalSize Maximum total buffer size.
	 * @param maxResourceSize Maximum per-resource size.
	 */
	@Experimental
	void setDataSizeLimitsForTest(@ParamName("maxTotalSize") Integer maxTotalSize, @ParamName("maxResourceSize") Integer maxResourceSize);

	/**
	 * Returns the DER-encoded certificate.
	 *
	 * @param origin Origin to get certificate for.
	 */
	@Experimental
	@Returns("tableNames")
	List<String> getCertificate(@ParamName("origin") String origin);

	/**
	 * Sets the requests to intercept that match a the provided patterns.
	 *
	 * @param enabled Whether requests should be intercepted. If patterns is not set, matches all and resets any previously set patterns. Other parameters are ignored if false.
	 */
	@Experimental
	void setRequestInterceptionEnabled(@ParamName("enabled") Boolean enabled);

	/**
	 * Sets the requests to intercept that match a the provided patterns.
	 *
	 * @param enabled Whether requests should be intercepted. If patterns is not set, matches all and resets any previously set patterns. Other parameters are ignored if false.
	 * @param patterns URLs matching any of these patterns will be forwarded and wait for the corresponding continueInterceptedRequest call. Wildcards ('*' -> zero or more, '?' -> exactly one) are allowed. Escape character is backslash. If omitted equivalent to ['*'] (intercept all).
	 */
	@Experimental
	void setRequestInterceptionEnabled(@ParamName("enabled") Boolean enabled, @Optional @ParamName("patterns") List<String> patterns);

	/**
	 * Response to Network.requestIntercepted which either modifies the request to continue with any modifications, or blocks it, or completes it with the provided response bytes. If a network fetch occurs as a result which encounters a redirect an additional Network.requestIntercepted event will be sent with the same InterceptionId.
	 *
	 * @param interceptionId
	 */
	@Experimental
	void continueInterceptedRequest(@ParamName("interceptionId") String interceptionId);

	/**
	 * Response to Network.requestIntercepted which either modifies the request to continue with any modifications, or blocks it, or completes it with the provided response bytes. If a network fetch occurs as a result which encounters a redirect an additional Network.requestIntercepted event will be sent with the same InterceptionId.
	 *
	 * @param interceptionId
	 * @param errorReason If set this causes the request to fail with the given reason. Passing <code>Aborted</code> for requests marked with <code>isNavigationRequest</code> also cancels the navigation. Must not be set in response to an authChallenge.
	 * @param rawResponse If set the requests completes using with the provided base64 encoded raw response, including HTTP status line and headers etc... Must not be set in response to an authChallenge.
	 * @param url If set the request url will be modified in a way that's not observable by page. Must not be set in response to an authChallenge.
	 * @param method If set this allows the request method to be overridden. Must not be set in response to an authChallenge.
	 * @param postData If set this allows postData to be set. Must not be set in response to an authChallenge.
	 * @param headers If set this allows the request headers to be changed. Must not be set in response to an authChallenge.
	 * @param authChallengeResponse Response to a requestIntercepted with an authChallenge. Must not be set otherwise.
	 */
	@Experimental
	void continueInterceptedRequest(@ParamName("interceptionId") String interceptionId, @Optional @ParamName("errorReason") ErrorReason errorReason, @Optional @ParamName("rawResponse") String rawResponse, @Optional @ParamName("url") String url, @Optional @ParamName("method") String method, @Optional @ParamName("postData") String postData, @Optional @ParamName("headers") Map<String, Object> headers, @Optional @ParamName("authChallengeResponse") AuthChallengeResponse authChallengeResponse);

	/**
	 * Fired when resource loading priority is changed
	 */
	@EventName("resourceChangedPriority")
	@Experimental
	EventListener onResourceChangedPriority(EventHandler<ResourceChangedPriority> eventListener);

	/**
	 * Fired when page is about to send HTTP request.
	 */
	@EventName("requestWillBeSent")
	EventListener onRequestWillBeSent(EventHandler<RequestWillBeSent> eventListener);

	/**
	 * Fired if request ended up loading from cache.
	 */
	@EventName("requestServedFromCache")
	EventListener onRequestServedFromCache(EventHandler<RequestServedFromCache> eventListener);

	/**
	 * Fired when HTTP response is available.
	 */
	@EventName("responseReceived")
	EventListener onResponseReceived(EventHandler<ResponseReceived> eventListener);

	/**
	 * Fired when data chunk was received over the network.
	 */
	@EventName("dataReceived")
	EventListener onDataReceived(EventHandler<DataReceived> eventListener);

	/**
	 * Fired when HTTP request has finished loading.
	 */
	@EventName("loadingFinished")
	EventListener onLoadingFinished(EventHandler<LoadingFinished> eventListener);

	/**
	 * Fired when HTTP request has failed to load.
	 */
	@EventName("loadingFailed")
	EventListener onLoadingFailed(EventHandler<LoadingFailed> eventListener);

	/**
	 * Fired when WebSocket is about to initiate handshake.
	 */
	@EventName("webSocketWillSendHandshakeRequest")
	@Experimental
	EventListener onWebSocketWillSendHandshakeRequest(EventHandler<WebSocketWillSendHandshakeRequest> eventListener);

	/**
	 * Fired when WebSocket handshake response becomes available.
	 */
	@EventName("webSocketHandshakeResponseReceived")
	@Experimental
	EventListener onWebSocketHandshakeResponseReceived(EventHandler<WebSocketHandshakeResponseReceived> eventListener);

	/**
	 * Fired upon WebSocket creation.
	 */
	@EventName("webSocketCreated")
	@Experimental
	EventListener onWebSocketCreated(EventHandler<WebSocketCreated> eventListener);

	/**
	 * Fired when WebSocket is closed.
	 */
	@EventName("webSocketClosed")
	@Experimental
	EventListener onWebSocketClosed(EventHandler<WebSocketClosed> eventListener);

	/**
	 * Fired when WebSocket frame is received.
	 */
	@EventName("webSocketFrameReceived")
	@Experimental
	EventListener onWebSocketFrameReceived(EventHandler<WebSocketFrameReceived> eventListener);

	/**
	 * Fired when WebSocket frame error occurs.
	 */
	@EventName("webSocketFrameError")
	@Experimental
	EventListener onWebSocketFrameError(EventHandler<WebSocketFrameError> eventListener);

	/**
	 * Fired when WebSocket frame is sent.
	 */
	@EventName("webSocketFrameSent")
	@Experimental
	EventListener onWebSocketFrameSent(EventHandler<WebSocketFrameSent> eventListener);

	/**
	 * Fired when EventSource message is received.
	 */
	@EventName("eventSourceMessageReceived")
	@Experimental
	EventListener onEventSourceMessageReceived(EventHandler<EventSourceMessageReceived> eventListener);

	/**
	 * Details of an intercepted HTTP request, which must be either allowed, blocked, modified or mocked.
	 */
	@EventName("requestIntercepted")
	@Experimental
	EventListener onRequestIntercepted(EventHandler<RequestIntercepted> eventListener);
}
