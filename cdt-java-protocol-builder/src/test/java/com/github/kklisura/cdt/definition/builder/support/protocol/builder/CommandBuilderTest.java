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

import static com.github.kklisura.cdt.definition.builder.support.protocol.builder.CommandBuilder.buildReturnClasses;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.github.kklisura.cdt.definition.builder.support.java.builder.*;
import com.github.kklisura.cdt.definition.builder.support.java.builder.support.CombinedBuilders;
import com.github.kklisura.cdt.definition.builder.support.java.builder.support.MethodParam;
import com.github.kklisura.cdt.definition.builder.support.protocol.builder.support.DomainTypeResolver;
import com.github.kklisura.cdt.protocol.definition.types.Command;
import com.github.kklisura.cdt.protocol.definition.types.Domain;
import com.github.kklisura.cdt.protocol.definition.types.Event;
import com.github.kklisura.cdt.protocol.definition.types.type.ArrayType;
import com.github.kklisura.cdt.protocol.definition.types.type.StringType;
import com.github.kklisura.cdt.protocol.definition.types.type.array.items.StringArrayItem;
import com.github.kklisura.cdt.protocol.definition.types.type.object.ObjectType;
import com.github.kklisura.cdt.protocol.definition.types.type.object.Property;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.*;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.array.items.EnumArrayItem;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.array.items.RefArrayItem;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.easymock.Capture;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Command builder test.
 *
 * @author Kenan Klisura
 */
@RunWith(EasyMockRunner.class)
public class CommandBuilderTest extends EasyMockSupport {

  private static final String BASE_PACKAGE_NAME = "com.github.kklisura.test";
  private static final String TYPE_PACKAGE_NAME = "com.github.kklisura.types";
  private static final String EVENTS_PACKAGE_NAME = "com.github.kklisura.events";
  private static final String SUPPORT_TYPES_PACKAGE_NAME = "com.github.kklisura.support.types";

  @Mock private JavaBuilderFactory javaBuilderFactory;

  @Mock private DomainTypeResolver resolver;

  @Mock private JavaInterfaceBuilder interfaceBuilder;

  @Mock private JavaClassBuilder javaClassBuilder;

  @Mock private JavaEnumBuilder javaEnumBuilder;

  private CommandBuilder commandBuilder;

  @Before
  public void setUp() throws Exception {
    commandBuilder =
        new CommandBuilder(
            BASE_PACKAGE_NAME,
            javaBuilderFactory,
            TYPE_PACKAGE_NAME,
            EVENTS_PACKAGE_NAME,
            SUPPORT_TYPES_PACKAGE_NAME);
  }

  @Test
  public void testBuildEmptyCommand() {
    final Domain domain = new Domain();
    domain.setDomain("domainName");

    expect(javaBuilderFactory.createInterfaceBuilder(BASE_PACKAGE_NAME, "DomainName"))
        .andReturn(interfaceBuilder);

    replayAll();

    assertEquals(interfaceBuilder, commandBuilder.build(domain, resolver));

    verifyAll();
  }

  @Test
  public void testBuildEmptyCommandWithAnnotations() {
    final Domain domain = new Domain();
    domain.setDomain("domainName");
    domain.setDescription("Description");
    domain.setDeprecated(Boolean.TRUE);
    domain.setExperimental(Boolean.TRUE);

    expect(javaBuilderFactory.createInterfaceBuilder(BASE_PACKAGE_NAME, "DomainName"))
        .andReturn(interfaceBuilder);

    interfaceBuilder.setJavaDoc("Description");

    interfaceBuilder.addAnnotation("Deprecated");
    interfaceBuilder.addAnnotation("Experimental");

    replayAll();

    assertEquals(interfaceBuilder, commandBuilder.build(domain, resolver));

    verifyAll();
  }

