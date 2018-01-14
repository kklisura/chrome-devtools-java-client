package com.github.kklisura.cdtp.protocol.types.cachestorage;

/**
 * Cache identifier.
 */
public class Cache {

	private String cacheId;

	private String securityOrigin;

	private String cacheName;

	/**
	 * An opaque unique id of the cache.
	 */
	public String getCacheId() {
		return cacheId;
	}

	/**
	 * An opaque unique id of the cache.
	 */
	public void setCacheId(String cacheId) {
		this.cacheId = cacheId;
	}

	/**
	 * Security origin of the cache.
	 */
	public String getSecurityOrigin() {
		return securityOrigin;
	}

	/**
	 * Security origin of the cache.
	 */
	public void setSecurityOrigin(String securityOrigin) {
		this.securityOrigin = securityOrigin;
	}

	/**
	 * The name of the cache.
	 */
	public String getCacheName() {
		return cacheName;
	}

	/**
	 * The name of the cache.
	 */
	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}
}
