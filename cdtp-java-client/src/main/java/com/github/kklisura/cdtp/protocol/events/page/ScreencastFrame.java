package com.github.kklisura.cdtp.protocol.events.page;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.types.page.ScreencastFrameMetadata;

/**
 * Compressed image data requested by the <code>startScreencast</code>.
 */
@Experimental
public class ScreencastFrame {

	private String data;

	private ScreencastFrameMetadata metadata;

	private Integer sessionId;

	/**
	 * Base64-encoded compressed image.
	 */
	public String getData() {
		return data;
	}

	/**
	 * Base64-encoded compressed image.
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * Screencast frame metadata.
	 */
	public ScreencastFrameMetadata getMetadata() {
		return metadata;
	}

	/**
	 * Screencast frame metadata.
	 */
	public void setMetadata(ScreencastFrameMetadata metadata) {
		this.metadata = metadata;
	}

	/**
	 * Frame number.
	 */
	public Integer getSessionId() {
		return sessionId;
	}

	/**
	 * Frame number.
	 */
	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}
}