  @Test
  public void testBuildCommandWithSimpleMethod() {
    final Domain domain = new Domain();
    domain.setDomain("domainName");
    domain.setDescription("Description");

    final Command command1 = new Command();
    command1.setName("command1");
    command1.setDescription("command1 description");
    command1.setDeprecated(Boolean.TRUE);
    command1.setExperimental(Boolean.TRUE);

    final Command command2 = new Command();
    command2.setName("command2");
    command2.setDescription("command2 description");

    final Command command3 = new Command();
    command3.setName("command3");
    command3.setDescription("command3 description");
    command3.setRedirect("Redirect");

    domain.setCommands(Arrays.asList(command1, command2, command3));

    expect(javaBuilderFactory.createInterfaceBuilder(BASE_PACKAGE_NAME, "DomainName"))
        .andReturn(interfaceBuilder);
    interfaceBuilder.setJavaDoc("Description");

    Capture<List<MethodParam>> methodParamCapture1 = Capture.newInstance();
    interfaceBuilder.addMethod(
        eq("command1"), eq("command1 description"), capture(methodParamCapture1), eq(null));

    interfaceBuilder.addMethodAnnotation("command1", "Deprecated");
    interfaceBuilder.addMethodAnnotation("command1", "Experimental");

    Capture<List<MethodParam>> methodParamCapture2 = Capture.newInstance();
    interfaceBuilder.addMethod(
        eq("command2"), eq("command2 description"), capture(methodParamCapture2), eq(null));

    replayAll();

    assertEquals(interfaceBuilder, commandBuilder.build(domain, resolver));

    verifyAll();

    assertTrue(methodParamCapture1.getValue().isEmpty());
    assertTrue(methodParamCapture2.getValue().isEmpty());
  }

  @Test
  public void testBuildCommandWithMethodWithSimpleParams() {
    final Domain domain = new Domain();
    domain.setDomain("domainName");
    domain.setDescription("Description");

    final Command command = new Command();
    command.setName("command");
    command.setDescription("command description");

    final StringProperty stringParam = new StringProperty();
    stringParam.setName("stringParam1");
    stringParam.setDeprecated(Boolean.TRUE);
    stringParam.setDescription("String param 1 description");

    final NumberProperty numberParam = new NumberProperty();
    numberParam.setName("numberParam1");
    numberParam.setExperimental(Boolean.TRUE);
    numberParam.setDescription("Number param 1 description");

    final BooleanProperty booleanReturnParam = new BooleanProperty();
    booleanReturnParam.setName("booleanReturn");

    command.setParameters(Arrays.asList(stringParam, numberParam));
    command.setReturns(Collections.singletonList(booleanReturnParam));

    domain.setCommands(Arrays.asList(command));

    expect(javaBuilderFactory.createInterfaceBuilder(BASE_PACKAGE_NAME, "DomainName"))
        .andReturn(interfaceBuilder);
    interfaceBuilder.setJavaDoc("Description");

    Capture<List<MethodParam>> methodParamCapture = Capture.newInstance();
    Capture<String> methodDescriptionCapture = Capture.newInstance();
    interfaceBuilder.addMethod(
        eq("command"),
        capture(methodDescriptionCapture),
        capture(methodParamCapture),
        eq("Boolean"));

    interfaceBuilder.addParametrizedMethodAnnotation("command", "Returns", "booleanReturn");

    replayAll();

    assertEquals(interfaceBuilder, commandBuilder.build(domain, resolver));

    verifyAll();

    assertEquals(
        "command description\r\n\r\n@param stringParam1 String param 1 description\r\n@param numberParam1 Number param 1 description",
        methodDescriptionCapture.getValue());

    List<MethodParam> params = methodParamCapture.getValue();
    assertEquals(2, params.size());
    Assert.assertEquals(stringParam.getName(), params.get(0).getName());
    assertEquals("String", params.get(0).getType());
    assertEquals("Deprecated", params.get(0).getAnnotations().get(0).getName());
    assertEquals("ParamName", params.get(0).getAnnotations().get(1).getName());
    assertEquals(stringParam.getName(), params.get(0).getAnnotations().get(1).getValue());

    assertEquals(numberParam.getName(), params.get(1).getName());
    assertEquals("Double", params.get(1).getType());
    assertEquals("Experimental", params.get(1).getAnnotations().get(0).getName());
    assertEquals("ParamName", params.get(1).getAnnotations().get(1).getName());
    assertEquals(numberParam.getName(), params.get(1).getAnnotations().get(1).getValue());
  }

