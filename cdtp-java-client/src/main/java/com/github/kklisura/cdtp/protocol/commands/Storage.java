package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.types.storage.UsageAndQuota;

@Experimental
public interface Storage {

	/**
	 * Clears storage for origin.
	 */
	void clearDataForOrigin(String origin, String storageTypes);

	/**
	 * Returns usage and quota in bytes.
	 */
	UsageAndQuota getUsageAndQuota(String origin);

	/**
	 * Registers origin to be notified when an update occurs to its cache storage list.
	 */
	void trackCacheStorageForOrigin(String origin);

	/**
	 * Unregisters origin from receiving notifications for cache storage.
	 */
	void untrackCacheStorageForOrigin(String origin);
}
