package com.github.kklisura.cdtp.protocol.types.network;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Type of this initiator. */
public enum Type {
  @JsonProperty("parser")
  PARSER,
  @JsonProperty("script")
  SCRIPT,
  @JsonProperty("preload")
  PRELOAD,
  @JsonProperty("other")
  OTHER
}
