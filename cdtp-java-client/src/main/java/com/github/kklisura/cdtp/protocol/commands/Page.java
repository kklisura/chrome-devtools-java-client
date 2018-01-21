package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.Optional;
import com.github.kklisura.cdtp.protocol.types.debugger.SearchMatch;
import com.github.kklisura.cdtp.protocol.types.emulation.ScreenOrientation;
import com.github.kklisura.cdtp.protocol.types.network.Cookie;
import com.github.kklisura.cdtp.protocol.types.page.AppManifest;
import com.github.kklisura.cdtp.protocol.types.page.Behavior;
import com.github.kklisura.cdtp.protocol.types.page.Configuration;
import com.github.kklisura.cdtp.protocol.types.page.Format;
import com.github.kklisura.cdtp.protocol.types.page.FrameResourceTree;
import com.github.kklisura.cdtp.protocol.types.page.LayoutMetrics;
import com.github.kklisura.cdtp.protocol.types.page.NavigationHistory;
import com.github.kklisura.cdtp.protocol.types.page.ResourceContent;
import com.github.kklisura.cdtp.protocol.types.page.TransitionType;
import com.github.kklisura.cdtp.protocol.types.page.Viewport;
import java.util.List;

/**
 * Actions and events related to the inspected page belong to the page domain.
 */
public interface Page {

	/**
	 * Enables page domain notifications.
	 */
	void enable();

	/**
	 * Disables page domain notifications.
	 */
	void disable();

	/**
	 * Deprecated, please use addScriptToEvaluateOnNewDocument instead.
	 */
	@Deprecated
	@Experimental
	String addScriptToEvaluateOnLoad(String scriptSource);

	/**
	 * Deprecated, please use removeScriptToEvaluateOnNewDocument instead.
	 */
	@Deprecated
	@Experimental
	void removeScriptToEvaluateOnLoad(String identifier);

	/**
	 * Evaluates given script in every frame upon creation (before loading frame's scripts).
	 */
	@Experimental
	String addScriptToEvaluateOnNewDocument(String source);

	/**
	 * Removes given script from the list.
	 */
	@Experimental
	void removeScriptToEvaluateOnNewDocument(String identifier);

	/**
	 * Controls whether browser will open a new inspector window for connected pages.
	 */
	@Experimental
	void setAutoAttachToCreatedPages(Boolean autoAttach);

	/**
	 * Reloads given page optionally ignoring the cache.
	 */
	void reload(@Optional Boolean ignoreCache, @Optional String scriptToEvaluateOnLoad);

	/**
	 * Enable Chrome's experimental ad filter on all sites.
	 */
	@Experimental
	void setAdBlockingEnabled(Boolean enabled);

	/**
	 * Navigates current page to the given URL.
	 */
	String navigate(String url, @Experimental @Optional String referrer, @Experimental @Optional TransitionType transitionType);

	/**
	 * Force the page stop all navigations and pending resource fetches.
	 */
	@Experimental
	void stopLoading();

	/**
	 * Returns navigation history for the current page.
	 */
	@Experimental
	NavigationHistory getNavigationHistory();

	/**
	 * Navigates current page to the given history entry.
	 */
	@Experimental
	void navigateToHistoryEntry(Integer entryId);

	/**
	 * Returns all browser cookies. Depending on the backend support, will return detailed cookie information in the <code>cookies</code> field.
	 */
	@Experimental
	List<Cookie> getCookies();

	/**
	 * Deletes browser cookie with given name, domain and path.
	 */
	@Experimental
	void deleteCookie(String cookieName, String url);

	/**
	 * Returns present frame / resource tree structure.
	 */
	@Experimental
	FrameResourceTree getResourceTree();

	/**
	 * Returns content of the given resource.
	 */
	@Experimental
	ResourceContent getResourceContent(String frameId, String url);

