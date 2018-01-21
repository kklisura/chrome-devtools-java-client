package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.types.log.ViolationSetting;
import java.util.List;

/**
 * Provides access to log entries.
 */
@Experimental
public interface Log {

	/**
	 * Enables log domain, sends the entries collected so far to the client by means of the <code>entryAdded</code> notification.
	 */
	void enable();

	/**
	 * Disables log domain, prevents further log entries from being reported to the client.
	 */
	void disable();

	/**
	 * Clears the log.
	 */
	void clear();

	/**
	 * start violation reporting.
	 */
	void startViolationsReport(@ParamName("config") List<ViolationSetting> config);

	/**
	 * Stop violation reporting.
	 */
	void stopViolationsReport();
}
