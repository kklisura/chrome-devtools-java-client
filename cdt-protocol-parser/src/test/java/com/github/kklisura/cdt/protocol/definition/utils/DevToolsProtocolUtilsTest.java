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

import com.github.kklisura.cdt.protocol.definition.DevToolsProtocol;
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
  public static final String JS_PROTOCOL_FIXTURE = "js_protocol.json";
  public static final String BROWSER_PROTOCOL_FIXTURE = "browser_protocol.json";

  @Test
  public void testJsSerializationDeserialization() throws IOException, JSONException {
    String json = readString(getFixture(JS_PROTOCOL_FIXTURE));

    DevToolsProtocol protocol = DevToolsProtocolUtils.readJson(getFixture(JS_PROTOCOL_FIXTURE));
    String output = DevToolsProtocolUtils.writeJson(protocol);

    JSONAssert.assertEquals(json, output, true);
  }

  @Test
  public void testBrowserSerializationDeserialization() throws IOException, JSONException {
    String json = readString(getFixture(BROWSER_PROTOCOL_FIXTURE));

    DevToolsProtocol protocol =
        DevToolsProtocolUtils.readJson(getFixture(BROWSER_PROTOCOL_FIXTURE));
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
