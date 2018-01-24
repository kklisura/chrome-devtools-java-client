package com.github.kklisura.cdtp.protocol.events.runtime;

import com.github.kklisura.cdtp.protocol.types.runtime.RemoteObject;
import java.util.Map;

/**
 * Issued when object should be inspected (for example, as a result of inspect() command line API call).
 */
public class InspectRequested {

	private RemoteObject object;

	private Map<String, Object> hints;

	public RemoteObject getObject() {
		return object;
	}

	public void setObject(RemoteObject object) {
		this.object = object;
	}

	public Map<String, Object> getHints() {
		return hints;
	}

	public void setHints(Map<String, Object> hints) {
		this.hints = hints;
	}
}
