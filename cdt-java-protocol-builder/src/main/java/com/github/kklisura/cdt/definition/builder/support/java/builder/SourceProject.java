package com.github.kklisura.cdt.definition.builder.support.java.builder;

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

import com.github.javaparser.ast.CompilationUnit;

/**
 * Source project.
 *
 * @author Kenan Klisura
 */
public interface SourceProject {
  /**
   * Adds a compilation unit to source-project.
   *
   * @param packageName Compilation unit package name.
   * @param name Name.
   * @param compilationUnit Compilation unit.
   */
  void addCompilationUnit(String packageName, String name, CompilationUnit compilationUnit);

  /** Saves all compilation units. */
  void saveAll();
}
