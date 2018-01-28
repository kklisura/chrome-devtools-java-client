package com.github.kklisura.cdtp.services.impl;

/*-
 * #%L
 * cdpt-java-client
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

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kklisura.cdtp.protocol.support.types.EventHandler;
import com.github.kklisura.cdtp.protocol.support.types.EventListener;
import com.github.kklisura.cdtp.services.WebSocketService;
import com.github.kklisura.cdtp.services.exceptions.ChromeDevToolsInvocationException;
import com.github.kklisura.cdtp.services.exceptions.WebSocketServiceException;
import com.github.kklisura.cdtp.services.types.ChromeTab;
import com.github.kklisura.cdtp.services.types.EventListenerImpl;
import com.github.kklisura.cdtp.services.types.MethodInvocation;
import com.github.kklisura.cdtp.services.utils.ProxyUtils;
import java.io.IOException;
import java.util.HashMap;
import org.easymock.Capture;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Kenan Klisura on 21/01/2018.
 *
 * @author Kenan Klisura
 */
@RunWith(EasyMockRunner.class)
public class ChromeDevToolsServiceImplTest extends EasyMockSupport {
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  @Mock private WebSocketService webSocketService;

  private ChromeDevToolsServiceImpl service;

  @Before
  public void setUp() throws Exception {
    webSocketService.addMessageHandler(anyObject());
    replayAll();

    service =
        ProxyUtils.createProxyFromAbstract(
            ChromeDevToolsServiceImpl.class,
            new Class[] {WebSocketService.class},
            new Object[] {webSocketService},
            (proxy, method, args) -> {
              throw new RuntimeException("This should not be called during testing");
            });

    verifyAll();
    resetAll();
  }

  @Test
  public void testAcceptWithUnknownInvocation() {
    resolveMessage("{\"id\":1,\"result\":{}}");
  }

  @Test
  public void testAcceptWithUnknownInvocation2() {
    resolveMessage("{\"id\":1}");
  }

  @Test
  public void testInvokeVoidMethodThrowsWebSocketException()
      throws WebSocketServiceException, IOException {
    MethodInvocation methodInvocation = new MethodInvocation();
    methodInvocation.setId(1L);
    methodInvocation.setMethod("SomeMethod");
    methodInvocation.setParams(new HashMap<>());
    methodInvocation.getParams().put("param", "value");

    WebSocketServiceException webSocketServiceException =
        new WebSocketServiceException("WS Failed");

    Capture<String> messageCapture = Capture.newInstance();
    webSocketService.send(capture(messageCapture));
    expectLastCall().andThrow(webSocketServiceException);

    replayAll();

    ChromeDevToolsInvocationException capturedException = null;
    try {
      service.invoke(null, Void.TYPE, methodInvocation);
    } catch (ChromeDevToolsInvocationException ex) {
      capturedException = ex;
    }
    assertNotNull(capturedException);

    assertEquals("Failed sending web socket message.", capturedException.getMessage());
    assertEquals(webSocketServiceException, capturedException.getCause());

    verifyAll();

    MethodInvocation sentInvocation =
        OBJECT_MAPPER.readerFor(MethodInvocation.class).readValue(messageCapture.getValue());
    assertEquals(methodInvocation.getId(), sentInvocation.getId());
    assertEquals(methodInvocation.getMethod(), sentInvocation.getMethod());
  }

