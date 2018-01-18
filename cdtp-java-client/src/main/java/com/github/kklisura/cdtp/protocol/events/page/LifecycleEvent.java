package com.github.kklisura.cdtp.protocol.events.page;

/**
 * Fired for top level page lifecycle events such as navigation, load, paint, etc.
 */
public class LifecycleEvent {

	private String name;

	private Double timestamp;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Double timestamp) {
		this.timestamp = timestamp;
	}
}
