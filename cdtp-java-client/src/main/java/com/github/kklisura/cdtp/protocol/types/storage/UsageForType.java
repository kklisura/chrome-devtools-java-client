package com.github.kklisura.cdtp.protocol.types.storage;

/** Usage for a storage type. */
public class UsageForType {

  private StorageType storageType;

  private Double usage;

  /** Name of storage type. */
  public StorageType getStorageType() {
    return storageType;
  }

  /** Name of storage type. */
  public void setStorageType(StorageType storageType) {
    this.storageType = storageType;
  }

  /** Storage usage (bytes). */
  public Double getUsage() {
    return usage;
  }

  /** Storage usage (bytes). */
  public void setUsage(Double usage) {
    this.usage = usage;
  }
}
