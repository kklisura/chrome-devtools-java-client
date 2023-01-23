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

import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.SingleMemberAnnotationExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.kklisura.cdt.definition.builder.support.java.builder.JavaEnumBuilder;
import com.github.kklisura.cdt.definition.builder.support.java.builder.utils.JavadocUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Java enum builder implementation.
 *
 * @author Kenan Klisura
 */
public class JavaEnumBuilderImpl extends BaseBuilder implements JavaEnumBuilder {
  private static final String JSON_PROPERTY = "JsonProperty";
  private static final String JSON_PROPERTY_PACKAGE = "com.fasterxml.jackson.annotation";

  private EnumDeclaration declaration;

  /**
   * Instantiates new java enum builder.
   *
   * @param packageName Package name.
   * @param name Enum name.
   */
  public JavaEnumBuilderImpl(String packageName, String name) {
    super(packageName);
    declaration = getCompilationUnit().addEnum(name);
    declaration.setName(name);

    Name jsonPropertyName = new Name();
    jsonPropertyName.setQualifier(new Name(JSON_PROPERTY_PACKAGE));
    jsonPropertyName.setIdentifier(JSON_PROPERTY);
    getCompilationUnit().addImport(new ImportDeclaration(jsonPropertyName, false, false));
  }

  /**
   * Adds new enum constant.
   *
   * @param name Constant name.
   * @param value Real constant value.
   */
  public void addEnumConstant(String name, String value) {
    EnumConstantDeclaration enumConstantDeclaration = new EnumConstantDeclaration(name);

    SingleMemberAnnotationExpr jsonPropertyAnnotation = new SingleMemberAnnotationExpr();
    jsonPropertyAnnotation.setName(JSON_PROPERTY);
    jsonPropertyAnnotation.setMemberValue(new StringLiteralExpr(value));
    enumConstantDeclaration.addAnnotation(jsonPropertyAnnotation);

    declaration.addEntry(enumConstantDeclaration);
  }

  /**
   * Sets the java doc comment for this enum.
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
   * Return enum name.
   *
   * @return Enum name.
   */
  @Override
  public String getName() {
    return declaration.getNameAsString();
  }
}
