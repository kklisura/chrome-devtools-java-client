package com.github.kklisura.cdtp.protocol.types.page;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Image compression format. */
public enum Format {
  @JsonProperty("jpeg")
  JPEG,
  @JsonProperty("png")
  PNG
}
