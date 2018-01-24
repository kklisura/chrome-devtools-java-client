package com.github.kklisura.cdtp.protocol.events.emulation;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;

/**
 * Notification sent after the virtual time has paused.
 */
@Experimental
public class VirtualTimePaused {

	private Integer virtualTimeElapsed;

	/**
	 * The amount of virtual time that has elapsed in milliseconds since virtual time was first enabled.
	 */
	public Integer getVirtualTimeElapsed() {
		return virtualTimeElapsed;
	}

	/**
	 * The amount of virtual time that has elapsed in milliseconds since virtual time was first enabled.
	 */
	public void setVirtualTimeElapsed(Integer virtualTimeElapsed) {
		this.virtualTimeElapsed = virtualTimeElapsed;
	}
}
