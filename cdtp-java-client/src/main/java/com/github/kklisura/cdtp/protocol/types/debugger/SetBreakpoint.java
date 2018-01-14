package com.github.kklisura.cdtp.protocol.types.debugger;

public class SetBreakpoint {

	private String breakpointId;

	private Location actualLocation;

	/**
	 * Id of the created breakpoint for further reference.
	 */
	public String getBreakpointId() {
		return breakpointId;
	}

	/**
	 * Id of the created breakpoint for further reference.
	 */
	public void setBreakpointId(String breakpointId) {
		this.breakpointId = breakpointId;
	}

	/**
	 * Location this breakpoint resolved into.
	 */
	public Location getActualLocation() {
		return actualLocation;
	}

	/**
	 * Location this breakpoint resolved into.
	 */
	public void setActualLocation(Location actualLocation) {
		this.actualLocation = actualLocation;
	}
}
