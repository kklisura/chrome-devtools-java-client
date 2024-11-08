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

/**
 * Java class builder.
 *
 * @author Kenan Klisura
 */
public interface JavaClassBuilder extends JavaImportAwareBuilder {
  /**
   * Sets the java doc for this class.
   *
   * @param comment Comment.
   */
  void setJavaDoc(String comment);

  /**
   * Adds a private field to this class with a given type.
   *
   * @param name Field name. Could not be in correct format.
   * @param type Field type.
   * @param description Field description.
   */
  void addPrivateField(String name, String type, String description);

  /**
   * Adds annotation to specified field.
   *
   * @param name Field name. Could not be in correct format.
   * @param annotationName Annotation name.
   */
  void addFieldAnnotation(String name, String annotationName);

  /**
   * Adds annotation to this class.
   *
   * @param annotationName Annotation name.
   */
  void addAnnotation(String annotationName);

  /** Generates getters and setters for all fields. */
  void generateGettersAndSetters();
}
