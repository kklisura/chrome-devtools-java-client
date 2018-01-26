package com.github.kklisura.cdtp.protocol.types.runtime;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Object type. Accessor means that the property itself is an accessor property. */
public enum Type {
  @JsonProperty("object")
  OBJECT,
  @JsonProperty("function")
  FUNCTION,
  @JsonProperty("undefined")
  UNDEFINED,
  @JsonProperty("string")
  STRING,
  @JsonProperty("number")
  NUMBER,
  @JsonProperty("boolean")
  BOOLEAN,
  @JsonProperty("symbol")
  SYMBOL,
  @JsonProperty("accessor")
  ACCESSOR
}
