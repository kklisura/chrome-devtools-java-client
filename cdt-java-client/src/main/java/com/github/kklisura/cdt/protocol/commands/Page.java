package com.github.kklisura.cdt.protocol.commands;

/*-
 * #%L
 * cdt-java-client
 * %%
 * Copyright (C) 2018 Kenan Klisura
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.github.kklisura.cdt.protocol.events.page.DomContentEventFired;
import com.github.kklisura.cdt.protocol.events.page.FrameAttached;
import com.github.kklisura.cdt.protocol.events.page.FrameClearedScheduledNavigation;
import com.github.kklisura.cdt.protocol.events.page.FrameDetached;
import com.github.kklisura.cdt.protocol.events.page.FrameNavigated;
import com.github.kklisura.cdt.protocol.events.page.FrameResized;
import com.github.kklisura.cdt.protocol.events.page.FrameScheduledNavigation;
import com.github.kklisura.cdt.protocol.events.page.FrameStartedLoading;
import com.github.kklisura.cdt.protocol.events.page.FrameStoppedLoading;
import com.github.kklisura.cdt.protocol.events.page.InterstitialHidden;
import com.github.kklisura.cdt.protocol.events.page.InterstitialShown;
import com.github.kklisura.cdt.protocol.events.page.JavascriptDialogClosed;
import com.github.kklisura.cdt.protocol.events.page.JavascriptDialogOpening;
import com.github.kklisura.cdt.protocol.events.page.LifecycleEvent;
import com.github.kklisura.cdt.protocol.events.page.LoadEventFired;
import com.github.kklisura.cdt.protocol.events.page.NavigatedWithinDocument;
import com.github.kklisura.cdt.protocol.events.page.ScreencastFrame;
import com.github.kklisura.cdt.protocol.events.page.ScreencastVisibilityChanged;
import com.github.kklisura.cdt.protocol.events.page.WindowOpen;
import com.github.kklisura.cdt.protocol.support.annotations.EventName;
import com.github.kklisura.cdt.protocol.support.annotations.Experimental;
import com.github.kklisura.cdt.protocol.support.annotations.Optional;
import com.github.kklisura.cdt.protocol.support.annotations.ParamName;
import com.github.kklisura.cdt.protocol.support.annotations.Returns;
import com.github.kklisura.cdt.protocol.support.types.EventHandler;
import com.github.kklisura.cdt.protocol.support.types.EventListener;
import com.github.kklisura.cdt.protocol.types.debugger.SearchMatch;
import com.github.kklisura.cdt.protocol.types.page.AppManifest;
import com.github.kklisura.cdt.protocol.types.page.CaptureScreenshotFormat;
import com.github.kklisura.cdt.protocol.types.page.FontFamilies;
import com.github.kklisura.cdt.protocol.types.page.FontSizes;
import com.github.kklisura.cdt.protocol.types.page.FrameResourceTree;
import com.github.kklisura.cdt.protocol.types.page.FrameTree;
import com.github.kklisura.cdt.protocol.types.page.LayoutMetrics;
import com.github.kklisura.cdt.protocol.types.page.Navigate;
import com.github.kklisura.cdt.protocol.types.page.NavigationHistory;
import com.github.kklisura.cdt.protocol.types.page.ResourceContent;
import com.github.kklisura.cdt.protocol.types.page.SetDownloadBehaviorBehavior;
import com.github.kklisura.cdt.protocol.types.page.SetWebLifecycleStateState;
import com.github.kklisura.cdt.protocol.types.page.StartScreencastFormat;
import com.github.kklisura.cdt.protocol.types.page.TransitionType;
import com.github.kklisura.cdt.protocol.types.page.Viewport;
import java.util.List;

/** Actions and events related to the inspected page belong to the page domain. */
public interface Page {

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
   * Evaluates given script in every frame upon creation (before loading frame's scripts).
   *
   * @param source
   */
  @Returns("identifier")
  String addScriptToEvaluateOnNewDocument(@ParamName("source") String source);

  /** Brings page to front (activates tab). */
  void bringToFront();

  /** Capture page screenshot. */
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
  @Returns("data")
  String captureScreenshot(
      @Optional @ParamName("format") CaptureScreenshotFormat format,
      @Optional @ParamName("quality") Integer quality,
      @Optional @ParamName("clip") Viewport clip,
      @Experimental @Optional @ParamName("fromSurface") Boolean fromSurface);

  /**
   * Creates an isolated world for the given frame.
   *
   * @param frameId Id of the frame in which the isolated world should be created.
   */
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
  @Returns("executionContextId")
  Integer createIsolatedWorld(
      @ParamName("frameId") String frameId,
      @Optional @ParamName("worldName") String worldName,
      @Optional @ParamName("grantUniveralAccess") Boolean grantUniveralAccess);

  /** Disables page domain notifications. */
  void disable();

  /** Enables page domain notifications. */
  void enable();

  AppManifest getAppManifest();

  /** Returns present frame tree structure. */
  @Returns("frameTree")
  FrameTree getFrameTree();

  /** Returns metrics relating to the layouting of the page, such as viewport bounds/scale. */
  LayoutMetrics getLayoutMetrics();

  /** Returns navigation history for the current page. */
  NavigationHistory getNavigationHistory();

  /**
   * Returns content of the given resource.
   *
   * @param frameId Frame id to get resource for.
   * @param url URL of the resource to get content for.
   */
  @Experimental
  ResourceContent getResourceContent(
      @ParamName("frameId") String frameId, @ParamName("url") String url);

  /** Returns present frame / resource tree structure. */
  @Experimental
  @Returns("frameTree")
  FrameResourceTree getResourceTree();

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

  /**
   * Navigates current page to the given URL.
   *
   * @param url URL to navigate the page to.
   */
  Navigate navigate(@ParamName("url") String url);

  /**
   * Navigates current page to the given URL.
   *
   * @param url URL to navigate the page to.
   * @param referrer Referrer URL.
   * @param transitionType Intended transition type.
   * @param frameId Frame id to navigate, if not specified navigates the top frame.
   */
  Navigate navigate(
      @ParamName("url") String url,
      @Optional @ParamName("referrer") String referrer,
      @Optional @ParamName("transitionType") TransitionType transitionType,
      @Optional @ParamName("frameId") String frameId);

  /**
   * Navigates current page to the given history entry.
   *
   * @param entryId Unique id of the entry to navigate to.
   */
  void navigateToHistoryEntry(@ParamName("entryId") Integer entryId);

  /** Print page as PDF. */
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
   * @param headerTemplate HTML template for the print header. Should be valid HTML markup with
   *     following classes used to inject printing values into them: - `date`: formatted print date
   *     - `title`: document title - `url`: document location - `pageNumber`: current page number -
   *     `totalPages`: total pages in the document
   *     <p>For example, `<span class=title></span>` would generate span containing the title.
   * @param footerTemplate HTML template for the print footer. Should use the same format as the
   *     `headerTemplate`.
   * @param preferCSSPageSize Whether or not to prefer page size as defined by css. Defaults to
   *     false, in which case the content will be scaled to fit the paper size.
   */
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
      @Optional @ParamName("ignoreInvalidPageRanges") Boolean ignoreInvalidPageRanges,
      @Optional @ParamName("headerTemplate") String headerTemplate,
      @Optional @ParamName("footerTemplate") String footerTemplate,
      @Optional @ParamName("preferCSSPageSize") Boolean preferCSSPageSize);

