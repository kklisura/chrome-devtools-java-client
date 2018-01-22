package com.github.kklisura.cdtp.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kklisura.cdtp.protocol.ChromeDevTools;
import com.github.kklisura.cdtp.protocol.commands.Network;
import com.github.kklisura.cdtp.services.WebSocketService;
import com.github.kklisura.cdtp.services.exceptions.ChromeServiceException;
import com.github.kklisura.cdtp.services.exceptions.WebSocketServiceException;
import com.github.kklisura.cdtp.services.factory.WebSocketServiceFactory;
import com.github.kklisura.cdtp.services.model.chrome.ChromeTab;
import com.github.kklisura.cdtp.services.model.chrome.ChromeVersion;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.List;

import static com.github.kklisura.cdtp.services.impl.utils.TestUtils.getFixture;
import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expect;
import static org.junit.Assert.*;

/**
 * Chrome service impl test.
 *
 * @author Kenan Klisura
 */
@RunWith(EasyMockRunner.class)
public class ChromeServiceImplTest extends EasyMockSupport {

	@Mock
	private WebSocketService webSocketService;

	@Mock
	private WebSocketServiceFactory webSocketServiceFactory;

	@Test(expected = ChromeServiceException.class)
	public void testGetTabsOnBadHost() throws IOException, ChromeServiceException, InterruptedException {
		ChromeServiceImpl service = new ChromeServiceImpl("unknown-schema://unknown-host", 9922);
		service.getTabs();
	}

	@Test(expected = ChromeServiceException.class)
	public void testGetDevToolsFailsConnectToWebSocket() throws ChromeServiceException, IOException, WebSocketServiceException {
		InputStream fixture = getFixture("chrome/tab.json");
		ObjectMapper objectMapper = new ObjectMapper();
		ChromeTab tab = objectMapper.readerFor(ChromeTab.class).readValue(fixture);

		expect(webSocketServiceFactory.createWebSocketService(tab.getWebSocketDebuggerUrl()))
				.andThrow(new WebSocketServiceException("Failed connecting to websocket."));

		replayAll();

		ChromeServiceImpl service = new ChromeServiceImpl(9992);
		service.setWebSocketServiceFactory(webSocketServiceFactory);
		service.getDevTools(tab);
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
		assertEquals("/devtools/inspector.html?ws=localhost:9222/devtools/page/(2C5C79DD1137419CC8839D61D91CEB2A)", tabs.get(0).getDevtoolsFrontendUrl());
		assertEquals("https://www.google.ba/images/branding/product/ico/googleg_lodp.ico", tabs.get(0).getFaviconUrl());
		assertEquals("(2C5C79DD1137419CC8839D61D91CEB2A)", tabs.get(0).getId());
		assertEquals("Google", tabs.get(0).getTitle());
		assertEquals("page", tabs.get(0).getType());
		assertTrue(tabs.get(0).isPageType());
		assertEquals("https://www.google.ba/?gws_rd=cr&dcr=0&ei=93piWq2oKqqJmgWIzbdg", tabs.get(0).getUrl());
		assertEquals("ws://localhost:9222/devtools/page/(2C5C79DD1137419CC8839D61D91CEB2A)", tabs.get(0).getWebSocketDebuggerUrl());

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
		assertEquals("/devtools/inspector.html?ws=localhost:9222/devtools/page/(2C5C79DD1137419CC8839D61D91CEB2A)", tab.getDevtoolsFrontendUrl());
		assertEquals("https://www.google.ba/images/branding/product/ico/googleg_lodp.ico", tab.getFaviconUrl());
		assertEquals("(2C5C79DD1137419CC8839D61D91CEB2A)", tab.getId());
		assertEquals("Google", tab.getTitle());
		assertEquals("page", tab.getType());
		assertTrue(tab.isPageType());
		assertEquals("https://www.google.ba/?gws_rd=cr&dcr=0&ei=93piWq2oKqqJmgWIzbdg", tab.getUrl());
		assertEquals("ws://localhost:9222/devtools/page/(2C5C79DD1137419CC8839D61D91CEB2A)", tab.getWebSocketDebuggerUrl());

		server.shutdown();
	}

	@Test
	public void testCreateTabWithAboutBlankPage() throws IOException, ChromeServiceException, InterruptedException {
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
		assertEquals("/devtools/inspector.html?ws=localhost:9222/devtools/page/(2C5C79DD1137419CC8839D61D91CEB2A)", tab.getDevtoolsFrontendUrl());
		assertEquals("https://www.google.ba/images/branding/product/ico/googleg_lodp.ico", tab.getFaviconUrl());
		assertEquals("(2C5C79DD1137419CC8839D61D91CEB2A)", tab.getId());
		assertEquals("Google", tab.getTitle());
		assertEquals("page", tab.getType());
		assertTrue(tab.isPageType());
		assertEquals("https://www.google.ba/?gws_rd=cr&dcr=0&ei=93piWq2oKqqJmgWIzbdg", tab.getUrl());
		assertEquals("ws://localhost:9222/devtools/page/(2C5C79DD1137419CC8839D61D91CEB2A)", tab.getWebSocketDebuggerUrl());

		server.shutdown();
	}

