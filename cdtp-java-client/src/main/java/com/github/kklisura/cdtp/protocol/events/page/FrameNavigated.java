package com.github.kklisura.cdtp.protocol.events.page;

import com.github.kklisura.cdtp.protocol.types.page.Frame;

/**
 * Fired once navigation of the frame has completed. Frame is now associated with the new loader.
 */
public class FrameNavigated {

	private Frame frame;

	/**
	 * Frame object.
	 */
	public Frame getFrame() {
		return frame;
	}

	/**
	 * Frame object.
	 */
	public void setFrame(Frame frame) {
		this.frame = frame;
	}
}
