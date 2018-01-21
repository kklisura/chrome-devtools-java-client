package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.annotations.Returns;
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

	void clear(@ParamName("storageId") StorageId storageId);

	@Returns("entries")
	List<List<String>> getDOMStorageItems(@ParamName("storageId") StorageId storageId);

	void setDOMStorageItem(@ParamName("storageId") StorageId storageId, @ParamName("key") String key, @ParamName("value") String value);

	void removeDOMStorageItem(@ParamName("storageId") StorageId storageId, @ParamName("key") String key);
}
