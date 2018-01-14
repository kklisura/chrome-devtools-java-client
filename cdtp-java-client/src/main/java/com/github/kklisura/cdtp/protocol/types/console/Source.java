package com.github.kklisura.cdtp.protocol.types.console;

/**
 * Message source.
 */
public enum Source {

	XML("xml"), JAVASCRIPT("javascript"), NETWORK("network"), CONSOLE_API("console-api"), STORAGE("storage"), APPCACHE("appcache"), RENDERING("rendering"), SECURITY("security"), OTHER("other"), DEPRECATION("deprecation"), WORKER("worker");

	final String propertyName;

	Source(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
