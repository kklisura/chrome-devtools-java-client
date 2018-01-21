package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.Optional;
import com.github.kklisura.cdtp.protocol.types.dom.RGBA;
import com.github.kklisura.cdtp.protocol.types.emulation.Configuration;
import com.github.kklisura.cdtp.protocol.types.emulation.ScreenOrientation;
import com.github.kklisura.cdtp.protocol.types.emulation.VirtualTimePolicy;

/**
 * This domain emulates different environments for the page.
 */
public interface Emulation {

	/**
	 * Overrides the values of device screen dimensions (window.screen.width, window.screen.height, window.innerWidth, window.innerHeight, and "device-width"/"device-height"-related CSS media query results).
	 */
	void setDeviceMetricsOverride(Integer width, Integer height, Double deviceScaleFactor, Boolean mobile, @Optional Double scale, @Experimental @Optional Integer screenWidth, @Experimental @Optional Integer screenHeight, @Experimental @Optional Integer positionX, @Experimental @Optional Integer positionY, @Experimental @Optional Boolean dontSetVisibleSize, @Optional ScreenOrientation screenOrientation);

	/**
	 * Clears the overriden device metrics.
	 */
	void clearDeviceMetricsOverride();

	/**
	 * Requests that page scale factor is reset to initial values.
	 */
	@Experimental
	void resetPageScaleFactor();

	/**
	 * Sets a specified page scale factor.
	 */
	@Experimental
	void setPageScaleFactor(Double pageScaleFactor);

	/**
	 * Resizes the frame/viewport of the page. Note that this does not affect the frame's container (e.g. browser window). Can be used to produce screenshots of the specified size. Not supported on Android.
	 */
	@Deprecated
	@Experimental
	void setVisibleSize(Integer width, Integer height);

	/**
	 * Switches script execution in the page.
	 */
	@Experimental
	void setScriptExecutionDisabled(Boolean value);

	/**
	 * Overrides the Geolocation Position or Error. Omitting any of the parameters emulates position unavailable.
	 */
	@Experimental
	void setGeolocationOverride(@Optional Double latitude, @Optional Double longitude, @Optional Double accuracy);

	/**
	 * Clears the overriden Geolocation Position and Error.
	 */
	@Experimental
	void clearGeolocationOverride();

	/**
	 * Enables touch on platforms which do not support them.
	 */
	void setTouchEmulationEnabled(Boolean enabled, @Optional Integer maxTouchPoints);

	@Experimental
	void setEmitTouchEventsForMouse(Boolean enabled, @Optional Configuration configuration);

	/**
	 * Emulates the given media for CSS media queries.
	 */
	void setEmulatedMedia(String media);

	/**
	 * Enables CPU throttling to emulate slow CPUs.
	 */
	@Experimental
	void setCPUThrottlingRate(Double rate);

	/**
	 * Tells whether emulation is supported.
	 */
	@Experimental
	Boolean canEmulate();

	/**
	 * Turns on virtual time for all frames (replacing real-time with a synthetic time source) and sets the current virtual time policy.  Note this supersedes any previous time budget.
	 */
	@Experimental
	void setVirtualTimePolicy(VirtualTimePolicy policy, @Optional Integer budget);

	/**
	 * Overrides value returned by the javascript navigator object.
	 */
	@Experimental
	void setNavigatorOverrides(String platform);

	/**
	 * Sets or clears an override of the default background color of the frame. This override is used if the content does not specify one.
	 */
	@Experimental
	void setDefaultBackgroundColorOverride(@Optional RGBA color);
}
