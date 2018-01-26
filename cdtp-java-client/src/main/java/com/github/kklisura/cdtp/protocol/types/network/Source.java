package com.github.kklisura.cdtp.protocol.types.network;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Source of the authentication challenge. */
public enum Source {
  @JsonProperty("Server")
  SERVER,
  @JsonProperty("Proxy")
  PROXY
}
