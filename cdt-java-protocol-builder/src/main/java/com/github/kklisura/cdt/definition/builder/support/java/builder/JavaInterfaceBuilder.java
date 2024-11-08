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

import com.github.kklisura.cdt.definition.builder.support.java.builder.support.MethodParam;
import java.util.List;

/**
 * Java interface builder.
 *
 * @author Kenan Klisura
 */
public interface JavaInterfaceBuilder extends JavaImportAwareBuilder {
  /**
   * Sets the java doc for this interface.
   *
   * @param comment Comment.
   */
  void setJavaDoc(String comment);

  /**
   * Adds annotation to this interface.
   *
   * @param annotationName Annotation name.
   */
  void addAnnotation(String annotationName);

  /**
   * Adds annotation to a method.
   *
   * @param methodName Method name.
   * @param annotationName Annotation name.
   */
  void addMethodAnnotation(String methodName, String annotationName);

  /**
   * Adds parametrized annotation to a method. Ie. @AnnotationName(parameter) void methodName() {
   * ... }
   *
   * @param methodName Method name.
   * @param annotationName Annotation name.
   * @param parameter Parameter.
   */
  void addParametrizedMethodAnnotation(String methodName, String annotationName, String parameter);

  /**
   * Adds a method to this interface.
   *
   * @param name Method name.
   * @param description Method description.
   * @param methodParams List of method params.
   * @param returnType Return parameter.
   */
  void addMethod(
      String name, String description, List<MethodParam> methodParams, String returnType);
}
