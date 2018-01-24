package com.github.kklisura.cdtp.protocol.events.network;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import com.github.kklisura.cdtp.protocol.types.network.AuthChallenge;
import com.github.kklisura.cdtp.protocol.types.network.Request;
import com.github.kklisura.cdtp.protocol.types.page.ResourceType;
import java.util.Map;

/**
 * Details of an intercepted HTTP request, which must be either allowed, blocked, modified or mocked.
 */
@Experimental
public class RequestIntercepted {

	private String interceptionId;

	private Request request;

	private ResourceType resourceType;

	private Boolean isNavigationRequest;

	@Optional
	private Map<String, Object> redirectHeaders;

	@Optional
	private Integer redirectStatusCode;

	@Optional
	private String redirectUrl;

	@Optional
	private AuthChallenge authChallenge;

	/**
	 * Each request the page makes will have a unique id, however if any redirects are encountered while processing that fetch, they will be reported with the same id as the original fetch. Likewise if HTTP authentication is needed then the same fetch id will be used.
	 */
	public String getInterceptionId() {
		return interceptionId;
	}

	/**
	 * Each request the page makes will have a unique id, however if any redirects are encountered while processing that fetch, they will be reported with the same id as the original fetch. Likewise if HTTP authentication is needed then the same fetch id will be used.
	 */
	public void setInterceptionId(String interceptionId) {
		this.interceptionId = interceptionId;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	/**
	 * How the requested resource will be used.
	 */
	public ResourceType getResourceType() {
		return resourceType;
	}

	/**
	 * How the requested resource will be used.
	 */
	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	/**
	 * Whether this is a navigation request, which can abort the navigation completely.
	 */
	public Boolean getIsNavigationRequest() {
		return isNavigationRequest;
	}

	/**
	 * Whether this is a navigation request, which can abort the navigation completely.
	 */
	public void setIsNavigationRequest(Boolean isNavigationRequest) {
		this.isNavigationRequest = isNavigationRequest;
	}

	/**
	 * HTTP response headers, only sent if a redirect was intercepted.
	 */
	public Map<String, Object> getRedirectHeaders() {
		return redirectHeaders;
	}

	/**
	 * HTTP response headers, only sent if a redirect was intercepted.
	 */
	public void setRedirectHeaders(Map<String, Object> redirectHeaders) {
		this.redirectHeaders = redirectHeaders;
	}

	/**
	 * HTTP response code, only sent if a redirect was intercepted.
	 */
	public Integer getRedirectStatusCode() {
		return redirectStatusCode;
	}

	/**
	 * HTTP response code, only sent if a redirect was intercepted.
	 */
	public void setRedirectStatusCode(Integer redirectStatusCode) {
		this.redirectStatusCode = redirectStatusCode;
	}

	/**
	 * Redirect location, only sent if a redirect was intercepted.
	 */
	public String getRedirectUrl() {
		return redirectUrl;
	}

	/**
	 * Redirect location, only sent if a redirect was intercepted.
	 */
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	/**
	 * Details of the Authorization Challenge encountered. If this is set then continueInterceptedRequest must contain an authChallengeResponse.
	 */
	public AuthChallenge getAuthChallenge() {
		return authChallenge;
	}

	/**
	 * Details of the Authorization Challenge encountered. If this is set then continueInterceptedRequest must contain an authChallengeResponse.
	 */
	public void setAuthChallenge(AuthChallenge authChallenge) {
		this.authChallenge = authChallenge;
	}
}
