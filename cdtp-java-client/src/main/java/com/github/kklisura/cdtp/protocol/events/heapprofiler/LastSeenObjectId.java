package com.github.kklisura.cdtp.protocol.events.heapprofiler;

/**
 * If heap objects tracking has been started then backend regularly sends a current value for last
 * seen object id and corresponding timestamp. If the were changes in the heap since last event then
 * one or more heapStatsUpdate events will be sent before a new lastSeenObjectId event.
 */
public class LastSeenObjectId {

  private Integer lastSeenObjectId;

  private Double timestamp;

  public Integer getLastSeenObjectId() {
    return lastSeenObjectId;
  }

  public void setLastSeenObjectId(Integer lastSeenObjectId) {
    this.lastSeenObjectId = lastSeenObjectId;
  }

  public Double getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Double timestamp) {
    this.timestamp = timestamp;
  }
}
