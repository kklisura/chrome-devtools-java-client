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

import com.github.javaparser.ast.CompilationUnit;
import com.github.kklisura.cdt.definition.builder.support.java.builder.Builder;
import com.github.kklisura.cdt.definition.builder.support.java.builder.SourceProject;
import java.io.IOException;
import lombok.Getter;

/**
 * Base builder.
 *
 * @author Kenan Klisura
 */
@Getter
public abstract class BaseBuilder implements Builder {
  private CompilationUnit compilationUnit;
  private String packageName;

  /**
   * Instantiates a new base builder given a package name.
   *
   * @param packageName Package name.
   */
  public BaseBuilder(String packageName) {
    this.compilationUnit = new CompilationUnit(packageName);
    this.packageName = packageName;
  }

  /**
   * Returns package name for this builder.
   *
   * @return Package name for this builder.
   */
  protected String getPackageName() {
    return packageName;
  }

  /**
   * Returns a name for this build item.
   *
   * @return Build item name.
   */
  public abstract String getName();

  /**
   * Builds an item. Generates a code.
   *
   * @param sourceProject Root path to save generated code.
   * @throws IOException If saving fails.
   */
  @Override
  public void build(SourceProject sourceProject) throws IOException {
    sourceProject.addCompilationUnit(getPackageName(), getName(), compilationUnit);
  }
}
