package com.github.kklisura.cdtp.protocol.events.debugger;

/**
 * Pause reason.
 */
public enum Reason {

	XHR("XHR"), DOM("DOM"), EVENT_LISTENER("EventListener"), EXCEPTION("exception"), ASSERT("assert"), DEBUG_COMMAND("debugCommand"), PROMISE_REJECTION("promiseRejection"), OOM("OOM"), OTHER("other"), AMBIGUOUS("ambiguous");

	final String propertyName;

	Reason(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
