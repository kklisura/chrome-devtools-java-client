package com.github.kklisura.cdtp.protocol.events.page;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import com.github.kklisura.cdtp.protocol.types.runtime.StackTrace;

/**
 * Fired when frame has been attached to its parent.
 */
public class FrameAttached {

	private String frameId;

	private String parentFrameId;

	@Experimental
	@Optional
	private StackTrace stack;

	/**
	 * Id of the frame that has been attached.
	 */
	public String getFrameId() {
		return frameId;
	}

	/**
	 * Id of the frame that has been attached.
	 */
	public void setFrameId(String frameId) {
		this.frameId = frameId;
	}

	/**
	 * Parent frame identifier.
	 */
	public String getParentFrameId() {
		return parentFrameId;
	}

	/**
	 * Parent frame identifier.
	 */
	public void setParentFrameId(String parentFrameId) {
		this.parentFrameId = parentFrameId;
	}

	/**
	 * JavaScript stack trace of when frame was attached, only set if frame initiated from script.
	 */
	public StackTrace getStack() {
		return stack;
	}

	/**
	 * JavaScript stack trace of when frame was attached, only set if frame initiated from script.
	 */
	public void setStack(StackTrace stack) {
		this.stack = stack;
	}
}
