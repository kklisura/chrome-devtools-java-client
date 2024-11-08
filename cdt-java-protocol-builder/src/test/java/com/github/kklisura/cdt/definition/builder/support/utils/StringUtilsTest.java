package com.github.kklisura.cdt.definition.builder.support.utils;

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
