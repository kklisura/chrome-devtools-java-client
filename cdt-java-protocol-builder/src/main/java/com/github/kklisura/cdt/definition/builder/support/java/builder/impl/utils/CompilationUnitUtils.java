package com.github.kklisura.cdt.definition.builder.support.java.builder.impl.utils;

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
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.Name;
import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.CollectionUtils;

/**
 * Compilation unit utility class.
 *
 * @author Kenan Klisura
 */
@UtilityClass
public class CompilationUnitUtils {
  /**
   * Checks if given name is already imported on compilation unit.
   *
   * @param compilationUnit Compilation unit.
   * @param name Name to check for.
   * @return True if name is already imported.
   */
  public static boolean isImported(CompilationUnit compilationUnit, Name name) {
    NodeList<ImportDeclaration> imports = compilationUnit.getImports();
    if (CollectionUtils.isNotEmpty(imports)) {
      for (ImportDeclaration importDeclaration : imports) {
        if (name.equals(importDeclaration.getName())) {
          return true;
        }
      }
    }

    return false;
  }
}
