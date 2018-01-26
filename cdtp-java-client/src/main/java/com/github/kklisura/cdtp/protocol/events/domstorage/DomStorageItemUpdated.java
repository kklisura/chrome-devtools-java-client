package com.github.kklisura.cdtp.protocol.events.domstorage;

import com.github.kklisura.cdtp.protocol.types.domstorage.StorageId;

public class DomStorageItemUpdated {

  private StorageId storageId;

  private String key;

  private String oldValue;

  private String newValue;

  public StorageId getStorageId() {
    return storageId;
  }

  public void setStorageId(StorageId storageId) {
    this.storageId = storageId;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getOldValue() {
    return oldValue;
  }

  public void setOldValue(String oldValue) {
    this.oldValue = oldValue;
  }

  public String getNewValue() {
    return newValue;
  }

  public void setNewValue(String newValue) {
    this.newValue = newValue;
  }
}
