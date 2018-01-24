package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.events.page.DomContentEventFired;
import com.github.kklisura.cdtp.protocol.events.page.FrameAttached;
import com.github.kklisura.cdtp.protocol.events.page.FrameClearedScheduledNavigation;
import com.github.kklisura.cdtp.protocol.events.page.FrameDetached;
import com.github.kklisura.cdtp.protocol.events.page.FrameNavigated;
import com.github.kklisura.cdtp.protocol.events.page.FrameResized;
import com.github.kklisura.cdtp.protocol.events.page.FrameScheduledNavigation;
import com.github.kklisura.cdtp.protocol.events.page.FrameStartedLoading;
import com.github.kklisura.cdtp.protocol.events.page.FrameStoppedLoading;
import com.github.kklisura.cdtp.protocol.events.page.InterstitialHidden;
import com.github.kklisura.cdtp.protocol.events.page.InterstitialShown;
import com.github.kklisura.cdtp.protocol.events.page.JavascriptDialogClosed;
import com.github.kklisura.cdtp.protocol.events.page.JavascriptDialogOpening;
import com.github.kklisura.cdtp.protocol.events.page.LifecycleEvent;
import com.github.kklisura.cdtp.protocol.events.page.LoadEventFired;
import com.github.kklisura.cdtp.protocol.events.page.ScreencastFrame;
import com.github.kklisura.cdtp.protocol.events.page.ScreencastVisibilityChanged;
import com.github.kklisura.cdtp.protocol.support.annotations.EventName;
import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import com.github.kklisura.cdtp.protocol.support.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.support.annotations.Returns;
import com.github.kklisura.cdtp.protocol.support.types.EventHandler;
import com.github.kklisura.cdtp.protocol.support.types.EventListener;
import com.github.kklisura.cdtp.protocol.types.debugger.SearchMatch;
import com.github.kklisura.cdtp.protocol.types.page.AppManifest;
import com.github.kklisura.cdtp.protocol.types.page.Behavior;
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
	@Returns("identifier")
	String addScriptToEvaluateOnLoad(@ParamName("scriptSource") String scriptSource);

	/**
	 * Deprecated, please use removeScriptToEvaluateOnNewDocument instead.
	 */
	@Deprecated
	@Experimental
	void removeScriptToEvaluateOnLoad(@ParamName("identifier") String identifier);

	/**
	 * Evaluates given script in every frame upon creation (before loading frame's scripts).
	 */
	@Experimental
	@Returns("identifier")
	String addScriptToEvaluateOnNewDocument(@ParamName("source") String source);

	/**
	 * Removes given script from the list.
	 */
	@Experimental
	void removeScriptToEvaluateOnNewDocument(@ParamName("identifier") String identifier);

	/**
	 * Controls whether browser will open a new inspector window for connected pages.
	 */
	@Experimental
	void setAutoAttachToCreatedPages(@ParamName("autoAttach") Boolean autoAttach);

	/**
	 * Reloads given page optionally ignoring the cache.
	 */
	void reload();

	/**
	 * Reloads given page optionally ignoring the cache.
	 */
	void reload(@Optional @ParamName("ignoreCache") Boolean ignoreCache, @Optional @ParamName("scriptToEvaluateOnLoad") String scriptToEvaluateOnLoad);

	/**
	 * Enable Chrome's experimental ad filter on all sites.
	 */
	@Experimental
	void setAdBlockingEnabled(@ParamName("enabled") Boolean enabled);

	/**
	 * Navigates current page to the given URL.
	 */
	@Returns("frameId")
	String navigate(@ParamName("url") String url);

	/**
	 * Navigates current page to the given URL.
	 */
	@Returns("frameId")
	String navigate(@ParamName("url") String url, @Experimental @Optional @ParamName("referrer") String referrer, @Experimental @Optional @ParamName("transitionType") TransitionType transitionType);

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
	void navigateToHistoryEntry(@ParamName("entryId") Integer entryId);

	/**
	 * Returns present frame / resource tree structure.
	 */
	@Experimental
	@Returns("frameTree")
	FrameResourceTree getResourceTree();

	/**
	 * Returns content of the given resource.
	 */
	@Experimental
	ResourceContent getResourceContent(@ParamName("frameId") String frameId, @ParamName("url") String url);

	/**
	 * Searches for given string in resource content.
	 */
	@Experimental
	@Returns("result")
	List<SearchMatch> searchInResource(@ParamName("frameId") String frameId, @ParamName("url") String url, @ParamName("query") String query);

	/**
	 * Searches for given string in resource content.
	 */
	@Experimental
	@Returns("result")
	List<SearchMatch> searchInResource(@ParamName("frameId") String frameId, @ParamName("url") String url, @ParamName("query") String query, @Optional @ParamName("caseSensitive") Boolean caseSensitive, @Optional @ParamName("isRegex") Boolean isRegex);

	/**
	 * Sets given markup as the document's HTML.
	 */
	@Experimental
	void setDocumentContent(@ParamName("frameId") String frameId, @ParamName("html") String html);

	/**
	 * Capture page screenshot.
	 */
	@Experimental
	@Returns("data")
	String captureScreenshot();

	/**
	 * Capture page screenshot.
	 */
	@Experimental
	@Returns("data")
	String captureScreenshot(@Optional @ParamName("format") Format format, @Optional @ParamName("quality") Integer quality, @Experimental @Optional @ParamName("clip") Viewport clip, @Experimental @Optional @ParamName("fromSurface") Boolean fromSurface);

	/**
	 * Print page as PDF.
	 */
	@Experimental
	@Returns("data")
	String printToPDF();

	/**
	 * Print page as PDF.
	 */
	@Experimental
	@Returns("data")
	String printToPDF(@Optional @ParamName("landscape") Boolean landscape, @Optional @ParamName("displayHeaderFooter") Boolean displayHeaderFooter, @Optional @ParamName("printBackground") Boolean printBackground, @Optional @ParamName("scale") Double scale, @Optional @ParamName("paperWidth") Double paperWidth, @Optional @ParamName("paperHeight") Double paperHeight, @Optional @ParamName("marginTop") Double marginTop, @Optional @ParamName("marginBottom") Double marginBottom, @Optional @ParamName("marginLeft") Double marginLeft, @Optional @ParamName("marginRight") Double marginRight, @Optional @ParamName("pageRanges") String pageRanges, @Optional @ParamName("ignoreInvalidPageRanges") Boolean ignoreInvalidPageRanges);

	/**
	 * Starts sending each frame using the <code>screencastFrame</code> event.
	 */
	@Experimental
	void startScreencast();

	/**
	 * Starts sending each frame using the <code>screencastFrame</code> event.
	 */
	@Experimental
	void startScreencast(@Optional @ParamName("format") Format format, @Optional @ParamName("quality") Integer quality, @Optional @ParamName("maxWidth") Integer maxWidth, @Optional @ParamName("maxHeight") Integer maxHeight, @Optional @ParamName("everyNthFrame") Integer everyNthFrame);

	/**
	 * Stops sending each frame in the <code>screencastFrame</code>.
	 */
	@Experimental
	void stopScreencast();

	/**
	 * Acknowledges that a screencast frame has been received by the frontend.
	 */
	@Experimental
	void screencastFrameAck(@ParamName("sessionId") Integer sessionId);

	/**
	 * Accepts or dismisses a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload).
	 */
	void handleJavaScriptDialog(@ParamName("accept") Boolean accept);

	/**
	 * Accepts or dismisses a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload).
	 */
	void handleJavaScriptDialog(@ParamName("accept") Boolean accept, @Optional @ParamName("promptText") String promptText);

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
	@Returns("executionContextId")
	Integer createIsolatedWorld(@ParamName("frameId") String frameId);

	/**
	 * Creates an isolated world for the given frame.
	 */
	@Experimental
	@Returns("executionContextId")
	Integer createIsolatedWorld(@ParamName("frameId") String frameId, @Optional @ParamName("worldName") String worldName, @Optional @ParamName("grantUniveralAccess") Boolean grantUniveralAccess);

	/**
	 * Brings page to front (activates tab).
	 */
	void bringToFront();

	/**
	 * Set the behavior when downloading a file.
	 */
	@Experimental
	void setDownloadBehavior(@ParamName("behavior") Behavior behavior);

	/**
	 * Set the behavior when downloading a file.
	 */
	@Experimental
	void setDownloadBehavior(@ParamName("behavior") Behavior behavior, @Optional @ParamName("downloadPath") String downloadPath);

	@EventName("domContentEventFired")
	EventListener onDomContentEventFired(EventHandler<DomContentEventFired> eventListener);

	@EventName("loadEventFired")
	EventListener onLoadEventFired(EventHandler<LoadEventFired> eventListener);

	/**
	 * Fired for top level page lifecycle events such as navigation, load, paint, etc.
	 */
	@EventName("lifecycleEvent")
	EventListener onLifecycleEvent(EventHandler<LifecycleEvent> eventListener);

	/**
	 * Fired when frame has been attached to its parent.
	 */
	@EventName("frameAttached")
	EventListener onFrameAttached(EventHandler<FrameAttached> eventListener);

	/**
	 * Fired once navigation of the frame has completed. Frame is now associated with the new loader.
	 */
	@EventName("frameNavigated")
	EventListener onFrameNavigated(EventHandler<FrameNavigated> eventListener);

	/**
	 * Fired when frame has been detached from its parent.
	 */
	@EventName("frameDetached")
	EventListener onFrameDetached(EventHandler<FrameDetached> eventListener);

	/**
	 * Fired when frame has started loading.
	 */
	@EventName("frameStartedLoading")
	@Experimental
	EventListener onFrameStartedLoading(EventHandler<FrameStartedLoading> eventListener);

	/**
	 * Fired when frame has stopped loading.
	 */
	@EventName("frameStoppedLoading")
	@Experimental
	EventListener onFrameStoppedLoading(EventHandler<FrameStoppedLoading> eventListener);

	/**
	 * Fired when frame schedules a potential navigation.
	 */
	@EventName("frameScheduledNavigation")
	@Experimental
	EventListener onFrameScheduledNavigation(EventHandler<FrameScheduledNavigation> eventListener);

	/**
	 * Fired when frame no longer has a scheduled navigation.
	 */
	@EventName("frameClearedScheduledNavigation")
	@Experimental
	EventListener onFrameClearedScheduledNavigation(EventHandler<FrameClearedScheduledNavigation> eventListener);

	@EventName("frameResized")
	@Experimental
	EventListener onFrameResized(EventHandler<FrameResized> eventListener);

	/**
	 * Fired when a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload) is about to open.
	 */
	@EventName("javascriptDialogOpening")
	EventListener onJavascriptDialogOpening(EventHandler<JavascriptDialogOpening> eventListener);

	/**
	 * Fired when a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload) has been closed.
	 */
	@EventName("javascriptDialogClosed")
	EventListener onJavascriptDialogClosed(EventHandler<JavascriptDialogClosed> eventListener);

	/**
	 * Compressed image data requested by the <code>startScreencast</code>.
	 */
	@EventName("screencastFrame")
	@Experimental
	EventListener onScreencastFrame(EventHandler<ScreencastFrame> eventListener);

	/**
	 * Fired when the page with currently enabled screencast was shown or hidden </code>.
	 */
	@EventName("screencastVisibilityChanged")
	@Experimental
	EventListener onScreencastVisibilityChanged(EventHandler<ScreencastVisibilityChanged> eventListener);

	/**
	 * Fired when interstitial page was shown
	 */
	@EventName("interstitialShown")
	EventListener onInterstitialShown(EventHandler<InterstitialShown> eventListener);

	/**
	 * Fired when interstitial page was hidden
	 */
	@EventName("interstitialHidden")
	EventListener onInterstitialHidden(EventHandler<InterstitialHidden> eventListener);
}
