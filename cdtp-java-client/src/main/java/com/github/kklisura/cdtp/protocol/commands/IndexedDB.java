package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.Optional;
import com.github.kklisura.cdtp.protocol.annotations.Returns;
import com.github.kklisura.cdtp.protocol.types.indexeddb.DatabaseWithObjectStores;
import com.github.kklisura.cdtp.protocol.types.indexeddb.KeyRange;
import com.github.kklisura.cdtp.protocol.types.indexeddb.RequestData;
import java.util.List;

@Experimental
public interface IndexedDB {

	/**
	 * Enables events from backend.
	 */
	void enable();

	/**
	 * Disables events from backend.
	 */
	void disable();

	/**
	 * Requests database names for given security origin.
	 */
	@Returns("databaseNames")
	List<String> requestDatabaseNames(String securityOrigin);

	/**
	 * Requests database with given name in given frame.
	 */
	@Returns("databaseWithObjectStores")
	DatabaseWithObjectStores requestDatabase(String securityOrigin, String databaseName);

	/**
	 * Requests data from object store or index.
	 */
	RequestData requestData(String securityOrigin, String databaseName, String objectStoreName, String indexName, Integer skipCount, Integer pageSize, @Optional KeyRange keyRange);

	/**
	 * Clears all entries from an object store.
	 */
	void clearObjectStore(String securityOrigin, String databaseName, String objectStoreName);

	/**
	 * Deletes a database.
	 */
	void deleteDatabase(String securityOrigin, String databaseName);
}
