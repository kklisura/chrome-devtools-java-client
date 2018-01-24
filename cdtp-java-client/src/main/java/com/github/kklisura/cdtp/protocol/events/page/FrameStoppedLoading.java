package com.github.kklisura.cdtp.protocol.events.page;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;

/**
 * Fired when frame has stopped loading.
 */
@Experimental
public class FrameStoppedLoading {

	private String frameId;

	/**
	 * Id of the frame that has stopped loading.
	 */
	public String getFrameId() {
		return frameId;
	}

	/**
	 * Id of the frame that has stopped loading.
	 */
	public void setFrameId(String frameId) {
		this.frameId = frameId;
	}
}
