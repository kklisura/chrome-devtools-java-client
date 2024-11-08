package com.github.kklisura.cdt.services.utils;

/*-
 * #%L
 * cdt-java-client
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
 * Configuration utils test.
 *
 * @author Kenan Klisura
 */
public class ConfigurationUtilsTest {
  @Test
  public void testSystemProperty() {
    final String propertyName = "testSystemProperty";
    assertEquals(10, ConfigurationUtils.systemProperty(propertyName, 10));
    assertEquals("10", ConfigurationUtils.systemProperty(propertyName, "10"));
    System.setProperty(propertyName, "invalid-value");
    assertEquals(10, ConfigurationUtils.systemProperty(propertyName, 10));
    System.setProperty(propertyName, "123");
    assertEquals(123, ConfigurationUtils.systemProperty(propertyName, 10));
    assertEquals("123", ConfigurationUtils.systemProperty(propertyName, "10"));
    System.setProperty(propertyName, "");
    assertEquals("10", ConfigurationUtils.systemProperty(propertyName, "10"));
    System.setProperty(propertyName, "      ");
    assertEquals("10", ConfigurationUtils.systemProperty(propertyName, "10"));
    System.setProperty(propertyName, "   123  ");
    assertEquals("123", ConfigurationUtils.systemProperty(propertyName, "10"));
  }
}
