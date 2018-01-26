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

/** Actions and events related to the inspected page belong to the page domain. */
public interface Page {

  /** Enables page domain notifications. */
  void enable();

  /** Disables page domain notifications. */
  void disable();

  /**
   * Deprecated, please use addScriptToEvaluateOnNewDocument instead.
   *
   * @param scriptSource
   */
  @Deprecated
  @Experimental
  @Returns("identifier")
  String addScriptToEvaluateOnLoad(@ParamName("scriptSource") String scriptSource);

  /**
   * Deprecated, please use removeScriptToEvaluateOnNewDocument instead.
   *
   * @param identifier
   */
  @Deprecated
  @Experimental
  void removeScriptToEvaluateOnLoad(@ParamName("identifier") String identifier);

  /**
   * Evaluates given script in every frame upon creation (before loading frame's scripts).
   *
   * @param source
   */
  @Experimental
  @Returns("identifier")
  String addScriptToEvaluateOnNewDocument(@ParamName("source") String source);

  /**
   * Removes given script from the list.
   *
   * @param identifier
   */
  @Experimental
  void removeScriptToEvaluateOnNewDocument(@ParamName("identifier") String identifier);

  /**
   * Controls whether browser will open a new inspector window for connected pages.
   *
   * @param autoAttach If true, browser will open a new inspector window for every page created from
   *     this one.
   */
  @Experimental
  void setAutoAttachToCreatedPages(@ParamName("autoAttach") Boolean autoAttach);

  /** Reloads given page optionally ignoring the cache. */
  void reload();

  /**
   * Reloads given page optionally ignoring the cache.
   *
   * @param ignoreCache If true, browser cache is ignored (as if the user pressed Shift+refresh).
   * @param scriptToEvaluateOnLoad If set, the script will be injected into all frames of the
   *     inspected page after reload.
   */
  void reload(
      @Optional @ParamName("ignoreCache") Boolean ignoreCache,
      @Optional @ParamName("scriptToEvaluateOnLoad") String scriptToEvaluateOnLoad);

  /**
   * Enable Chrome's experimental ad filter on all sites.
   *
   * @param enabled Whether to block ads.
   */
  @Experimental
  void setAdBlockingEnabled(@ParamName("enabled") Boolean enabled);

  /**
   * Navigates current page to the given URL.
   *
   * @param url URL to navigate the page to.
   */
  @Returns("frameId")
  String navigate(@ParamName("url") String url);

  /**
   * Navigates current page to the given URL.
   *
   * @param url URL to navigate the page to.
   * @param referrer Referrer URL.
   * @param transitionType Intended transition type.
   */
  @Returns("frameId")
  String navigate(
      @ParamName("url") String url,
      @Experimental @Optional @ParamName("referrer") String referrer,
      @Experimental @Optional @ParamName("transitionType") TransitionType transitionType);

  /** Force the page stop all navigations and pending resource fetches. */
  @Experimental
  void stopLoading();

  /** Returns navigation history for the current page. */
  @Experimental
  NavigationHistory getNavigationHistory();

  /**
   * Navigates current page to the given history entry.
   *
   * @param entryId Unique id of the entry to navigate to.
   */
  @Experimental
  void navigateToHistoryEntry(@ParamName("entryId") Integer entryId);

  /** Returns present frame / resource tree structure. */
  @Experimental
  @Returns("frameTree")
  FrameResourceTree getResourceTree();

  /**
   * Returns content of the given resource.
   *
   * @param frameId Frame id to get resource for.
   * @param url URL of the resource to get content for.
   */
  @Experimental
  ResourceContent getResourceContent(
      @ParamName("frameId") String frameId, @ParamName("url") String url);

  /**
   * Searches for given string in resource content.
   *
   * @param frameId Frame id for resource to search in.
   * @param url URL of the resource to search in.
   * @param query String to search for.
   */
  @Experimental
  @Returns("result")
  List<SearchMatch> searchInResource(
      @ParamName("frameId") String frameId,
      @ParamName("url") String url,
      @ParamName("query") String query);

  /**
   * Searches for given string in resource content.
   *
   * @param frameId Frame id for resource to search in.
   * @param url URL of the resource to search in.
   * @param query String to search for.
   * @param caseSensitive If true, search is case sensitive.
   * @param isRegex If true, treats string parameter as regex.
   */
  @Experimental
  @Returns("result")
  List<SearchMatch> searchInResource(
      @ParamName("frameId") String frameId,
      @ParamName("url") String url,
      @ParamName("query") String query,
      @Optional @ParamName("caseSensitive") Boolean caseSensitive,
      @Optional @ParamName("isRegex") Boolean isRegex);

  /**
   * Sets given markup as the document's HTML.
   *
   * @param frameId Frame id to set HTML for.
   * @param html HTML content to set.
   */
  @Experimental
  void setDocumentContent(@ParamName("frameId") String frameId, @ParamName("html") String html);

