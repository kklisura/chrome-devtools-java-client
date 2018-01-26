package com.github.kklisura.cdtp.services.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Configuration utils.
 *
 * @author Kenan Klisura
 */
public final class ConfigurationUtils {
  private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationUtils.class);

  /** Empty ctor. */
  private ConfigurationUtils() {
    // Empty ctor.
  }

  /**
   * Returns name system property or default value if no property name exists or is invalid.
   *
   * @param name Environment name.
   * @param defaultValue Default value.
   * @return Long value.
   */
  public static long systemProperty(String name, long defaultValue) {
    String propertyValue = System.getProperty(name);
    if (propertyValue != null) {
      try {
        return Long.parseLong(propertyValue.trim());
      } catch (NumberFormatException ex) {
        LOGGER.error("Failed parsing {} value.", name, ex);
      }
    }

    return defaultValue;
  }
}
