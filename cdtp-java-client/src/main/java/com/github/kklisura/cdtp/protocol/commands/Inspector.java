package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;

@Experimental
public interface Inspector {

	/**
	 * Enables inspector domain notifications.
	 */
	void enable();

	/**
	 * Disables inspector domain notifications.
	 */
	void disable();
}
