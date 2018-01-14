package com.github.kklisura.cdtp.protocol.events.page;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;

/**
 * Fired when frame no longer has a scheduled navigation.
 */
@Experimental
public class FrameClearedScheduledNavigation {

	private String frameId;

	/**
	 * Id of the frame that has cleared its scheduled navigation.
	 */
	public String getFrameId() {
		return frameId;
	}

	/**
	 * Id of the frame that has cleared its scheduled navigation.
	 */
	public void setFrameId(String frameId) {
		this.frameId = frameId;
	}
}