	@Test
	public void testActivateTab() throws IOException, ChromeServiceException, InterruptedException {
		MockWebServer server = new MockWebServer();
		ObjectMapper objectMapper = new ObjectMapper();

		ChromeTab tab = objectMapper.readerFor(ChromeTab.class).readValue(getFixture("chrome/tab.json"));

		server.enqueue(new MockResponse());
		server.start();

		ChromeServiceImpl service = new ChromeServiceImpl(server.getHostName(), server.getPort());

		service.activateTab(tab);

		RecordedRequest request = server.takeRequest();
		assertEquals(1, server.getRequestCount());
		assertEquals("GET /json/activate/(2C5C79DD1137419CC8839D61D91CEB2A) HTTP/1.1", request.getRequestLine());

		server.shutdown();
	}

	@Test(expected = ChromeServiceException.class)
	public void testActivateTabOnNotFoundResponse() throws IOException, ChromeServiceException, InterruptedException {
		MockWebServer server = new MockWebServer();
		ObjectMapper objectMapper = new ObjectMapper();

		ChromeTab tab = objectMapper.readerFor(ChromeTab.class).readValue(getFixture("chrome/tab.json"));

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
	public void testCloseTab() throws IOException, ChromeServiceException, InterruptedException {
		MockWebServer server = new MockWebServer();

		ObjectMapper objectMapper = new ObjectMapper();
		ChromeTab tab = objectMapper.readerFor(ChromeTab.class).readValue(getFixture("chrome/tab.json"));

		server.enqueue(new MockResponse());
		server.start();

		ChromeServiceImpl service = new ChromeServiceImpl(server.getHostName(), server.getPort());

		service.closeTab(tab);

		RecordedRequest request = server.takeRequest();
		assertEquals(1, server.getRequestCount());
		assertEquals("GET /json/close/(2C5C79DD1137419CC8839D61D91CEB2A) HTTP/1.1", request.getRequestLine());

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
		assertEquals("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36", version.getUserAgent());
		assertEquals("6.3.292.49", version.getV8Version());
		assertEquals("537.36 (@2e6edcfee630baa3775f37cb11796b1603a64360)", version.getWebKitVersion());
		assertEquals("ws://localhost:9222/devtools/browser/63318df0-09e4-4143-910e-f89525dda26b", version.getWebSocketDebuggerUrl());

		server.shutdown();
	}

	@Test
	public void testGetDevTools() throws IOException, ChromeServiceException, WebSocketServiceException {
		ChromeServiceImpl service = new ChromeServiceImpl(9222, webSocketServiceFactory);

		ObjectMapper objectMapper = new ObjectMapper();
		ChromeTab tab = objectMapper.readerFor(ChromeTab.class).readValue(getFixture("chrome/tab.json"));

		expect(webSocketServiceFactory.createWebSocketService(tab.getWebSocketDebuggerUrl()))
				.andReturn(webSocketService);

		webSocketService.addMessageHandler(anyObject());

		replayAll();

		ChromeDevTools devTools = service.getDevTools(tab);

		verifyAll();

		assertNotNull(devTools);
	}

	@Test
	public void testGetDevToolsIsCachedPerTab() throws IOException, ChromeServiceException, WebSocketServiceException {
		ChromeServiceImpl service = new ChromeServiceImpl(9222, webSocketServiceFactory);

		ObjectMapper objectMapper = new ObjectMapper();
		ChromeTab tab = objectMapper.readerFor(ChromeTab.class).readValue(getFixture("chrome/tab.json"));

		expect(webSocketServiceFactory.createWebSocketService(tab.getWebSocketDebuggerUrl()))
				.andReturn(webSocketService);

		webSocketService.addMessageHandler(anyObject());

		replayAll();

		ChromeDevTools devTools = service.getDevTools(tab);
		assertTrue(devTools == service.getDevTools(tab));
		assertTrue(devTools == service.getDevTools(tab));
		assertTrue(devTools == service.getDevTools(tab));

		verifyAll();

		devTools = null;
		System.gc();

		resetAll();

		expect(webSocketServiceFactory.createWebSocketService(tab.getWebSocketDebuggerUrl()))
				.andReturn(webSocketService);

		webSocketService.addMessageHandler(anyObject());

		replayAll();

		devTools = service.getDevTools(tab);

		assertNotNull(devTools);
	}

	@Test
	public void testGetDevToolsSubTypeIsCachedPerTab() throws IOException, ChromeServiceException, WebSocketServiceException {
		ChromeServiceImpl service = new ChromeServiceImpl(9222, webSocketServiceFactory);

		ObjectMapper objectMapper = new ObjectMapper();
		ChromeTab tab = objectMapper.readerFor(ChromeTab.class).readValue(getFixture("chrome/tab.json"));

		expect(webSocketServiceFactory.createWebSocketService(tab.getWebSocketDebuggerUrl()))
				.andReturn(webSocketService);

		webSocketService.addMessageHandler(anyObject());

		replayAll();

		Network network = service.getDevTools(tab).getNetwork();
		assertTrue(network == service.getDevTools(tab).getNetwork());
		assertTrue(network == service.getDevTools(tab).getNetwork());
		assertTrue(network == service.getDevTools(tab).getNetwork());

		verifyAll();
		assertNotNull(network);
	}
}