  @Test
  public void testInvokeVoidMethodInterruptsWaiting()
      throws WebSocketServiceException, IOException {
    MethodInvocation methodInvocation = new MethodInvocation();
    methodInvocation.setId(1L);
    methodInvocation.setMethod("SomeMethod");
    methodInvocation.setParams(new HashMap<>());
    methodInvocation.getParams().put("param", "value");

    Capture<String> messageCapture = Capture.newInstance();
    webSocketService.send(capture(messageCapture));

    replayAll();

    final Thread currentThread = Thread.currentThread();
    new Thread(
            () -> {
              try {
                Thread.sleep(1000);
                currentThread.interrupt();
              } catch (InterruptedException e) {
                // We can ignore this
              }
            })
        .start();

    ChromeDevToolsInvocationException capturedException = null;
    try {
      service.invoke(null, Void.TYPE, methodInvocation);
    } catch (ChromeDevToolsInvocationException ex) {
      capturedException = ex;
    }
    assertNotNull(capturedException);

    assertEquals("Interrupted while waiting response.", capturedException.getMessage());
    assertTrue(capturedException.getCause() instanceof InterruptedException);

    verifyAll();

    MethodInvocation sentInvocation =
        OBJECT_MAPPER.readerFor(MethodInvocation.class).readValue(messageCapture.getValue());
    assertEquals(methodInvocation.getId(), sentInvocation.getId());
    assertEquals(methodInvocation.getMethod(), sentInvocation.getMethod());
  }

  @Test
  public void testInvokeVoidMethod() throws WebSocketServiceException, IOException {
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

    MethodInvocation sentInvocation =
        OBJECT_MAPPER.readerFor(MethodInvocation.class).readValue(messageCapture.getValue());
    assertEquals(methodInvocation.getId(), sentInvocation.getId());
    assertEquals(methodInvocation.getMethod(), sentInvocation.getMethod());
    assertEquals(methodInvocation.getParams(), sentInvocation.getParams());
  }

  @Test
  public void testInvokeStringMethod() throws WebSocketServiceException, IOException {
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

    MethodInvocation sentInvocation =
        OBJECT_MAPPER.readerFor(MethodInvocation.class).readValue(messageCapture.getValue());
    assertEquals(methodInvocation.getId(), sentInvocation.getId());
    assertEquals(methodInvocation.getMethod(), sentInvocation.getMethod());
    assertEquals(methodInvocation.getParams(), sentInvocation.getParams());
  }

  @Test(expected = ChromeDevToolsInvocationException.class)
  public void testInvokeStringMethodWithNullResult() throws WebSocketServiceException, IOException {
    MethodInvocation methodInvocation = new MethodInvocation();
    methodInvocation.setId(1L);
    methodInvocation.setMethod("SomeMethod");
    methodInvocation.setParams(new HashMap<>());
    methodInvocation.getParams().put("param", "value");

    Capture<String> messageCapture = Capture.newInstance();
    webSocketService.send(capture(messageCapture));

    replayAll();

    resolveMessage("{\"id\":1}");
    service.invoke("resultProperty", String.class, methodInvocation);
  }

  @Test
  public void testInvokeTestMessageMethod() throws WebSocketServiceException, IOException {
    MethodInvocation methodInvocation = new MethodInvocation();
    methodInvocation.setId(1L);
    methodInvocation.setMethod("SomeMethod");
    methodInvocation.setParams(new HashMap<>());
    methodInvocation.getParams().put("param", "value");

    Capture<String> messageCapture = Capture.newInstance();
    webSocketService.send(capture(messageCapture));

    replayAll();

    resolveMessage(
        "{\"id\":1,\"result\":{\"testProperty\":\"resultValue\",\"testProperty2\":\"resultValue2\"}}");
    TestMessage testMessage = service.invoke(null, TestMessage.class, methodInvocation);

    verifyAll();

    assertEquals("resultValue", testMessage.getTestProperty());
    assertEquals("resultValue2", testMessage.getTestProperty2());

    MethodInvocation sentInvocation =
        OBJECT_MAPPER.readerFor(MethodInvocation.class).readValue(messageCapture.getValue());
    assertEquals(methodInvocation.getId(), sentInvocation.getId());
    assertEquals(methodInvocation.getMethod(), sentInvocation.getMethod());
    assertEquals(methodInvocation.getParams(), sentInvocation.getParams());
  }

