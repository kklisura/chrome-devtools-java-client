package com.github.kklisura.cdtp.protocol.types.serviceworker;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ServiceWorkerVersionRunningStatus {
  @JsonProperty("stopped")
  STOPPED,
  @JsonProperty("starting")
  STARTING,
  @JsonProperty("running")
  RUNNING,
  @JsonProperty("stopping")
  STOPPING
}
