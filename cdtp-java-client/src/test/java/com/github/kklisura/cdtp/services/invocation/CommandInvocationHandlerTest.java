package com.github.kklisura.cdtp.services.invocation;

import com.github.kklisura.cdtp.protocol.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.annotations.Returns;
import com.github.kklisura.cdtp.services.ChromeDevToolsService;
import com.github.kklisura.cdtp.services.model.chrome.MethodInvocation;
import org.easymock.Capture;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Method;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

/**
 * Command invocation handler test.
 *
 * @author Kenan Klisura
 */
@RunWith(EasyMockRunner.class)
public class CommandInvocationHandlerTest extends EasyMockSupport {

	@Mock
	private ChromeDevToolsService chromeDevToolsService;

	private CommandInvocationHandler invocationHandler;

	@Before
	public void setUp() throws Exception {
		invocationHandler = new CommandInvocationHandler();
		invocationHandler.setChromeDevToolsService(chromeDevToolsService);
	}

	@Test
	public void testInvokeVoidMethod() throws Throwable {
		Capture<MethodInvocation> methodInvocationCapture = Capture.newInstance();
		expect(chromeDevToolsService.invoke(eq(null), eq(Void.TYPE), capture(methodInvocationCapture)))
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
		expect(chromeDevToolsService.invoke(eq(null), eq(Void.TYPE), capture(methodInvocationCapture)))
				.andReturn(null);

		replayAll();

		assertNull(invocationHandler.invoke(this, getMethodByName("voidMethod"), new Object[] { }));

		verifyAll();

		methodInvocation = methodInvocationCapture.getValue();

		assertNotNull(methodInvocation.getId());
		assertEquals("CommandInvocationHandlerTest.voidMethod", methodInvocation.getMethod());
		assertTrue(methodInvocation.getParams().isEmpty());
	}

	@Test
	public void testInvokeStringMethodWithParams() throws Throwable {
		Capture<MethodInvocation> methodInvocationCapture = Capture.newInstance();
		expect(chromeDevToolsService.invoke(eq(null), eq(String.class), capture(methodInvocationCapture)))
				.andReturn(null);

		replayAll();

		assertNull(invocationHandler.invoke(this, getMethodByName("stringMethodWithParams"),
				new Object[] { "Test", 1 }));

		verifyAll();

		MethodInvocation methodInvocation = methodInvocationCapture.getValue();

		assertNotNull(methodInvocation.getId());
		assertEquals("CommandInvocationHandlerTest.stringMethodWithParams", methodInvocation.getMethod());
		assertFalse(methodInvocation.getParams().isEmpty());

		assertEquals("Test", methodInvocation.getParams().get("paramTest"));
		assertEquals(1, (int) methodInvocation.getParams().get("paramTest1"));
	}

	@Test
	public void testInvokeStringMethodWithParamsAndReturnsAnnotation() throws Throwable {
		Capture<MethodInvocation> methodInvocationCapture = Capture.newInstance();
		expect(chromeDevToolsService.invoke(eq("ReturnsValue"), eq(String.class), capture(methodInvocationCapture)))
				.andReturn(null);

		replayAll();

		assertNull(invocationHandler.invoke(this, getMethodByName("stringMethodWithParamsAndAnnotation"),
				new Object[] { "Test", 1 }));

		verifyAll();

		MethodInvocation methodInvocation = methodInvocationCapture.getValue();

		assertNotNull(methodInvocation.getId());
		assertEquals("CommandInvocationHandlerTest.stringMethodWithParamsAndAnnotation", methodInvocation.getMethod());
		assertFalse(methodInvocation.getParams().isEmpty());

		assertEquals("Test", methodInvocation.getParams().get("paramTest"));
		assertEquals(1, (int) methodInvocation.getParams().get("paramTest1"));
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

	private void voidMethod() {}

	private String stringMethodWithParams(@ParamName("paramTest") String param1, @ParamName("paramTest1") Integer param2) {
		return "EMPTY-STRING";
	}

	@Returns("ReturnsValue")
	private String stringMethodWithParamsAndAnnotation(@ParamName("paramTest") String param1, @ParamName("paramTest1") Integer param2) {
		return "EMPTY-STRING";
	}

}