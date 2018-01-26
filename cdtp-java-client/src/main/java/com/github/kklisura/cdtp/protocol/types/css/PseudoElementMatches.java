package com.github.kklisura.cdtp.protocol.types.css;

import com.github.kklisura.cdtp.protocol.types.dom.PseudoType;
import java.util.List;

/** CSS rule collection for a single pseudo style. */
public class PseudoElementMatches {

  private PseudoType pseudoType;

  private List<RuleMatch> matches;

  /** Pseudo element type. */
  public PseudoType getPseudoType() {
    return pseudoType;
  }

  /** Pseudo element type. */
  public void setPseudoType(PseudoType pseudoType) {
    this.pseudoType = pseudoType;
  }

  /** Matches of CSS rules applicable to the pseudo style. */
  public List<RuleMatch> getMatches() {
    return matches;
  }

  /** Matches of CSS rules applicable to the pseudo style. */
  public void setMatches(List<RuleMatch> matches) {
    this.matches = matches;
  }
}
