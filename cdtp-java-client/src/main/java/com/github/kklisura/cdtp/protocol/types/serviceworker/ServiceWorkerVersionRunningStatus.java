package com.github.kklisura.cdtp.protocol.types.serviceworker;

public enum ServiceWorkerVersionRunningStatus {

	STOPPED("stopped"), STARTING("starting"), RUNNING("running"), STOPPING("stopping");

	final String propertyName;

	ServiceWorkerVersionRunningStatus(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
