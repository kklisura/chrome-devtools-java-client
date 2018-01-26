package com.github.kklisura.cdtp.protocol.types.accessibility;

import com.fasterxml.jackson.annotation.JsonProperty;

/** States which apply to widgets. */
public enum AXWidgetStates {
  @JsonProperty("checked")
  CHECKED,
  @JsonProperty("expanded")
  EXPANDED,
  @JsonProperty("modal")
  MODAL,
  @JsonProperty("pressed")
  PRESSED,
  @JsonProperty("selected")
  SELECTED
}
