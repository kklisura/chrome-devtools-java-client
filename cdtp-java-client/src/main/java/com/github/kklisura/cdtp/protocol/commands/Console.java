package com.github.kklisura.cdtp.protocol.commands;

/**
 * This domain is deprecated - use Runtime or Log instead.
 */
@Deprecated
public interface Console {

	/**
	 * Enables console domain, sends the messages collected so far to the client by means of the <code>messageAdded</code> notification.
	 */
	void enable();

	/**
	 * Disables console domain, prevents further console messages from being reported to the client.
	 */
	void disable();

	/**
	 * Does nothing.
	 */
	void clearMessages();
}
