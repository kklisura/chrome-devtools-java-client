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
	 *
	 * @param securityOrigin Security origin.
	 */
	@Returns("caches")
	List<Cache> requestCacheNames(@ParamName("securityOrigin") String securityOrigin);

	/**
	 * Requests data from cache.
	 *
	 * @param cacheId ID of cache to get entries from.
	 * @param skipCount Number of records to skip.
	 * @param pageSize Number of records to fetch.
	 */
	RequestEntries requestEntries(@ParamName("cacheId") String cacheId, @ParamName("skipCount") Integer skipCount, @ParamName("pageSize") Integer pageSize);

	/**
	 * Deletes a cache.
	 *
	 * @param cacheId Id of cache for deletion.
	 */
	void deleteCache(@ParamName("cacheId") String cacheId);

	/**
	 * Deletes a cache entry.
	 *
	 * @param cacheId Id of cache where the entry will be deleted.
	 * @param request URL spec of the request.
	 */
	void deleteEntry(@ParamName("cacheId") String cacheId, @ParamName("request") String request);

	/**
	 * Fetches cache entry.
	 *
	 * @param cacheId Id of cache that contains the enty.
	 * @param requestURL URL spec of the request.
	 */
	@Returns("response")
	CachedResponse requestCachedResponse(@ParamName("cacheId") String cacheId, @ParamName("requestURL") String requestURL);
}
