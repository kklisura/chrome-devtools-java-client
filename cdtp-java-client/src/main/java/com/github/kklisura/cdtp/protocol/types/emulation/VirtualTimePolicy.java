package com.github.kklisura.cdtp.protocol.types.emulation;

/**
 * advance: If the scheduler runs out of immediate work, the virtual time base may fast forward to allow the next delayed task (if any) to run; pause: The virtual time base may not advance; pauseIfNetworkFetchesPending: The virtual time base may not advance if there are any pending resource fetches.
 */
public enum VirtualTimePolicy {

	ADVANCE("advance"), PAUSE("pause"), PAUSE_IF_NETWORK_FETCHES_PENDING("pauseIfNetworkFetchesPending");

	final String propertyName;

	VirtualTimePolicy(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}
}
