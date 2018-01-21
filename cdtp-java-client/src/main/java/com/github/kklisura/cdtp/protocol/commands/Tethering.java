package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.ParamName;

/**
 * The Tethering domain defines methods and events for browser port binding.
 */
@Experimental
public interface Tethering {

	/**
	 * Request browser port binding.
	 */
	void bind(@ParamName("port") Integer port);

	/**
	 * Request browser port unbinding.
	 */
	void unbind(@ParamName("port") Integer port);
}
