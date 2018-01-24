package com.github.kklisura.cdtp.protocol.events.profiler;

import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import com.github.kklisura.cdtp.protocol.types.debugger.Location;

/**
 * Sent when new profile recording is started using console.profile() call.
 */
public class ConsoleProfileStarted {

	private String id;

	private Location location;

	@Optional
	private String title;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Location of console.profile().
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * Location of console.profile().
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * Profile title passed as an argument to console.profile().
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Profile title passed as an argument to console.profile().
	 */
	public void setTitle(String title) {
		this.title = title;
	}
}
