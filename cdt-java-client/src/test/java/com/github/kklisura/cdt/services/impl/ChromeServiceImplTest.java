package com.github.kklisura.cdt.services.impl;

/*-
 * #%L
 * cdt-java-client
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

import static com.github.kklisura.cdt.services.impl.utils.TestUtils.getFixture;
import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expect;
import static org.junit.Assert.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kklisura.cdt.protocol.ChromeDevTools;
import com.github.kklisura.cdt.protocol.commands.Network;
import com.github.kklisura.cdt.services.WebSocketService;
import com.github.kklisura.cdt.services.exceptions.ChromeServiceException;
import com.github.kklisura.cdt.services.exceptions.WebSocketServiceException;
import com.github.kklisura.cdt.services.factory.WebSocketServiceFactory;
import com.github.kklisura.cdt.services.types.ChromeTab;
import com.github.kklisura.cdt.services.types.ChromeVersion;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.List;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Chrome service impl test.
 *
 * @author Kenan Klisura
 */
@RunWith(EasyMockRunner.class)
public class ChromeServiceImplTest extends EasyMockSupport {

  @Mock private WebSocketService webSocketService;

  @Mock private WebSocketServiceFactory webSocketServiceFactory;

  @Test(expected = ChromeServiceException.class)
  public void testGetTabsOnBadHost()
      throws IOException, ChromeServiceException, InterruptedException {
    ChromeServiceImpl service = new ChromeServiceImpl("unknown-schema://unknown-host", 9922);
    service.getTabs();
  }

  @Test(expected = ChromeServiceException.class)
  public void testGetDevToolsFailsConnectToWebSocket()
      throws ChromeServiceException, IOException, WebSocketServiceException {
    InputStream fixture = getFixture("chrome/tab.json");
    ObjectMapper objectMapper = new ObjectMapper();
    ChromeTab tab = objectMapper.readerFor(ChromeTab.class).readValue(fixture);

    expect(webSocketServiceFactory.createWebSocketService(tab.getWebSocketDebuggerUrl()))
        .andThrow(new WebSocketServiceException("Failed connecting to websocket."));

    replayAll();

    ChromeServiceImpl service = new ChromeServiceImpl(9992);
    service.setWebSocketServiceFactory(webSocketServiceFactory);
    service.createDevToolsService(tab);
  }

  @Test
  public void testGetTabs() throws IOException, ChromeServiceException, InterruptedException {
    MockWebServer server = new MockWebServer();

    InputStream fixture = getFixture("chrome/tabs.json");
    server.enqueue(new MockResponse().setBody(ChromeServiceImpl.inputStreamToString(fixture)));

    server.start();

    ChromeServiceImpl service = new ChromeServiceImpl(server.getHostName(), server.getPort());

    List<ChromeTab> tabs = service.getTabs();

    RecordedRequest request = server.takeRequest();
    assertEquals(1, server.getRequestCount());
    assertEquals("GET /json/list HTTP/1.1", request.getRequestLine());

    assertFalse(tabs.isEmpty());
    assertEquals(2, tabs.size());

    assertEquals("", tabs.get(0).getDescription());
    assertEquals(
        "/devtools/inspector.html?ws=localhost:9222/devtools/page/(2C5C79DD1137419CC8839D61D91CEB2A)",
        tabs.get(0).getDevtoolsFrontendUrl());
    assertEquals(
        "https://www.google.ba/images/branding/product/ico/googleg_lodp.ico",
        tabs.get(0).getFaviconUrl());
    assertEquals("(2C5C79DD1137419CC8839D61D91CEB2A)", tabs.get(0).getId());
    assertEquals("Google", tabs.get(0).getTitle());
    assertEquals("page", tabs.get(0).getType());
    assertTrue(tabs.get(0).isPageType());
    assertEquals(
        "https://www.google.ba/?gws_rd=cr&dcr=0&ei=93piWq2oKqqJmgWIzbdg", tabs.get(0).getUrl());
    assertEquals(
        "ws://localhost:9222/devtools/page/(2C5C79DD1137419CC8839D61D91CEB2A)",
        tabs.get(0).getWebSocketDebuggerUrl());

    server.shutdown();
  }

