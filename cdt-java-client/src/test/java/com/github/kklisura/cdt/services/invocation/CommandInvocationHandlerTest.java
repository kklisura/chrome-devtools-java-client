package com.github.kklisura.cdt.services.invocation;

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

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import com.github.kklisura.cdt.protocol.support.annotations.EventName;
import com.github.kklisura.cdt.protocol.support.annotations.ParamName;
import com.github.kklisura.cdt.protocol.support.annotations.ReturnTypeParameter;
import com.github.kklisura.cdt.protocol.support.annotations.Returns;
import com.github.kklisura.cdt.protocol.support.types.EventHandler;
import com.github.kklisura.cdt.protocol.support.types.EventListener;
import com.github.kklisura.cdt.services.ChromeDevToolsService;
import com.github.kklisura.cdt.services.types.MethodInvocation;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import org.easymock.Capture;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Command invocation handler test.
 *
 * @author Kenan Klisura
 */
@RunWith(EasyMockRunner.class)
public class CommandInvocationHandlerTest extends EasyMockSupport {

  @Mock private ChromeDevToolsService chromeDevToolsService;

  private CommandInvocationHandler invocationHandler;

  @Before
  public void setUp() throws Exception {
    invocationHandler = new CommandInvocationHandler();
    invocationHandler.setChromeDevToolsService(chromeDevToolsService);
  }

  @Test
  public void testInvokeVoidMethod() throws Throwable {
    Capture<MethodInvocation> methodInvocationCapture = Capture.newInstance();
    expect(
            chromeDevToolsService.invoke(
                eq(null), eq(Void.TYPE), eq(null), capture(methodInvocationCapture)))
        .andReturn(null);

    replayAll();

    assertNull(invocationHandler.invoke(this, getMethodByName("voidMethod"), null));

    verifyAll();

    MethodInvocation methodInvocation = methodInvocationCapture.getValue();

    assertNotNull(methodInvocation.getId());
    assertEquals("CommandInvocationHandlerTest.voidMethod", methodInvocation.getMethod());
    assertTrue(methodInvocation.getParams().isEmpty());

    resetAll();

    methodInvocationCapture = Capture.newInstance();
    expect(
            chromeDevToolsService.invoke(
                eq(null), eq(Void.TYPE), eq(null), capture(methodInvocationCapture)))
        .andReturn(null);

    replayAll();

    assertNull(invocationHandler.invoke(this, getMethodByName("voidMethod"), new Object[] {}));

    verifyAll();

    methodInvocation = methodInvocationCapture.getValue();

    assertNotNull(methodInvocation.getId());
    assertEquals("CommandInvocationHandlerTest.voidMethod", methodInvocation.getMethod());
    assertTrue(methodInvocation.getParams().isEmpty());
  }

  @Test
  public void testInvokeStringMethodWithParams() throws Throwable {
    Capture<MethodInvocation> methodInvocationCapture = Capture.newInstance();
    expect(
            chromeDevToolsService.invoke(
                eq(null), eq(String.class), eq(null), capture(methodInvocationCapture)))
        .andReturn(null);

    replayAll();

    assertNull(
        invocationHandler.invoke(
            this, getMethodByName("stringMethodWithParams"), new Object[] {"Test", 1}));

    verifyAll();

    MethodInvocation methodInvocation = methodInvocationCapture.getValue();

    assertNotNull(methodInvocation.getId());
    assertEquals(
        "CommandInvocationHandlerTest.stringMethodWithParams", methodInvocation.getMethod());
    assertFalse(methodInvocation.getParams().isEmpty());

    assertEquals("Test", methodInvocation.getParams().get("paramTest"));
    assertEquals(1, (int) methodInvocation.getParams().get("paramTest1"));
  }

  @Test
  public void testInvokeStringMethodWithParamsAndReturnsAnnotation() throws Throwable {
    Capture<MethodInvocation> methodInvocationCapture = Capture.newInstance();
    expect(
            chromeDevToolsService.invoke(
                eq("ReturnsValue"), eq(String.class), eq(null), capture(methodInvocationCapture)))
        .andReturn(null);

    replayAll();

    assertNull(
        invocationHandler.invoke(
            this,
            getMethodByName("stringMethodWithParamsAndAnnotation"),
            new Object[] {"Test", 1}));

    verifyAll();

    MethodInvocation methodInvocation = methodInvocationCapture.getValue();

    assertNotNull(methodInvocation.getId());
    assertEquals(
        "CommandInvocationHandlerTest.stringMethodWithParamsAndAnnotation",
        methodInvocation.getMethod());
    assertFalse(methodInvocation.getParams().isEmpty());

    assertEquals("Test", methodInvocation.getParams().get("paramTest"));
    assertEquals(1, (int) methodInvocation.getParams().get("paramTest1"));
  }

