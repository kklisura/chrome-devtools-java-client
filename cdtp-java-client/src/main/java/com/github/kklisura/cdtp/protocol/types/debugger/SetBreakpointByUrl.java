package com.github.kklisura.cdtp.protocol.types.debugger;

import java.util.List;

public class SetBreakpointByUrl {

  private String breakpointId;

  private List<Location> locations;

  /** Id of the created breakpoint for further reference. */
  public String getBreakpointId() {
    return breakpointId;
  }

  /** Id of the created breakpoint for further reference. */
  public void setBreakpointId(String breakpointId) {
    this.breakpointId = breakpointId;
  }

  /** List of the locations this breakpoint resolved into upon addition. */
  public List<Location> getLocations() {
    return locations;
  }

  /** List of the locations this breakpoint resolved into upon addition. */
  public void setLocations(List<Location> locations) {
    this.locations = locations;
  }
}
