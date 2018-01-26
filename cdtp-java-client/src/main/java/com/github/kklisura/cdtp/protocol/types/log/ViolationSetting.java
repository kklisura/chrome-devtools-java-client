package com.github.kklisura.cdtp.protocol.types.log;

/** Violation configuration setting. */
public class ViolationSetting {

  private Name name;

  private Double threshold;

  /** Violation type. */
  public Name getName() {
    return name;
  }

  /** Violation type. */
  public void setName(Name name) {
    this.name = name;
  }

  /** Time threshold to trigger upon. */
  public Double getThreshold() {
    return threshold;
  }

  /** Time threshold to trigger upon. */
  public void setThreshold(Double threshold) {
    this.threshold = threshold;
  }
}
