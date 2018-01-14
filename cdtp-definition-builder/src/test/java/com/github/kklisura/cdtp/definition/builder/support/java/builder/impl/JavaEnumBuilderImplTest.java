package com.github.kklisura.cdtp.definition.builder.support.java.builder.impl;

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
 * Java enum builder impl test.
 *
 * @author Kenan Klisura
 */
@RunWith(EasyMockRunner.class)
public class JavaEnumBuilderImplTest extends EasyMockSupport {
	private static final String PACKAGE = "com.github.kklisura";
	private static final String NAME = "EnumName";

	private JavaEnumBuilderImpl javaEnumBuilder;

	@Mock
	private SourceRoot sourceRoot;

	private Path rootPath;

	@Before
	public void setUp() throws Exception {
		rootPath = new File("/tmp/test-class-builder").toPath();
		javaEnumBuilder = new JavaEnumBuilderImpl(PACKAGE, NAME);
	}

	@Test
	public void testBasicEnum() throws IOException {
		Capture<CompilationUnit> compilationUnitCapture = Capture.newInstance();

		expect(sourceRoot.getRoot())
				.andReturn(rootPath);
		expect(sourceRoot.add(capture(compilationUnitCapture)))
				.andReturn(sourceRoot);

		javaEnumBuilder.setJavaDoc("");

		replayAll();

		javaEnumBuilder.build(sourceRoot);

		assertEquals("package com.github.kklisura;\n" +
				"\n" +
				"public enum EnumName {\n" +
				"\n" +
				"    ;\n" +
				"\n" +
				"    final String propertyName;\n" +
				"\n" +
				"    EnumName(final String propertyName) {\n" +
				"        this.propertyName = propertyName;\n" +
				"    }\n" +
				"\n" +
				"    public String getPropertyName() {\n" +
				"        return this.propertyName;\n" +
				"    }\n" +
				"}\n", compilationUnitCapture.getValue().toString());

		verifyAll();
	}

	@Test
	public void testBasicEnumWithJavadocAndConstant() throws IOException {
		Capture<CompilationUnit> compilationUnitCapture = Capture.newInstance();

		expect(sourceRoot.getRoot())
				.andReturn(rootPath);
		expect(sourceRoot.add(capture(compilationUnitCapture)))
				.andReturn(sourceRoot);

		javaEnumBuilder.addEnumConstant("ENUM_CONSTANT", "enumConstant");
		javaEnumBuilder.setJavaDoc("Java doc.");

		replayAll();

		javaEnumBuilder.build(sourceRoot);

		assertEquals("package com.github.kklisura;\n" +
				"\n" +
				"/**\n" +
				" * Java doc.\n" +
				" */\n" +
				"public enum EnumName {\n" +
				"\n" +
				"    ENUM_CONSTANT(\"enumConstant\");\n" +
				"\n" +
				"    final String propertyName;\n" +
				"\n" +
				"    EnumName(final String propertyName) {\n" +
				"        this.propertyName = propertyName;\n" +
				"    }\n" +
				"\n" +
				"    public String getPropertyName() {\n" +
				"        return this.propertyName;\n" +
				"    }\n" +
				"}\n", compilationUnitCapture.getValue().toString());

		verifyAll();
	}

}