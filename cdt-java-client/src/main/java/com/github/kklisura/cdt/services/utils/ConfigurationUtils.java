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

  /**
   * Returns name system property or default value if no property name exists or is invalid.
   *
   * @param name Environment name.
   * @param defaultValue Default value.
   * @return String value.
   */
  public static String systemProperty(String name, String defaultValue) {
    String propertyValue = System.getProperty(name);
    if (propertyValue != null && !propertyValue.trim().isEmpty()) {
      return propertyValue.trim();
    }

    return defaultValue;
  }
}
