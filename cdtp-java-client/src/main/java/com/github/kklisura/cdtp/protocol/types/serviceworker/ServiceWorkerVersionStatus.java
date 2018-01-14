package com.github.kklisura.cdtp.protocol.types.serviceworker;

public enum ServiceWorkerVersionStatus {

	NEW("new"), INSTALLING("installing"), INSTALLED("installed"), ACTIVATING("activating"), ACTIVATED("activated"), REDUNDANT("redundant");

	final String propertyName;

	ServiceWorkerVersionStatus(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
