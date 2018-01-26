package com.github.kklisura.cdtp.protocol.types.debugger;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Pause on exceptions mode. */
public enum State {
  @JsonProperty("none")
  NONE,
  @JsonProperty("uncaught")
  UNCAUGHT,
  @JsonProperty("all")
  ALL
}
