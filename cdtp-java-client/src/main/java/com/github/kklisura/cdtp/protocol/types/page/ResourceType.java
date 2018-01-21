package com.github.kklisura.cdtp.protocol.types.page;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Resource type as it was perceived by the rendering engine.
 */
public enum ResourceType {

	@JsonProperty("Document")
	DOCUMENT, @JsonProperty("Stylesheet")
	STYLESHEET, @JsonProperty("Image")
	IMAGE, @JsonProperty("Media")
	MEDIA, @JsonProperty("Font")
	FONT, @JsonProperty("Script")
	SCRIPT, @JsonProperty("TextTrack")
	TEXT_TRACK, @JsonProperty("XHR")
	XHR, @JsonProperty("Fetch")
	FETCH, @JsonProperty("EventSource")
	EVENT_SOURCE, @JsonProperty("WebSocket")
	WEB_SOCKET, @JsonProperty("Manifest")
	MANIFEST, @JsonProperty("Other")
	OTHER
}
