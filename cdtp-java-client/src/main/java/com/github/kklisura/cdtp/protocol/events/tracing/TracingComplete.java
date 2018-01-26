package com.github.kklisura.cdtp.protocol.events.tracing;

import com.github.kklisura.cdtp.protocol.support.annotations.Optional;

/**
 * Signals that tracing is stopped and there is no trace buffers pending flush, all data were
 * delivered via dataCollected events.
 */
public class TracingComplete {

  @Optional private String stream;

  /** A handle of the stream that holds resulting trace data. */
  public String getStream() {
    return stream;
  }

  /** A handle of the stream that holds resulting trace data. */
  public void setStream(String stream) {
    this.stream = stream;
  }
}