  /** Capture page screenshot. */
  @Experimental
  @Returns("data")
  String captureScreenshot();

  /**
   * Capture page screenshot.
   *
   * @param format Image compression format (defaults to png).
   * @param quality Compression quality from range [0..100] (jpeg only).
   * @param clip Capture the screenshot of a given region only.
   * @param fromSurface Capture the screenshot from the surface, rather than the view. Defaults to
   *     true.
   */
  @Experimental
  @Returns("data")
  String captureScreenshot(
      @Optional @ParamName("format") Format format,
      @Optional @ParamName("quality") Integer quality,
      @Experimental @Optional @ParamName("clip") Viewport clip,
      @Experimental @Optional @ParamName("fromSurface") Boolean fromSurface);

  /** Print page as PDF. */
  @Experimental
  @Returns("data")
  String printToPDF();

  /**
   * Print page as PDF.
   *
   * @param landscape Paper orientation. Defaults to false.
   * @param displayHeaderFooter Display header and footer. Defaults to false.
   * @param printBackground Print background graphics. Defaults to false.
   * @param scale Scale of the webpage rendering. Defaults to 1.
   * @param paperWidth Paper width in inches. Defaults to 8.5 inches.
   * @param paperHeight Paper height in inches. Defaults to 11 inches.
   * @param marginTop Top margin in inches. Defaults to 1cm (~0.4 inches).
   * @param marginBottom Bottom margin in inches. Defaults to 1cm (~0.4 inches).
   * @param marginLeft Left margin in inches. Defaults to 1cm (~0.4 inches).
   * @param marginRight Right margin in inches. Defaults to 1cm (~0.4 inches).
   * @param pageRanges Paper ranges to print, e.g., '1-5, 8, 11-13'. Defaults to the empty string,
   *     which means print all pages.
   * @param ignoreInvalidPageRanges Whether to silently ignore invalid but successfully parsed page
   *     ranges, such as '3-2'. Defaults to false.
   */
  @Experimental
  @Returns("data")
  String printToPDF(
      @Optional @ParamName("landscape") Boolean landscape,
      @Optional @ParamName("displayHeaderFooter") Boolean displayHeaderFooter,
      @Optional @ParamName("printBackground") Boolean printBackground,
      @Optional @ParamName("scale") Double scale,
      @Optional @ParamName("paperWidth") Double paperWidth,
      @Optional @ParamName("paperHeight") Double paperHeight,
      @Optional @ParamName("marginTop") Double marginTop,
      @Optional @ParamName("marginBottom") Double marginBottom,
      @Optional @ParamName("marginLeft") Double marginLeft,
      @Optional @ParamName("marginRight") Double marginRight,
      @Optional @ParamName("pageRanges") String pageRanges,
      @Optional @ParamName("ignoreInvalidPageRanges") Boolean ignoreInvalidPageRanges);

  /** Starts sending each frame using the <code>screencastFrame</code> event. */
  @Experimental
  void startScreencast();

  /**
   * Starts sending each frame using the <code>screencastFrame</code> event.
   *
   * @param format Image compression format.
   * @param quality Compression quality from range [0..100].
   * @param maxWidth Maximum screenshot width.
   * @param maxHeight Maximum screenshot height.
   * @param everyNthFrame Send every n-th frame.
   */
  @Experimental
  void startScreencast(
      @Optional @ParamName("format") Format format,
      @Optional @ParamName("quality") Integer quality,
      @Optional @ParamName("maxWidth") Integer maxWidth,
      @Optional @ParamName("maxHeight") Integer maxHeight,
      @Optional @ParamName("everyNthFrame") Integer everyNthFrame);

  /** Stops sending each frame in the <code>screencastFrame</code>. */
  @Experimental
  void stopScreencast();

  /**
   * Acknowledges that a screencast frame has been received by the frontend.
   *
   * @param sessionId Frame number.
   */
  @Experimental
  void screencastFrameAck(@ParamName("sessionId") Integer sessionId);

  /**
   * Accepts or dismisses a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload).
   *
   * @param accept Whether to accept or dismiss the dialog.
   */
  void handleJavaScriptDialog(@ParamName("accept") Boolean accept);

  /**
   * Accepts or dismisses a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload).
   *
   * @param accept Whether to accept or dismiss the dialog.
   * @param promptText The text to enter into the dialog prompt before accepting. Used only if this
   *     is a prompt dialog.
   */
  void handleJavaScriptDialog(
      @ParamName("accept") Boolean accept, @Optional @ParamName("promptText") String promptText);

  @Experimental
  AppManifest getAppManifest();

  @Experimental
  void requestAppBanner();

  /** Returns metrics relating to the layouting of the page, such as viewport bounds/scale. */
  @Experimental
  LayoutMetrics getLayoutMetrics();

