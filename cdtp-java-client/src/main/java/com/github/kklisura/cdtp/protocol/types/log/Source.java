package com.github.kklisura.cdtp.protocol.types.log;

/**
 * Log entry source.
 */
public enum Source {

	XML("xml"), JAVASCRIPT("javascript"), NETWORK("network"), STORAGE("storage"), APPCACHE("appcache"), RENDERING("rendering"), SECURITY("security"), DEPRECATION("deprecation"), WORKER("worker"), VIOLATION("violation"), INTERVENTION("intervention"), OTHER("other");

	final String propertyName;

	Source(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
