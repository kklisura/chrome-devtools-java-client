package com.github.kklisura.cdtp.protocol.events.debugger;

import com.github.kklisura.cdtp.protocol.types.debugger.Location;

/**
 * Fired when breakpoint is resolved to an actual script and location.
 */
public class BreakpointResolved {

	private String breakpointId;

	private Location location;

	/**
	 * Breakpoint unique identifier.
	 */
	public String getBreakpointId() {
		return breakpointId;
	}

	/**
	 * Breakpoint unique identifier.
	 */
	public void setBreakpointId(String breakpointId) {
		this.breakpointId = breakpointId;
	}

	/**
	 * Actual breakpoint location.
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * Actual breakpoint location.
	 */
	public void setLocation(Location location) {
		this.location = location;
	}
}
