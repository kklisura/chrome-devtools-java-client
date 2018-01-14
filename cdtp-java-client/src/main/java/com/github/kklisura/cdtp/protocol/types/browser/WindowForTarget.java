package com.github.kklisura.cdtp.protocol.types.browser;

public class WindowForTarget {

	private Integer windowId;

	private Bounds bounds;

	/**
	 * Browser window id.
	 */
	public Integer getWindowId() {
		return windowId;
	}

	/**
	 * Browser window id.
	 */
	public void setWindowId(Integer windowId) {
		this.windowId = windowId;
	}

	/**
	 * Bounds information of the window. When window state is 'minimized', the restored window position and size are returned.
	 */
	public Bounds getBounds() {
		return bounds;
	}

	/**
	 * Bounds information of the window. When window state is 'minimized', the restored window position and size are returned.
	 */
	public void setBounds(Bounds bounds) {
		this.bounds = bounds;
	}
}
