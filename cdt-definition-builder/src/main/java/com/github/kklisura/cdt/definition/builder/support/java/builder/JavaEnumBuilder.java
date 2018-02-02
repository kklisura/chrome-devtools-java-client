package com.github.kklisura.cdt.definition.builder.support.java.builder;

/**
 * Java enum type builder.
 *
 * @author Kenan Klisura
 */
public interface JavaEnumBuilder extends Builder {
  /**
   * Adds new enum constant. This constant is in form CONSTANT_NAME("constant_value").
   *
   * @param name Constant name.
   * @param value Constant value.
   */
  void addEnumConstant(String name, String value);

  /**
   * Sets the java doc for this class.
   *
   * @param comment Comment.
   */
  void setJavaDoc(String comment);
}
