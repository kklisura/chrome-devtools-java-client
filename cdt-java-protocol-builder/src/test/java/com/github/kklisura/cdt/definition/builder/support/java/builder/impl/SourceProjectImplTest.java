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

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.utils.SourceRoot;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Kenan Klisura on 06/02/2018.
 *
 * @author Kenan Klisura
 */
@RunWith(EasyMockRunner.class)
public class SourceProjectImplTest extends EasyMockSupport {

  @Mock private SourceRoot sourceRoot;

  @Mock private Path path;

  private SourceProjectImpl sourceProject;

  @Before
  public void setUp() throws Exception {
    sourceProject = new SourceProjectImpl(sourceRoot);
  }

  @Test
  public void testAddCompilationUnit() {
    CompilationUnit compilationUnit1 = new CompilationUnit();

    expect(path.resolve("com/github/kklisura"))
        .andReturn(Paths.get("source-project/main/com/github/kklisura"));

    expect(sourceRoot.getRoot()).andReturn(path);

    expect(sourceRoot.add(compilationUnit1)).andReturn(sourceRoot);

    replayAll();

    sourceProject.addCompilationUnit("com.github.kklisura", "name", compilationUnit1);

    verifyAll();

    assertTrue(
        compilationUnit1
            .getStorage()
            .get()
            .getPath()
            .endsWith("source-project/main/com/github/kklisura/name.java"));
  }

  @Test(expected = RuntimeException.class)
  public void testAddCompilationUnitThrowsExceptionWhenAddingDuplicateCompilationUnit() {
    CompilationUnit compilationUnit1 = new CompilationUnit();
    CompilationUnit compilationUnit2 = new CompilationUnit();
    compilationUnit2.addClass("TestClass");

    expect(path.resolve("com/github/kklisura"))
        .andReturn(Paths.get("source-project/main/com/github/kklisura"))
        .times(2);

    expect(sourceRoot.getRoot()).andReturn(path).times(2);

    expect(sourceRoot.add(compilationUnit1)).andReturn(sourceRoot);
    expect(sourceRoot.add(compilationUnit2)).andReturn(sourceRoot);

    replayAll();

    sourceProject.addCompilationUnit("com.github.kklisura", "name", compilationUnit1);
    sourceProject.addCompilationUnit("com.github.kklisura", "name", compilationUnit2);
  }

  @Test
  public void testAddCompilationUnitAddingDuplicateCompilationUnit() {
    CompilationUnit compilationUnit1 = new CompilationUnit();

    expect(path.resolve("com/github/kklisura"))
        .andReturn(Paths.get("source-project/main/com/github/kklisura"))
        .times(2);

    expect(sourceRoot.getRoot()).andReturn(path).times(2);

    expect(sourceRoot.add(compilationUnit1)).andReturn(sourceRoot).times(2);

    replayAll();

    sourceProject.addCompilationUnit("com.github.kklisura", "name", compilationUnit1);
    sourceProject.addCompilationUnit("com.github.kklisura", "name", compilationUnit1);

    verifyAll();

    assertTrue(
        compilationUnit1
            .getStorage()
            .get()
            .getPath()
            .endsWith("source-project/main/com/github/kklisura/name.java"));
  }

  @Test
  public void testSaveAll() throws IOException {
    CompilationUnit compilationUnit = new CompilationUnit();
    ClassOrInterfaceDeclaration classDeclaration = compilationUnit.addClass("TestClass");
    classDeclaration.addPrivateField("FieldType", "fieldName").createGetter();

    Path path = Files.createTempDirectory("cdt-test-dir");
    sourceProject = new SourceProjectImpl(path);
    sourceProject.addCompilationUnit("com.github.kklisura", "TestClass", compilationUnit);
    sourceProject.saveAll();

    File file = path.resolve("com/github/kklisura/TestClass.java").toFile();
    assertNotNull(file);

    try {
      FileInputStream fileInputStream = new FileInputStream(file);

      int length;
      byte[] buffer = new byte[1024];
      ByteArrayOutputStream result = new ByteArrayOutputStream();

      while ((length = fileInputStream.read(buffer)) != -1) {
        result.write(buffer, 0, length);
      }

      String outputFile = result.toString("UTF-8");

      assertEquals(
          "public class TestClass {\n"
              + "\n"
              + "  private FieldType fieldName;\n"
              + "\n"
              + "  public FieldType getFieldName() {\n"
              + "    return fieldName;\n"
              + "  }\n"
              + "}\n",
          outputFile);
    } finally {
      file.delete();
    }
  }
}
