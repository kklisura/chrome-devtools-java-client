package com.github.kklisura.cdt.protocol.commands;

/*-
 * #%L
 * cdt-java-client
 * %%
 * Copyright (C) 2018 - 2025 Kenan Klisura
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

import com.github.kklisura.cdt.protocol.events.page.BackForwardCacheNotUsed;
import com.github.kklisura.cdt.protocol.events.page.CompilationCacheProduced;
import com.github.kklisura.cdt.protocol.events.page.DocumentOpened;
import com.github.kklisura.cdt.protocol.events.page.DomContentEventFired;
import com.github.kklisura.cdt.protocol.events.page.DownloadProgress;
import com.github.kklisura.cdt.protocol.events.page.DownloadWillBegin;
import com.github.kklisura.cdt.protocol.events.page.FileChooserOpened;
import com.github.kklisura.cdt.protocol.events.page.FrameAttached;
import com.github.kklisura.cdt.protocol.events.page.FrameClearedScheduledNavigation;
import com.github.kklisura.cdt.protocol.events.page.FrameDetached;
import com.github.kklisura.cdt.protocol.events.page.FrameNavigated;
import com.github.kklisura.cdt.protocol.events.page.FrameRequestedNavigation;
import com.github.kklisura.cdt.protocol.events.page.FrameResized;
import com.github.kklisura.cdt.protocol.events.page.FrameScheduledNavigation;
import com.github.kklisura.cdt.protocol.events.page.FrameStartedLoading;
import com.github.kklisura.cdt.protocol.events.page.FrameStartedNavigating;
import com.github.kklisura.cdt.protocol.events.page.FrameStoppedLoading;
import com.github.kklisura.cdt.protocol.events.page.FrameSubtreeWillBeDetached;
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
import com.github.kklisura.cdt.protocol.support.annotations.ReturnTypeParameter;
import com.github.kklisura.cdt.protocol.support.annotations.Returns;
import com.github.kklisura.cdt.protocol.support.types.EventHandler;
import com.github.kklisura.cdt.protocol.support.types.EventListener;
import com.github.kklisura.cdt.protocol.types.debugger.SearchMatch;
import com.github.kklisura.cdt.protocol.types.page.AdScriptId;
import com.github.kklisura.cdt.protocol.types.page.AppId;
import com.github.kklisura.cdt.protocol.types.page.AppManifest;
import com.github.kklisura.cdt.protocol.types.page.AutoResponseMode;
import com.github.kklisura.cdt.protocol.types.page.CaptureScreenshotFormat;
import com.github.kklisura.cdt.protocol.types.page.CaptureSnapshotFormat;
import com.github.kklisura.cdt.protocol.types.page.CompilationCacheParams;
import com.github.kklisura.cdt.protocol.types.page.FontFamilies;
import com.github.kklisura.cdt.protocol.types.page.FontSizes;
import com.github.kklisura.cdt.protocol.types.page.FrameResourceTree;
import com.github.kklisura.cdt.protocol.types.page.FrameTree;
import com.github.kklisura.cdt.protocol.types.page.InstallabilityError;
import com.github.kklisura.cdt.protocol.types.page.LayoutMetrics;
import com.github.kklisura.cdt.protocol.types.page.Navigate;
import com.github.kklisura.cdt.protocol.types.page.NavigationHistory;
import com.github.kklisura.cdt.protocol.types.page.OriginTrial;
import com.github.kklisura.cdt.protocol.types.page.PermissionsPolicyFeatureState;
import com.github.kklisura.cdt.protocol.types.page.PrintToPDF;
import com.github.kklisura.cdt.protocol.types.page.PrintToPDFTransferMode;
import com.github.kklisura.cdt.protocol.types.page.ReferrerPolicy;
import com.github.kklisura.cdt.protocol.types.page.ResourceContent;
import com.github.kklisura.cdt.protocol.types.page.ScriptFontFamilies;
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

  /**
   * Evaluates given script in every frame upon creation (before loading frame's scripts).
   *
   * @param source
   * @param worldName If specified, creates an isolated world with the given name and evaluates
   *     given script in it. This world name will be used as the ExecutionContextDescription::name
   *     when the corresponding event is emitted.
   * @param includeCommandLineAPI Specifies whether command line API should be available to the
   *     script, defaults to false.
   * @param runImmediately If true, runs the script immediately on existing execution contexts or
   *     worlds. Default: false.
   */
  @Returns("identifier")
  String addScriptToEvaluateOnNewDocument(
      @ParamName("source") String source,
      @Experimental @Optional @ParamName("worldName") String worldName,
      @Experimental @Optional @ParamName("includeCommandLineAPI") Boolean includeCommandLineAPI,
      @Experimental @Optional @ParamName("runImmediately") Boolean runImmediately);

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
   * @param captureBeyondViewport Capture the screenshot beyond the viewport. Defaults to false.
   * @param optimizeForSpeed Optimize image encoding for speed, not for resulting size (defaults to
   *     false)
   */
  @Returns("data")
  String captureScreenshot(
      @Optional @ParamName("format") CaptureScreenshotFormat format,
      @Optional @ParamName("quality") Integer quality,
      @Optional @ParamName("clip") Viewport clip,
      @Experimental @Optional @ParamName("fromSurface") Boolean fromSurface,
      @Experimental @Optional @ParamName("captureBeyondViewport") Boolean captureBeyondViewport,
      @Experimental @Optional @ParamName("optimizeForSpeed") Boolean optimizeForSpeed);

  /**
   * Returns a snapshot of the page as a string. For MHTML format, the serialization includes
   * iframes, shadow DOM, external resources, and element-inline styles.
   */
  @Experimental
  @Returns("data")
  String captureSnapshot();

  /**
   * Returns a snapshot of the page as a string. For MHTML format, the serialization includes
   * iframes, shadow DOM, external resources, and element-inline styles.
   *
   * @param format Format (defaults to mhtml).
   */
  @Experimental
  @Returns("data")
  String captureSnapshot(@Optional @ParamName("format") CaptureSnapshotFormat format);

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

  /**
   * Gets the processed manifest for this current document. This API always waits for the manifest
   * to be loaded. If manifestId is provided, and it does not match the manifest of the current
   * document, this API errors out. If there is not a loaded page, this API errors out immediately.
   */
  AppManifest getAppManifest();

  /**
   * Gets the processed manifest for this current document. This API always waits for the manifest
   * to be loaded. If manifestId is provided, and it does not match the manifest of the current
   * document, this API errors out. If there is not a loaded page, this API errors out immediately.
   *
   * @param manifestId
   */
  AppManifest getAppManifest(@Optional @ParamName("manifestId") String manifestId);

  @Experimental
  @Returns("installabilityErrors")
  @ReturnTypeParameter(InstallabilityError.class)
  List<InstallabilityError> getInstallabilityErrors();

  /**
   * Deprecated because it's not guaranteed that the returned icon is in fact the one used for PWA
   * installation.
   */
  @Deprecated
  @Experimental
  @Returns("primaryIcon")
  String getManifestIcons();

  /**
   * Returns the unique (PWA) app id. Only returns values if the feature flag
   * 'WebAppEnableManifestId' is enabled
   */
  @Experimental
  AppId getAppId();

  /** @param frameId */
  @Experimental
  @Returns("adScriptId")
  AdScriptId getAdScriptId(@ParamName("frameId") String frameId);

  /** Returns present frame tree structure. */
  @Returns("frameTree")
  FrameTree getFrameTree();

  /** Returns metrics relating to the layouting of the page, such as viewport bounds/scale. */
  LayoutMetrics getLayoutMetrics();

  /** Returns navigation history for the current page. */
  NavigationHistory getNavigationHistory();

  /** Resets navigation history for the current page. */
  void resetNavigationHistory();

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
   * @param referrerPolicy Referrer-policy used for the navigation.
   */
  Navigate navigate(
      @ParamName("url") String url,
      @Optional @ParamName("referrer") String referrer,
      @Optional @ParamName("transitionType") TransitionType transitionType,
      @Optional @ParamName("frameId") String frameId,
      @Experimental @Optional @ParamName("referrerPolicy") ReferrerPolicy referrerPolicy);

  /**
   * Navigates current page to the given history entry.
   *
   * @param entryId Unique id of the entry to navigate to.
   */
  void navigateToHistoryEntry(@ParamName("entryId") Integer entryId);

  /** Print page as PDF. */
  PrintToPDF printToPDF();

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
   * @param pageRanges Paper ranges to print, one based, e.g., '1-5, 8, 11-13'. Pages are printed in
   *     the document order, not in the order specified, and no more than once. Defaults to empty
   *     string, which implies the entire document is printed. The page numbers are quietly capped
   *     to actual page count of the document, and ranges beyond the end of the document are
   *     ignored. If this results in no pages to print, an error is reported. It is an error to
   *     specify a range with start greater than end.
   * @param headerTemplate HTML template for the print header. Should be valid HTML markup with
   *     following classes used to inject printing values into them: - `date`: formatted print date
   *     - `title`: document title - `url`: document location - `pageNumber`: current page number -
   *     `totalPages`: total pages in the document
   *     <p>For example, `<span class=title></span>` would generate span containing the title.
   * @param footerTemplate HTML template for the print footer. Should use the same format as the
   *     `headerTemplate`.
   * @param preferCSSPageSize Whether or not to prefer page size as defined by css. Defaults to
   *     false, in which case the content will be scaled to fit the paper size.
   * @param transferMode return as stream
   * @param generateTaggedPDF Whether or not to generate tagged (accessible) PDF. Defaults to
   *     embedder choice.
   * @param generateDocumentOutline Whether or not to embed the document outline into the PDF.
   */
  PrintToPDF printToPDF(
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
      @Optional @ParamName("headerTemplate") String headerTemplate,
      @Optional @ParamName("footerTemplate") String footerTemplate,
      @Optional @ParamName("preferCSSPageSize") Boolean preferCSSPageSize,
      @Experimental @Optional @ParamName("transferMode") PrintToPDFTransferMode transferMode,
      @Experimental @Optional @ParamName("generateTaggedPDF") Boolean generateTaggedPDF,
      @Experimental @Optional @ParamName("generateDocumentOutline")
          Boolean generateDocumentOutline);

  /** Reloads given page optionally ignoring the cache. */
  void reload();

  /**
   * Reloads given page optionally ignoring the cache.
   *
   * @param ignoreCache If true, browser cache is ignored (as if the user pressed Shift+refresh).
   * @param scriptToEvaluateOnLoad If set, the script will be injected into all frames of the
   *     inspected page after reload. Argument will be ignored if reloading dataURL origin.
   * @param loaderId If set, an error will be thrown if the target page's main frame's loader id
   *     does not match the provided id. This prevents accidentally reloading an unintended target
   *     in case there's a racing navigation.
   */
  void reload(
      @Optional @ParamName("ignoreCache") Boolean ignoreCache,
      @Optional @ParamName("scriptToEvaluateOnLoad") String scriptToEvaluateOnLoad,
      @Experimental @Optional @ParamName("loaderId") String loaderId);

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
  @ReturnTypeParameter(SearchMatch.class)
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
  @ReturnTypeParameter(SearchMatch.class)
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
  void setBypassCSP(@ParamName("enabled") Boolean enabled);

  /**
   * Get Permissions Policy state on given frame.
   *
   * @param frameId
   */
  @Experimental
  @Returns("states")
  @ReturnTypeParameter(PermissionsPolicyFeatureState.class)
  List<PermissionsPolicyFeatureState> getPermissionsPolicyState(
      @ParamName("frameId") String frameId);

  /**
   * Get Origin Trials on given frame.
   *
   * @param frameId
   */
  @Experimental
  @Returns("originTrials")
  @ReturnTypeParameter(OriginTrial.class)
  List<OriginTrial> getOriginTrials(@ParamName("frameId") String frameId);

  /**
   * Set generic font families.
   *
   * @param fontFamilies Specifies font families to set. If a font family is not specified, it won't
   *     be changed.
   */
  @Experimental
  void setFontFamilies(@ParamName("fontFamilies") FontFamilies fontFamilies);

  /**
   * Set generic font families.
   *
   * @param fontFamilies Specifies font families to set. If a font family is not specified, it won't
   *     be changed.
   * @param forScripts Specifies font families to set for individual scripts.
   */
  @Experimental
  void setFontFamilies(
      @ParamName("fontFamilies") FontFamilies fontFamilies,
      @Optional @ParamName("forScripts") List<ScriptFontFamilies> forScripts);

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
  @Deprecated
  @Experimental
  void setDownloadBehavior(@ParamName("behavior") SetDownloadBehaviorBehavior behavior);

  /**
   * Set the behavior when downloading a file.
   *
   * @param behavior Whether to allow all or deny all download requests, or use default Chrome
   *     behavior if available (otherwise deny).
   * @param downloadPath The default path to save downloaded files to. This is required if behavior
   *     is set to 'allow'
   */
  @Deprecated
  @Experimental
  void setDownloadBehavior(
      @ParamName("behavior") SetDownloadBehaviorBehavior behavior,
      @Optional @ParamName("downloadPath") String downloadPath);

  /**
   * Controls whether page will emit lifecycle events.
   *
   * @param enabled If true, starts emitting lifecycle events.
   */
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

  /**
   * Requests backend to produce compilation cache for the specified scripts. `scripts` are appended
   * to the list of scripts for which the cache would be produced. The list may be reset during page
   * navigation. When script with a matching URL is encountered, the cache is optionally produced
   * upon backend discretion, based on internal heuristics. See also:
   * `Page.compilationCacheProduced`.
   *
   * @param scripts
   */
  @Experimental
  void produceCompilationCache(@ParamName("scripts") List<CompilationCacheParams> scripts);

  /**
   * Seeds compilation cache for given url. Compilation cache does not survive cross-process
   * navigation.
   *
   * @param url
   * @param data Base64-encoded data (Encoded as a base64 string when passed over JSON)
   */
  @Experimental
  void addCompilationCache(@ParamName("url") String url, @ParamName("data") String data);

  /** Clears seeded compilation cache. */
  @Experimental
  void clearCompilationCache();

  /**
   * Sets the Secure Payment Confirmation transaction mode.
   * https://w3c.github.io/secure-payment-confirmation/#sctn-automation-set-spc-transaction-mode
   *
   * @param mode
   */
  @Experimental
  void setSPCTransactionMode(@ParamName("mode") AutoResponseMode mode);

  /**
   * Extensions for Custom Handlers API:
   * https://html.spec.whatwg.org/multipage/system-state.html#rph-automation
   *
   * @param mode
   */
  @Experimental
  void setRPHRegistrationMode(@ParamName("mode") AutoResponseMode mode);

  /**
   * Generates a report for testing.
   *
   * @param message Message to be displayed in the report.
   */
  @Experimental
  void generateTestReport(@ParamName("message") String message);

  /**
   * Generates a report for testing.
   *
   * @param message Message to be displayed in the report.
   * @param group Specifies the endpoint group to deliver the report to.
   */
  @Experimental
  void generateTestReport(
      @ParamName("message") String message, @Optional @ParamName("group") String group);

  /** Pauses page execution. Can be resumed using generic Runtime.runIfWaitingForDebugger. */
  @Experimental
  void waitForDebugger();

  /**
   * Intercept file chooser requests and transfer control to protocol clients. When file chooser
   * interception is enabled, native file chooser dialog is not shown. Instead, a protocol event
   * `Page.fileChooserOpened` is emitted.
   *
   * @param enabled
   */
  void setInterceptFileChooserDialog(@ParamName("enabled") Boolean enabled);

  /**
   * Enable/disable prerendering manually.
   *
   * <p>This command is a short-term solution for https://crbug.com/1440085. See
   * https://docs.google.com/document/d/12HVmFxYj5Jc-eJr5OmWsa2bqTJsbgGLKI6ZIyx0_wpA for more
   * details.
   *
   * <p>TODO(https://crbug.com/1440085): Remove this once Puppeteer supports tab targets.
   *
   * @param isAllowed
   */
  @Experimental
  void setPrerenderingAllowed(@ParamName("isAllowed") Boolean isAllowed);

  @EventName("domContentEventFired")
  EventListener onDomContentEventFired(EventHandler<DomContentEventFired> eventListener);

  /** Emitted only when `page.interceptFileChooser` is enabled. */
  @EventName("fileChooserOpened")
  EventListener onFileChooserOpened(EventHandler<FileChooserOpened> eventListener);

  /** Fired when frame has been attached to its parent. */
  @EventName("frameAttached")
  EventListener onFrameAttached(EventHandler<FrameAttached> eventListener);

  /** Fired when frame no longer has a scheduled navigation. */
  @EventName("frameClearedScheduledNavigation")
  @Deprecated
  EventListener onFrameClearedScheduledNavigation(
      EventHandler<FrameClearedScheduledNavigation> eventListener);

  /** Fired when frame has been detached from its parent. */
  @EventName("frameDetached")
  EventListener onFrameDetached(EventHandler<FrameDetached> eventListener);

  /**
   * Fired before frame subtree is detached. Emitted before any frame of the subtree is actually
   * detached.
   */
  @EventName("frameSubtreeWillBeDetached")
  @Experimental
  EventListener onFrameSubtreeWillBeDetached(
      EventHandler<FrameSubtreeWillBeDetached> eventListener);

  /**
   * Fired once navigation of the frame has completed. Frame is now associated with the new loader.
   */
  @EventName("frameNavigated")
  EventListener onFrameNavigated(EventHandler<FrameNavigated> eventListener);

  /** Fired when opening document to write to. */
  @EventName("documentOpened")
  @Experimental
  EventListener onDocumentOpened(EventHandler<DocumentOpened> eventListener);

  @EventName("frameResized")
  @Experimental
  EventListener onFrameResized(EventHandler<FrameResized> eventListener);

  /**
   * Fired when a navigation starts. This event is fired for both renderer-initiated and
   * browser-initiated navigations. For renderer-initiated navigations, the event is fired after
   * `frameRequestedNavigation`. Navigation may still be cancelled after the event is issued.
   * Multiple events can be fired for a single navigation, for example, when a same-document
   * navigation becomes a cross-document navigation (such as in the case of a frameset).
   */
  @EventName("frameStartedNavigating")
  @Experimental
  EventListener onFrameStartedNavigating(EventHandler<FrameStartedNavigating> eventListener);

  /**
   * Fired when a renderer-initiated navigation is requested. Navigation may still be cancelled
   * after the event is issued.
   */
  @EventName("frameRequestedNavigation")
  @Experimental
  EventListener onFrameRequestedNavigation(EventHandler<FrameRequestedNavigation> eventListener);

  /** Fired when frame schedules a potential navigation. */
  @EventName("frameScheduledNavigation")
  @Deprecated
  EventListener onFrameScheduledNavigation(EventHandler<FrameScheduledNavigation> eventListener);

  /** Fired when frame has started loading. */
  @EventName("frameStartedLoading")
  @Experimental
  EventListener onFrameStartedLoading(EventHandler<FrameStartedLoading> eventListener);

  /** Fired when frame has stopped loading. */
  @EventName("frameStoppedLoading")
  @Experimental
  EventListener onFrameStoppedLoading(EventHandler<FrameStoppedLoading> eventListener);

  /**
   * Fired when page is about to start a download. Deprecated. Use Browser.downloadWillBegin
   * instead.
   */
  @EventName("downloadWillBegin")
  @Deprecated
  @Experimental
  EventListener onDownloadWillBegin(EventHandler<DownloadWillBegin> eventListener);

  /**
   * Fired when download makes progress. Last call has |done| == true. Deprecated. Use
   * Browser.downloadProgress instead.
   */
  @EventName("downloadProgress")
  @Deprecated
  @Experimental
  EventListener onDownloadProgress(EventHandler<DownloadProgress> eventListener);

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

  /**
   * Fired for lifecycle events (navigation, load, paint, etc) in the current target (including
   * local frames).
   */
  @EventName("lifecycleEvent")
  EventListener onLifecycleEvent(EventHandler<LifecycleEvent> eventListener);

  /**
   * Fired for failed bfcache history navigations if BackForwardCache feature is enabled. Do not
   * assume any ordering with the Page.frameNavigated event. This event is fired only for main-frame
   * history navigation where the document changes (non-same-document navigations), when bfcache
   * navigation fails.
   */
  @EventName("backForwardCacheNotUsed")
  @Experimental
  EventListener onBackForwardCacheNotUsed(EventHandler<BackForwardCacheNotUsed> eventListener);

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

  /**
   * Issued for every compilation cache generated. Is only available if
   * Page.setGenerateCompilationCache is enabled.
   */
  @EventName("compilationCacheProduced")
  @Experimental
  EventListener onCompilationCacheProduced(EventHandler<CompilationCacheProduced> eventListener);
}