  @Test
  public void testCreateTab() throws IOException, ChromeServiceException, InterruptedException {
    MockWebServer server = new MockWebServer();

    InputStream fixture = getFixture("chrome/tab.json");
    server.enqueue(new MockResponse().setBody(ChromeServiceImpl.inputStreamToString(fixture)));

    server.start();

    ChromeServiceImpl service = new ChromeServiceImpl(server.getHostName(), server.getPort());

    ChromeTab tab = service.createTab("some-tab-name");

    RecordedRequest request = server.takeRequest();
    assertEquals(1, server.getRequestCount());
    assertEquals("GET /json/new?some-tab-name HTTP/1.1", request.getRequestLine());

    assertEquals("", tab.getDescription());
    assertEquals(
        "/devtools/inspector.html?ws=localhost:54011/devtools/page/D4CEC22C995F1A9C8526737014CD436D",
        tab.getDevtoolsFrontendUrl());
    assertEquals(
        "https://www.google.ba/images/branding/product/ico/googleg_lodp.ico", tab.getFaviconUrl());
    assertEquals("D4CEC22C995F1A9C8526737014CD436D", tab.getId());
    assertEquals("", tab.getTitle());
    assertEquals("page", tab.getType());
    assertTrue(tab.isPageType());
    assertEquals("about:blank", tab.getUrl());
    assertEquals(
        "ws://localhost:54011/devtools/page/D4CEC22C995F1A9C8526737014CD436D",
        tab.getWebSocketDebuggerUrl());

    server.shutdown();
  }

  @Test
  public void testCreateTabWithAboutBlankPage()
      throws IOException, ChromeServiceException, InterruptedException {
    MockWebServer server = new MockWebServer();

    InputStream fixture = getFixture("chrome/tab.json");
    server.enqueue(new MockResponse().setBody(ChromeServiceImpl.inputStreamToString(fixture)));

    server.start();

    ChromeServiceImpl service = new ChromeServiceImpl(server.getHostName(), server.getPort());

    ChromeTab tab = service.createTab();

    RecordedRequest request = server.takeRequest();
    assertEquals(1, server.getRequestCount());
    assertEquals("GET /json/new?about:blank HTTP/1.1", request.getRequestLine());

    assertEquals("", tab.getDescription());
    assertEquals(
        "/devtools/inspector.html?ws=localhost:54011/devtools/page/D4CEC22C995F1A9C8526737014CD436D",
        tab.getDevtoolsFrontendUrl());
    assertEquals(
        "https://www.google.ba/images/branding/product/ico/googleg_lodp.ico", tab.getFaviconUrl());
    assertEquals("D4CEC22C995F1A9C8526737014CD436D", tab.getId());
    assertEquals("", tab.getTitle());
    assertEquals("page", tab.getType());
    assertTrue(tab.isPageType());
    assertEquals("about:blank", tab.getUrl());
    assertEquals(
        "ws://localhost:54011/devtools/page/D4CEC22C995F1A9C8526737014CD436D",
        tab.getWebSocketDebuggerUrl());

    server.shutdown();
  }

  @Test
  public void testActivateTab() throws IOException, ChromeServiceException, InterruptedException {
    MockWebServer server = new MockWebServer();
    ObjectMapper objectMapper = new ObjectMapper();

    ChromeTab tab =
        objectMapper.readerFor(ChromeTab.class).readValue(getFixture("chrome/tab.json"));

    server.enqueue(new MockResponse());
    server.start();

    ChromeServiceImpl service = new ChromeServiceImpl(server.getHostName(), server.getPort());

    service.activateTab(tab);

    RecordedRequest request = server.takeRequest();
    assertEquals(1, server.getRequestCount());
    assertEquals(
        "GET /json/activate/D4CEC22C995F1A9C8526737014CD436D HTTP/1.1", request.getRequestLine());

    server.shutdown();
  }

