package com.github.kklisura.cdt.definition.builder.support.java.builder.impl;

/*-
 * #%L
 * cdt-java-protocol-builder
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

import static org.easymock.EasyMock.capture;
import static org.easymock.EasyMock.eq;
import static org.junit.Assert.assertEquals;

import com.github.javaparser.ast.CompilationUnit;
import com.github.kklisura.cdt.definition.builder.support.java.builder.SourceProject;
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
 * Java class builder test.
 *
 * @author Kenan Klisura
 */
@RunWith(EasyMockRunner.class)
public class JavaClassBuilderImplTest extends EasyMockSupport {
  private static final String PACKAGE_NAME = "com.github.kklisura";
  private static final String CLASS_NAME = "ClassName";
  private static final String ANNOTATIONS_PACKAGE_NAME = "com.github.kklisura.annotations";

  @Mock private SourceProject sourceProject;

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

    sourceProject.addCompilationUnit(
        eq(PACKAGE_NAME), eq(CLASS_NAME), capture(compilationUnitCapture));

    javaClassBuilder.setJavaDoc("");

    replayAll();

    javaClassBuilder.build(sourceProject);

    assertEquals(
        "package com.github.kklisura;\n" + "\n" + "public class ClassName {\n" + "}\n" + "",
        compilationUnitCapture.getValue().toString());

    verifyAll();
  }

  @Test
  public void testBasicClassWithAnnotation() throws IOException {
    Capture<CompilationUnit> compilationUnitCapture = Capture.newInstance();

    sourceProject.addCompilationUnit(
        eq(PACKAGE_NAME), eq(CLASS_NAME), capture(compilationUnitCapture));

    javaClassBuilder.addAnnotation("Annotation");
    javaClassBuilder.addAnnotation("Deprecated");

    replayAll();

    javaClassBuilder.build(sourceProject);

    assertEquals(
        "package com.github.kklisura;\n"
            + "\n"
            + "import com.github.kklisura.annotations.Annotation;\n"
            + "\n"
            + "@Annotation\n"
            + "@Deprecated\n"
            + "public class ClassName {\n"
            + "}\n"
            + "",
        compilationUnitCapture.getValue().toString());

    verifyAll();
  }

  @Test
  public void testSetJavadoc() throws IOException {
    Capture<CompilationUnit> compilationUnitCapture = Capture.newInstance();

    sourceProject.addCompilationUnit(
        eq(PACKAGE_NAME), eq(CLASS_NAME), capture(compilationUnitCapture));

    javaClassBuilder.setJavaDoc("Java doc.");

    replayAll();

    javaClassBuilder.build(sourceProject);

    assertEquals(
        "package com.github.kklisura;\n"
            + "\n"
            + "/**\n"
            + " * Java doc.\n"
            + " */\n"
            + "public class ClassName {\n"
            + "}\n",
        compilationUnitCapture.getValue().toString());

    verifyAll();
  }

  @Test
  public void testAddingImports() throws IOException {
    Capture<CompilationUnit> compilationUnitCapture = Capture.newInstance();

    sourceProject.addCompilationUnit(
        eq(PACKAGE_NAME), eq(CLASS_NAME), capture(compilationUnitCapture));

    replayAll();

    javaClassBuilder.addImport("java.util", "List");
    javaClassBuilder.addImport("java.util", "List");
    javaClassBuilder.addImport("java.util", "List");

    javaClassBuilder.build(sourceProject);

    assertEquals(
        "package com.github.kklisura;\n\n"
            + "import java.util.List;\n"
            + "\n"
            + "public class ClassName {\n"
            + "}\n"
            + "",
        compilationUnitCapture.getValue().toString());

    verifyAll();
  }

  @Test
  public void testAddingImportsOnSamePackage() throws IOException {
    Capture<CompilationUnit> compilationUnitCapture = Capture.newInstance();

    sourceProject.addCompilationUnit(
        eq(PACKAGE_NAME), eq(CLASS_NAME), capture(compilationUnitCapture));

    replayAll();

    javaClassBuilder.addImport(PACKAGE_NAME, "Test");
    javaClassBuilder.addImport("java.util", "List");

    javaClassBuilder.build(sourceProject);

    assertEquals(
        "package com.github.kklisura;\n\n"
            + "import java.util.List;\n"
            + "\n"
            + "public class ClassName {\n"
            + "}\n"
            + "",
        compilationUnitCapture.getValue().toString());

    verifyAll();
  }

  @Test
  public void testGenerateGettersAndSetters() throws IOException {
    Capture<CompilationUnit> compilationUnitCapture = Capture.newInstance();

    sourceProject.addCompilationUnit(
        eq(PACKAGE_NAME), eq(CLASS_NAME), capture(compilationUnitCapture));

    replayAll();

    javaClassBuilder.addPrivateField("privateField", "String", "Private field description");

    javaClassBuilder.generateGettersAndSetters();

    javaClassBuilder.build(sourceProject);

    assertEquals(
        "package com.github.kklisura;\n"
            + "\n"
            + "public class ClassName {\n"
            + "\n"
            + "    private String privateField;\n"
            + "\n"
            + "    /**\n"
            + "     * Private field description\n"
            + "     */\n"
            + "    public String getPrivateField() {\n"
            + "        return privateField;\n"
            + "    }\n"
            + "\n"
            + "    /**\n"
            + "     * Private field description\n"
            + "     */\n"
            + "    public void setPrivateField(String privateField) {\n"
            + "        this.privateField = privateField;\n"
            + "    }\n"
            + "}\n",
        compilationUnitCapture.getValue().toString());

    verifyAll();
  }

  @Test
  public void testGenerateFieldAnnotation() throws IOException {
    Capture<CompilationUnit> compilationUnitCapture = Capture.newInstance();

    sourceProject.addCompilationUnit(
        eq(PACKAGE_NAME), eq(CLASS_NAME), capture(compilationUnitCapture));

    replayAll();

    javaClassBuilder.addPrivateField("privateField", "String", "Private field description");

    javaClassBuilder.addAnnotation("Annotation");
    javaClassBuilder.addFieldAnnotation("privateField", "Annotation");
    javaClassBuilder.addFieldAnnotation("privateField", "Annotation1");
    javaClassBuilder.addFieldAnnotation("privateField", "Deprecated");

    javaClassBuilder.build(sourceProject);

    assertEquals(
        "package com.github.kklisura;\n"
            + "\n"
            + "import com.github.kklisura.annotations.Annotation;\n"
            + "import com.github.kklisura.annotations.Annotation1;\n"
            + "\n"
            + "@Annotation\n"
            + "public class ClassName {\n"
            + "\n"
            + "    @Annotation\n"
            + "    @Annotation1\n"
            + "    @Deprecated\n"
            + "    private String privateField;\n"
            + "}\n",
        compilationUnitCapture.getValue().toString());

    verifyAll();
  }
}
