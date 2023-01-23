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
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.kklisura.cdt.definition.builder.support.java.builder.JavaInterfaceBuilder;
import com.github.kklisura.cdt.definition.builder.support.java.builder.impl.utils.CompilationUnitUtils;
import com.github.kklisura.cdt.definition.builder.support.java.builder.support.MethodParam;
import com.github.kklisura.cdt.definition.builder.support.java.builder.utils.JavadocUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Java interface builder.
 *
 * @author Kenan Klisura
 */
public class JavaInterfaceBuilderImpl extends BaseBuilder implements JavaInterfaceBuilder {
  private static final String DEPRECATED_ANNOTATION = "Deprecated";

  private static final Pattern SINGLE_CLASS = Pattern.compile("\\s*\\S+\\.class\\s*");
  private static final Pattern CLASS_LIST =
      Pattern.compile("\\{(\\s*\\S+\\.class)(\\s*,\\s*\\S+\\.class)*\\s*}");
  private static final Pattern CLASS_LIST_CONTENT = Pattern.compile("\\{(.*)}");

  private String name;
  private ClassOrInterfaceDeclaration declaration;
  private String annotationsPackage;

  private static final Logger LOGGER = LoggerFactory.getLogger("TestLogger");

  public static void main(String[] args) {
    final String name = "test";
    final RuntimeException e = new RuntimeException("Test exception");

    LOGGER.error("Error while processing event " + name, e);
    LOGGER.error("Error while processing event {}", name, e);
  }

  /**
   * Instantiates a new class builder implementation.
   *
   * @param packageName Package name.
   * @param name Interface name.
   * @param annotationsPackage Package where support annotations are located (Optional,
   *     Experimental...)
   */
  public JavaInterfaceBuilderImpl(String packageName, String name, String annotationsPackage) {
    super(packageName);
    this.name = name;
    this.declaration = getCompilationUnit().addInterface(name);
    this.annotationsPackage = annotationsPackage;
  }

  @Override
  public void setJavaDoc(String comment) {
    if (StringUtils.isNotEmpty(comment)) {
      declaration.setJavadocComment(
          JavadocUtils.createJavadocComment(comment, INDENTATION_NO_INDENTATION));
    }
  }

  @Override
  public void addAnnotation(String annotationName) {
    Optional<AnnotationExpr> annotation = declaration.getAnnotationByName(annotationName);
    if (!annotation.isPresent()) {
      MarkerAnnotationExpr annotationExpr = new MarkerAnnotationExpr();
      annotationExpr.setName(annotationName);
      declaration.addAnnotation(annotationExpr);

      importAnnotation(annotationName);
    }
  }

  @Override
  public void addMethodAnnotation(String methodName, String annotationName) {
    List<MethodDeclaration> methods = declaration.getMethodsByName(methodName);
    for (MethodDeclaration methodDeclaration : methods) {
      Optional<AnnotationExpr> annotation = methodDeclaration.getAnnotationByName(annotationName);
      if (!annotation.isPresent()) {
        methodDeclaration.addMarkerAnnotation(annotationName);
      }
    }

    importAnnotation(annotationName);
  }

  @Override
  public void addParametrizedMethodAnnotation(
      String methodName, String annotationName, String parameter) {
    List<MethodDeclaration> methods = declaration.getMethodsByName(methodName);
    for (MethodDeclaration methodDeclaration : methods) {
      Optional<AnnotationExpr> annotation = methodDeclaration.getAnnotationByName(annotationName);
      if (!annotation.isPresent()) {

        if (isClassList(parameter)) {
          final List<String> classList = getClassList(parameter);

          if (classList.size() == 1) {
            methodDeclaration.addSingleMemberAnnotation(
                annotationName,
                new ClassExpr(new ClassOrInterfaceType(getClassName(classList.get(0)))));
          } else {
            final List<Expression> nodes =
                classList.stream()
                    .map(clazz -> new ClassExpr(new ClassOrInterfaceType(getClassName(clazz))))
                    .collect(Collectors.toList());

            methodDeclaration.addSingleMemberAnnotation(
                annotationName, new ArrayInitializerExpr(new NodeList<>(nodes)));
          }
        } else {
          methodDeclaration.addSingleMemberAnnotation(
              annotationName, new StringLiteralExpr(parameter));
        }
      }
    }

    importAnnotation(annotationName);
  }

  @Override
  public String getName() {
    return name;
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

  @Override
  public void addMethod(
      String name, String description, List<MethodParam> methodParams, String returnType) {
    MethodDeclaration methodDeclaration = declaration.addMethod(name);
    methodDeclaration.setBody(null);

    if (StringUtils.isNotEmpty(description)) {
      methodDeclaration.setJavadocComment(
          JavadocUtils.createJavadocComment(description, INDENTATION_TAB));
    }

    if (StringUtils.isNotEmpty(returnType)) {
      methodDeclaration.setType(returnType);
    }

    if (CollectionUtils.isNotEmpty(methodParams)) {
      for (MethodParam methodParam : methodParams) {
        Parameter parameter =
            methodDeclaration.addAndGetParameter(methodParam.getType(), methodParam.getName());

        if (CollectionUtils.isNotEmpty(methodParam.getAnnotations())) {
          for (MethodParam.Annotation annotation : methodParam.getAnnotations()) {
            Optional<AnnotationExpr> currentAnnotation =
                parameter.getAnnotationByName(annotation.getName());
            if (!currentAnnotation.isPresent()) {
              if (StringUtils.isNotEmpty(annotation.getValue())) {
                parameter.addSingleMemberAnnotation(
                    annotation.getName(), new StringLiteralExpr(annotation.getValue()));
              } else {
                parameter.addMarkerAnnotation(annotation.getName());
              }

              if (!DEPRECATED_ANNOTATION.equals(annotation.getName())) {
                addImport(annotationsPackage, annotation.getName());
              }
            }
          }
        }
      }
    }
  }

  private void importAnnotation(String annotationName) {
    if (!DEPRECATED_ANNOTATION.equals(annotationName)) {
      addImport(annotationsPackage, annotationName);
    }
  }

  /**
   * Given a class property accessor ie TestClass.class, return a class name ie TestClass.
   *
   * @param classPropertyAccessor Class property accessor string.
   * @return Class name.
   */
  private static String getClassName(String classPropertyAccessor) {
    final int i = classPropertyAccessor.indexOf(".class");
    return classPropertyAccessor.substring(0, i);
  }

  static boolean isClassList(String value) {
    if (SINGLE_CLASS.matcher(value).matches()) {
      return true;
    }

    return CLASS_LIST.matcher(value).matches();
  }

  static List<String> getClassList(String value) {
    final List<String> result = new ArrayList<>();

    if (isClassList(value)) {
      if (SINGLE_CLASS.matcher(value).matches()) {
        result.add(value.trim());
        return result;
      }

      final Matcher matcher = CLASS_LIST_CONTENT.matcher(value);
      if (matcher.matches()) {
        final String content = matcher.group(1);

        final String[] items = content.trim().split(",");

        for (int i = 0; i < items.length; i++) {
          result.add(items[i].trim());
        }
      }
    }

    return result;
  }
}
