package com.github.kklisura.cdt.definition.builder.support.java.builder.utils;

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

import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.javadoc.Javadoc;
import com.github.javaparser.javadoc.description.JavadocDescription;
import com.github.javaparser.javadoc.description.JavadocSnippet;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

/**
 * Javadoc utils.
 *
 * @author Kenan Klisura
 */
@UtilityClass
public class JavadocUtils {
  public static String INDENTATION_SPACE = " ";
  public static String INDENTATION_NO_INDENTATION = "";
  public static String INDENTATION_TAB = "\t";

  /**
   * Creates a javadoc comment object given a comment string.
   *
   * @param comment Comment.
   * @param indentation Indentation.
   * @return Javadoc comment object.
   */
  public JavadocComment createJavadocComment(String comment, String indentation) {
    JavadocSnippet javadocSnippet = new JavadocSnippet("");
    if (StringUtils.isNotEmpty(comment)) {
      javadocSnippet = new JavadocSnippet(comment);
    }

    JavadocDescription description = new JavadocDescription();
    description.addElement(javadocSnippet);
    Javadoc javadoc = new Javadoc(description);
    return javadoc.toComment(indentation);
  }
}
