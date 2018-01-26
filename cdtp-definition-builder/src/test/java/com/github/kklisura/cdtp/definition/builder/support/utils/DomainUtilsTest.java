package com.github.kklisura.cdtp.definition.builder.support.utils;

import com.github.kklisura.cdtp.definition.builder.protocol.DevToolsProtocol;
import com.github.kklisura.cdtp.definition.builder.protocol.types.Domain;
import com.github.kklisura.cdtp.definition.builder.protocol.types.Type;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.StringType;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

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
