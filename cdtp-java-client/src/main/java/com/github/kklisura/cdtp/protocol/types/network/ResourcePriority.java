package com.github.kklisura.cdtp.protocol.types.network;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Loading priority of a resource request. */
public enum ResourcePriority {
  @JsonProperty("VeryLow")
  VERY_LOW,
  @JsonProperty("Low")
  LOW,
  @JsonProperty("Medium")
  MEDIUM,
  @JsonProperty("High")
  HIGH,
  @JsonProperty("VeryHigh")
  VERY_HIGH
}
