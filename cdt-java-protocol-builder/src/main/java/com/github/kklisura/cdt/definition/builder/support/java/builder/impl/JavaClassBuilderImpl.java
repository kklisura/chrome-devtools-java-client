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

import static com.github.kklisura.cdt.definition.builder.support.java.builder.utils.JavadocUtils.INDENTATION_NO_INDENTATION;
import static com.github.kklisura.cdt.definition.builder.support.java.builder.utils.JavadocUtils.INDENTATION_TAB;

import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MarkerAnnotationExpr;
import com.github.javaparser.ast.expr.Name;
import com.github.kklisura.cdt.definition.builder.support.java.builder.JavaClassBuilder;
import com.github.kklisura.cdt.definition.builder.support.java.builder.impl.utils.CompilationUnitUtils;
import com.github.kklisura.cdt.definition.builder.support.java.builder.utils.JavadocUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Java class generator.
 *
 * @author Kenan Klisura
 */
public class JavaClassBuilderImpl extends BaseBuilder implements JavaClassBuilder {
  public static final Logger LOGGER = LoggerFactory.getLogger(JavaClassBuilderImpl.class);

  private static final String DEPRECATED_ANNOTATION = "Deprecated";

  private static final Map<String, String> KEYWORD_TO_FIELD_MAPPING = new HashMap<>();

  // Registers a keyword mapping.
  static {
    registerKeyword("this", "that");
  }

  private String name;
  private ClassOrInterfaceDeclaration declaration;
  private String annotationsPackage;

  private Map<String, String> fieldDescriptions = new HashMap<>();

  /**
   * Instantiates a new class builder implementation.
   *
   * @param packageName Package name.
   * @param name Class name.
   * @param annotationsPackage Package where support annotations are located (Optional,
   *     Experimental...)
   */
  public JavaClassBuilderImpl(String packageName, String name, String annotationsPackage) {
    super(packageName);
    this.name = name;
    this.declaration = getCompilationUnit().addClass(name);
    this.annotationsPackage = annotationsPackage;
  }

  /**
   * Returns a class name.
   *
   * @return Class name.
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Sets the java doc for this class.
   *
   * @param comment Comment.
   */
  @Override
  public void setJavaDoc(String comment) {
    if (StringUtils.isNotEmpty(comment)) {
      declaration.setJavadocComment(
          JavadocUtils.createJavadocComment(comment, INDENTATION_NO_INDENTATION));
    }
  }

  /**
   * Adds the private field of a given type to this class.
   *
   * @param name Field name.
   * @param type Field type.
   */
  @Override
  public void addPrivateField(String name, String type, String description) {
    String fieldName = getFieldName(name);
    declaration.addField(type, fieldName, Modifier.PRIVATE);
    fieldDescriptions.put(fieldName, description);
  }

  /**
   * Adds annotation to field.
   *
   * @param name Field name. Could not be in correct format.
   * @param annotationName Annotation name.
   */
  @Override
  public void addFieldAnnotation(String name, String annotationName) {
    Optional<FieldDeclaration> fieldDeclaration = declaration.getFieldByName(getFieldName(name));
    if (fieldDeclaration.isPresent()) {
      MarkerAnnotationExpr annotationExpr = new MarkerAnnotationExpr();
      annotationExpr.setName(annotationName);
      fieldDeclaration.get().addAnnotation(annotationExpr);

      importAnnotation(annotationName);
    } else {
      throw new RuntimeException("Field " + name + " is not present in current class.");
    }
  }

  @Override
  public void addAnnotation(String annotationName) {
    MarkerAnnotationExpr annotationExpr = new MarkerAnnotationExpr();
    annotationExpr.setName(annotationName);
    declaration.addAnnotation(annotationExpr);

    importAnnotation(annotationName);
  }

  @Override
  public void generateGettersAndSetters() {
    List<FieldDeclaration> fields = declaration.getFields();
    for (FieldDeclaration fieldDeclaration : fields) {
      String fieldName = fieldDeclaration.getVariables().get(0).getNameAsString();

      setMethodJavadoc(fieldName, fieldDeclaration.createGetter());
      setMethodJavadoc(fieldName, fieldDeclaration.createSetter());
    }
  }

  @Override
  public void addImport(String packageName, String object) {
    Name name = new Name();
    name.setQualifier(new Name(packageName));
    name.setIdentifier(object);

    if (!getPackageName().equalsIgnoreCase(packageName)
        && !CompilationUnitUtils.isImported(getCompilationUnit(), name)) {
      getCompilationUnit().addImport(new ImportDeclaration(name, false, false));
    }
  }

  private void setMethodJavadoc(String fieldName, MethodDeclaration methodDeclaration) {
    String description = fieldDescriptions.get(fieldName);
    if (StringUtils.isNotEmpty(description)) {
      methodDeclaration.setJavadocComment(
          JavadocUtils.createJavadocComment(description, INDENTATION_TAB));
    }
  }

  private void importAnnotation(String annotationName) {
    if (!DEPRECATED_ANNOTATION.equals(annotationName)) {
      addImport(annotationsPackage, annotationName);
    }
  }

  /**
   * Returns a field name given an input. This is used to rename field names that are keyword names.
   *
   * @param input Input field name.
   * @return Field name.
   */
  private static String getFieldName(String input) {
    return KEYWORD_TO_FIELD_MAPPING.getOrDefault(input, input);
  }

  /**
   * Registers keyword mapping. This is used to rename a field that belongs to a keyword to some
   * other name (mapping).
   *
   * @param keyword Keyword to be renamed.
   * @param mapping Mapping to be renamed into.
   */
  private static void registerKeyword(String keyword, String mapping) {
    KEYWORD_TO_FIELD_MAPPING.put(keyword, mapping);
  }
}
