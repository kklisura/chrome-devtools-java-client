package com.github.kklisura.cdtp.protocol.types.security;

import java.util.List;

/** An explanation of an factor contributing to the security state. */
public class SecurityStateExplanation {

  private SecurityState securityState;

  private String summary;

  private String description;

  private MixedContentType mixedContentType;

  private List<String> certificate;

  /** Security state representing the severity of the factor being explained. */
  public SecurityState getSecurityState() {
    return securityState;
  }

  /** Security state representing the severity of the factor being explained. */
  public void setSecurityState(SecurityState securityState) {
    this.securityState = securityState;
  }

  /** Short phrase describing the type of factor. */
  public String getSummary() {
    return summary;
  }

  /** Short phrase describing the type of factor. */
  public void setSummary(String summary) {
    this.summary = summary;
  }

  /** Full text explanation of the factor. */
  public String getDescription() {
    return description;
  }

  /** Full text explanation of the factor. */
  public void setDescription(String description) {
    this.description = description;
  }

  /** The type of mixed content described by the explanation. */
  public MixedContentType getMixedContentType() {
    return mixedContentType;
  }

  /** The type of mixed content described by the explanation. */
  public void setMixedContentType(MixedContentType mixedContentType) {
    this.mixedContentType = mixedContentType;
  }

  /** Page certificate. */
  public List<String> getCertificate() {
    return certificate;
  }

  /** Page certificate. */
  public void setCertificate(List<String> certificate) {
    this.certificate = certificate;
  }
}
