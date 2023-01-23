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

import static com.github.javaparser.utils.CodeGenerationUtils.packageToPath;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.printer.PrettyPrinter;
import com.github.javaparser.printer.PrettyPrinterConfiguration;
import com.github.javaparser.utils.SourceRoot;
import com.github.kklisura.cdt.definition.builder.support.java.builder.SourceProject;
import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;
import com.google.googlejavaformat.java.JavaFormatterOptions;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Source project implementation.
 *
 * @author Kenan Klisura
 */
public class SourceProjectImpl implements SourceProject {
  private SourceRoot sourceRoot;

  private Map<String, CompilationUnit> compilationUnitCache;

  public SourceProjectImpl(Path outputLocation) {
    this(new SourceRoot(outputLocation));
  }

  public SourceProjectImpl(SourceRoot sourceRoot) {
    this.sourceRoot = sourceRoot;
    this.compilationUnitCache = new HashMap<>();
  }

  @Override
  public void addCompilationUnit(String packageName, String name, CompilationUnit compilationUnit) {
    Path path = sourceRoot.getRoot().resolve(packageToPath(packageName));
    compilationUnit.setStorage(path.resolve(name + ".java"));

    String compilationCacheKey = packageName + "." + name;

    if (compilationUnitCache.containsKey(compilationCacheKey)) {
      CompilationUnit alreadyAdded = compilationUnitCache.get(compilationCacheKey);

      if (!alreadyAdded.equals(compilationUnit)) {
        throw new RuntimeException(
            "Duplicate interface/class/enum found on " + compilationCacheKey);
      }
    }
    compilationUnitCache.put(compilationCacheKey, compilationUnit);

    sourceRoot.add(compilationUnit);
  }

  @Override
  public void saveAll() {
    PrettyPrinterConfiguration prettyPrinterConfiguration = new PrettyPrinterConfiguration();
    prettyPrinterConfiguration.setIndent("\t");
    prettyPrinterConfiguration.setPrintComments(true);
    prettyPrinterConfiguration.setPrintJavadoc(true);
    prettyPrinterConfiguration.setOrderImports(true);

    PrettyPrinter prettyPrinter = new PrettyPrinter(prettyPrinterConfiguration);

    JavaFormatterOptions javaFormatterOptions =
        JavaFormatterOptions.builder().style(JavaFormatterOptions.Style.GOOGLE).build();

    sourceRoot.setPrinter(googleCodeFormatter(javaFormatterOptions, prettyPrinter::print));
    sourceRoot.saveAll(sourceRoot.getRoot());
  }

  private static Function<CompilationUnit, String> googleCodeFormatter(
      JavaFormatterOptions options, Function<CompilationUnit, String> printer) {
    Formatter formatter = new Formatter(options);
    return compilationUnit -> {
      String source = printer.apply(compilationUnit);
      try {
        return formatter.formatSourceAndFixImports(source);
      } catch (FormatterException e) {
        throw new RuntimeException("Failed formatting source.", e);
      }
    };
  }
}
