package com.github.kklisura.cdtp.protocol.types.domsnapshot;

import java.util.List;

/** A subset of the full ComputedStyle as defined by the request whitelist. */
public class ComputedStyle {

  private List<NameValue> properties;

  /** Name/value pairs of computed style properties. */
  public List<NameValue> getProperties() {
    return properties;
  }

  /** Name/value pairs of computed style properties. */
  public void setProperties(List<NameValue> properties) {
    this.properties = properties;
  }
}
