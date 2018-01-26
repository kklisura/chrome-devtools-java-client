package com.github.kklisura.cdtp.protocol.types.indexeddb;

import java.util.List;

/** Object store. */
public class ObjectStore {

  private String name;

  private KeyPath keyPath;

  private Boolean autoIncrement;

  private List<ObjectStoreIndex> indexes;

  /** Object store name. */
  public String getName() {
    return name;
  }

  /** Object store name. */
  public void setName(String name) {
    this.name = name;
  }

  /** Object store key path. */
  public KeyPath getKeyPath() {
    return keyPath;
  }

  /** Object store key path. */
  public void setKeyPath(KeyPath keyPath) {
    this.keyPath = keyPath;
  }

  /** If true, object store has auto increment flag set. */
  public Boolean getAutoIncrement() {
    return autoIncrement;
  }

  /** If true, object store has auto increment flag set. */
  public void setAutoIncrement(Boolean autoIncrement) {
    this.autoIncrement = autoIncrement;
  }

  /** Indexes in this object store. */
  public List<ObjectStoreIndex> getIndexes() {
    return indexes;
  }

  /** Indexes in this object store. */
  public void setIndexes(List<ObjectStoreIndex> indexes) {
    this.indexes = indexes;
  }
}
