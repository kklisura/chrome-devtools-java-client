package com.github.kklisura.cdtp.protocol.types.input;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum GestureSourceType {
  @JsonProperty("default")
  DEFAULT,
  @JsonProperty("touch")
  TOUCH,
  @JsonProperty("mouse")
  MOUSE
}
