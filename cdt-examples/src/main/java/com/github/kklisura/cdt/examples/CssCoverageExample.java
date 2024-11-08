package com.github.kklisura.cdt.examples;

/*-
 * #%L
 * cdt-examples
 * %%
 * Copyright (C) 2018 - 2023 Kenan Klisura
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

import com.github.kklisura.cdt.launch.ChromeLauncher;
import com.github.kklisura.cdt.protocol.commands.CSS;
import com.github.kklisura.cdt.protocol.commands.DOM;
import com.github.kklisura.cdt.protocol.commands.Page;
import com.github.kklisura.cdt.protocol.events.page.LoadEventFired;
import com.github.kklisura.cdt.protocol.support.types.EventHandler;
import com.github.kklisura.cdt.protocol.support.types.EventListener;
import com.github.kklisura.cdt.protocol.types.css.CSSStyleSheetHeader;
import com.github.kklisura.cdt.protocol.types.css.RuleUsage;
import com.github.kklisura.cdt.services.ChromeDevToolsService;
import com.github.kklisura.cdt.services.ChromeService;
import com.github.kklisura.cdt.services.types.ChromeTab;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * This examples dumps css coverage on github.com
 *
 * @author Kenan Klisura
 */
public class CssCoverageExample {
  public static void main(String[] args) throws InterruptedException {
    // Create chrome launcher.
    final ChromeLauncher launcher = new ChromeLauncher();

    // Launch chrome either as headless (true) or regular (false).
    final ChromeService chromeService = launcher.launch(false);

    // Create empty tab ie about:blank.
    final ChromeTab tab = chromeService.createTab();

    // Get DevTools service to this tab
    final ChromeDevToolsService devToolsService = chromeService.createDevToolsService(tab);

    final Collection<CoverageData> coverage =
        new CssCoverage(devToolsService).start("https://github.com");

    System.out.printf(
        "%92.92s %16.16s %16.16s %16.16s\r\n", "URL", "Total bytes", "Unused bytes", "Coverage");

    for (CoverageData coverageData : coverage) {
      System.out.printf(
          "%92.92s %16d %16d %15.2f%%\r\n",
          coverageData.getUrl(),
          coverageData.getTotalBytes(),
          coverageData.getUnusedBytes(),
          coverageData.getCoverage());
    }

    devToolsService.close();
  }

  static class CssCoverage implements EventHandler<LoadEventFired> {
    private ChromeDevToolsService chromeDevToolsService;

    private EventListener eventListener;

    private BlockingQueue<List<CoverageData>> queue = new ArrayBlockingQueue<>(1);
    private Map<String, CSSStyleSheetHeader> styleSheets = new HashMap<>();

    CssCoverage(ChromeDevToolsService chromeDevToolsService) {
      this.chromeDevToolsService = chromeDevToolsService;
    }

    private void installListenerAndNavigate(String url) {
      final Page page = chromeDevToolsService.getPage();
      final CSS css = chromeDevToolsService.getCSS();
      final DOM dom = chromeDevToolsService.getDOM();

      dom.enable();
      css.enable();

      css.onStyleSheetAdded(
          styleSheetAddedEvent -> {
            final CSSStyleSheetHeader header = styleSheetAddedEvent.getHeader();
            if (header != null) {
              styleSheets.put(header.getStyleSheetId(), header);
            }
          });
      css.onStyleSheetRemoved(
          styleSheetRemovedEvent -> {
            styleSheets.remove(styleSheetRemovedEvent.getStyleSheetId());
          });

      this.eventListener = page.onLoadEventFired(this);
      page.enable();
      page.navigate(url);
    }

    @Override
    public void onEvent(LoadEventFired event) {
      eventListener.unsubscribe();
      final CSS css = chromeDevToolsService.getCSS();

      css.startRuleUsageTracking();

      final List<RuleUsage> ruleUsages = css.stopRuleUsageTracking();
      queue.offer(processCoverageData(ruleUsages));
    }

    private List<CoverageData> processCoverageData(List<RuleUsage> ruleUsages) {
      final Map<String, CoverageData> coveragePerSourceURL = new HashMap<>();
      final Map<String, CoverageData> coverageDataMap = new HashMap<>();

      for (RuleUsage ruleUsage : ruleUsages) {
        final String styleSheetId = ruleUsage.getStyleSheetId();
        final CSSStyleSheetHeader cssStyleSheetHeader = styleSheets.get(styleSheetId);
        final String sourceURL = cssStyleSheetHeader.getSourceURL();

        final CoverageData coverageData =
            coverageDataMap.computeIfAbsent(
                styleSheetId,
                key -> new CoverageData(sourceURL, cssStyleSheetHeader.getLength().longValue()));

        coverageData.unusedBytes -= (ruleUsage.getEndOffset() - ruleUsage.getStartOffset());

        if (sourceURL != null && !sourceURL.isEmpty()) {
          coveragePerSourceURL.put(sourceURL, coverageData);
        }
      }

      return new ArrayList<>(coveragePerSourceURL.values());
    }

    public List<CoverageData> start(String url) throws InterruptedException {
      installListenerAndNavigate(url);
      return queue.take();
    }
  }

  static class CoverageData {
    private final String url;
    private final long totalBytes;
    private long unusedBytes;

    public CoverageData(String url, long totalBytes) {
      this.url = url;
      this.totalBytes = totalBytes;
      this.unusedBytes = totalBytes;
    }

    public String getUrl() {
      return url;
    }

    public long getTotalBytes() {
      return totalBytes;
    }

    public long getUnusedBytes() {
      return unusedBytes;
    }

    public float getCoverage() {
      return ((float) unusedBytes / totalBytes) * 100;
    }
  }
}
