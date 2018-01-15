package com.github.kklisura.cdtp.protocol.events.runtime;

import com.github.kklisura.cdtp.protocol.types.runtime.RemoteObject;

/**
 * Issued when object should be inspected (for example, as a result of inspect() command line API
 * call).
 */
public class InspectRequested {

	private RemoteObject object;

	private Object hints;

	public RemoteObject getObject() {
		return object;
	}

	public void setObject(RemoteObject object) {
		this.object = object;
	}

	public Object getHints() {
		return hints;
	}

	public void setHints(Object hints) {
		this.hints = hints;
	}
}
