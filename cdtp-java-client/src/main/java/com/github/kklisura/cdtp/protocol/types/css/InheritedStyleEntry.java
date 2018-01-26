package com.github.kklisura.cdtp.protocol.types.css;

import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import java.util.List;

/** Inherited CSS rule collection from ancestor node. */
public class InheritedStyleEntry {

  @Optional private CSSStyle inlineStyle;

  private List<RuleMatch> matchedCSSRules;

  /** The ancestor node's inline style, if any, in the style inheritance chain. */
  public CSSStyle getInlineStyle() {
    return inlineStyle;
  }

  /** The ancestor node's inline style, if any, in the style inheritance chain. */
  public void setInlineStyle(CSSStyle inlineStyle) {
    this.inlineStyle = inlineStyle;
  }

  /** Matches of CSS rules matching the ancestor node in the style inheritance chain. */
  public List<RuleMatch> getMatchedCSSRules() {
    return matchedCSSRules;
  }

  /** Matches of CSS rules matching the ancestor node in the style inheritance chain. */
  public void setMatchedCSSRules(List<RuleMatch> matchedCSSRules) {
    this.matchedCSSRules = matchedCSSRules;
  }
}
