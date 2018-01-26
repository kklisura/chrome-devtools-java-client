package com.github.kklisura.cdtp.definition.builder.support.java.builder;

/**
 * Java builder factory.
 *
 * @author Kenan Klisura
 */
public interface JavaBuilderFactory {
  /**
   * Creates a class builder.
   *
   * @param packageName Package name.
   * @param className Class name.
   * @return Java class builder.
   */
  JavaClassBuilder createClassBuilder(String packageName, String className);

  /**
   * Creates a new enum builder.
   *
   * @param packageName Package name.
   * @param enumName Enum name.
   * @return Enum builder.
   */
  JavaEnumBuilder createEnumBuilder(String packageName, String enumName);

  /**
   * Creates a new interface builder.
   *
   * @param packageName Package name.
   * @param interfaceName Interface name.
   */
  JavaInterfaceBuilder createInterfaceBuilder(String packageName, String interfaceName);
}
