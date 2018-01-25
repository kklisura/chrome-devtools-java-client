package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.ParamName;

@Experimental
public interface DeviceOrientation {

	/**
	 * Overrides the Device Orientation.
	 *
	 * @param alpha Mock alpha
	 * @param beta Mock beta
	 * @param gamma Mock gamma
	 */
	void setDeviceOrientationOverride(@ParamName("alpha") Double alpha, @ParamName("beta") Double beta, @ParamName("gamma") Double gamma);

	/**
	 * Clears the overridden Device Orientation.
	 */
	void clearDeviceOrientationOverride();
}
