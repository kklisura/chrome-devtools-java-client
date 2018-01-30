package com.github.kklisura.cdtp.definition.builder.support.java.builder;

import com.github.kklisura.cdtp.definition.builder.support.java.builder.support.MethodParam;
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
