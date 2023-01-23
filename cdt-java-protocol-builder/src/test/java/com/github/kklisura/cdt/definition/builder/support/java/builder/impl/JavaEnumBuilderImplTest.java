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
 * Java enum builder impl test.
 *
 * @author Kenan Klisura
 */
@RunWith(EasyMockRunner.class)
public class JavaEnumBuilderImplTest extends EasyMockSupport {
  private static final String PACKAGE = "com.github.kklisura";
  private static final String NAME = "EnumName";

  private JavaEnumBuilderImpl javaEnumBuilder;

  @Mock private SourceProject sourceProject;

  private Path rootPath;

  @Before
  public void setUp() throws Exception {
    rootPath = new File("/tmp/test-class-builder").toPath();
    javaEnumBuilder = new JavaEnumBuilderImpl(PACKAGE, NAME);
  }

  @Test
  public void testBasicEnum() throws IOException {
    Capture<CompilationUnit> compilationUnitCapture = Capture.newInstance();

    sourceProject.addCompilationUnit(eq(PACKAGE), eq(NAME), capture(compilationUnitCapture));

    javaEnumBuilder.setJavaDoc("");

    replayAll();

    javaEnumBuilder.build(sourceProject);

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

    sourceProject.addCompilationUnit(eq(PACKAGE), eq(NAME), capture(compilationUnitCapture));

    javaEnumBuilder.addEnumConstant("ENUM_CONSTANT", "enumConstant");
    javaEnumBuilder.setJavaDoc("Java doc.");

    replayAll();

    javaEnumBuilder.build(sourceProject);

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