  @Test
  public void testInvokeStringMethodWithParamsAndReturnTypeAnnotation() throws Throwable {
    Capture<MethodInvocation> methodInvocationCapture = Capture.newInstance();
    expect(
            chromeDevToolsService.invoke(
                eq("ReturnsValue"),
                eq(List.class),
                aryEq(new Class[] {String.class}),
                capture(methodInvocationCapture)))
        .andReturn(null);

    replayAll();

    assertNull(
        invocationHandler.invoke(
            this,
            getMethodByName("stringMethodWithParamsAndReturnTypeAnnotation"),
            new Object[] {"Test", 1}));

    verifyAll();

    MethodInvocation methodInvocation = methodInvocationCapture.getValue();

    assertNotNull(methodInvocation.getId());
    assertEquals(
        "CommandInvocationHandlerTest.stringMethodWithParamsAndReturnTypeAnnotation",
        methodInvocation.getMethod());
    assertFalse(methodInvocation.getParams().isEmpty());

    assertEquals("Test", methodInvocation.getParams().get("paramTest"));
    assertEquals(1, (int) methodInvocation.getParams().get("paramTest1"));
  }

  @Test
  public void testIsEventSubscription() {
    assertFalse(CommandInvocationHandler.isEventSubscription(getMethodByName("voidMethod")));
    assertFalse(
        CommandInvocationHandler.isEventSubscription(getMethodByName("stringMethodWithParams")));

    assertFalse(
        CommandInvocationHandler.isEventSubscription(getMethodByName("onEventListenerTestMethod")));
    assertFalse(
        CommandInvocationHandler.isEventSubscription(
            getMethodByName("onEventListenerTestMethod1")));
    assertFalse(
        CommandInvocationHandler.isEventSubscription(
            getMethodByName("onEventListenerTestMethod2")));
    assertFalse(
        CommandInvocationHandler.isEventSubscription(
            getMethodByName("onEventListenerTestMethod3")));
    assertTrue(
        CommandInvocationHandler.isEventSubscription(
            getMethodByName("onEventListenerTestMethod4")));
  }

  @Test
  public void testInvokeWithEvent() throws Throwable {
    EventHandler<String> eventHandler = event -> {};
    EventListener eventListener = mock(EventListener.class);

    expect(
            chromeDevToolsService.addEventListener(
                "CommandInvocationHandlerTest", "someEventName", eventHandler, String.class))
        .andReturn(eventListener);

    replayAll();

    assertEquals(
        eventListener,
        invocationHandler.invoke(
            null, getMethodByName("onEventListenerTestMethod4"), new Object[] {eventHandler}));

    verifyAll();
  }

  private Method getMethodByName(String name) {
    Method[] declaredMethods = this.getClass().getDeclaredMethods();
    for (Method method : declaredMethods) {
      if (name.equals(method.getName())) {
        return method;
      }
    }

    throw new RuntimeException("Could not find method " + name);
  }

  private void onEventListenerTestMethod() {}

  private EventListener onEventListenerTestMethod1() {
    return null;
  }

  private void onEventListenerTestMethod2(String param, String param2) {}

  private EventListener onEventListenerTestMethod3(String param, String param2) {
    return null;
  }

  @EventName("someEventName")
  private EventListener onEventListenerTestMethod4(EventHandler<String> handler) {
    return null;
  }

  private void voidMethod() {}

  private String stringMethodWithParams(
      @ParamName("paramTest") String param1, @ParamName("paramTest1") Integer param2) {
    return "EMPTY-STRING";
  }

  @Returns("ReturnsValue")
  private String stringMethodWithParamsAndAnnotation(
      @ParamName("paramTest") String param1, @ParamName("paramTest1") Integer param2) {
    return "EMPTY-STRING";
  }

  @Returns("ReturnsValue")
  @ReturnTypeParameter(String.class)
  private List<String> stringMethodWithParamsAndReturnTypeAnnotation(
      @ParamName("paramTest") String param1, @ParamName("paramTest1") Integer param2) {
    return Collections.emptyList();
  }
}
