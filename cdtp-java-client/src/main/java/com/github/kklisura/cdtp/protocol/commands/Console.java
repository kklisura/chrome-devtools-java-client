package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.events.console.MessageAdded;
import com.github.kklisura.cdtp.protocol.support.annotations.EventName;
import com.github.kklisura.cdtp.protocol.support.types.EventHandler;
import com.github.kklisura.cdtp.protocol.support.types.EventListener;

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

	/**
	 * Issued when new console message is added.
	 */
	@EventName("messageAdded")
	EventListener onMessageAdded(EventHandler<MessageAdded> eventListener);
}
