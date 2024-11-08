package com.github.kklisura.cdt.protocol.definition.utils;

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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kklisura.cdt.protocol.definition.DevToolsProtocol;
import java.io.IOException;
import java.io.InputStream;
import lombok.experimental.UtilityClass;

/**
 * Dev tools protocol utils methods.
 *
 * @author Kenan Klisura
 */
@UtilityClass
public class DevToolsProtocolUtils {
  private static final ObjectMapper OBJECT_MAPPER =
      new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

  /**
   * Deserializes dev tools protocol from string value.
   *
   * @param value Json string value.
   * @return DevToolsProtocol
   * @throws IOException If any serialization exception occurs.
   */
  public static DevToolsProtocol readJson(String value) throws IOException {
    return OBJECT_MAPPER.readerFor(DevToolsProtocol.class).readValue(value);
  }

  /**
   * Deserializes dev tools protocol from input stream.
   *
   * @param inputStream Input stream.
   * @return DevToolsProtocol
   * @throws IOException If any deserialization exception occurs.
   */
  public static DevToolsProtocol readJson(InputStream inputStream) throws IOException {
    return OBJECT_MAPPER.readerFor(DevToolsProtocol.class).readValue(inputStream);
  }

  /**
   * Serializes protocol as json string.
   *
   * @param protocol Protocol object.
   * @return Json string.
   * @throws IOException If any serialization exceptions occur.
   */
  public static String writeJson(DevToolsProtocol protocol) throws IOException {
    return OBJECT_MAPPER.writeValueAsString(protocol);
  }
}
