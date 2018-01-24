package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.events.storage.CacheStorageContentUpdated;
import com.github.kklisura.cdtp.protocol.events.storage.CacheStorageListUpdated;
import com.github.kklisura.cdtp.protocol.support.annotations.EventName;
import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.support.types.EventHandler;
import com.github.kklisura.cdtp.protocol.support.types.EventListener;
import com.github.kklisura.cdtp.protocol.types.storage.UsageAndQuota;

@Experimental
public interface Storage {

	/**
	 * Clears storage for origin.
	 */
	void clearDataForOrigin(@ParamName("origin") String origin, @ParamName("storageTypes") String storageTypes);

	/**
	 * Returns usage and quota in bytes.
	 */
	UsageAndQuota getUsageAndQuota(@ParamName("origin") String origin);

	/**
	 * Registers origin to be notified when an update occurs to its cache storage list.
	 */
	void trackCacheStorageForOrigin(@ParamName("origin") String origin);

	/**
	 * Unregisters origin from receiving notifications for cache storage.
	 */
	void untrackCacheStorageForOrigin(@ParamName("origin") String origin);

	/**
	 * A cache has been added/deleted.
	 */
	@EventName("cacheStorageListUpdated")
	EventListener onCacheStorageListUpdated(EventHandler<CacheStorageListUpdated> eventListener);

	/**
	 * A cache's contents have been modified.
	 */
	@EventName("cacheStorageContentUpdated")
	EventListener onCacheStorageContentUpdated(EventHandler<CacheStorageContentUpdated> eventListener);
}
