package com.github.kklisura.cdtp.protocol.events.inspector;

/**
 * Fired when remote debugging connection is about to be terminated. Contains detach reason.
 */
public class Detached {

	private String reason;

	/**
	 * The reason why connection has been terminated.
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * The reason why connection has been terminated.
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
}
