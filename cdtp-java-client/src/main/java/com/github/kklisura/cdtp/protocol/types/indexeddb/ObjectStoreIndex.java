package com.github.kklisura.cdtp.protocol.types.indexeddb;

/** Object store index. */
public class ObjectStoreIndex {

  private String name;

  private KeyPath keyPath;

  private Boolean unique;

  private Boolean multiEntry;

  /** Index name. */
  public String getName() {
    return name;
  }

  /** Index name. */
  public void setName(String name) {
    this.name = name;
  }

  /** Index key path. */
  public KeyPath getKeyPath() {
    return keyPath;
  }

  /** Index key path. */
  public void setKeyPath(KeyPath keyPath) {
    this.keyPath = keyPath;
  }

  /** If true, index is unique. */
  public Boolean getUnique() {
    return unique;
  }

  /** If true, index is unique. */
  public void setUnique(Boolean unique) {
    this.unique = unique;
  }

  /** If true, index allows multiple entries for a key. */
  public Boolean getMultiEntry() {
    return multiEntry;
  }

  /** If true, index allows multiple entries for a key. */
  public void setMultiEntry(Boolean multiEntry) {
    this.multiEntry = multiEntry;
  }
}
