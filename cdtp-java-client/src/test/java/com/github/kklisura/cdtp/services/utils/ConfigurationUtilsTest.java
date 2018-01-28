package com.github.kklisura.cdtp.services.utils;

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
    System.setProperty(propertyName, "invalid-value");
    assertEquals(10, ConfigurationUtils.systemProperty(propertyName, 10));
    System.setProperty(propertyName, "123");
    assertEquals(123, ConfigurationUtils.systemProperty(propertyName, 10));
  }
}
