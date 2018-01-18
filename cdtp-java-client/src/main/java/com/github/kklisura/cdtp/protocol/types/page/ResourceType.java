package com.github.kklisura.cdtp.protocol.types.page;

/**
 * Resource type as it was perceived by the rendering engine.
 */
public enum ResourceType {

	DOCUMENT("Document"), STYLESHEET("Stylesheet"), IMAGE("Image"), MEDIA("Media"), FONT("Font"), SCRIPT("Script"), TEXT_TRACK("TextTrack"), XHR("XHR"), FETCH("Fetch"), EVENT_SOURCE("EventSource"), WEB_SOCKET("WebSocket"), MANIFEST("Manifest"), OTHER("Other");

	final String propertyName;

	ResourceType(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
