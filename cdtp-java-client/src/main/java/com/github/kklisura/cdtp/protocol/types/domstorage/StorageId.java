package com.github.kklisura.cdtp.protocol.types.domstorage;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;

/** DOM Storage identifier. */
@Experimental
public class StorageId {

  private String securityOrigin;

  private Boolean isLocalStorage;

  /** Security origin for the storage. */
  public String getSecurityOrigin() {
    return securityOrigin;
  }

  /** Security origin for the storage. */
  public void setSecurityOrigin(String securityOrigin) {
    this.securityOrigin = securityOrigin;
  }

  /** Whether the storage is local storage (not session storage). */
  public Boolean getIsLocalStorage() {
    return isLocalStorage;
  }

  /** Whether the storage is local storage (not session storage). */
  public void setIsLocalStorage(Boolean isLocalStorage) {
    this.isLocalStorage = isLocalStorage;
  }
}
