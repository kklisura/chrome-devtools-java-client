package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.types.database.ExecuteSQL;
import java.util.List;

@Experimental
public interface Database {

	/**
	 * Enables database tracking, database events will now be delivered to the client.
	 */
	void enable();

	/**
	 * Disables database tracking, prevents database events from being sent to the client.
	 */
	void disable();

	List<String> getDatabaseTableNames(String databaseId);

	ExecuteSQL executeSQL(String databaseId, String query);
}
