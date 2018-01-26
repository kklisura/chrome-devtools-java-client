package com.github.kklisura.cdtp.protocol.types.runtime;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Primitive value which cannot be JSON-stringified. */
public enum UnserializableValue {
  @JsonProperty("Infinity")
  INFINITY,
  @JsonProperty("NaN")
  NAN,
  @JsonProperty("-Infinity")
  NEGATIVE_INFINITY,
  @JsonProperty("-0")
  NEGATIVE_0
}
