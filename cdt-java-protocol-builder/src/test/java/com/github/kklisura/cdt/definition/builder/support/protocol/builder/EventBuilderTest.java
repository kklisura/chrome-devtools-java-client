package com.github.kklisura.cdt.definition.builder.support.protocol.builder;

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

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.github.kklisura.cdt.definition.builder.support.java.builder.Builder;
import com.github.kklisura.cdt.definition.builder.support.java.builder.JavaBuilderFactory;
import com.github.kklisura.cdt.definition.builder.support.java.builder.JavaClassBuilder;
import com.github.kklisura.cdt.definition.builder.support.utils.DomainUtils;
import com.github.kklisura.cdt.protocol.definition.DevToolsProtocol;
import com.github.kklisura.cdt.protocol.definition.types.Domain;
import com.github.kklisura.cdt.protocol.definition.types.Event;
import com.github.kklisura.cdt.protocol.definition.types.type.object.ObjectType;
import com.github.kklisura.cdt.protocol.definition.types.type.object.Property;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.RefProperty;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.StringProperty;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Event builder test.
 *
 * @author Kenan Klisura
 */
@RunWith(EasyMockRunner.class)
public class EventBuilderTest extends EasyMockSupport {
  private static final String BASE_PACKAGE_NAME = "com.github.kklisura.events";
  private static final String TYPES_PACKAGE_NAME = "com.github.kklisura.types";

  @Mock private JavaBuilderFactory javaBuilderFactory;

  @Mock private JavaClassBuilder javaClassBuilder;

  private EventBuilder eventBuilder;

  @Before
  public void setUp() throws Exception {
    eventBuilder = new EventBuilder(BASE_PACKAGE_NAME, javaBuilderFactory, TYPES_PACKAGE_NAME);
  }

  @Test
  public void testBuildOnEmptyEvents() {
    final DevToolsProtocol devToolsProtocol = new DevToolsProtocol();
    final Domain domain = new Domain();
    assertTrue(
        eventBuilder
            .build(domain, DomainUtils.devToolsProtocolResolver(devToolsProtocol))
            .isEmpty());
  }

  @Test
  public void testBuild() throws InstantiationException, IllegalAccessException {
    final DevToolsProtocol devToolsProtocol = new DevToolsProtocol();
    final Domain domain1 = new Domain();
    domain1.setDomain("Domain1");
    domain1.setDescription("Domain1 description");

    final Domain domain2 = new Domain();
    domain2.setDomain("Domain2");
    domain2.setDescription("Domain2 description");

    final ObjectType refObjectProperty = new ObjectType();
    refObjectProperty.setId("Domain2RefProperty");
    refObjectProperty.setProperties(
        java.util.Collections.singletonList(
            createProperty(StringProperty.class, "stringDomain2RefProperty")));
    domain2.setTypes(java.util.Collections.singletonList(refObjectProperty));

    final StringProperty stringProperty = new StringProperty();
    stringProperty.setName("stringProperty");
    stringProperty.setExperimental(Boolean.TRUE);
    stringProperty.setDescription("String property description.");

    final RefProperty refProperty = new RefProperty();
    refProperty.setName("refProperty");
    refProperty.setRef("Domain2.Domain2RefProperty");
    refProperty.setOptional(Boolean.TRUE);

    final Event event = new Event();
    event.setName("eventName");
    event.setDescription("Event description.");
    event.setExperimental(Boolean.TRUE);
    event.setDeprecated(Boolean.TRUE);
    event.setParameters(Arrays.asList(stringProperty, refProperty));

    domain1.setEvents(Collections.singletonList(event));
    devToolsProtocol.setDomains(Arrays.asList(domain1, domain2));

    expect(javaBuilderFactory.createClassBuilder(BASE_PACKAGE_NAME + ".domain1", "EventName"))
        .andStubReturn(javaClassBuilder);

    javaClassBuilder.setJavaDoc("Event description.");
    javaClassBuilder.addAnnotation("Experimental");
    javaClassBuilder.addAnnotation("Deprecated");

    javaClassBuilder.addImport(TYPES_PACKAGE_NAME + ".domain2", "Domain2RefProperty");

    javaClassBuilder.addPrivateField("stringProperty", "String", "String property description.");
    javaClassBuilder.addFieldAnnotation("stringProperty", "Experimental");

    javaClassBuilder.addPrivateField("refProperty", "Domain2RefProperty", null);
    javaClassBuilder.addFieldAnnotation("refProperty", "Optional");

    javaClassBuilder.generateGettersAndSetters();

    replayAll();

    List<Builder> build =
        eventBuilder.build(domain1, DomainUtils.devToolsProtocolResolver(devToolsProtocol));

    verifyAll();

    assertEquals(1, build.size());
    assertEquals(javaClassBuilder, build.get(0));
  }

  private <T extends Property> T createProperty(Class<T> clazz, String name)
      throws IllegalAccessException, InstantiationException {
    T property = clazz.newInstance();
    property.setName(name);
    property.setDescription(name + "Description");
    return property;
  }
}
