package com.github.kklisura.cdtp.protocol.types.page;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.Optional;

/** Information about the Resource on the page. */
@Experimental
public class FrameResource {

  private String url;

  private ResourceType type;

  private String mimeType;

  @Optional private Double lastModified;

  @Optional private Double contentSize;

  @Optional private Boolean failed;

  @Optional private Boolean canceled;

  /** Resource URL. */
  public String getUrl() {
    return url;
  }

  /** Resource URL. */
  public void setUrl(String url) {
    this.url = url;
  }

  /** Type of this resource. */
  public ResourceType getType() {
    return type;
  }

  /** Type of this resource. */
  public void setType(ResourceType type) {
    this.type = type;
  }

  /** Resource mimeType as determined by the browser. */
  public String getMimeType() {
    return mimeType;
  }

  /** Resource mimeType as determined by the browser. */
  public void setMimeType(String mimeType) {
    this.mimeType = mimeType;
  }

  /** last-modified timestamp as reported by server. */
  public Double getLastModified() {
    return lastModified;
  }

  /** last-modified timestamp as reported by server. */
  public void setLastModified(Double lastModified) {
    this.lastModified = lastModified;
  }

  /** Resource content size. */
  public Double getContentSize() {
    return contentSize;
  }

  /** Resource content size. */
  public void setContentSize(Double contentSize) {
    this.contentSize = contentSize;
  }

  /** True if the resource failed to load. */
  public Boolean getFailed() {
    return failed;
  }

  /** True if the resource failed to load. */
  public void setFailed(Boolean failed) {
    this.failed = failed;
  }

  /** True if the resource was canceled during loading. */
  public Boolean getCanceled() {
    return canceled;
  }

  /** True if the resource was canceled during loading. */
  public void setCanceled(Boolean canceled) {
    this.canceled = canceled;
  }
}
