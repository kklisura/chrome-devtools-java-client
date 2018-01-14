package com.github.kklisura.cdtp.protocol.types.storage;

/**
 * Enum of possible storage types.
 */
public enum StorageType {

	APPCACHE("appcache"), COOKIES("cookies"), FILE_SYSTEMS("file_systems"), INDEXEDDB("indexeddb"), LOCAL_STORAGE("local_storage"), SHADER_CACHE("shader_cache"), WEBSQL("websql"), SERVICE_WORKERS("service_workers"), CACHE_STORAGE("cache_storage"), ALL("all"), OTHER("other");

	final String propertyName;

	StorageType(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