  @Test
  public void testBuildCommandWithMethodWithComplexParams()
      throws InstantiationException, IllegalAccessException {
    final Domain domain = new Domain();
    domain.setDomain("domainName");
    domain.setDescription("Description");

    final Command command = new Command();
    command.setName("command");
    command.setDescription("command description");

    final RefProperty refParam = new RefProperty();
    refParam.setName("stringParam1");
    refParam.setDeprecated(Boolean.TRUE);
    refParam.setRef("TestRef");

    final ArrayProperty arrayProperty = new ArrayProperty();
    arrayProperty.setName("enumParam1");
    arrayProperty.setExperimental(Boolean.TRUE);
    arrayProperty.setOptional(Boolean.TRUE);
    arrayProperty.setDescription("enum param 1 description");

    EnumArrayItem enumArrayItem = new EnumArrayItem();
    enumArrayItem.setEnumValues(Arrays.asList("enum1", "enum2"));
    arrayProperty.setItems(enumArrayItem);

    final BooleanProperty booleanReturnParam = new BooleanProperty();
    booleanReturnParam.setName("booleanReturnValue");

    command.setParameters(Arrays.asList(refParam, arrayProperty));
    command.setReturns(Collections.singletonList(booleanReturnParam));

    domain.setCommands(Collections.singletonList(command));

    expect(
            javaBuilderFactory.createEnumBuilder(
                "com.github.kklisura.types.domainname", "EnumParam1"))
        .andReturn(javaEnumBuilder);

    javaEnumBuilder.addEnumConstant("ENUM_1", "enum1");
    javaEnumBuilder.addEnumConstant("ENUM_2", "enum2");

    expect(javaBuilderFactory.createInterfaceBuilder(BASE_PACKAGE_NAME, "DomainName"))
        .andReturn(interfaceBuilder);
    interfaceBuilder.setJavaDoc("Description");

    Capture<String> mandatoryMethodDescriptionCapture = Capture.newInstance();
    Capture<List<MethodParam>> mandatoryMethodParamCapture = Capture.newInstance();
    interfaceBuilder.addMethod(
        eq("command"),
        capture(mandatoryMethodDescriptionCapture),
        capture(mandatoryMethodParamCapture),
        eq("Boolean"));

    Capture<String> allMethodDescriptionCapture = Capture.newInstance();
    Capture<List<MethodParam>> allMethodParamCapture = Capture.newInstance();
    interfaceBuilder.addMethod(
        eq("command"),
        capture(allMethodDescriptionCapture),
        capture(allMethodParamCapture),
        eq("Boolean"));

    final ObjectType resolvedRefType = new ObjectType();
    resolvedRefType.setId("TestRef");
    resolvedRefType.setProperties(
        Collections.singletonList(createProperty(StringProperty.class, "stringPropertyTestRef")));

    expect(resolver.resolve("domainName", "TestRef")).andReturn(resolvedRefType).times(2);

    interfaceBuilder.addImport("com.github.kklisura.types.domainname", "TestRef");
    expectLastCall().times(2);
    interfaceBuilder.addImport("java.util", "List");
    interfaceBuilder.addImport("com.github.kklisura.types.domainname", "EnumParam1");

    interfaceBuilder.addParametrizedMethodAnnotation("command", "Returns", "booleanReturnValue");
    interfaceBuilder.addParametrizedMethodAnnotation("command", "Returns", "booleanReturnValue");

    replayAll();

    Builder build = commandBuilder.build(domain, resolver);

    verifyAll();

    assertTrue(build instanceof CombinedBuilders);

    List<Builder> builderList = ((CombinedBuilders) build).getBuilderList();
    assertEquals(2, builderList.size());

    assertEquals(javaEnumBuilder, builderList.get(0));
    assertEquals(interfaceBuilder, builderList.get(1));

    assertEquals(
        "command description\r\n\r\n@param stringParam1",
        mandatoryMethodDescriptionCapture.getValue());

    assertEquals(
        "command description\r\n\r\n@param stringParam1\r\n@param enumParam1 enum param 1 description",
        allMethodDescriptionCapture.getValue());

    List<MethodParam> mandatoryParams = mandatoryMethodParamCapture.getValue();
    assertEquals(1, mandatoryParams.size());
    assertEquals(refParam.getName(), mandatoryParams.get(0).getName());
    assertEquals("TestRef", mandatoryParams.get(0).getType());
    assertEquals("Deprecated", mandatoryParams.get(0).getAnnotations().get(0).getName());
    assertEquals("ParamName", mandatoryParams.get(0).getAnnotations().get(1).getName());
    assertEquals(refParam.getName(), mandatoryParams.get(0).getAnnotations().get(1).getValue());

    List<MethodParam> allParams = allMethodParamCapture.getValue();
    assertEquals(2, allParams.size());
    assertEquals(refParam.getName(), allParams.get(0).getName());
    assertEquals("TestRef", allParams.get(0).getType());
    assertEquals("Deprecated", allParams.get(0).getAnnotations().get(0).getName());
    assertEquals("ParamName", allParams.get(0).getAnnotations().get(1).getName());
    assertEquals(refParam.getName(), allParams.get(0).getAnnotations().get(1).getValue());

    Assert.assertEquals(arrayProperty.getName(), allParams.get(1).getName());
    assertEquals("List<EnumParam1>", allParams.get(1).getType());
    assertEquals("Experimental", allParams.get(1).getAnnotations().get(0).getName());
    assertEquals("Optional", allParams.get(1).getAnnotations().get(1).getName());
    assertEquals("ParamName", allParams.get(1).getAnnotations().get(2).getName());
    assertEquals(arrayProperty.getName(), allParams.get(1).getAnnotations().get(2).getValue());
  }

