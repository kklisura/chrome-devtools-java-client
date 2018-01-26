package com.github.kklisura.cdtp.protocol.types.accessibility;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Attributes which apply to nodes in live regions. */
public enum AXLiveRegionAttributes {
  @JsonProperty("live")
  LIVE,
  @JsonProperty("atomic")
  ATOMIC,
  @JsonProperty("relevant")
  RELEVANT,
  @JsonProperty("root")
  ROOT
}