  /** Reloads given page optionally ignoring the cache. */
  void reload();

  /**
   * Reloads given page optionally ignoring the cache.
   *
   * @param ignoreCache If true, browser cache is ignored (as if the user pressed Shift+refresh).
   * @param scriptToEvaluateOnLoad If set, the script will be injected into all frames of the
   *     inspected page after reload. Argument will be ignored if reloading dataURL origin.
   */
  void reload(
      @Optional @ParamName("ignoreCache") Boolean ignoreCache,
      @Optional @ParamName("scriptToEvaluateOnLoad") String scriptToEvaluateOnLoad);

  /**
   * Deprecated, please use removeScriptToEvaluateOnNewDocument instead.
   *
   * @param identifier
   */
  @Deprecated
  @Experimental
  void removeScriptToEvaluateOnLoad(@ParamName("identifier") String identifier);

  /**
   * Removes given script from the list.
   *
   * @param identifier
   */
  void removeScriptToEvaluateOnNewDocument(@ParamName("identifier") String identifier);

  @Experimental
  void requestAppBanner();

  /**
   * Acknowledges that a screencast frame has been received by the frontend.
   *
   * @param sessionId Frame number.
   */
  @Experimental
  void screencastFrameAck(@ParamName("sessionId") Integer sessionId);

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
   * Enable Chrome's experimental ad filter on all sites.
   *
   * @param enabled Whether to block ads.
   */
  @Experimental
  void setAdBlockingEnabled(@ParamName("enabled") Boolean enabled);