	/**
	 * Searches for given string in resource content.
	 */
	@Experimental
	List<SearchMatch> searchInResource(String frameId, String url, String query, @Optional Boolean caseSensitive, @Optional Boolean isRegex);

	/**
	 * Sets given markup as the document's HTML.
	 */
	@Experimental
	void setDocumentContent(String frameId, String html);

	/**
	 * Overrides the values of device screen dimensions (window.screen.width, window.screen.height, window.innerWidth, window.innerHeight, and "device-width"/"device-height"-related CSS media query results).
	 */
	@Experimental
	void setDeviceMetricsOverride(Integer width, Integer height, Double deviceScaleFactor, Boolean mobile, @Optional Double scale, @Optional Integer screenWidth, @Optional Integer screenHeight, @Optional Integer positionX, @Optional Integer positionY, @Optional Boolean dontSetVisibleSize, @Optional ScreenOrientation screenOrientation);

	/**
	 * Clears the overriden device metrics.
	 */
	@Experimental
	void clearDeviceMetricsOverride();

	/**
	 * Overrides the Geolocation Position or Error. Omitting any of the parameters emulates position unavailable.
	 */
	void setGeolocationOverride(@Optional Double latitude, @Optional Double longitude, @Optional Double accuracy);

	/**
	 * Clears the overriden Geolocation Position and Error.
	 */
	void clearGeolocationOverride();

	/**
	 * Overrides the Device Orientation.
	 */
	@Experimental
	void setDeviceOrientationOverride(Double alpha, Double beta, Double gamma);

	/**
	 * Clears the overridden Device Orientation.
	 */
	@Experimental
	void clearDeviceOrientationOverride();

	/**
	 * Toggles mouse event-based touch event emulation.
	 */
	@Experimental
	void setTouchEmulationEnabled(Boolean enabled, @Optional Configuration configuration);

	/**
	 * Capture page screenshot.
	 */
	@Experimental
	String captureScreenshot(@Optional Format format, @Optional Integer quality, @Experimental @Optional Viewport clip, @Experimental @Optional Boolean fromSurface);

	/**
	 * Print page as PDF.
	 */
	@Experimental
	String printToPDF(@Optional Boolean landscape, @Optional Boolean displayHeaderFooter, @Optional Boolean printBackground, @Optional Double scale, @Optional Double paperWidth, @Optional Double paperHeight, @Optional Double marginTop, @Optional Double marginBottom, @Optional Double marginLeft, @Optional Double marginRight, @Optional String pageRanges, @Optional Boolean ignoreInvalidPageRanges);

	/**
	 * Starts sending each frame using the <code>screencastFrame</code> event.
	 */
	@Experimental
	void startScreencast(@Optional Format format, @Optional Integer quality, @Optional Integer maxWidth, @Optional Integer maxHeight, @Optional Integer everyNthFrame);

	/**
	 * Stops sending each frame in the <code>screencastFrame</code>.
	 */
	@Experimental
	void stopScreencast();

	/**
	 * Acknowledges that a screencast frame has been received by the frontend.
	 */
	@Experimental
	void screencastFrameAck(Integer sessionId);

	/**
	 * Accepts or dismisses a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload).
	 */
	void handleJavaScriptDialog(Boolean accept, @Optional String promptText);

	@Experimental
	AppManifest getAppManifest();

	@Experimental
	void requestAppBanner();

	/**
	 * Returns metrics relating to the layouting of the page, such as viewport bounds/scale.
	 */
	@Experimental
	LayoutMetrics getLayoutMetrics();

	/**
	 * Creates an isolated world for the given frame.
	 */
	@Experimental
	Integer createIsolatedWorld(String frameId, @Optional String worldName, @Optional Boolean grantUniveralAccess);

	/**
	 * Brings page to front (activates tab).
	 */
	void bringToFront();

	/**
	 * Set the behavior when downloading a file.
	 */
	@Experimental
	void setDownloadBehavior(Behavior behavior, @Optional String downloadPath);
}
