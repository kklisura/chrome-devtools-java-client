package com.github.kklisura.cdtp.protocol.types.css;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import java.util.List;

/** Media query descriptor. */
@Experimental
public class MediaQuery {

  private List<MediaQueryExpression> expressions;

  private Boolean active;

  /** Array of media query expressions. */
  public List<MediaQueryExpression> getExpressions() {
    return expressions;
  }

  /** Array of media query expressions. */
  public void setExpressions(List<MediaQueryExpression> expressions) {
    this.expressions = expressions;
  }

  /** Whether the media query condition is satisfied. */
  public Boolean getActive() {
    return active;
  }

  /** Whether the media query condition is satisfied. */
  public void setActive(Boolean active) {
    this.active = active;
  }
}
