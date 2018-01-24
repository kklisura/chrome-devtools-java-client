package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import com.github.kklisura.cdtp.protocol.support.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.support.annotations.Returns;
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
	List<String> requestDatabaseNames(@ParamName("securityOrigin") String securityOrigin);

	/**
	 * Requests database with given name in given frame.
	 */
	@Returns("databaseWithObjectStores")
	DatabaseWithObjectStores requestDatabase(@ParamName("securityOrigin") String securityOrigin, @ParamName("databaseName") String databaseName);

	/**
	 * Requests data from object store or index.
	 */
	RequestData requestData(@ParamName("securityOrigin") String securityOrigin, @ParamName("databaseName") String databaseName, @ParamName("objectStoreName") String objectStoreName, @ParamName("indexName") String indexName, @ParamName("skipCount") Integer skipCount, @ParamName("pageSize") Integer pageSize);

	/**
	 * Requests data from object store or index.
	 */
	RequestData requestData(@ParamName("securityOrigin") String securityOrigin, @ParamName("databaseName") String databaseName, @ParamName("objectStoreName") String objectStoreName, @ParamName("indexName") String indexName, @ParamName("skipCount") Integer skipCount, @ParamName("pageSize") Integer pageSize, @Optional @ParamName("keyRange") KeyRange keyRange);

	/**
	 * Clears all entries from an object store.
	 */
	void clearObjectStore(@ParamName("securityOrigin") String securityOrigin, @ParamName("databaseName") String databaseName, @ParamName("objectStoreName") String objectStoreName);

	/**
	 * Deletes a database.
	 */
	void deleteDatabase(@ParamName("securityOrigin") String securityOrigin, @ParamName("databaseName") String databaseName);
}
