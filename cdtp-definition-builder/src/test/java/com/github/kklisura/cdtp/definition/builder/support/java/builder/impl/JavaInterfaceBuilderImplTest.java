package com.github.kklisura.cdtp.definition.builder.support.java.builder.impl;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.utils.SourceRoot;
import com.github.kklisura.cdtp.definition.builder.support.java.builder.support.MethodParam;
import org.easymock.Capture;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;

import static org.easymock.EasyMock.capture;
import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;

/**
 * Java interface builder test.
 *
 * @author Kenan Klisura
 */
@RunWith(EasyMockRunner.class)
public class JavaInterfaceBuilderImplTest extends EasyMockSupport {
	private static final String NAME = "InterfaceTest";
	private static final String BASE_PACKAGE_NAME = "com.github.kklisura";
	private static final String ANNOTATIONS_PACKAGE_NAME = "com.github.kklisura.annotations";

	private JavaInterfaceBuilderImpl interfaceBuilder;

	@Mock
	private SourceRoot sourceRoot;

	private Path rootPath;

	@Before
	public void setUp() throws Exception {
		rootPath = new File("/tmp/test-class-builder").toPath();
		interfaceBuilder = new JavaInterfaceBuilderImpl(BASE_PACKAGE_NAME, NAME, ANNOTATIONS_PACKAGE_NAME);
	}

	@Test
	public void testBasicInterface() throws IOException {
		Capture<CompilationUnit> compilationUnitCapture = Capture.newInstance();

		expect(sourceRoot.getRoot())
				.andReturn(rootPath);
		expect(sourceRoot.add(capture(compilationUnitCapture)))
				.andReturn(sourceRoot);

		interfaceBuilder.setJavaDoc("");

		replayAll();

		interfaceBuilder.build(sourceRoot);

		assertEquals("package com.github.kklisura;\n" +
				"\n" +
				"public interface InterfaceTest {\n" +
				"}\n" +
				"", compilationUnitCapture.getValue().toString());

		verifyAll();
	}

	@Test
	public void testBasicInterfaceWithAnnotation() throws IOException {
		Capture<CompilationUnit> compilationUnitCapture = Capture.newInstance();

		expect(sourceRoot.getRoot())
				.andReturn(rootPath);
		expect(sourceRoot.add(capture(compilationUnitCapture)))
				.andReturn(sourceRoot);

		interfaceBuilder.addAnnotation("Annotation");
		interfaceBuilder.addAnnotation("Deprecated");

		replayAll();

		interfaceBuilder.build(sourceRoot);

		assertEquals("package com.github.kklisura;\n" +
				"\n" +
				"import com.github.kklisura.annotations.Annotation;\n" +
				"\n" +
				"@Annotation\n" +
				"@Deprecated\n" +
				"public interface InterfaceTest {\n" +
				"}\n" +
				"", compilationUnitCapture.getValue().toString());

		verifyAll();
	}

	@Test
	public void testSetJavadoc() throws IOException {
		Capture<CompilationUnit> compilationUnitCapture = Capture.newInstance();

		expect(sourceRoot.getRoot())
				.andReturn(rootPath);
		expect(sourceRoot.add(capture(compilationUnitCapture)))
				.andReturn(sourceRoot);

		interfaceBuilder.setJavaDoc("Java doc.");

		replayAll();

		interfaceBuilder.build(sourceRoot);

		assertEquals("package com.github.kklisura;\n" +
				"\n" +
				"/**\n" +
				" * Java doc.\n" +
				" */\n" +
				"public interface InterfaceTest {\n" +
				"}\n", compilationUnitCapture.getValue().toString());

		verifyAll();
	}

	@Test
	public void testAddingImportsOnSamePackage() throws IOException {
		Capture<CompilationUnit> compilationUnitCapture = Capture.newInstance();

		expect(sourceRoot.getRoot())
				.andReturn(rootPath);
		expect(sourceRoot.add(capture(compilationUnitCapture)))
				.andReturn(sourceRoot);

		replayAll();

		interfaceBuilder.addImport(BASE_PACKAGE_NAME, "Test");
		interfaceBuilder.addImport("java.util", "List");
		interfaceBuilder.addImport("java.util", "List");
		interfaceBuilder.addImport("java.util", "List");

		interfaceBuilder.build(sourceRoot);

		assertEquals("package com.github.kklisura;\n\n" +
				"import java.util.List;\n" +
				"\n" +
				"public interface InterfaceTest {\n" +
				"}\n" +
				"", compilationUnitCapture.getValue().toString());

		verifyAll();
	}

	@Test
	public void testBasicInterfaceWithSimpleMethod() throws IOException {
		Capture<CompilationUnit> compilationUnitCapture = Capture.newInstance();

		expect(sourceRoot.getRoot())
				.andReturn(rootPath);
		expect(sourceRoot.add(capture(compilationUnitCapture)))
				.andReturn(sourceRoot);

		interfaceBuilder.addMethod("someMethod1", "Method description", Collections.emptyList(), null);

		replayAll();

		interfaceBuilder.build(sourceRoot);

		assertEquals("package com.github.kklisura;\n" +
				"\n" +
				"public interface InterfaceTest {\n" +
				"\n" +
				"    /**\n" +
				"     * Method description\n" +
				"     */\n" +
				"    void someMethod1();" +
				"\n" +
				"}\n" +
				"", compilationUnitCapture.getValue().toString());

		verifyAll();
	}

	@Test
	public void testBasicInterfaceWithSimpleMethod2() throws IOException {
		Capture<CompilationUnit> compilationUnitCapture = Capture.newInstance();

		expect(sourceRoot.getRoot())
				.andReturn(rootPath);
		expect(sourceRoot.add(capture(compilationUnitCapture)))
				.andReturn(sourceRoot);

		final MethodParam param1 = new MethodParam();
		param1.setType("Integer");
		param1.setName("param1");

		final MethodParam param2 = new MethodParam();
		param2.setType("String");
		param2.setName("param2");
		param2.setAnnotations(Arrays.asList(createAnnotation("Annotation"), createAnnotation("Annotation1"),
				createAnnotation("Deprecated"), createAnnotation("ParamValue", "paramValueName")));

		interfaceBuilder.addMethod("someMethod1", "", Arrays.asList(param1, param2), "String");
		interfaceBuilder.addMethodAnnotation("someMethod1", "Annotation");
		interfaceBuilder.addParametrizedMethodAnnotation("someMethod1", "Annotation2", "param");

		replayAll();

		interfaceBuilder.build(sourceRoot);

		assertEquals("package com.github.kklisura;\n" +
				"\n" +
				"import com.github.kklisura.annotations.Annotation;\n" +
				"import com.github.kklisura.annotations.Annotation1;\n" +
				"import com.github.kklisura.annotations.ParamValue;\n" +
				"import com.github.kklisura.annotations.Annotation2;\n" +
				"\n" +
				"public interface InterfaceTest {\n" +
				"\n" +
				"    @Annotation\n" +
				"    @Annotation2(\"param\")\n" +
				"    String someMethod1(Integer param1, @Annotation @Annotation1 @Deprecated @ParamValue(\"paramValueName\") String param2);\n" +
				"}\n", compilationUnitCapture.getValue().toString());

		verifyAll();
	}

	private MethodParam.Annotation createAnnotation(String name) {
		return new MethodParam.Annotation(name);
	}

	private MethodParam.Annotation createAnnotation(String name, String value) {
		return new MethodParam.Annotation(name, value);
	}
}