package com.github.kklisura.cdtp.protocol.types.css;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ForcedPseudoClasses {
  @JsonProperty("active")
  ACTIVE,
  @JsonProperty("focus")
  FOCUS,
  @JsonProperty("hover")
  HOVER,
  @JsonProperty("visited")
  VISITED
}
