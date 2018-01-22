package com.github.kklisura.cdtp.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kklisura.cdtp.services.WebSocketService;
import com.github.kklisura.cdtp.services.exceptions.ChromeDevToolsException;
import com.github.kklisura.cdtp.services.exceptions.WebSocketServiceException;
import com.github.kklisura.cdtp.services.model.chrome.MethodInvocation;
import org.easymock.Capture;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.HashMap;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

/**
 * Created by Kenan Klisura on 21/01/2018.
 *
 * @author Kenan Klisura
 */
@RunWith(EasyMockRunner.class)
public class DevToolsServiceImplTest extends EasyMockSupport {
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@Mock
	private WebSocketService webSocketService;

	private DevToolsServiceImpl service;

	@Before
	public void setUp() throws Exception {
		webSocketService.addMessageHandler(anyObject());
		replayAll();

		service = new DevToolsServiceImpl(webSocketService);

		verifyAll();
		resetAll();
	}

	@Test
	public void testAcceptWithUnknownInvocation() {
		resolveMessage("{\"id\":1,\"result\":{}}");
	}

	@Test
	public void testInvokeVoidMethodThrowsWebSocketException() throws ChromeDevToolsException, WebSocketServiceException, IOException {
		MethodInvocation methodInvocation = new MethodInvocation();
		methodInvocation.setId(1L);
		methodInvocation.setMethod("SomeMethod");
		methodInvocation.setParams(new HashMap<>());
		methodInvocation.getParams().put("param", "value");

		WebSocketServiceException webSocketServiceException = new WebSocketServiceException("WS Failed");

		Capture<String> messageCapture = Capture.newInstance();
		webSocketService.send(capture(messageCapture));
		expectLastCall().andThrow(webSocketServiceException);

		replayAll();

		ChromeDevToolsException capturedException = null;
		try {
			service.invoke(null, Void.TYPE, methodInvocation);
		} catch (ChromeDevToolsException ex) {
			capturedException = ex;
		}
		assertNotNull(capturedException);

		assertEquals("Failed sending web socket message.", capturedException.getMessage());
		assertEquals(webSocketServiceException, capturedException.getCause());

		verifyAll();

		MethodInvocation sentInvocation = OBJECT_MAPPER.readerFor(MethodInvocation.class).readValue(messageCapture.getValue());
		assertEquals(methodInvocation.getId(), sentInvocation.getId());
		assertEquals(methodInvocation.getMethod(), sentInvocation.getMethod());
	}

	@Test
	public void testInvokeVoidMethodInterruptsWaiting() throws ChromeDevToolsException, WebSocketServiceException, IOException {
		MethodInvocation methodInvocation = new MethodInvocation();
		methodInvocation.setId(1L);
		methodInvocation.setMethod("SomeMethod");
		methodInvocation.setParams(new HashMap<>());
		methodInvocation.getParams().put("param", "value");

		Capture<String> messageCapture = Capture.newInstance();
		webSocketService.send(capture(messageCapture));

		replayAll();

		final Thread currentThread = Thread.currentThread();
		new Thread(() -> {
			try {
				Thread.sleep(1000);
				currentThread.interrupt();
			} catch (InterruptedException e) {
				// We can ignore this
			}
		}).start();

		ChromeDevToolsException capturedException = null;
		try {
			service.invoke(null, Void.TYPE, methodInvocation);
		} catch (ChromeDevToolsException ex) {
			capturedException = ex;
		}
		assertNotNull(capturedException);

		assertEquals("Interrupted while waiting response.", capturedException.getMessage());
		assertTrue(capturedException.getCause() instanceof InterruptedException);

		verifyAll();

		MethodInvocation sentInvocation = OBJECT_MAPPER.readerFor(MethodInvocation.class).readValue(messageCapture.getValue());
		assertEquals(methodInvocation.getId(), sentInvocation.getId());
		assertEquals(methodInvocation.getMethod(), sentInvocation.getMethod());
	}

	@Test
	public void testInvokeVoidMethod() throws ChromeDevToolsException, WebSocketServiceException, IOException {
		MethodInvocation methodInvocation = new MethodInvocation();
		methodInvocation.setId(1L);
		methodInvocation.setMethod("SomeMethod");
		methodInvocation.setParams(new HashMap<>());
		methodInvocation.getParams().put("param", "value");

		Capture<String> messageCapture = Capture.newInstance();
		webSocketService.send(capture(messageCapture));

		replayAll();

		resolveMessage("{\"id\":1,\"result\":{}}");
		assertNull(service.invoke(null, Void.TYPE, methodInvocation));

		verifyAll();

		MethodInvocation sentInvocation = OBJECT_MAPPER.readerFor(MethodInvocation.class).readValue(messageCapture.getValue());
		assertEquals(methodInvocation.getId(), sentInvocation.getId());
		assertEquals(methodInvocation.getMethod(), sentInvocation.getMethod());
		assertEquals(methodInvocation.getParams(), sentInvocation.getParams());
	}

	@Test
	public void testInvokeStringMethod() throws ChromeDevToolsException, WebSocketServiceException, IOException {
		MethodInvocation methodInvocation = new MethodInvocation();
		methodInvocation.setId(1L);
		methodInvocation.setMethod("SomeMethod");
		methodInvocation.setParams(new HashMap<>());
		methodInvocation.getParams().put("param", "value");

		Capture<String> messageCapture = Capture.newInstance();
		webSocketService.send(capture(messageCapture));

		replayAll();

		resolveMessage("{\"id\":1,\"result\":{\"resultProperty\":\"resultValue\"}}");
		assertEquals("resultValue", service.invoke("resultProperty", String.class, methodInvocation));

		verifyAll();

		MethodInvocation sentInvocation = OBJECT_MAPPER.readerFor(MethodInvocation.class).readValue(messageCapture.getValue());
		assertEquals(methodInvocation.getId(), sentInvocation.getId());
		assertEquals(methodInvocation.getMethod(), sentInvocation.getMethod());
		assertEquals(methodInvocation.getParams(), sentInvocation.getParams());
	}

