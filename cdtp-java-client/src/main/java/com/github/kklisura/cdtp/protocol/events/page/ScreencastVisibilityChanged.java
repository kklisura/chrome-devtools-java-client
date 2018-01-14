package com.github.kklisura.cdtp.protocol.events.page;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;

/**
 * Fired when the page with currently enabled screencast was shown or hidden </code>.
 */
@Experimental
public class ScreencastVisibilityChanged {

	private Boolean visible;

	/**
	 * True if the page is visible.
	 */
	public Boolean getVisible() {
		return visible;
	}

	/**
	 * True if the page is visible.
	 */
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
}
