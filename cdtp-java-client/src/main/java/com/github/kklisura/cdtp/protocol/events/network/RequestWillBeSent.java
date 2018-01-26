package com.github.kklisura.cdtp.protocol.events.network;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import com.github.kklisura.cdtp.protocol.types.network.Initiator;
import com.github.kklisura.cdtp.protocol.types.network.Request;
import com.github.kklisura.cdtp.protocol.types.network.Response;
import com.github.kklisura.cdtp.protocol.types.page.ResourceType;

/** Fired when page is about to send HTTP request. */
public class RequestWillBeSent {

  private String requestId;

  private String loaderId;

  private String documentURL;

  private Request request;

  private Double timestamp;

  @Experimental private Double wallTime;

  private Initiator initiator;

  @Optional private Response redirectResponse;

  @Experimental @Optional private ResourceType type;

  @Experimental @Optional private String frameId;

  /** Request identifier. */
  public String getRequestId() {
    return requestId;
  }

  /** Request identifier. */
  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  /** Loader identifier. Empty string if the request is fetched form worker. */
  public String getLoaderId() {
    return loaderId;
  }

  /** Loader identifier. Empty string if the request is fetched form worker. */
  public void setLoaderId(String loaderId) {
    this.loaderId = loaderId;
  }

  /** URL of the document this request is loaded for. */
  public String getDocumentURL() {
    return documentURL;
  }

  /** URL of the document this request is loaded for. */
  public void setDocumentURL(String documentURL) {
    this.documentURL = documentURL;
  }

  /** Request data. */
  public Request getRequest() {
    return request;
  }

  /** Request data. */
  public void setRequest(Request request) {
    this.request = request;
  }

  /** Timestamp. */
  public Double getTimestamp() {
    return timestamp;
  }

  /** Timestamp. */
  public void setTimestamp(Double timestamp) {
    this.timestamp = timestamp;
  }

  /** Timestamp. */
  public Double getWallTime() {
    return wallTime;
  }

  /** Timestamp. */
  public void setWallTime(Double wallTime) {
    this.wallTime = wallTime;
  }

  /** Request initiator. */
  public Initiator getInitiator() {
    return initiator;
  }

  /** Request initiator. */
  public void setInitiator(Initiator initiator) {
    this.initiator = initiator;
  }

  /** Redirect response data. */
  public Response getRedirectResponse() {
    return redirectResponse;
  }

  /** Redirect response data. */
  public void setRedirectResponse(Response redirectResponse) {
    this.redirectResponse = redirectResponse;
  }

  /** Type of this resource. */
  public ResourceType getType() {
    return type;
  }

  /** Type of this resource. */
  public void setType(ResourceType type) {
    this.type = type;
  }

  /** Frame identifier. */
  public String getFrameId() {
    return frameId;
  }

  /** Frame identifier. */
  public void setFrameId(String frameId) {
    this.frameId = frameId;
  }
}