	@Test
	public void testInvokeTestMessageMethod() throws ChromeDevToolsException, WebSocketServiceException, IOException {
		MethodInvocation methodInvocation = new MethodInvocation();
		methodInvocation.setId(1L);
		methodInvocation.setMethod("SomeMethod");
		methodInvocation.setParams(new HashMap<>());
		methodInvocation.getParams().put("param", "value");

		Capture<String> messageCapture = Capture.newInstance();
		webSocketService.send(capture(messageCapture));

		replayAll();

		resolveMessage("{\"id\":1,\"result\":{\"testProperty\":\"resultValue\",\"testProperty2\":\"resultValue2\"}}");
		TestMessage testMessage = service.invoke(null, TestMessage.class, methodInvocation);

		verifyAll();

		assertEquals("resultValue", testMessage.getTestProperty());
		assertEquals("resultValue2", testMessage.getTestProperty2());

		MethodInvocation sentInvocation = OBJECT_MAPPER.readerFor(MethodInvocation.class).readValue(messageCapture.getValue());
		assertEquals(methodInvocation.getId(), sentInvocation.getId());
		assertEquals(methodInvocation.getMethod(), sentInvocation.getMethod());
		assertEquals(methodInvocation.getParams(), sentInvocation.getParams());
	}

	@Test
	public void testInvokeTestMessageMethodWithBadJson() throws ChromeDevToolsException, WebSocketServiceException, IOException {
		webSocketService.addMessageHandler(anyObject());
		replayAll();

		service = new DevToolsServiceImpl(webSocketService, 100);

		verifyAll();
		resetAll();


		MethodInvocation methodInvocation = new MethodInvocation();
		methodInvocation.setId(1L);
		methodInvocation.setMethod("SomeMethod");
		methodInvocation.setParams(new HashMap<>());
		methodInvocation.getParams().put("param", "value");

		Capture<String> messageCapture = Capture.newInstance();
		webSocketService.send(capture(messageCapture));

		replayAll();

		resolveMessage("{\"id\":1,\"result\":{\"testProperty\":\"resultValue\",\"testProperty2\"-\"resultValue2\"}}");
		ChromeDevToolsException capturedException = null;
		try {
			service.invoke(null, Void.TYPE, methodInvocation);
		} catch (ChromeDevToolsException ex) {
			capturedException = ex;
		}
		assertNotNull(capturedException);

		assertEquals("Timeout expired while waiting for server response.", capturedException.getMessage());

		verifyAll();

		MethodInvocation sentInvocation = OBJECT_MAPPER.readerFor(MethodInvocation.class).readValue(messageCapture.getValue());
		assertEquals(methodInvocation.getId(), sentInvocation.getId());
		assertEquals(methodInvocation.getMethod(), sentInvocation.getMethod());
	}

	@Test
	public void testInvokeVoidMethodWithError() throws WebSocketServiceException, IOException {
		MethodInvocation methodInvocation = new MethodInvocation();
		methodInvocation.setId(1L);
		methodInvocation.setMethod("SomeMethod");
		methodInvocation.setParams(new HashMap<>());
		methodInvocation.getParams().put("param", "value");

		Capture<String> messageCapture = Capture.newInstance();
		webSocketService.send(capture(messageCapture));

		replayAll();

		resolveMessage("{\"id\":1,\"error\":{\"code\":1,\"message\":\"Error message for id 1\",\"data\": \"Test data\"}}");

		ChromeDevToolsException capturedException = null;
		try {
			service.invoke(null, Void.TYPE, methodInvocation);
		} catch (ChromeDevToolsException ex) {
			capturedException = ex;
		}

		verifyAll();

		assertNotNull(capturedException);

		assertEquals(1, (long) capturedException.getCode());
		assertEquals("Error message for id 1: Test data", capturedException.getMessage());
	}

	@Test
	public void testInvokeVoidMethodWithErrorNoTestData() throws WebSocketServiceException, IOException {
		MethodInvocation methodInvocation = new MethodInvocation();
		methodInvocation.setId(1L);
		methodInvocation.setMethod("SomeMethod");
		methodInvocation.setParams(new HashMap<>());
		methodInvocation.getParams().put("param", "value");

		Capture<String> messageCapture = Capture.newInstance();
		webSocketService.send(capture(messageCapture));

		replayAll();

		resolveMessage("{\"id\":1,\"error\":{\"code\":1,\"message\":\"Error message for id 1\"}}");

		ChromeDevToolsException capturedException = null;
		try {
			service.invoke(null, Void.TYPE, methodInvocation);
		} catch (ChromeDevToolsException ex) {
			capturedException = ex;
		}

		verifyAll();

		assertNotNull(capturedException);

		assertEquals(1, (long) capturedException.getCode());
		assertEquals("Error message for id 1", capturedException.getMessage());
	}

	private void resolveMessage(String message) {
		new Thread(() -> {
			try {
				Thread.sleep(500);
				service.accept(message);
			} catch (InterruptedException e) {
				// We can ignore this
			}
		}).start();
	}

	public static class TestMessage {
		private String testProperty;
		private String testProperty2;

		public String getTestProperty() {
			return testProperty;
		}

		public void setTestProperty(String testProperty) {
			this.testProperty = testProperty;
		}

		public String getTestProperty2() {
			return testProperty2;
		}

		public void setTestProperty2(String testProperty2) {
			this.testProperty2 = testProperty2;
		}
	}

}