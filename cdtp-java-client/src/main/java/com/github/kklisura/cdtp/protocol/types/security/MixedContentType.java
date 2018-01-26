package com.github.kklisura.cdtp.protocol.types.security;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A description of mixed content (HTTP resources on HTTPS pages), as defined by
 * https://www.w3.org/TR/mixed-content/#categories
 */
public enum MixedContentType {
  @JsonProperty("blockable")
  BLOCKABLE,
  @JsonProperty("optionally-blockable")
  OPTIONALLY_BLOCKABLE,
  @JsonProperty("none")
  NONE
}
