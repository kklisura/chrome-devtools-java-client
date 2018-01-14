package com.github.kklisura.cdtp.protocol.types.applicationcache;

/**
 * Detailed application cache resource information.
 */
public class ApplicationCacheResource {

	private String url;

	private Integer size;

	private String type;

	/**
	 * Resource url.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Resource url.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Resource size.
	 */
	public Integer getSize() {
		return size;
	}

	/**
	 * Resource size.
	 */
	public void setSize(Integer size) {
		this.size = size;
	}

	/**
	 * Resource type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Resource type.
	 */
	public void setType(String type) {
		this.type = type;
	}
}
