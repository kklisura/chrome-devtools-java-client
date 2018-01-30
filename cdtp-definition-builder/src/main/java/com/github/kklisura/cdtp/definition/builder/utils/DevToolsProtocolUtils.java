package com.github.kklisura.cdtp.definition.builder.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kklisura.cdtp.definition.builder.protocol.DevToolsProtocol;
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
