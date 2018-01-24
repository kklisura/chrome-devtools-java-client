package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.support.annotations.Returns;
import com.github.kklisura.cdtp.protocol.types.cachestorage.Cache;
import com.github.kklisura.cdtp.protocol.types.cachestorage.CachedResponse;
import com.github.kklisura.cdtp.protocol.types.cachestorage.RequestEntries;
import java.util.List;

@Experimental
public interface CacheStorage {

	/**
	 * Requests cache names.
	 */
	@Returns("caches")
	List<Cache> requestCacheNames(@ParamName("securityOrigin") String securityOrigin);

	/**
	 * Requests data from cache.
	 */
	RequestEntries requestEntries(@ParamName("cacheId") String cacheId, @ParamName("skipCount") Integer skipCount, @ParamName("pageSize") Integer pageSize);

	/**
	 * Deletes a cache.
	 */
	void deleteCache(@ParamName("cacheId") String cacheId);

	/**
	 * Deletes a cache entry.
	 */
	void deleteEntry(@ParamName("cacheId") String cacheId, @ParamName("request") String request);

	/**
	 * Fetches cache entry.
	 */
	@Returns("response")
	CachedResponse requestCachedResponse(@ParamName("cacheId") String cacheId, @ParamName("requestURL") String requestURL);
}
