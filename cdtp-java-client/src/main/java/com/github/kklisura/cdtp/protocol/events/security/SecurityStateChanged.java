package com.github.kklisura.cdtp.protocol.events.security;

import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import com.github.kklisura.cdtp.protocol.types.security.InsecureContentStatus;
import com.github.kklisura.cdtp.protocol.types.security.SecurityState;
import com.github.kklisura.cdtp.protocol.types.security.SecurityStateExplanation;
import java.util.List;

/** The security state of the page changed. */
public class SecurityStateChanged {

  private SecurityState securityState;

  private Boolean schemeIsCryptographic;

  private List<SecurityStateExplanation> explanations;

  private InsecureContentStatus insecureContentStatus;

  @Optional private String summary;

  /** Security state. */
  public SecurityState getSecurityState() {
    return securityState;
  }

  /** Security state. */
  public void setSecurityState(SecurityState securityState) {
    this.securityState = securityState;
  }

  /** True if the page was loaded over cryptographic transport such as HTTPS. */
  public Boolean getSchemeIsCryptographic() {
    return schemeIsCryptographic;
  }

  /** True if the page was loaded over cryptographic transport such as HTTPS. */
  public void setSchemeIsCryptographic(Boolean schemeIsCryptographic) {
    this.schemeIsCryptographic = schemeIsCryptographic;
  }

  /**
   * List of explanations for the security state. If the overall security state is `insecure` or
   * `warning`, at least one corresponding explanation should be included.
   */
  public List<SecurityStateExplanation> getExplanations() {
    return explanations;
  }

  /**
   * List of explanations for the security state. If the overall security state is `insecure` or
   * `warning`, at least one corresponding explanation should be included.
   */
  public void setExplanations(List<SecurityStateExplanation> explanations) {
    this.explanations = explanations;
  }

  /** Information about insecure content on the page. */
  public InsecureContentStatus getInsecureContentStatus() {
    return insecureContentStatus;
  }

  /** Information about insecure content on the page. */
  public void setInsecureContentStatus(InsecureContentStatus insecureContentStatus) {
    this.insecureContentStatus = insecureContentStatus;
  }

  /** Overrides user-visible description of the state. */
  public String getSummary() {
    return summary;
  }

  /** Overrides user-visible description of the state. */
  public void setSummary(String summary) {
    this.summary = summary;
  }
}
