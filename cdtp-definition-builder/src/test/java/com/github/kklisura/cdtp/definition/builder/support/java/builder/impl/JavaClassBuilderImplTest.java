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

		javaClassBuilder.setJavaDoc("");

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
	public void testBasicClassWithAnnotation() throws IOException {
		Capture<CompilationUnit> compilationUnitCapture = Capture.newInstance();

		expect(sourceRoot.getRoot())
				.andReturn(rootPath);
		expect(sourceRoot.add(capture(compilationUnitCapture)))
				.andReturn(sourceRoot);

		javaClassBuilder.addAnnotation("Annotation");
		javaClassBuilder.addAnnotation("Deprecated");

		replayAll();

		javaClassBuilder.build(sourceRoot);

		assertEquals("package com.github.kklisura;\n" +
				"\n" +
				"import com.github.kklisura.annotations.Annotation;\n" +
				"\n" +
				"@Annotation\n" +
				"@Deprecated\n" +
				"public class ClassName {\n" +
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

		javaClassBuilder.setJavaDoc("Java doc.");

		replayAll();

		javaClassBuilder.build(sourceRoot);

		assertEquals("package com.github.kklisura;\n" +
				"\n" +
				"/**\n" +
				" * Java doc.\n" +
				" */\n" +
				"public class ClassName {\n" +
				"}\n", compilationUnitCapture.getValue().toString());

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

	@Test
	public void testAddingImportsOnSamePackage() throws IOException {
		Capture<CompilationUnit> compilationUnitCapture = Capture.newInstance();

		expect(sourceRoot.getRoot())
				.andReturn(rootPath);
		expect(sourceRoot.add(capture(compilationUnitCapture)))
				.andReturn(sourceRoot);

		replayAll();

		javaClassBuilder.addImport(PACKAGE_NAME, "Test");
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

	@Test
	public void testGenerateGettersAndSetters() throws IOException {
		Capture<CompilationUnit> compilationUnitCapture = Capture.newInstance();

		expect(sourceRoot.getRoot())
				.andReturn(rootPath);
		expect(sourceRoot.add(capture(compilationUnitCapture)))
				.andReturn(sourceRoot);

		replayAll();

		javaClassBuilder.addPrivateField("privateField", "String", "Private field description");

		javaClassBuilder.generateGettersAndSetters();

		javaClassBuilder.build(sourceRoot);

		assertEquals("package com.github.kklisura;\n" +
				"\n" +
				"public class ClassName {\n" +
				"\n" +
				"    private String privateField;\n" +
				"\n" +
				"    /**\n" +
				"     * Private field description\n" +
				"     */\n" +
				"    public String getPrivateField() {\n" +
				"        return privateField;\n" +
				"    }\n" +
				"\n" +
				"    /**\n" +
				"     * Private field description\n" +
				"     */\n" +
				"    public void setPrivateField(String privateField) {\n" +
				"        this.privateField = privateField;\n" +
				"    }\n" +
				"}\n", compilationUnitCapture.getValue().toString());

		verifyAll();
	}

	@Test
	public void testGenerateFieldAnnotation() throws IOException {
		Capture<CompilationUnit> compilationUnitCapture = Capture.newInstance();

		expect(sourceRoot.getRoot())
				.andReturn(rootPath);
		expect(sourceRoot.add(capture(compilationUnitCapture)))
				.andReturn(sourceRoot);

		replayAll();

		javaClassBuilder.addPrivateField("privateField", "String", "Private field description");

		javaClassBuilder.addAnnotation("Annotation");
		javaClassBuilder.addFieldAnnotation("privateField", "Annotation");
		javaClassBuilder.addFieldAnnotation("privateField", "Annotation1");
		javaClassBuilder.addFieldAnnotation("privateField", "Deprecated");

		javaClassBuilder.build(sourceRoot);

		assertEquals("package com.github.kklisura;\n" +
				"\n" +
				"import com.github.kklisura.annotations.Annotation;\n" +
				"import com.github.kklisura.annotations.Annotation1;\n" +
				"\n" +
				"@Annotation\n" +
				"public class ClassName {\n" +
				"\n" +
				"    @Annotation\n" +
				"    @Annotation1\n" +
				"    @Deprecated\n" +
				"    private String privateField;\n" +
				"}\n", compilationUnitCapture.getValue().toString());

		verifyAll();
	}
}