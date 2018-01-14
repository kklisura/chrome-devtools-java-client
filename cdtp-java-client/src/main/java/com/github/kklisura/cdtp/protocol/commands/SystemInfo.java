package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.types.systeminfo.Info;

/**
 * The SystemInfo domain defines methods and events for querying low-level system information.
 */
@Experimental
public interface SystemInfo {

	/**
	 * Returns information about the system.
	 */
	Info getInfo();
}
