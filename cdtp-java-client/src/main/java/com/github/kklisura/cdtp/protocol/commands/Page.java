package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.Optional;
import com.github.kklisura.cdtp.protocol.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.annotations.Returns;
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
}
