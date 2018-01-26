package com.github.kklisura.cdtp.protocol.events.tracing;

import java.util.List;

/**
 * Contains an bucket of collected trace events. When tracing is stopped collected events will be
 * send as a sequence of dataCollected events followed by tracingComplete event.
 */
public class DataCollected {

  private List<Object> value;

  public List<Object> getValue() {
    return value;
  }

  public void setValue(List<Object> value) {
    this.value = value;
  }
}