  @Test
  public void testBuildCommandWithMethodWithArrayStringParam() {
    final Domain domain = new Domain();
    domain.setDomain("domainName");
    domain.setDescription("Description");

    final Command command = new Command();
    command.setName("command");
    command.setDescription("command description");

    final ArrayProperty arrayProperty = new ArrayProperty();
    arrayProperty.setName("enumParam1");
    arrayProperty.setOptional(Boolean.FALSE);
    arrayProperty.setDescription("enum param 1 description");

    RefArrayItem enumArrayItem = new RefArrayItem();
    enumArrayItem.setRef("SomeRefType");
    arrayProperty.setItems(enumArrayItem);
    command.setParameters(Collections.singletonList(arrayProperty));

    domain.setCommands(Collections.singletonList(command));

    expect(javaBuilderFactory.createInterfaceBuilder(BASE_PACKAGE_NAME, "DomainName"))
        .andReturn(interfaceBuilder);
    interfaceBuilder.setJavaDoc("Description");

    Capture<List<MethodParam>> methodParamCapture = Capture.newInstance();
    interfaceBuilder.addMethod(eq("command"), anyString(), capture(methodParamCapture), eq(null));

    final ArrayType resolvedRefType = new ArrayType();
    resolvedRefType.setId("arrayRefType");

    final StringArrayItem stringArrayItem = new StringArrayItem();
    resolvedRefType.setItems(stringArrayItem);

    expect(resolver.resolve("domainName", "SomeRefType")).andReturn(resolvedRefType);

    interfaceBuilder.addImport("java.util", "List");
    expectLastCall().times(2);

    replayAll();

    Builder build = commandBuilder.build(domain, resolver);

    verifyAll();

    assertTrue(build instanceof JavaInterfaceBuilder);
    assertEquals(interfaceBuilder, build);

    List<MethodParam> params = methodParamCapture.getValue();
    assertEquals(1, params.size());

    Assert.assertEquals(arrayProperty.getName(), params.get(0).getName());
    assertEquals("List<List<String>>", params.get(0).getType());
    assertEquals("ParamName", params.get(0).getAnnotations().get(0).getName());
    assertEquals(arrayProperty.getName(), params.get(0).getAnnotations().get(0).getValue());
  }

  @Test
  public void testBuildCommandWithMethodWithArrayRefParam() {
    final Domain domain = new Domain();
    domain.setDomain("domainName");
    domain.setDescription("Description");

    final Command command = new Command();
    command.setName("command");
    command.setDescription("command description");

    final ArrayProperty arrayProperty = new ArrayProperty();
    arrayProperty.setName("enumParam1");
    arrayProperty.setOptional(Boolean.FALSE);
    arrayProperty.setDescription("enum param 1 description");

    RefArrayItem enumArrayItem = new RefArrayItem();
    enumArrayItem.setRef("SomeRefType");
    arrayProperty.setItems(enumArrayItem);
    command.setParameters(Collections.singletonList(arrayProperty));

    domain.setCommands(Collections.singletonList(command));

    expect(javaBuilderFactory.createInterfaceBuilder(BASE_PACKAGE_NAME, "DomainName"))
        .andReturn(interfaceBuilder);
    interfaceBuilder.setJavaDoc("Description");

    Capture<List<MethodParam>> methodParamCapture = Capture.newInstance();
    interfaceBuilder.addMethod(eq("command"), anyString(), capture(methodParamCapture), eq(null));

    final ArrayType resolvedRefType = new ArrayType();
    resolvedRefType.setId("arrayRefType");

    final com.github.kklisura.cdt.protocol.definition.types.type.array.items.RefArrayItem
        refArrayItem =
            new com.github.kklisura.cdt.protocol.definition.types.type.array.items.RefArrayItem();
    refArrayItem.setRef("TestRefArrayItem");
    resolvedRefType.setItems(refArrayItem);

    final StringType stringType = new StringType();
    stringType.setId("stringType");

    expect(resolver.resolve("domainName", "SomeRefType")).andReturn(resolvedRefType);
    expect(resolver.resolve("domainName", "TestRefArrayItem")).andReturn(stringType);

    interfaceBuilder.addImport("java.util", "List");
    expectLastCall().times(2);

    replayAll();

    Builder build = commandBuilder.build(domain, resolver);

    verifyAll();

    assertTrue(build instanceof JavaInterfaceBuilder);
    assertEquals(interfaceBuilder, build);

    List<MethodParam> params = methodParamCapture.getValue();
    assertEquals(1, params.size());

    Assert.assertEquals(arrayProperty.getName(), params.get(0).getName());
    assertEquals("List<List<String>>", params.get(0).getType());
    assertEquals("ParamName", params.get(0).getAnnotations().get(0).getName());
    assertEquals(arrayProperty.getName(), params.get(0).getAnnotations().get(0).getValue());
  }

