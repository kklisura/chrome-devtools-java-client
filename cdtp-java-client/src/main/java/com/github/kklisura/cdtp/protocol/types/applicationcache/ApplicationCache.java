package com.github.kklisura.cdtp.protocol.types.applicationcache;

import java.util.List;

/** Detailed application cache information. */
public class ApplicationCache {

  private String manifestURL;

  private Double size;

  private Double creationTime;

  private Double updateTime;

  private List<ApplicationCacheResource> resources;

  /** Manifest URL. */
  public String getManifestURL() {
    return manifestURL;
  }

  /** Manifest URL. */
  public void setManifestURL(String manifestURL) {
    this.manifestURL = manifestURL;
  }

  /** Application cache size. */
  public Double getSize() {
    return size;
  }

  /** Application cache size. */
  public void setSize(Double size) {
    this.size = size;
  }

  /** Application cache creation time. */
  public Double getCreationTime() {
    return creationTime;
  }

  /** Application cache creation time. */
  public void setCreationTime(Double creationTime) {
    this.creationTime = creationTime;
  }

  /** Application cache update time. */
  public Double getUpdateTime() {
    return updateTime;
  }

  /** Application cache update time. */
  public void setUpdateTime(Double updateTime) {
    this.updateTime = updateTime;
  }

  /** Application cache resources. */
  public List<ApplicationCacheResource> getResources() {
    return resources;
  }

  /** Application cache resources. */
  public void setResources(List<ApplicationCacheResource> resources) {
    this.resources = resources;
  }
}
