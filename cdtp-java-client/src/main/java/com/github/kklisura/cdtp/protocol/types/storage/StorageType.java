package com.github.kklisura.cdtp.protocol.types.storage;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Enum of possible storage types.
 */
public enum StorageType {

	@JsonProperty("appcache")
	APPCACHE, @JsonProperty("cookies")
	COOKIES, @JsonProperty("file_systems")
	FILE_SYSTEMS, @JsonProperty("indexeddb")
	INDEXEDDB, @JsonProperty("local_storage")
	LOCAL_STORAGE, @JsonProperty("shader_cache")
	SHADER_CACHE, @JsonProperty("websql")
	WEBSQL, @JsonProperty("service_workers")
	SERVICE_WORKERS, @JsonProperty("cache_storage")
	CACHE_STORAGE, @JsonProperty("all")
	ALL, @JsonProperty("other")
	OTHER
}
