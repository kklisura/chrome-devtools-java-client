package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.support.annotations.Returns;
import com.github.kklisura.cdtp.protocol.types.browser.Bounds;
import com.github.kklisura.cdtp.protocol.types.browser.Version;
import com.github.kklisura.cdtp.protocol.types.browser.WindowForTarget;

/**
 * The Browser domain defines methods and events for browser managing.
 */
@Experimental
public interface Browser {

	/**
	 * Get the browser window that contains the devtools target.
	 *
	 * @param targetId Devtools agent host id.
	 */
	WindowForTarget getWindowForTarget(@ParamName("targetId") String targetId);

	/**
	 * Returns version information.
	 */
	Version getVersion();

	/**
	 * Set position and/or size of the browser window.
	 *
	 * @param windowId Browser window id.
	 * @param bounds New window bounds. The 'minimized', 'maximized' and 'fullscreen' states cannot be combined with 'left', 'top', 'width' or 'height'. Leaves unspecified fields unchanged.
	 */
	void setWindowBounds(@ParamName("windowId") Integer windowId, @ParamName("bounds") Bounds bounds);

	/**
	 * Get position and size of the browser window.
	 *
	 * @param windowId Browser window id.
	 */
	@Returns("bounds")
	Bounds getWindowBounds(@ParamName("windowId") Integer windowId);
}