  @Test
  public void testInvokeTestMessageMethodWithBadJson()
      throws WebSocketServiceException, IOException {
    webSocketService.addMessageHandler(anyObject());
    replayAll();

    service =
        ProxyUtils.createProxyFromAbstract(
            ChromeDevToolsServiceImpl.class,
            new Class[] {WebSocketService.class, Long.TYPE},
            new Object[] {webSocketService, 100},
            (proxy, method, args) -> {
              throw new RuntimeException("This should not be called during testing");
            });

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

    resolveMessage(
        "{\"id\":1,\"result\":{\"testProperty\":\"resultValue\",\"testProperty2\"-\"resultValue2\"}}");
    ChromeDevToolsInvocationException capturedException = null;
    try {
      service.invoke(null, Void.TYPE, methodInvocation);
    } catch (ChromeDevToolsInvocationException ex) {
      capturedException = ex;
    }
    assertNotNull(capturedException);

    assertEquals(
        "Timeout expired while waiting for server response.", capturedException.getMessage());

    verifyAll();

    MethodInvocation sentInvocation =
        OBJECT_MAPPER.readerFor(MethodInvocation.class).readValue(messageCapture.getValue());
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

    resolveMessage(
        "{\"id\":1,\"error\":{\"code\":1,\"message\":\"Error message for id 1\",\"data\": \"Test data\"}}");

    ChromeDevToolsInvocationException capturedException = null;
    try {
      service.invoke(null, Void.TYPE, methodInvocation);
    } catch (ChromeDevToolsInvocationException ex) {
      capturedException = ex;
    }

    verifyAll();

    assertNotNull(capturedException);

    assertEquals(1, (long) capturedException.getCode());
    assertEquals("Error message for id 1: Test data", capturedException.getMessage());
  }

  @Test
  public void testInvokeVoidMethodWithErrorNoTestData()
      throws WebSocketServiceException, IOException {
    MethodInvocation methodInvocation = new MethodInvocation();
    methodInvocation.setId(1L);
    methodInvocation.setMethod("SomeMethod");
    methodInvocation.setParams(new HashMap<>());
    methodInvocation.getParams().put("param", "value");

    Capture<String> messageCapture = Capture.newInstance();
    webSocketService.send(capture(messageCapture));

    replayAll();

    resolveMessage("{\"id\":1,\"error\":{\"code\":1,\"message\":\"Error message for id 1\"}}");

    ChromeDevToolsInvocationException capturedException = null;
    try {
      service.invoke(null, Void.TYPE, methodInvocation);
    } catch (ChromeDevToolsInvocationException ex) {
      capturedException = ex;
    }

    verifyAll();

    assertNotNull(capturedException);

    assertEquals(1, (long) capturedException.getCode());
    assertEquals("Error message for id 1", capturedException.getMessage());
  }

  @Test
  public void testClose() {
    service.close();

    ChromeTab chromeTab = new ChromeTab();

    ChromeServiceImpl chromeService = mock(ChromeServiceImpl.class);
    service.setChromeService(chromeService);
    service.setChromeTab(chromeTab);

    chromeService.clearChromeDevToolsServiceCache(chromeTab);

    replay(chromeService);

    service.close();

    verify(chromeService);
  }

  @Test
  public void testAddEventListener() {
    EventHandler<String> eventHandler = event -> {};

    EventListener eventListener =
        service.addEventListener("domain", "event", eventHandler, String.class);
    assertNotNull(eventListener);

    assertEquals(eventHandler, ((EventListenerImpl) eventListener).getHandler());
    assertEquals("domain.event", ((EventListenerImpl) eventListener).getKey());
    assertEquals(String.class, ((EventListenerImpl) eventListener).getParamType());

    service.removeEventListener(eventListener);
  }

  @Test
  public void testEventReceivedWithOff() throws InterruptedException {
    // Non existing event handler
    service.accept("{\"method\":\"Domain.name\",\"params\":{\"testProperty\":\"testValue\"}}");

    Capture<TestMessage> testMessageCapture = Capture.newInstance();

    EventHandler<TestMessage> eventHandler = testMessageCapture::setValue;

    EventListener eventListener =
        service.addEventListener("Domain", "name", eventHandler, TestMessage.class);
    service.accept("{\"method\":\"Domain.name\",\"params\":{\"testProperty\":\"testValue\"}}");

    assertNotNull(testMessageCapture.getValue());
    assertEquals("testValue", testMessageCapture.getValue().getTestProperty());

    testMessageCapture.reset();

    eventListener.off();

    service.accept("{\"method\":\"Domain.name\",\"params\":{\"testProperty\":\"testValue\"}}");

    assertFalse(testMessageCapture.hasCaptured());
  }

