package com.github.kklisura.cdtp.protocol.types.emulation;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Touch/gesture events configuration. Default: current platform. */
public enum Configuration {
  @JsonProperty("mobile")
  MOBILE,
  @JsonProperty("desktop")
  DESKTOP
}