  /**
   * Creates an isolated world for the given frame.
   *
   * @param frameId Id of the frame in which the isolated world should be created.
   */
  @Experimental
  @Returns("executionContextId")
  Integer createIsolatedWorld(@ParamName("frameId") String frameId);

  /**
   * Creates an isolated world for the given frame.
   *
   * @param frameId Id of the frame in which the isolated world should be created.
   * @param worldName An optional name which is reported in the Execution Context.
   * @param grantUniveralAccess Whether or not universal access should be granted to the isolated
   *     world. This is a powerful option, use with caution.
   */
  @Experimental
  @Returns("executionContextId")
  Integer createIsolatedWorld(
      @ParamName("frameId") String frameId,
      @Optional @ParamName("worldName") String worldName,
      @Optional @ParamName("grantUniveralAccess") Boolean grantUniveralAccess);

  /** Brings page to front (activates tab). */
  void bringToFront();

  /**
   * Set the behavior when downloading a file.
   *
   * @param behavior Whether to allow all or deny all download requests, or use default Chrome
   *     behavior if available (otherwise deny).
   */
  @Experimental
  void setDownloadBehavior(@ParamName("behavior") Behavior behavior);

  /**
   * Set the behavior when downloading a file.
   *
   * @param behavior Whether to allow all or deny all download requests, or use default Chrome
   *     behavior if available (otherwise deny).
   * @param downloadPath The default path to save downloaded files to. This is requred if behavior
   *     is set to 'allow'
   */
  @Experimental
  void setDownloadBehavior(
      @ParamName("behavior") Behavior behavior,
      @Optional @ParamName("downloadPath") String downloadPath);

  @EventName("domContentEventFired")
  EventListener onDomContentEventFired(EventHandler<DomContentEventFired> eventListener);

  @EventName("loadEventFired")
  EventListener onLoadEventFired(EventHandler<LoadEventFired> eventListener);

  /** Fired for top level page lifecycle events such as navigation, load, paint, etc. */
  @EventName("lifecycleEvent")
  EventListener onLifecycleEvent(EventHandler<LifecycleEvent> eventListener);

  /** Fired when frame has been attached to its parent. */
  @EventName("frameAttached")
  EventListener onFrameAttached(EventHandler<FrameAttached> eventListener);

  /**
   * Fired once navigation of the frame has completed. Frame is now associated with the new loader.
   */
  @EventName("frameNavigated")
  EventListener onFrameNavigated(EventHandler<FrameNavigated> eventListener);

  /** Fired when frame has been detached from its parent. */
  @EventName("frameDetached")
  EventListener onFrameDetached(EventHandler<FrameDetached> eventListener);

  /** Fired when frame has started loading. */
  @EventName("frameStartedLoading")
  @Experimental
  EventListener onFrameStartedLoading(EventHandler<FrameStartedLoading> eventListener);

  /** Fired when frame has stopped loading. */
  @EventName("frameStoppedLoading")
  @Experimental
  EventListener onFrameStoppedLoading(EventHandler<FrameStoppedLoading> eventListener);

  /** Fired when frame schedules a potential navigation. */
  @EventName("frameScheduledNavigation")
  @Experimental
  EventListener onFrameScheduledNavigation(EventHandler<FrameScheduledNavigation> eventListener);

  /** Fired when frame no longer has a scheduled navigation. */
  @EventName("frameClearedScheduledNavigation")
  @Experimental
  EventListener onFrameClearedScheduledNavigation(
      EventHandler<FrameClearedScheduledNavigation> eventListener);

  @EventName("frameResized")
  @Experimental
  EventListener onFrameResized(EventHandler<FrameResized> eventListener);

  /**
   * Fired when a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload) is about
   * to open.
   */
  @EventName("javascriptDialogOpening")
  EventListener onJavascriptDialogOpening(EventHandler<JavascriptDialogOpening> eventListener);

  /**
   * Fired when a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload) has been
   * closed.
   */
  @EventName("javascriptDialogClosed")
  EventListener onJavascriptDialogClosed(EventHandler<JavascriptDialogClosed> eventListener);

  /** Compressed image data requested by the <code>startScreencast</code>. */
  @EventName("screencastFrame")
  @Experimental
  EventListener onScreencastFrame(EventHandler<ScreencastFrame> eventListener);

  /** Fired when the page with currently enabled screencast was shown or hidden </code>. */
  @EventName("screencastVisibilityChanged")
  @Experimental
  EventListener onScreencastVisibilityChanged(
      EventHandler<ScreencastVisibilityChanged> eventListener);

  /** Fired when interstitial page was shown */
  @EventName("interstitialShown")
  EventListener onInterstitialShown(EventHandler<InterstitialShown> eventListener);

  /** Fired when interstitial page was hidden */
  @EventName("interstitialHidden")
  EventListener onInterstitialHidden(EventHandler<InterstitialHidden> eventListener);
}