  @Test(expected = ChromeServiceException.class)
  public void testActivateTabOnNotFoundResponse()
      throws IOException, ChromeServiceException, InterruptedException {
    MockWebServer server = new MockWebServer();
    ObjectMapper objectMapper = new ObjectMapper();

    ChromeTab tab =
        objectMapper.readerFor(ChromeTab.class).readValue(getFixture("chrome/tab.json"));

    server.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_NOT_FOUND));
    server.start();

    try {
      ChromeServiceImpl service = new ChromeServiceImpl(server.getHostName(), server.getPort());
      service.activateTab(tab);
    } finally {
      server.shutdown();
    }
  }

  @Test
  public void testCloseTabClearsDevTools()
      throws IOException, ChromeServiceException, InterruptedException, WebSocketServiceException {
    MockWebServer server = new MockWebServer();

    ObjectMapper objectMapper = new ObjectMapper();
    ChromeTab tab =
        objectMapper.readerFor(ChromeTab.class).readValue(getFixture("chrome/tab.json"));

    server.enqueue(new MockResponse());
    server.start();

    expect(webSocketServiceFactory.createWebSocketService(tab.getWebSocketDebuggerUrl()))
        .andReturn(webSocketService);

    webSocketService.addMessageHandler(anyObject());
    webSocketService.close();

    replayAll();

    ChromeServiceImpl service =
        new ChromeServiceImpl(server.getHostName(), server.getPort(), webSocketServiceFactory);

    service.createDevToolsService(tab);

    service.closeTab(tab);

    RecordedRequest request = server.takeRequest();
    assertEquals(1, server.getRequestCount());
    assertEquals(
        "GET /json/close/D4CEC22C995F1A9C8526737014CD436D HTTP/1.1", request.getRequestLine());

    server.shutdown();

    verifyAll();
  }

  @Test
  public void testCloseTab() throws IOException, ChromeServiceException, InterruptedException {
    MockWebServer server = new MockWebServer();

    ObjectMapper objectMapper = new ObjectMapper();
    ChromeTab tab =
        objectMapper.readerFor(ChromeTab.class).readValue(getFixture("chrome/tab.json"));

    server.enqueue(new MockResponse());
    server.start();

    ChromeServiceImpl service = new ChromeServiceImpl(server.getHostName(), server.getPort());

    service.closeTab(tab);

    RecordedRequest request = server.takeRequest();
    assertEquals(1, server.getRequestCount());
    assertEquals(
        "GET /json/close/D4CEC22C995F1A9C8526737014CD436D HTTP/1.1", request.getRequestLine());

    server.shutdown();
  }

  @Test
  public void testVersion() throws IOException, ChromeServiceException, InterruptedException {
    MockWebServer server = new MockWebServer();

    InputStream fixture = getFixture("chrome/version.json");
    server.enqueue(new MockResponse().setBody(ChromeServiceImpl.inputStreamToString(fixture)));

    server.start();

    ChromeServiceImpl service = new ChromeServiceImpl(server.getHostName(), server.getPort());

    ChromeVersion version = service.getVersion();

    RecordedRequest request = server.takeRequest();
    assertEquals(1, server.getRequestCount());
    assertEquals("GET /json/version HTTP/1.1", request.getRequestLine());

    assertEquals("Chrome/63.0.3239.132", version.getBrowser());
    assertEquals("1.2", version.getProtocolVersion());
    assertEquals(
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36",
        version.getUserAgent());
    assertEquals("6.3.292.49", version.getV8Version());
    assertEquals("537.36 (@2e6edcfee630baa3775f37cb11796b1603a64360)", version.getWebKitVersion());
    assertEquals(
        "ws://localhost:9222/devtools/browser/63318df0-09e4-4143-910e-f89525dda26b",
        version.getWebSocketDebuggerUrl());

    server.shutdown();
  }

  @Test
  public void testGetDevTools()
      throws IOException, ChromeServiceException, WebSocketServiceException {
    ChromeServiceImpl service = new ChromeServiceImpl(9222, webSocketServiceFactory);

    ObjectMapper objectMapper = new ObjectMapper();
    ChromeTab tab =
        objectMapper.readerFor(ChromeTab.class).readValue(getFixture("chrome/tab.json"));

    expect(webSocketServiceFactory.createWebSocketService(tab.getWebSocketDebuggerUrl()))
        .andReturn(webSocketService);

    webSocketService.addMessageHandler(anyObject());

    replayAll();

    ChromeDevTools devTools = service.createDevToolsService(tab);

    verifyAll();

    assertNotNull(devTools);
  }

  @Test
  public void testGetDevToolsIsCachedPerTab()
      throws IOException, ChromeServiceException, WebSocketServiceException {
    ChromeServiceImpl service = new ChromeServiceImpl(9222, webSocketServiceFactory);

    ObjectMapper objectMapper = new ObjectMapper();
    ChromeTab tab =
        objectMapper.readerFor(ChromeTab.class).readValue(getFixture("chrome/tab.json"));

    expect(webSocketServiceFactory.createWebSocketService(tab.getWebSocketDebuggerUrl()))
        .andReturn(webSocketService);

    webSocketService.addMessageHandler(anyObject());

    replayAll();

    ChromeDevTools devTools = service.createDevToolsService(tab);
    assertTrue(devTools == service.createDevToolsService(tab));
    assertTrue(devTools == service.createDevToolsService(tab));
    assertTrue(devTools == service.createDevToolsService(tab));

    verifyAll();

    devTools = null;
    System.gc();

    resetAll();

    expect(webSocketServiceFactory.createWebSocketService(tab.getWebSocketDebuggerUrl()))
        .andReturn(webSocketService);

    webSocketService.addMessageHandler(anyObject());

    replayAll();

    devTools = service.createDevToolsService(tab);

    assertNotNull(devTools);
  }

  @Test
  public void testGetDevToolsSubTypeIsCachedPerTab()
      throws IOException, ChromeServiceException, WebSocketServiceException {
    ChromeServiceImpl service = new ChromeServiceImpl(9222, webSocketServiceFactory);

    ObjectMapper objectMapper = new ObjectMapper();
    ChromeTab tab =
        objectMapper.readerFor(ChromeTab.class).readValue(getFixture("chrome/tab.json"));

    expect(webSocketServiceFactory.createWebSocketService(tab.getWebSocketDebuggerUrl()))
        .andReturn(webSocketService);

    webSocketService.addMessageHandler(anyObject());

    replayAll();

    Network network = service.createDevToolsService(tab).getNetwork();
    assertTrue(network == service.createDevToolsService(tab).getNetwork());
    assertTrue(network == service.createDevToolsService(tab).getNetwork());
    assertTrue(network == service.createDevToolsService(tab).getNetwork());

    verifyAll();
    assertNotNull(network);
  }

  @Test
  public void testClearChromeDevToolsServiceCacheAndCloseDevTools()
      throws IOException, ChromeServiceException, WebSocketServiceException {
    ChromeServiceImpl service = new ChromeServiceImpl(9222, webSocketServiceFactory);

    ObjectMapper objectMapper = new ObjectMapper();
    ChromeTab tab =
        objectMapper.readerFor(ChromeTab.class).readValue(getFixture("chrome/tab.json"));

    expect(webSocketServiceFactory.createWebSocketService(tab.getWebSocketDebuggerUrl()))
        .andReturn(webSocketService);

    webSocketService.addMessageHandler(anyObject());
    webSocketService.close();

    replayAll();

    service.createDevToolsService(tab);

    service.clearChromeDevToolsServiceCache(tab);

    verifyAll();
  }

  @Test
  public void testClearChromeDevToolsServiceCache() throws IOException {
    ChromeServiceImpl service = new ChromeServiceImpl(9222, webSocketServiceFactory);
    service.clearChromeDevToolsServiceCache(createChromeTab("UNUSED"));
  }

  private static ChromeTab createChromeTab(String id) throws IOException {
    return new ObjectMapper()
        .readerFor(ChromeTab.class)
        .readValue(String.format("{\"id\":\"%s\"}", id));
  }
}
