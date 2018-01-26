package com.github.kklisura.cdtp.protocol.events.storage;

/** A cache has been added/deleted. */
public class CacheStorageListUpdated {

  private String origin;

  /** Origin to update. */
  public String getOrigin() {
    return origin;
  }

  /** Origin to update. */
  public void setOrigin(String origin) {
    this.origin = origin;
  }
}
