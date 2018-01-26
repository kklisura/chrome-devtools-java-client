package com.github.kklisura.cdtp.protocol.types.cachestorage;

import java.util.List;

public class RequestEntries {

  private List<DataEntry> cacheDataEntries;

  private Boolean hasMore;

  /** Array of object store data entries. */
  public List<DataEntry> getCacheDataEntries() {
    return cacheDataEntries;
  }

  /** Array of object store data entries. */
  public void setCacheDataEntries(List<DataEntry> cacheDataEntries) {
    this.cacheDataEntries = cacheDataEntries;
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