  /**
   * Enable page Content Security Policy by-passing.
   *
   * @param enabled Whether to bypass page CSP.
   */
  @Experimental
  void setBypassCSP(@ParamName("enabled") Boolean enabled);

  /**
   * Set generic font families.
   *
   * @param fontFamilies Specifies font families to set. If a font family is not specified, it won't
   *     be changed.
   */
  @Experimental
  void setFontFamilies(@ParamName("fontFamilies") FontFamilies fontFamilies);

  /**
   * Set default font sizes.
   *
   * @param fontSizes Specifies font sizes to set. If a font size is not specified, it won't be
   *     changed.
   */
  @Experimental
  void setFontSizes(@ParamName("fontSizes") FontSizes fontSizes);

  /**
   * Sets given markup as the document's HTML.
   *
   * @param frameId Frame id to set HTML for.
   * @param html HTML content to set.
   */
  void setDocumentContent(@ParamName("frameId") String frameId, @ParamName("html") String html);

  /**
   * Set the behavior when downloading a file.
   *
   * @param behavior Whether to allow all or deny all download requests, or use default Chrome
   *     behavior if available (otherwise deny).
   */
  @Experimental
  void setDownloadBehavior(@ParamName("behavior") SetDownloadBehaviorBehavior behavior);

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
      @ParamName("behavior") SetDownloadBehaviorBehavior behavior,
      @Optional @ParamName("downloadPath") String downloadPath);

  /**
   * Controls whether page will emit lifecycle events.
   *
   * @param enabled If true, starts emitting lifecycle events.
   */
  @Experimental
  void setLifecycleEventsEnabled(@ParamName("enabled") Boolean enabled);

  /** Starts sending each frame using the `screencastFrame` event. */
  @Experimental
  void startScreencast();

  /**
   * Starts sending each frame using the `screencastFrame` event.
   *
   * @param format Image compression format.
   * @param quality Compression quality from range [0..100].
   * @param maxWidth Maximum screenshot width.
   * @param maxHeight Maximum screenshot height.
   * @param everyNthFrame Send every n-th frame.
   */
  @Experimental
  void startScreencast(
      @Optional @ParamName("format") StartScreencastFormat format,
      @Optional @ParamName("quality") Integer quality,
      @Optional @ParamName("maxWidth") Integer maxWidth,
      @Optional @ParamName("maxHeight") Integer maxHeight,
      @Optional @ParamName("everyNthFrame") Integer everyNthFrame);

  /** Force the page stop all navigations and pending resource fetches. */
  void stopLoading();

  /** Crashes renderer on the IO thread, generates minidumps. */
  @Experimental
  void crash();

  /** Tries to close page, running its beforeunload hooks, if any. */
  @Experimental
  void close();

  /**
   * Tries to update the web lifecycle state of the page. It will transition the page to the given
   * state according to: https://github.com/WICG/web-lifecycle/
   *
   * @param state Target lifecycle state
   */
  @Experimental
  void setWebLifecycleState(@ParamName("state") SetWebLifecycleStateState state);

  /** Stops sending each frame in the `screencastFrame`. */
  @Experimental
  void stopScreencast();

  @EventName("domContentEventFired")
  EventListener onDomContentEventFired(EventHandler<DomContentEventFired> eventListener);

  /** Fired when frame has been attached to its parent. */
  @EventName("frameAttached")
  EventListener onFrameAttached(EventHandler<FrameAttached> eventListener);

  /** Fired when frame no longer has a scheduled navigation. */
  @EventName("frameClearedScheduledNavigation")
  @Experimental
  EventListener onFrameClearedScheduledNavigation(
      EventHandler<FrameClearedScheduledNavigation> eventListener);

  /** Fired when frame has been detached from its parent. */
  @EventName("frameDetached")
  EventListener onFrameDetached(EventHandler<FrameDetached> eventListener);

  /**
   * Fired once navigation of the frame has completed. Frame is now associated with the new loader.
   */
  @EventName("frameNavigated")
  EventListener onFrameNavigated(EventHandler<FrameNavigated> eventListener);

  @EventName("frameResized")
  @Experimental
  EventListener onFrameResized(EventHandler<FrameResized> eventListener);

  /** Fired when frame schedules a potential navigation. */
  @EventName("frameScheduledNavigation")
  @Experimental
  EventListener onFrameScheduledNavigation(EventHandler<FrameScheduledNavigation> eventListener);

  /** Fired when frame has started loading. */
  @EventName("frameStartedLoading")
  @Experimental
  EventListener onFrameStartedLoading(EventHandler<FrameStartedLoading> eventListener);

  /** Fired when frame has stopped loading. */
  @EventName("frameStoppedLoading")
  @Experimental
  EventListener onFrameStoppedLoading(EventHandler<FrameStoppedLoading> eventListener);

  /** Fired when interstitial page was hidden */
  @EventName("interstitialHidden")
  EventListener onInterstitialHidden(EventHandler<InterstitialHidden> eventListener);

  /** Fired when interstitial page was shown */
  @EventName("interstitialShown")
  EventListener onInterstitialShown(EventHandler<InterstitialShown> eventListener);

  /**
   * Fired when a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload) has been
   * closed.
   */
  @EventName("javascriptDialogClosed")
  EventListener onJavascriptDialogClosed(EventHandler<JavascriptDialogClosed> eventListener);

  /**
   * Fired when a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload) is about
   * to open.
   */
  @EventName("javascriptDialogOpening")
  EventListener onJavascriptDialogOpening(EventHandler<JavascriptDialogOpening> eventListener);

  /** Fired for top level page lifecycle events such as navigation, load, paint, etc. */
  @EventName("lifecycleEvent")
  EventListener onLifecycleEvent(EventHandler<LifecycleEvent> eventListener);

  @EventName("loadEventFired")
  EventListener onLoadEventFired(EventHandler<LoadEventFired> eventListener);

  /**
   * Fired when same-document navigation happens, e.g. due to history API usage or anchor
   * navigation.
   */
  @EventName("navigatedWithinDocument")
  @Experimental
  EventListener onNavigatedWithinDocument(EventHandler<NavigatedWithinDocument> eventListener);

  /** Compressed image data requested by the `startScreencast`. */
  @EventName("screencastFrame")
  @Experimental
  EventListener onScreencastFrame(EventHandler<ScreencastFrame> eventListener);

  /** Fired when the page with currently enabled screencast was shown or hidden `. */
  @EventName("screencastVisibilityChanged")
  @Experimental
  EventListener onScreencastVisibilityChanged(
      EventHandler<ScreencastVisibilityChanged> eventListener);

  /**
   * Fired when a new window is going to be opened, via window.open(), link click, form submission,
   * etc.
   */
  @EventName("windowOpen")
  EventListener onWindowOpen(EventHandler<WindowOpen> eventListener);
}
