package com.github.kklisura.cdtp.protocol.types.animation;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Animation type of <code>Animation</code>. */
public enum Type {
  @JsonProperty("CSSTransition")
  CSS_TRANSITION,
  @JsonProperty("CSSAnimation")
  CSS_ANIMATION,
  @JsonProperty("WebAnimation")
  WEB_ANIMATION
}