  @Test
  public void testBuildCommandWithMethodWithDeepArrayRefParam() {
    final Domain domain = new Domain();
    domain.setDomain("domainName");
    domain.setDescription("Description");

    final Command command = new Command();
    command.setName("command");
    command.setDescription("command description");

    final ArrayProperty arrayProperty = new ArrayProperty();
    arrayProperty.setName("enumParam1");
    arrayProperty.setOptional(Boolean.FALSE);
    arrayProperty.setDescription("enum param 1 description");

    RefArrayItem enumArrayItem = new RefArrayItem();
    enumArrayItem.setRef("SomeRefType");
    arrayProperty.setItems(enumArrayItem);
    command.setParameters(Collections.singletonList(arrayProperty));

    domain.setCommands(Collections.singletonList(command));

    expect(javaBuilderFactory.createInterfaceBuilder(BASE_PACKAGE_NAME, "DomainName"))
        .andReturn(interfaceBuilder);
    interfaceBuilder.setJavaDoc("Description");

    Capture<List<MethodParam>> methodParamCapture = Capture.newInstance();
    interfaceBuilder.addMethod(eq("command"), anyString(), capture(methodParamCapture), eq(null));

    final ArrayType resolvedRefType = new ArrayType();
    resolvedRefType.setId("arrayRefType");

    final com.github.kklisura.cdt.protocol.definition.types.type.array.items.RefArrayItem
        refArrayItem =
            new com.github.kklisura.cdt.protocol.definition.types.type.array.items.RefArrayItem();
    refArrayItem.setRef("TestRefArrayItem");
    resolvedRefType.setItems(refArrayItem);

    final ArrayType resolvedRefType2 = new ArrayType();
    resolvedRefType2.setId("arrayRefType2");

    final com.github.kklisura.cdt.protocol.definition.types.type.array.items.RefArrayItem
        refArrayItem2 =
            new com.github.kklisura.cdt.protocol.definition.types.type.array.items.RefArrayItem();
    refArrayItem2.setRef("SomeOtherDomain.TestRefArrayItem2");
    resolvedRefType2.setItems(refArrayItem2);

    final ObjectType objectType = new ObjectType();
    objectType.setId("objectType");
    final StringProperty stringProperty = new StringProperty();
    stringProperty.setName("stringProperty");
    objectType.setProperties(Collections.singletonList(stringProperty));

    expect(resolver.resolve("domainName", "SomeRefType")).andReturn(resolvedRefType);
    expect(resolver.resolve("domainName", "TestRefArrayItem")).andReturn(resolvedRefType2);
    expect(resolver.resolve("SomeOtherDomain", "TestRefArrayItem2")).andReturn(objectType);

    interfaceBuilder.addImport("com.github.kklisura.types.someotherdomain", "TestRefArrayItem2");

    interfaceBuilder.addImport("java.util", "List");
    expectLastCall().times(3);

    replayAll();

    Builder build = commandBuilder.build(domain, resolver);

    verifyAll();

    assertTrue(build instanceof JavaInterfaceBuilder);
    assertEquals(interfaceBuilder, build);

    List<MethodParam> params = methodParamCapture.getValue();
    assertEquals(1, params.size());

    Assert.assertEquals(arrayProperty.getName(), params.get(0).getName());
    assertEquals("List<List<List<TestRefArrayItem2>>>", params.get(0).getType());
    assertEquals("ParamName", params.get(0).getAnnotations().get(0).getName());
    assertEquals(arrayProperty.getName(), params.get(0).getAnnotations().get(0).getValue());
  }

