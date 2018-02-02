package com.github.kklisura.cdt.definition.builder.support.java.builder.impl;

import static org.easymock.EasyMock.capture;
import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.utils.SourceRoot;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import org.easymock.Capture;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

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

  @Mock private SourceRoot sourceRoot;

  private Path rootPath;

  @Before
  public void setUp() throws Exception {
    rootPath = new File("/tmp/test-class-builder").toPath();
    javaEnumBuilder = new JavaEnumBuilderImpl(PACKAGE, NAME);
  }

  @Test
  public void testBasicEnum() throws IOException {
    Capture<CompilationUnit> compilationUnitCapture = Capture.newInstance();

    expect(sourceRoot.getRoot()).andReturn(rootPath);
    expect(sourceRoot.add(capture(compilationUnitCapture))).andReturn(sourceRoot);

    javaEnumBuilder.setJavaDoc("");

    replayAll();

    javaEnumBuilder.build(sourceRoot);

    assertEquals(
        "package com.github.kklisura;\n"
            + "\n"
            + "import com.fasterxml.jackson.annotation.JsonProperty;\n"
            + "\n"
            + "public enum EnumName {\n"
            + "}\n",
        compilationUnitCapture.getValue().toString());

    verifyAll();
  }

  @Test
  public void testBasicEnumWithJavadocAndConstant() throws IOException {
    Capture<CompilationUnit> compilationUnitCapture = Capture.newInstance();

    expect(sourceRoot.getRoot()).andReturn(rootPath);
    expect(sourceRoot.add(capture(compilationUnitCapture))).andReturn(sourceRoot);

    javaEnumBuilder.addEnumConstant("ENUM_CONSTANT", "enumConstant");
    javaEnumBuilder.setJavaDoc("Java doc.");

    replayAll();

    javaEnumBuilder.build(sourceRoot);

    assertEquals(
        "package com.github.kklisura;\n"
            + "\n"
            + "import com.fasterxml.jackson.annotation.JsonProperty;\n"
            + "\n"
            + "/**\n"
            + " * Java doc.\n"
            + " */\n"
            + "public enum EnumName {\n"
            + "\n"
            + "    @JsonProperty(\"enumConstant\")\n"
            + "    ENUM_CONSTANT\n"
            + "}\n",
        compilationUnitCapture.getValue().toString());

    verifyAll();
  }
}
