package com.github.kklisura.cdtp.protocol.events.domstorage;

import com.github.kklisura.cdtp.protocol.types.domstorage.StorageId;

public class DomStorageItemsCleared {

	private StorageId storageId;

	public StorageId getStorageId() {
		return storageId;
	}

	public void setStorageId(StorageId storageId) {
		this.storageId = storageId;
	}
}
