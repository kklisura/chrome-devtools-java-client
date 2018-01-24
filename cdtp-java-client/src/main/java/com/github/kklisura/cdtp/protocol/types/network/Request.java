package com.github.kklisura.cdtp.protocol.types.network;

import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import com.github.kklisura.cdtp.protocol.types.security.MixedContentType;
import java.util.Map;

/**
 * HTTP request data.
 */
public class Request {

	private String url;

	private String method;

	private Map<String, Object> headers;

	@Optional
	private String postData;

	@Optional
	private MixedContentType mixedContentType;

	private ResourcePriority initialPriority;

	private ReferrerPolicy referrerPolicy;

	@Optional
	private Boolean isLinkPreload;

	/**
	 * Request URL.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Request URL.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * HTTP request method.
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * HTTP request method.
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * HTTP request headers.
	 */
	public Map<String, Object> getHeaders() {
		return headers;
	}

	/**
	 * HTTP request headers.
	 */
	public void setHeaders(Map<String, Object> headers) {
		this.headers = headers;
	}

	/**
	 * HTTP POST request data.
	 */
	public String getPostData() {
		return postData;
	}

	/**
	 * HTTP POST request data.
	 */
	public void setPostData(String postData) {
		this.postData = postData;
	}

	/**
	 * The mixed content type of the request.
	 */
	public MixedContentType getMixedContentType() {
		return mixedContentType;
	}

	/**
	 * The mixed content type of the request.
	 */
	public void setMixedContentType(MixedContentType mixedContentType) {
		this.mixedContentType = mixedContentType;
	}

	/**
	 * Priority of the resource request at the time request is sent.
	 */
	public ResourcePriority getInitialPriority() {
		return initialPriority;
	}

	/**
	 * Priority of the resource request at the time request is sent.
	 */
	public void setInitialPriority(ResourcePriority initialPriority) {
		this.initialPriority = initialPriority;
	}

	/**
	 * The referrer policy of the request, as defined in https://www.w3.org/TR/referrer-policy/
	 */
	public ReferrerPolicy getReferrerPolicy() {
		return referrerPolicy;
	}

	/**
	 * The referrer policy of the request, as defined in https://www.w3.org/TR/referrer-policy/
	 */
	public void setReferrerPolicy(ReferrerPolicy referrerPolicy) {
		this.referrerPolicy = referrerPolicy;
	}

	/**
	 * Whether is loaded via link preload.
	 */
	public Boolean getIsLinkPreload() {
		return isLinkPreload;
	}

	/**
	 * Whether is loaded via link preload.
	 */
	public void setIsLinkPreload(Boolean isLinkPreload) {
		this.isLinkPreload = isLinkPreload;
	}
}