  @Test
  public void testBuildCommandWithMethodWithComplexReturnParams() {
    final Domain domain = new Domain();
    domain.setDomain("domainName");
    domain.setDescription("Description");

    final Command command = new Command();
    command.setName("getCommandSomeResult");
    command.setDescription("command description");

    final StringProperty stringParam = new StringProperty();
    stringParam.setName("stringParam1");
    stringParam.setDeprecated(Boolean.TRUE);

    final NumberProperty numberParam = new NumberProperty();
    numberParam.setName("numberParam1");
    numberParam.setExperimental(Boolean.TRUE);

    command.setReturns(Arrays.asList(stringParam, numberParam));
    domain.setCommands(Collections.singletonList(command));

    expect(
            javaBuilderFactory.createClassBuilder(
                TYPE_PACKAGE_NAME + ".domainname", "CommandSomeResult"))
        .andReturn(javaClassBuilder);

    javaClassBuilder.addPrivateField("stringParam1", "String", null);
    javaClassBuilder.addPrivateField("numberParam1", "Double", null);

    javaClassBuilder.addFieldAnnotation("stringParam1", "Deprecated");
    javaClassBuilder.addFieldAnnotation("numberParam1", "Experimental");

    javaClassBuilder.generateGettersAndSetters();

    expect(javaBuilderFactory.createInterfaceBuilder(BASE_PACKAGE_NAME, "DomainName"))
        .andReturn(interfaceBuilder);
    interfaceBuilder.setJavaDoc("Description");

    Capture<List<MethodParam>> methodParamCapture = Capture.newInstance();
    interfaceBuilder.addMethod(
        eq("getCommandSomeResult"),
        eq("command description"),
        capture(methodParamCapture),
        eq("CommandSomeResult"));

    interfaceBuilder.addImport("com.github.kklisura.types.domainname", "CommandSomeResult");

    replayAll();

    Builder build = commandBuilder.build(domain, resolver);

    verifyAll();

    assertTrue(build instanceof CombinedBuilders);

    List<Builder> builderList = ((CombinedBuilders) build).getBuilderList();
    assertEquals(2, builderList.size());

    assertEquals(javaClassBuilder, builderList.get(0));
    assertEquals(interfaceBuilder, builderList.get(1));

    assertTrue(methodParamCapture.getValue().isEmpty());
  }

  @Test
  public void testBuildCommandWithMethodWithStringTypedReturnParams() {
    final Domain domain = new Domain();
    domain.setDomain("domainName");

    final Command command = new Command();
    command.setName("getStringList");
    command.setDescription("command description");

    final ArrayProperty arrayStringProperty = new ArrayProperty();
    arrayStringProperty.setName("arrayStringProperty");
    arrayStringProperty.setItems(
        new com.github.kklisura.cdt.protocol.definition.types.type.object.properties.array.items
            .StringArrayItem());

    command.setReturns(Collections.singletonList(arrayStringProperty));
    domain.setCommands(Collections.singletonList(command));

    expect(javaBuilderFactory.createInterfaceBuilder(BASE_PACKAGE_NAME, "DomainName"))
        .andReturn(interfaceBuilder);

    interfaceBuilder.addImport("java.util", "List");

    interfaceBuilder.addMethod(
        eq("getStringList"),
        eq("command description"),
        eq(Collections.emptyList()),
        eq("List<String>"));

    interfaceBuilder.addParametrizedMethodAnnotation(
        eq("getStringList"), eq("Returns"), eq("arrayStringProperty"));

    interfaceBuilder.addParametrizedMethodAnnotation(
        eq("getStringList"), eq("ReturnTypeParameter"), eq("{String.class}"));

    replayAll();

    Builder build = commandBuilder.build(domain, resolver);

    verifyAll();

    assertTrue(build instanceof JavaInterfaceBuilder);
    assertEquals(interfaceBuilder, build);
  }

