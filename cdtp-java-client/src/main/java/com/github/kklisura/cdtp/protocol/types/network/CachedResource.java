package com.github.kklisura.cdtp.protocol.types.network;

import com.github.kklisura.cdtp.protocol.types.page.ResourceType;
import com.github.kklisura.cdtp.protocol.annotations.Optional;

/**
 * Information about the cached resource.
 */
public class CachedResource {

	private String url;

	private ResourceType type;

	@Optional
	private Response response;

	private Double bodySize;

	/**
	 * Resource URL. This is the url of the original network request.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Resource URL. This is the url of the original network request.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Type of this resource.
	 */
	public ResourceType getType() {
		return type;
	}

	/**
	 * Type of this resource.
	 */
	public void setType(ResourceType type) {
		this.type = type;
	}

	/**
	 * Cached response data.
	 */
	public Response getResponse() {
		return response;
	}

	/**
	 * Cached response data.
	 */
	public void setResponse(Response response) {
		this.response = response;
	}

	/**
	 * Cached response body size.
	 */
	public Double getBodySize() {
		return bodySize;
	}

	/**
	 * Cached response body size.
	 */
	public void setBodySize(Double bodySize) {
		this.bodySize = bodySize;
	}
}
