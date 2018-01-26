package com.github.kklisura.cdtp.protocol.types.indexeddb;

import java.util.List;

public class RequestData {

  private List<DataEntry> objectStoreDataEntries;

  private Boolean hasMore;

  /** Array of object store data entries. */
  public List<DataEntry> getObjectStoreDataEntries() {
    return objectStoreDataEntries;
  }

  /** Array of object store data entries. */
  public void setObjectStoreDataEntries(List<DataEntry> objectStoreDataEntries) {
    this.objectStoreDataEntries = objectStoreDataEntries;
  }

  /** If true, there are more entries to fetch in the given range. */
  public Boolean getHasMore() {
    return hasMore;
  }

  /** If true, there are more entries to fetch in the given range. */
  public void setHasMore(Boolean hasMore) {
    this.hasMore = hasMore;
  }
}
