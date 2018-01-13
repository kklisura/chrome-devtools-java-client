package com.github.kklisura.dev.tools.java.generator.support.java.builder.impl;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.utils.SourceRoot;
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

import static org.easymock.EasyMock.capture;
import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;

/**
 * Java class builder test.
 *
 * @author Kenan Klisura
 */
@RunWith(EasyMockRunner.class)
public class JavaClassBuilderImplTest extends EasyMockSupport {
	private static final String PACKAGE_NAME = "com.github.kklisura";
	private static final String CLASS_NAME = "ClassName";
	private static final String ANNOTATIONS_PACKAGE_NAME = "com.github.kklisura.annotations";

	@Mock
	private SourceRoot sourceRoot;

	private Path rootPath;

	private JavaClassBuilderImpl javaClassBuilder;

	@Before
	public void setUp() throws Exception {
		rootPath = new File("/tmp/test-class-builder").toPath();
		javaClassBuilder = new JavaClassBuilderImpl(PACKAGE_NAME, CLASS_NAME, ANNOTATIONS_PACKAGE_NAME);
	}

	@Test
	public void testBasicClass() throws IOException {
		Capture<CompilationUnit> compilationUnitCapture = Capture.newInstance();

		expect(sourceRoot.getRoot())
				.andReturn(rootPath);
		expect(sourceRoot.add(capture(compilationUnitCapture)))
				.andReturn(sourceRoot);

		replayAll();

		javaClassBuilder.build(sourceRoot);

		assertEquals("package com.github.kklisura;\n" +
				"\n" +
				"public class ClassName {\n" +
				"}\n" +
				"", compilationUnitCapture.getValue().toString());

		verifyAll();
	}

	@Test
	public void testAddingImports() throws IOException {
		Capture<CompilationUnit> compilationUnitCapture = Capture.newInstance();

		expect(sourceRoot.getRoot())
				.andReturn(rootPath);
		expect(sourceRoot.add(capture(compilationUnitCapture)))
				.andReturn(sourceRoot);

		replayAll();

		javaClassBuilder.addImport("java.util", "List");
		javaClassBuilder.addImport("java.util", "List");
		javaClassBuilder.addImport("java.util", "List");

		javaClassBuilder.build(sourceRoot);

		assertEquals("package com.github.kklisura;\n\n" +
				"import java.util.List;\n" +
				"\n" +
				"public class ClassName {\n" +
				"}\n" +
				"", compilationUnitCapture.getValue().toString());

		verifyAll();
	}
}