  @Test
  public void testBuildCommandWithMethodWithStringArrayTypedReturnParams() {
    final Domain domain = new Domain();
    domain.setDomain("domainName");

    final Command command = new Command();
    command.setName("getStringList");
    command.setDescription("command description");

    final RefArrayItem refArrayItem = new RefArrayItem();
    refArrayItem.setType("RefItemType");
    refArrayItem.setRef("RefItem");

    final ArrayProperty arrayOfArraysProperty = new ArrayProperty();
    arrayOfArraysProperty.setName("arrayOfArraysProperty");
    arrayOfArraysProperty.setItems(refArrayItem);

    command.setReturns(Collections.singletonList(arrayOfArraysProperty));
    domain.setCommands(Collections.singletonList(command));

    final ArrayType refArrayItemType = new ArrayType();
    refArrayItemType.setId("RefItem");
    refArrayItemType.setItems(new StringArrayItem());

    expect(resolver.resolve("domainName", "RefItem")).andReturn(refArrayItemType);

    expect(javaBuilderFactory.createInterfaceBuilder(BASE_PACKAGE_NAME, "DomainName"))
        .andReturn(interfaceBuilder);

    interfaceBuilder.addImport("java.util", "List");
    expectLastCall().times(2);

    interfaceBuilder.addMethod(
        eq("getStringList"),
        eq("command description"),
        eq(Collections.emptyList()),
        eq("List<List<String>>"));

    interfaceBuilder.addParametrizedMethodAnnotation(
        eq("getStringList"), eq("Returns"), eq("arrayOfArraysProperty"));

    interfaceBuilder.addParametrizedMethodAnnotation(
        eq("getStringList"), eq("ReturnTypeParameter"), eq("{List.class,String.class}"));

    replayAll();

    Builder build = commandBuilder.build(domain, resolver);

    verifyAll();

    assertTrue(build instanceof JavaInterfaceBuilder);
    assertEquals(interfaceBuilder, build);
  }

  @Test
  public void testBuildCommandWithMethodWithRefTypedReturnParams() {
    final Domain domain = new Domain();
    domain.setDomain("domainName");

    final Command command = new Command();
    command.setName("getMetricsList");
    command.setDescription("command description");

    final ArrayProperty arrayStringProperty = new ArrayProperty();
    arrayStringProperty.setName("arrayMetricsProperty");

    final RefArrayItem refArrayItem = new RefArrayItem();
    refArrayItem.setRef("Metrics");
    refArrayItem.setType("Metrics");
    arrayStringProperty.setItems(refArrayItem);

    command.setReturns(Collections.singletonList(arrayStringProperty));
    domain.setCommands(Collections.singletonList(command));

    final ObjectType resolvedRefType = new ObjectType();
    resolvedRefType.setId("Metrics");
    final StringProperty resolvedRefTypeStringProperty = new StringProperty();
    resolvedRefTypeStringProperty.setName("arrayMetricsProperty");
    resolvedRefType.setProperties(Collections.singletonList(resolvedRefTypeStringProperty));

    expect(javaBuilderFactory.createInterfaceBuilder(BASE_PACKAGE_NAME, "DomainName"))
        .andReturn(interfaceBuilder);

    expect(resolver.resolve("domainName", "Metrics")).andReturn(resolvedRefType);

    interfaceBuilder.addImport("java.util", "List");
    interfaceBuilder.addImport("com.github.kklisura.types.domainname", "Metrics");

    interfaceBuilder.addMethod(
        eq("getMetricsList"),
        eq("command description"),
        eq(Collections.emptyList()),
        eq("List<Metrics>"));

    interfaceBuilder.addParametrizedMethodAnnotation(
        eq("getMetricsList"), eq("Returns"), eq("arrayMetricsProperty"));

    interfaceBuilder.addParametrizedMethodAnnotation(
        eq("getMetricsList"), eq("ReturnTypeParameter"), eq("{Metrics.class}"));

    replayAll();

    Builder build = commandBuilder.build(domain, resolver);

    verifyAll();

    assertTrue(build instanceof JavaInterfaceBuilder);
    assertEquals(interfaceBuilder, build);
  }

