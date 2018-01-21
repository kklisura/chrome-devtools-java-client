package com.github.kklisura.cdtp.protocol.types.page;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.Optional;
import java.util.List;

/**
 * Information about the Frame hierarchy along with their cached resources.
 */
@Experimental
public class FrameResourceTree {

	private Frame frame;

	@Optional
	private List<com.github.kklisura.cdtp.protocol.types.page.FrameResourceTree> childFrames;

	private List<FrameResource> resources;

	/**
	 * Frame information for this tree item.
	 */
	public Frame getFrame() {
		return frame;
	}

	/**
	 * Frame information for this tree item.
	 */
	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	/**
	 * Child frames.
	 */
	public List<com.github.kklisura.cdtp.protocol.types.page.FrameResourceTree> getChildFrames() {
		return childFrames;
	}

	/**
	 * Child frames.
	 */
	public void setChildFrames(List<com.github.kklisura.cdtp.protocol.types.page.FrameResourceTree> childFrames) {
		this.childFrames = childFrames;
	}

	/**
	 * Information about frame resources.
	 */
	public List<FrameResource> getResources() {
		return resources;
	}

	/**
	 * Information about frame resources.
	 */
	public void setResources(List<FrameResource> resources) {
		this.resources = resources;
	}
}
