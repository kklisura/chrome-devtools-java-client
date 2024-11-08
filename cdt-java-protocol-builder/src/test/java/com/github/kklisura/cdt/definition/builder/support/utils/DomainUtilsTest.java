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

import com.github.kklisura.cdt.protocol.definition.DevToolsProtocol;
import com.github.kklisura.cdt.protocol.definition.types.Domain;
import com.github.kklisura.cdt.protocol.definition.types.Type;
import com.github.kklisura.cdt.protocol.definition.types.type.StringType;
import java.util.Arrays;
import java.util.Collections;
import org.junit.Test;

/**
 * Domain type utils test.
 *
 * @author Kenan Klisura
 */
public class DomainUtilsTest {
  @Test(expected = RuntimeException.class)
  public void testDevToolsProtocolResolverOnNonExistingDomain() {
    DevToolsProtocol devToolsProtocol = new DevToolsProtocol();
    devToolsProtocol.setDomains(Collections.emptyList());
    DomainUtils.devToolsProtocolResolver(devToolsProtocol).resolve("domain", "test");
  }

  @Test(expected = RuntimeException.class)
  public void testDevToolsProtocolResolverOnNonExistingType() {
    Type type1 = new StringType();
    type1.setId("test-type");
    Type type2 = new StringType();
    type2.setId("test-type");
    Domain domain = new Domain();
    domain.setDomain("domain");
    domain.setTypes(Arrays.asList(type1, type2));
    DevToolsProtocol devToolsProtocol = new DevToolsProtocol();
    devToolsProtocol.setDomains(Collections.singletonList(domain));
    DomainUtils.devToolsProtocolResolver(devToolsProtocol).resolve("domain", "test");
  }

  @Test
  public void testDevToolsProtocolResolver() {
    Type type = new StringType();
    type.setId("test");
    Domain domain = new Domain();
    domain.setDomain("domain");
    domain.setTypes(Collections.singletonList(type));
    DevToolsProtocol devToolsProtocol = new DevToolsProtocol();
    devToolsProtocol.setDomains(Collections.singletonList(domain));

    Type resolvedType =
        DomainUtils.devToolsProtocolResolver(devToolsProtocol).resolve("domain", "test");

    assertEquals(type, resolvedType);
  }
}
