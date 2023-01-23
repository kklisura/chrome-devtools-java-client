package com.github.kklisura.cdt.definition.builder.support.java.builder.support;

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

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Method param.
 *
 * @author Kenan Klisura
 */
@Getter
@Setter
public class MethodParam {
  private String type;

  private String name;

  private List<Annotation> annotations;

  /** Method param annotation. Supports both Marker and simple string argument annotation. */
  public static class Annotation {
    private String name;
    private String value;

    /**
     * Instantiates a new Method param annotation.
     *
     * @param name Annotation name.
     * @param value Annotation value for simple string argument annotation.
     */
    public Annotation(String name, String value) {
      this.name = name;
      this.value = value;
    }

    /**
     * Instantiates a new Method param annotation.
     *
     * @param name Annotation name.
     */
    public Annotation(String name) {
      this.name = name;
    }

    /**
     * Gets name.
     *
     * @return Annotation name.
     */
    public String getName() {
      return name;
    }

    /**
     * Gets annotation value.
     *
     * @return Annotation value.
     */
    public String getValue() {
      return value;
    }
  }
}
