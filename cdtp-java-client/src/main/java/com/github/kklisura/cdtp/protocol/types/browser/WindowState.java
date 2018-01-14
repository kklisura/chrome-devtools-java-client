package com.github.kklisura.cdtp.protocol.types.browser;

/**
 * The state of the browser window.
 */
public enum WindowState {

	NORMAL("normal"), MINIMIZED("minimized"), MAXIMIZED("maximized"), FULLSCREEN("fullscreen");

	final String propertyName;

	WindowState(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
