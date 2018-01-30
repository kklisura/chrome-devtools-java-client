package com.github.kklisura.cdtp.definition.builder.support.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * String utils test.
 *
 * @author Kenan Klisura
 */
public class StringUtilsTest {
  @Test
  public void testToEnumConstant() {
    assertEquals("ENUM_CONSTANT", StringUtils.toEnumConstant("enum_constant"));
    assertEquals("ENUM_CONSTANT", StringUtils.toEnumConstant("enumConstant"));
    assertEquals("ENUM_CONSTANT", StringUtils.toEnumConstant("Enum-Constant"));
    assertEquals("ENUM_CONSTANT_123", StringUtils.toEnumConstant("Enum-Constant123"));
    assertEquals("DOM_CONSTANT", StringUtils.toEnumConstant("DOMConstant"));
    assertEquals("DOM_CONSTANT", StringUtils.toEnumConstant("DOM*Constant"));
    assertEquals("NAN", StringUtils.toEnumConstant("NaN"));
    assertEquals("NEGATIVE_INFINITY", StringUtils.toEnumConstant("-Infinity"));
    assertEquals("NEGATIVE_0", StringUtils.toEnumConstant("-0"));
  }

  @Test
  public void testBuildPackageName() {
    assertEquals("com.github.kklisura", StringUtils.buildPackageName("com.github", "kklisura"));
  }

  @Test
  public void testCapitalize() {
    assertEquals("TestCapitalization", StringUtils.capitalize("testCapitalization"));
  }

  @Test
  public void testGetReturnTypeFromGetter() {
    assertEquals("Test", StringUtils.getReturnTypeFromGetter("test"));
    assertEquals("Test", StringUtils.getReturnTypeFromGetter("getTest"));
    assertEquals("Test", StringUtils.getReturnTypeFromGetter("gettest"));
  }
}