  @Test
  public void testEventReceivedWithUnsubscribe() throws InterruptedException {
    // Non existing event handler
    service.accept("{\"method\":\"Domain.name\",\"params\":{\"testProperty\":\"testValue\"}}");

    Capture<TestMessage> testMessageCapture = Capture.newInstance();

    EventHandler<TestMessage> eventHandler = testMessageCapture::setValue;

    EventListener eventListener =
        service.addEventListener("Domain", "name", eventHandler, TestMessage.class);
    service.accept("{\"method\":\"Domain.name\",\"params\":{\"testProperty\":\"testValue\"}}");

    assertNotNull(testMessageCapture.getValue());
    assertEquals("testValue", testMessageCapture.getValue().getTestProperty());

    testMessageCapture.reset();

    eventListener.unsubscribe();

    service.accept("{\"method\":\"Domain.name\",\"params\":{\"testProperty\":\"testValue\"}}");

    assertFalse(testMessageCapture.hasCaptured());
  }

  @Test
  public void testEventReceivedWithUnsubscribeOnService() throws InterruptedException {
    // Non existing event handler
    service.accept("{\"method\":\"Domain.name\",\"params\":{\"testProperty\":\"testValue\"}}");

    Capture<TestMessage> testMessageCapture = Capture.newInstance();

    EventHandler<TestMessage> eventHandler = testMessageCapture::setValue;

    EventListener eventListener =
        service.addEventListener("Domain", "name", eventHandler, TestMessage.class);
    service.accept("{\"method\":\"Domain.name\",\"params\":{\"testProperty\":\"testValue\"}}");

    assertNotNull(testMessageCapture.getValue());
    assertEquals("testValue", testMessageCapture.getValue().getTestProperty());

    service.removeEventListener(eventListener);

    testMessageCapture.reset();

    service.accept("{\"method\":\"Domain.name\",\"params\":{\"testProperty\":\"testValue\"}}");

    assertFalse(testMessageCapture.hasCaptured());
  }

  @Test
  public void testEventReceivedHandlerThrowsExeption() throws InterruptedException {
    // Non existing event handler
    service.accept("{\"method\":\"Domain.name\",\"params\":{\"testProperty\":\"testValue\"}}");

    Capture<TestMessage> testMessageCapture = Capture.newInstance();

    EventHandler<TestMessage> eventHandlerThrowsException =
        event -> {
          throw new RuntimeException("test");
        };
    EventHandler<TestMessage> eventHandler = testMessageCapture::setValue;

    EventListener eventListenerWithException =
        service.addEventListener("Domain", "name", eventHandlerThrowsException, TestMessage.class);
    EventListener eventListener =
        service.addEventListener("Domain", "name", eventHandler, TestMessage.class);

    service.accept("{\"method\":\"Domain.name\",\"params\":{\"testProperty\":\"testValue\"}}");

    assertNotNull(testMessageCapture.getValue());
    assertEquals("testValue", testMessageCapture.getValue().getTestProperty());

    service.removeEventListener(eventListenerWithException);

    testMessageCapture.reset();

    service.accept("{\"method\":\"Domain.name\",\"params\":{\"testProperty\":\"testValue\"}}");

    assertNotNull(testMessageCapture.getValue());
    assertEquals("testValue", testMessageCapture.getValue().getTestProperty());
  }

  private void resolveMessage(String message) {
    new Thread(
            () -> {
              try {
                Thread.sleep(500);
                service.accept(message);
              } catch (InterruptedException e) {
                // We can ignore this
              }
            })
        .start();
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
