package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;

/**
 * The Tethering domain defines methods and events for browser port binding.
 */
@Experimental
public interface Tethering {

	/**
	 * Request browser port binding.
	 */
	void bind(Integer port);

	/**
	 * Request browser port unbinding.
	 */
	void unbind(Integer port);
}
