package com.github.kklisura.cdtp.definition.builder.utils;

import com.github.kklisura.cdtp.definition.builder.protocol.DevToolsProtocol;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

/**
 * DevToolsProtocolUtils test.
 *
 * @author Kenan Klisura
 */
public class DevToolsProtocolUtilsTest {
  public static final String PROTOCOL_FIXTURE = "protocol.json";

  @Test
  public void testSerializationDeserialization() throws IOException, JSONException {
    String json = readString(getFixture(PROTOCOL_FIXTURE));

    DevToolsProtocol protocol = DevToolsProtocolUtils.readJson(getFixture(PROTOCOL_FIXTURE));
    String output = DevToolsProtocolUtils.writeJson(protocol);

    JSONAssert.assertEquals(json, output, true);
  }

  private static InputStream getFixture(String resource) {
    return DevToolsProtocolUtilsTest.class.getClassLoader().getResourceAsStream(resource);
  }

  private static String readString(InputStream inputStream) throws IOException {
    ByteArrayOutputStream buf = new ByteArrayOutputStream();
    int result = inputStream.read();
    while (result != -1) {
      buf.write((byte) result);
      result = inputStream.read();
    }

    return buf.toString("UTF-8");
  }
}
