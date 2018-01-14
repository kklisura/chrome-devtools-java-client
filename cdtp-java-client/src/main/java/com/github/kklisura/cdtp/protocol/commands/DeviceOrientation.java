package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;

@Experimental
public interface DeviceOrientation {

	/**
	 * Overrides the Device Orientation.
	 */
	void setDeviceOrientationOverride(Double alpha, Double beta, Double gamma);

	/**
	 * Clears the overridden Device Orientation.
	 */
	void clearDeviceOrientationOverride();
}
