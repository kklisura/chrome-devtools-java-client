package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.events.database.AddDatabase;
import com.github.kklisura.cdtp.protocol.support.annotations.EventName;
import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.support.annotations.Returns;
import com.github.kklisura.cdtp.protocol.support.types.EventHandler;
import com.github.kklisura.cdtp.protocol.support.types.EventListener;
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

	@Returns("tableNames")
	List<String> getDatabaseTableNames(@ParamName("databaseId") String databaseId);

	ExecuteSQL executeSQL(@ParamName("databaseId") String databaseId, @ParamName("query") String query);

	@EventName("addDatabase")
	EventListener onAddDatabase(EventHandler<AddDatabase> eventListener);
}
