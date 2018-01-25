package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.events.emulation.VirtualTimeBudgetExpired;
import com.github.kklisura.cdtp.protocol.events.emulation.VirtualTimePaused;
import com.github.kklisura.cdtp.protocol.support.annotations.EventName;
import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import com.github.kklisura.cdtp.protocol.support.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.support.annotations.Returns;
import com.github.kklisura.cdtp.protocol.support.types.EventHandler;
import com.github.kklisura.cdtp.protocol.support.types.EventListener;
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
	 *
	 * @param width Overriding width value in pixels (minimum 0, maximum 10000000). 0 disables the override.
	 * @param height Overriding height value in pixels (minimum 0, maximum 10000000). 0 disables the override.
	 * @param deviceScaleFactor Overriding device scale factor value. 0 disables the override.
	 * @param mobile Whether to emulate mobile device. This includes viewport meta tag, overlay scrollbars, text autosizing and more.
	 */
	void setDeviceMetricsOverride(@ParamName("width") Integer width, @ParamName("height") Integer height, @ParamName("deviceScaleFactor") Double deviceScaleFactor, @ParamName("mobile") Boolean mobile);

	/**
	 * Overrides the values of device screen dimensions (window.screen.width, window.screen.height, window.innerWidth, window.innerHeight, and "device-width"/"device-height"-related CSS media query results).
	 *
	 * @param width Overriding width value in pixels (minimum 0, maximum 10000000). 0 disables the override.
	 * @param height Overriding height value in pixels (minimum 0, maximum 10000000). 0 disables the override.
	 * @param deviceScaleFactor Overriding device scale factor value. 0 disables the override.
	 * @param mobile Whether to emulate mobile device. This includes viewport meta tag, overlay scrollbars, text autosizing and more.
	 * @param scale Scale to apply to resulting view image.
	 * @param screenWidth Overriding screen width value in pixels (minimum 0, maximum 10000000).
	 * @param screenHeight Overriding screen height value in pixels (minimum 0, maximum 10000000).
	 * @param positionX Overriding view X position on screen in pixels (minimum 0, maximum 10000000).
	 * @param positionY Overriding view Y position on screen in pixels (minimum 0, maximum 10000000).
	 * @param dontSetVisibleSize Do not set visible view size, rely upon explicit setVisibleSize call.
	 * @param screenOrientation Screen orientation override.
	 */
	void setDeviceMetricsOverride(@ParamName("width") Integer width, @ParamName("height") Integer height, @ParamName("deviceScaleFactor") Double deviceScaleFactor, @ParamName("mobile") Boolean mobile, @Optional @ParamName("scale") Double scale, @Experimental @Optional @ParamName("screenWidth") Integer screenWidth, @Experimental @Optional @ParamName("screenHeight") Integer screenHeight, @Experimental @Optional @ParamName("positionX") Integer positionX, @Experimental @Optional @ParamName("positionY") Integer positionY, @Experimental @Optional @ParamName("dontSetVisibleSize") Boolean dontSetVisibleSize, @Optional @ParamName("screenOrientation") ScreenOrientation screenOrientation);

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
	 *
	 * @param pageScaleFactor Page scale factor.
	 */
	@Experimental
	void setPageScaleFactor(@ParamName("pageScaleFactor") Double pageScaleFactor);

	/**
	 * Resizes the frame/viewport of the page. Note that this does not affect the frame's container (e.g. browser window). Can be used to produce screenshots of the specified size. Not supported on Android.
	 *
	 * @param width Frame width (DIP).
	 * @param height Frame height (DIP).
	 */
	@Deprecated
	@Experimental
	void setVisibleSize(@ParamName("width") Integer width, @ParamName("height") Integer height);

	/**
	 * Switches script execution in the page.
	 *
	 * @param value Whether script execution should be disabled in the page.
	 */
	@Experimental
	void setScriptExecutionDisabled(@ParamName("value") Boolean value);

	/**
	 * Overrides the Geolocation Position or Error. Omitting any of the parameters emulates position unavailable.
	 */
	@Experimental
	void setGeolocationOverride();

	/**
	 * Overrides the Geolocation Position or Error. Omitting any of the parameters emulates position unavailable.
	 *
	 * @param latitude Mock latitude
	 * @param longitude Mock longitude
	 * @param accuracy Mock accuracy
	 */
	@Experimental
	void setGeolocationOverride(@Optional @ParamName("latitude") Double latitude, @Optional @ParamName("longitude") Double longitude, @Optional @ParamName("accuracy") Double accuracy);

	/**
	 * Clears the overriden Geolocation Position and Error.
	 */
	@Experimental
	void clearGeolocationOverride();

	/**
	 * Enables touch on platforms which do not support them.
	 *
	 * @param enabled Whether the touch event emulation should be enabled.
	 */
	void setTouchEmulationEnabled(@ParamName("enabled") Boolean enabled);

	/**
	 * Enables touch on platforms which do not support them.
	 *
	 * @param enabled Whether the touch event emulation should be enabled.
	 * @param maxTouchPoints Maximum touch points supported. Defaults to one.
	 */
	void setTouchEmulationEnabled(@ParamName("enabled") Boolean enabled, @Optional @ParamName("maxTouchPoints") Integer maxTouchPoints);

	/**
	 * @param enabled Whether touch emulation based on mouse input should be enabled.
	 */
	@Experimental
	void setEmitTouchEventsForMouse(@ParamName("enabled") Boolean enabled);

	/**
	 * @param enabled Whether touch emulation based on mouse input should be enabled.
	 * @param configuration Touch/gesture events configuration. Default: current platform.
	 */
	@Experimental
	void setEmitTouchEventsForMouse(@ParamName("enabled") Boolean enabled, @Optional @ParamName("configuration") Configuration configuration);

	/**
	 * Emulates the given media for CSS media queries.
	 *
	 * @param media Media type to emulate. Empty string disables the override.
	 */
	void setEmulatedMedia(@ParamName("media") String media);

	/**
	 * Enables CPU throttling to emulate slow CPUs.
	 *
	 * @param rate Throttling rate as a slowdown factor (1 is no throttle, 2 is 2x slowdown, etc).
	 */
	@Experimental
	void setCPUThrottlingRate(@ParamName("rate") Double rate);

	/**
	 * Tells whether emulation is supported.
	 */
	@Experimental
	@Returns("result")
	Boolean canEmulate();

	/**
	 * Turns on virtual time for all frames (replacing real-time with a synthetic time source) and sets the current virtual time policy.  Note this supersedes any previous time budget.
	 *
	 * @param policy
	 */
	@Experimental
	void setVirtualTimePolicy(@ParamName("policy") VirtualTimePolicy policy);

	/**
	 * Turns on virtual time for all frames (replacing real-time with a synthetic time source) and sets the current virtual time policy.  Note this supersedes any previous time budget.
	 *
	 * @param policy
	 * @param budget If set, after this many virtual milliseconds have elapsed virtual time will be paused and a virtualTimeBudgetExpired event is sent.
	 */
	@Experimental
	void setVirtualTimePolicy(@ParamName("policy") VirtualTimePolicy policy, @Optional @ParamName("budget") Integer budget);

	/**
	 * Overrides value returned by the javascript navigator object.
	 *
	 * @param platform The platform navigator.platform should return.
	 */
	@Experimental
	void setNavigatorOverrides(@ParamName("platform") String platform);

	/**
	 * Sets or clears an override of the default background color of the frame. This override is used if the content does not specify one.
	 */
	@Experimental
	void setDefaultBackgroundColorOverride();

	/**
	 * Sets or clears an override of the default background color of the frame. This override is used if the content does not specify one.
	 *
	 * @param color RGBA of the default background color. If not specified, any existing override will be cleared.
	 */
	@Experimental
	void setDefaultBackgroundColorOverride(@Optional @ParamName("color") RGBA color);

	/**
	 * Notification sent after the virtual time budget for the current VirtualTimePolicy has run out.
	 */
	@EventName("virtualTimeBudgetExpired")
	@Experimental
	EventListener onVirtualTimeBudgetExpired(EventHandler<VirtualTimeBudgetExpired> eventListener);

	/**
	 * Notification sent after the virtual time has paused.
	 */
	@EventName("virtualTimePaused")
	@Experimental
	EventListener onVirtualTimePaused(EventHandler<VirtualTimePaused> eventListener);
}
