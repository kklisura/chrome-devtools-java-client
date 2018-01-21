package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
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
	 */
	WindowForTarget getWindowForTarget(String targetId);

	/**
	 * Returns version information.
	 */
	Version getVersion();

	/**
	 * Set position and/or size of the browser window.
	 */
	void setWindowBounds(Integer windowId, Bounds bounds);

	/**
	 * Get position and size of the browser window.
	 */
	Bounds getWindowBounds(Integer windowId);
}
