package com.github.kklisura.cdtp.protocol.events.page;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;

/**
 * Fired when frame schedules a potential navigation.
 */
@Experimental
public class FrameScheduledNavigation {

	private String frameId;

	private Double delay;

	@Experimental
	private Reason reason;

	@Experimental
	private String url;

	/**
	 * Id of the frame that has scheduled a navigation.
	 */
	public String getFrameId() {
		return frameId;
	}

	/**
	 * Id of the frame that has scheduled a navigation.
	 */
	public void setFrameId(String frameId) {
		this.frameId = frameId;
	}

	/**
	 * Delay (in seconds) until the navigation is scheduled to begin. The navigation is not guaranteed to start.
	 */
	public Double getDelay() {
		return delay;
	}

	/**
	 * Delay (in seconds) until the navigation is scheduled to begin. The navigation is not guaranteed to start.
	 */
	public void setDelay(Double delay) {
		this.delay = delay;
	}

	/**
	 * The reason for the navigation.
	 */
	public Reason getReason() {
		return reason;
	}

	/**
	 * The reason for the navigation.
	 */
	public void setReason(Reason reason) {
		this.reason = reason;
	}

	/**
	 * The destination URL for the scheduled navigation.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * The destination URL for the scheduled navigation.
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}