  @Test
  public void testBuildCommandWithMethodWithEnumTypedReturnParams() {
    final Domain domain = new Domain();
    domain.setDomain("domainName");

    final Command command = new Command();
    command.setName("getEnumList");
    command.setDescription("command description");

    final ArrayProperty arrayStringProperty = new ArrayProperty();
    arrayStringProperty.setName("enumListProperty");

    final EnumArrayItem enumArrayItem = new EnumArrayItem();
    enumArrayItem.setEnumValues(Arrays.asList("A", "B"));
    arrayStringProperty.setItems(enumArrayItem);

    command.setReturns(Collections.singletonList(arrayStringProperty));
    domain.setCommands(Collections.singletonList(command));

    expect(
            javaBuilderFactory.createEnumBuilder(
                "com.github.kklisura.types.domainname", "EnumListProperty"))
        .andReturn(javaEnumBuilder);
    javaEnumBuilder.addEnumConstant("A", "A");
    javaEnumBuilder.addEnumConstant("B", "B");

    expect(javaBuilderFactory.createInterfaceBuilder(BASE_PACKAGE_NAME, "DomainName"))
        .andReturn(interfaceBuilder);

    interfaceBuilder.addImport("java.util", "List");
    interfaceBuilder.addImport("com.github.kklisura.types.domainname", "EnumListProperty");

    interfaceBuilder.addMethod(
        eq("getEnumList"),
        eq("command description"),
        eq(Collections.emptyList()),
        eq("List<EnumListProperty>"));

    interfaceBuilder.addParametrizedMethodAnnotation(
        eq("getEnumList"), eq("Returns"), eq("enumListProperty"));

    interfaceBuilder.addParametrizedMethodAnnotation(
        eq("getEnumList"), eq("ReturnTypeParameter"), eq("{EnumListProperty.class}"));

    replayAll();

    Builder build = commandBuilder.build(domain, resolver);

    verifyAll();

    assertTrue(build instanceof CombinedBuilders);
  }

  @Test
  public void testBuildCommandWithEvents() {
    final Domain domain = new Domain();
    domain.setDomain("domainName");
    domain.setDescription("Description");

    final Event event = new Event();
    event.setName("someEvent");
    event.setDescription("event description");

    final Event event1 = new Event();
    event1.setName("someEvent1");
    event1.setDescription("event description");
    event1.setDeprecated(Boolean.TRUE);
    event1.setExperimental(Boolean.TRUE);

    domain.setEvents(Arrays.asList(event, event1));

    expect(javaBuilderFactory.createInterfaceBuilder(BASE_PACKAGE_NAME, "DomainName"))
        .andReturn(interfaceBuilder);
    interfaceBuilder.setJavaDoc("Description");

    interfaceBuilder.addImport("com.github.kklisura.support.types", "EventListener");
    interfaceBuilder.addImport("com.github.kklisura.support.types", "EventHandler");
    interfaceBuilder.addImport("com.github.kklisura.events.domainname", "SomeEvent");

    interfaceBuilder.addImport("com.github.kklisura.support.types", "EventListener");
    interfaceBuilder.addImport("com.github.kklisura.support.types", "EventHandler");
    interfaceBuilder.addImport("com.github.kklisura.events.domainname", "SomeEvent1");

    Capture<List<MethodParam>> methodParamCapture = Capture.newInstance();
    interfaceBuilder.addMethod(
        eq("onSomeEvent"),
        eq("event description"),
        capture(methodParamCapture),
        eq("EventListener"));

    Capture<List<MethodParam>> methodParamCapture1 = Capture.newInstance();
    interfaceBuilder.addMethod(
        eq("onSomeEvent1"),
        eq("event description"),
        capture(methodParamCapture1),
        eq("EventListener"));

    interfaceBuilder.addParametrizedMethodAnnotation("onSomeEvent", "EventName", "someEvent");
    interfaceBuilder.addParametrizedMethodAnnotation("onSomeEvent1", "EventName", "someEvent1");

    interfaceBuilder.addMethodAnnotation("onSomeEvent1", "Deprecated");
    interfaceBuilder.addMethodAnnotation("onSomeEvent1", "Experimental");

    replayAll();

    Builder build = commandBuilder.build(domain, resolver);

    verifyAll();

    assertEquals(interfaceBuilder, build);

    List<MethodParam> value = methodParamCapture.getValue();
    assertEquals(1, value.size());
    assertEquals("eventListener", value.get(0).getName());
    assertEquals("EventHandler<SomeEvent>", value.get(0).getType());

    List<MethodParam> value1 = methodParamCapture1.getValue();
    assertEquals(1, value1.size());
    assertEquals("eventListener", value1.get(0).getName());
    assertEquals("EventHandler<SomeEvent1>", value1.get(0).getType());
  }

  @Test
  public void testBuildReturnClasses() {
    assertEquals("{Test.class}", buildReturnClasses("Test"));
    assertEquals("{List.class,Test.class}", buildReturnClasses("List<Test>"));
    assertEquals("{List.class,List.class,Test.class}", buildReturnClasses("List<List<Test>>"));
  }

  private <T extends Property> T createProperty(Class<T> clazz, String name)
      throws IllegalAccessException, InstantiationException {
    T property = clazz.newInstance();
    property.setName(name);
    property.setDescription(name + "Description");
    return property;
  }
}
