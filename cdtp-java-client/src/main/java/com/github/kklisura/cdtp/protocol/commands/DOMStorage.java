package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.types.domstorage.StorageId;
import java.util.List;

/**
 * Query and modify DOM storage.
 */
@Experimental
public interface DOMStorage {

	/**
	 * Enables storage tracking, storage events will now be delivered to the client.
	 */
	void enable();

	/**
	 * Disables storage tracking, prevents storage events from being sent to the client.
	 */
	void disable();

	void clear(StorageId storageId);

	List<List<String>> getDOMStorageItems(StorageId storageId);

	void setDOMStorageItem(StorageId storageId, String key, String value);

	void removeDOMStorageItem(StorageId storageId, String key);
}
