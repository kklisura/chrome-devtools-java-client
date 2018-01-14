package com.github.kklisura.cdtp.protocol.events.page;

/**
 * Fired when frame has been detached from its parent.
 */
public class FrameDetached {

	private String frameId;

	/**
	 * Id of the frame that has been detached.
	 */
	public String getFrameId() {
		return frameId;
	}

	/**
	 * Id of the frame that has been detached.
	 */
	public void setFrameId(String frameId) {
		this.frameId = frameId;
	}
}
