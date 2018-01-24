package com.github.kklisura.cdtp.protocol.events.tracing;

import com.github.kklisura.cdtp.protocol.support.annotations.Optional;

public class BufferUsage {

	@Optional
	private Double percentFull;

	@Optional
	private Double eventCount;

	@Optional
	private Double value;

	/**
	 * A number in range [0..1] that indicates the used size of event buffer as a fraction of its total size.
	 */
	public Double getPercentFull() {
		return percentFull;
	}

	/**
	 * A number in range [0..1] that indicates the used size of event buffer as a fraction of its total size.
	 */
	public void setPercentFull(Double percentFull) {
		this.percentFull = percentFull;
	}

	/**
	 * An approximate number of events in the trace log.
	 */
	public Double getEventCount() {
		return eventCount;
	}

	/**
	 * An approximate number of events in the trace log.
	 */
	public void setEventCount(Double eventCount) {
		this.eventCount = eventCount;
	}

	/**
	 * A number in range [0..1] that indicates the used size of event buffer as a fraction of its total size.
	 */
	public Double getValue() {
		return value;
	}

	/**
	 * A number in range [0..1] that indicates the used size of event buffer as a fraction of its total size.
	 */
	public void setValue(Double value) {
		this.value = value;
	}
}
