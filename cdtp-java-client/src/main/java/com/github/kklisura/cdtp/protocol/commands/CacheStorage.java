package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.types.cachestorage.Cache;
import com.github.kklisura.cdtp.protocol.types.cachestorage.CachedResponse;
import com.github.kklisura.cdtp.protocol.types.cachestorage.RequestEntries;
import java.util.List;

@Experimental
public interface CacheStorage {

	/**
	 * Requests cache names.
	 */
	List<Cache> requestCacheNames(String securityOrigin);

	/**
	 * Requests data from cache.
	 */
	RequestEntries requestEntries(String cacheId, Integer skipCount, Integer pageSize);

	/**
	 * Deletes a cache.
	 */
	void deleteCache(String cacheId);

	/**
	 * Deletes a cache entry.
	 */
	void deleteEntry(String cacheId, String request);

	/**
	 * Fetches cache entry.
	 */
	CachedResponse requestCachedResponse(String cacheId, String requestURL);
}
