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
import com.github.kklisura.cdt.definition.builder.support.java.builder.JavaEnumBuilder;
import com.github.kklisura.cdt.definition.builder.support.java.builder.support.CombinedBuilders;
import com.github.kklisura.cdt.definition.builder.support.protocol.builder.support.DomainTypeResolver;
import com.github.kklisura.cdt.protocol.definition.types.Domain;
import com.github.kklisura.cdt.protocol.definition.types.Type;
import com.github.kklisura.cdt.protocol.definition.types.type.ArrayType;
import com.github.kklisura.cdt.protocol.definition.types.type.EnumType;
import com.github.kklisura.cdt.protocol.definition.types.type.NumberType;
import com.github.kklisura.cdt.protocol.definition.types.type.StringType;
import com.github.kklisura.cdt.protocol.definition.types.type.object.ObjectType;
import com.github.kklisura.cdt.protocol.definition.types.type.object.Property;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.AnyProperty;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.ArrayProperty;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.BooleanProperty;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.EnumProperty;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.IntegerProperty;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.NumberProperty;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.ObjectProperty;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.RefProperty;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.StringProperty;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.array.ArrayItem;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.array.items.AnyArrayItem;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.array.items.EnumArrayItem;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.array.items.IntegerArrayItem;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.array.items.NumberArrayItem;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.array.items.ObjectArrayItem;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.array.items.RefArrayItem;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.array.items.StringArrayItem;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Kenan Klisura on 02/01/2018.
 *
 * @author Kenan Klisura
 */
@RunWith(EasyMockRunner.class)
public class TypesBuilderTest extends EasyMockSupport {
  private static final String BASE_PACKAGE_NAME = "my.test.package";

  @Mock private JavaBuilderFactory javaBuilderFactory;

  @Mock private JavaEnumBuilder javaEnumBuilder1;

  @Mock private JavaEnumBuilder javaEnumBuilder2;

  @Mock private JavaClassBuilder javaClassBuilder1;

  @Mock private JavaClassBuilder javaClassBuilder2;

  @Mock private DomainTypeResolver resolver;

  private TypesBuilder builder;

  @Before
  public void setUp() throws Exception {
    builder = new TypesBuilder(BASE_PACKAGE_NAME, javaBuilderFactory);
  }

  @Test
  public void testTypesGeneratorGeneratesCorrectTypesOnEmptyDomain() {
    Domain domain = new Domain();
    assertTrue(builder.build(domain, TypesBuilder.NULL_DOMAIN_TYPE_RESOLVER).isEmpty());
  }

  @Test
  public void testTypesGeneratorGeneratesCorrectTypesOnEnumType() {
    Domain domain = new Domain();
    domain.setDomain("domain-name");
    domain.setTypes(
        Arrays.asList(
            new StringType(),
            createEnumType("someEnumType1", "Description1", null),
            createEnumType("someEnumType2", "Description2", Arrays.asList("val1", "Val2"))));

    expect(javaBuilderFactory.createEnumBuilder("my.test.package.domain-name", "SomeEnumType1"))
        .andReturn(javaEnumBuilder1);
    javaEnumBuilder1.setJavaDoc("Description1");

    expect(javaBuilderFactory.createEnumBuilder("my.test.package.domain-name", "SomeEnumType2"))
        .andReturn(javaEnumBuilder2);
    javaEnumBuilder2.setJavaDoc("Description2");
    javaEnumBuilder2.addEnumConstant("VAL_1", "val1");
    javaEnumBuilder2.addEnumConstant("VAL_2", "Val2");

    replayAll();

    List<Builder> builderList = builder.build(domain, TypesBuilder.NULL_DOMAIN_TYPE_RESOLVER);

    verifyAll();

    assertEquals(2, builderList.size());
    assertEquals(javaEnumBuilder1, builderList.get(0));
    assertEquals(javaEnumBuilder2, builderList.get(1));
  }

  @Test
  public void testTypesGeneratorGeneratesCorrectTypesOnClassTypeWithoutProperties() {
    Domain domain = new Domain();
    domain.setDomain("domain-name");
    domain.setTypes(
        Arrays.asList(
            createObjectType("someObjectType1", "Description1"),
            createObjectType("someObjectType2", "Description2")));

    // Set first type to experimental
    domain.getTypes().get(0).setExperimental(Boolean.TRUE);

    // Set second to deprecated
    domain.getTypes().get(1).setDeprecated(Boolean.TRUE);

    expect(javaBuilderFactory.createClassBuilder("my.test.package.domain-name", "SomeObjectType1"))
        .andReturn(javaClassBuilder1);
    javaClassBuilder1.setJavaDoc("Description1");
    javaClassBuilder1.addAnnotation("Experimental");
    javaClassBuilder1.generateGettersAndSetters();

    expect(javaBuilderFactory.createClassBuilder("my.test.package.domain-name", "SomeObjectType2"))
        .andReturn(javaClassBuilder2);
    javaClassBuilder2.setJavaDoc("Description2");
    javaClassBuilder2.addAnnotation("Deprecated");
    javaClassBuilder2.generateGettersAndSetters();

    replayAll();

    List<Builder> builderList = builder.build(domain, TypesBuilder.NULL_DOMAIN_TYPE_RESOLVER);

    verifyAll();

    assertEquals(0, builderList.size());
  }

  @Test
  public void testTypesGeneratorGeneratesCorrectTypesOnClassTypeWithProperties()
      throws InstantiationException, IllegalAccessException {
    ObjectType objectType = createObjectType("someObjectType1", "Description1");
    objectType.setProperties(
        Arrays.asList(
            createProperty(StringProperty.class, "propertyTypeString"),
            createProperty(NumberProperty.class, "propertyTypeDouble"),
            createProperty(BooleanProperty.class, "propertyTypeBoolean"),
            createProperty(AnyProperty.class, "propertyTypeObject"),
            createProperty(IntegerProperty.class, "propertyTypeInteger"),
            createProperty(ObjectProperty.class, "propertyTypeObject")));

    Domain domain = new Domain();
    domain.setDomain("domain-name");
    domain.setTypes(Collections.singletonList(objectType));

    expect(javaBuilderFactory.createClassBuilder("my.test.package.domain-name", "SomeObjectType1"))
        .andReturn(javaClassBuilder1);

    javaClassBuilder1.addImport("java.util", "Map");

    javaClassBuilder1.setJavaDoc("Description1");
    javaClassBuilder1.addPrivateField(
        "propertyTypeString", "String", "propertyTypeStringDescription");
    javaClassBuilder1.addPrivateField(
        "propertyTypeDouble", "Double", "propertyTypeDoubleDescription");
    javaClassBuilder1.addPrivateField(
        "propertyTypeBoolean", "Boolean", "propertyTypeBooleanDescription");
    javaClassBuilder1.addPrivateField(
        "propertyTypeObject", "Object", "propertyTypeObjectDescription");
    javaClassBuilder1.addPrivateField(
        "propertyTypeInteger", "Integer", "propertyTypeIntegerDescription");
    javaClassBuilder1.addPrivateField(
        "propertyTypeObject", "Map<String, Object>", "propertyTypeObjectDescription");

    javaClassBuilder1.generateGettersAndSetters();

    replayAll();

    List<Builder> builderList = builder.build(domain, TypesBuilder.NULL_DOMAIN_TYPE_RESOLVER);

    verifyAll();

    assertEquals(1, builderList.size());
    assertEquals(javaClassBuilder1, builderList.get(0));
  }

  @Test
  public void testTypesGeneratorGeneratesCorrectTypesOnClassTypeWithEmptyObjectProperties()
      throws InstantiationException, IllegalAccessException {
    ObjectType objectType = createObjectType("someObjectType1", "Description1");

    objectType.setProperties(
        Collections.singletonList(createProperty(ObjectProperty.class, "emptyObjectType")));

    Domain domain = new Domain();
    domain.setDomain("domain-name");
    domain.setTypes(Collections.singletonList(objectType));

    expect(javaBuilderFactory.createClassBuilder("my.test.package.domain-name", "SomeObjectType1"))
        .andReturn(javaClassBuilder1);
    javaClassBuilder1.setJavaDoc("Description1");
    javaClassBuilder1.addPrivateField(
        "emptyObjectType", "Map<String, Object>", "emptyObjectTypeDescription");

    javaClassBuilder1.addImport("java.util", "Map");

    javaClassBuilder1.generateGettersAndSetters();

    replayAll();

    List<Builder> builderList = builder.build(domain, TypesBuilder.NULL_DOMAIN_TYPE_RESOLVER);

    verifyAll();

    assertEquals(1, builderList.size());
    assertEquals(javaClassBuilder1, builderList.get(0));
  }

  @Test
  public void testTypesGeneratorGeneratesCorrectTypesOnClassTypeWithPropertyAnnotations()
      throws InstantiationException, IllegalAccessException {
    ObjectType objectType = createObjectType("someObjectType1", "Description1");
    StringProperty property = createProperty(StringProperty.class, "propertyTypeString");
    objectType.setProperties(Collections.singletonList(property));

    property.setExperimental(Boolean.TRUE);
    property.setDeprecated(Boolean.TRUE);
    property.setOptional(Boolean.TRUE);

    Domain domain = new Domain();
    domain.setDomain("domain-name");
    domain.setTypes(Collections.singletonList(objectType));

    expect(javaBuilderFactory.createClassBuilder("my.test.package.domain-name", "SomeObjectType1"))
        .andReturn(javaClassBuilder1);
    javaClassBuilder1.setJavaDoc("Description1");
    javaClassBuilder1.addPrivateField(
        "propertyTypeString", "String", "propertyTypeStringDescription");

    javaClassBuilder1.addFieldAnnotation("propertyTypeString", "Experimental");
    javaClassBuilder1.addFieldAnnotation("propertyTypeString", "Deprecated");
    javaClassBuilder1.addFieldAnnotation("propertyTypeString", "Optional");

    javaClassBuilder1.generateGettersAndSetters();

    replayAll();

    List<Builder> builderList = builder.build(domain, TypesBuilder.NULL_DOMAIN_TYPE_RESOLVER);

    verifyAll();

    assertEquals(1, builderList.size());
    assertEquals(javaClassBuilder1, builderList.get(0));
  }

  @Test
  public void testTypesGeneratorGeneratesCorrectTypesOnClassTypeWithEnumProperty()
      throws InstantiationException, IllegalAccessException {
    EnumProperty enumProperty = createProperty(EnumProperty.class, "enumProperty");

    enumProperty.setDescription("Some property description");
    enumProperty.setEnumValues(Arrays.asList("enum1", "enum2"));

    ObjectType objectType = createObjectType("someObjectType1", "Description1");
    objectType.setProperties(Collections.singletonList(enumProperty));

    Domain domain = new Domain();
    domain.setDomain("domain-name");
    domain.setTypes(Collections.singletonList(objectType));

    expect(javaBuilderFactory.createClassBuilder("my.test.package.domain-name", "SomeObjectType1"))
        .andReturn(javaClassBuilder1);
    javaClassBuilder1.setJavaDoc("Description1");

    javaClassBuilder1.addPrivateField(
        "enumProperty", "SomeObjectType1EnumProperty", "Some property description");
    javaClassBuilder1.generateGettersAndSetters();

    expect(
            javaBuilderFactory.createEnumBuilder(
                "my.test.package.domain-name", "SomeObjectType1EnumProperty"))
        .andReturn(javaEnumBuilder1);
    javaEnumBuilder1.setJavaDoc("Some property description");

    javaEnumBuilder1.addEnumConstant("ENUM_1", "enum1");
    javaEnumBuilder1.addEnumConstant("ENUM_2", "enum2");

    replayAll();

    List<Builder> builderList = builder.build(domain, TypesBuilder.NULL_DOMAIN_TYPE_RESOLVER);

    verifyAll();

    assertEquals(1, builderList.size());
    assertTrue(builderList.get(0) instanceof CombinedBuilders);

    assertEquals(2, ((CombinedBuilders) builderList.get(0)).getBuilderList().size());

    Assert.assertEquals(
        javaEnumBuilder1, ((CombinedBuilders) builderList.get(0)).getBuilderList().get(0));
    Assert.assertEquals(
        javaClassBuilder1, ((CombinedBuilders) builderList.get(0)).getBuilderList().get(1));
  }

  @Test
  public void
      testTypesGeneratorGeneratesCorrectTypesOnClassTypeWithRefPropertyToObjectTypeWithProperties()
          throws InstantiationException, IllegalAccessException {
    RefProperty refProperty1 = createProperty(RefProperty.class, "refPropertyName1");

    refProperty1.setDescription("Some property description");
    refProperty1.setRef("TestPackage.RefObject1");

    RefProperty refProperty2 = createProperty(RefProperty.class, "refPropertyName2");
    refProperty2.setRef("RefObject2");

    ObjectType objectType = createObjectType("someObjectType1", "Description1");
    objectType.setProperties(Arrays.asList(refProperty1, refProperty2));

    ObjectType resolvedType1 = new ObjectType();
    resolvedType1.setId("RefObject1");
    resolvedType1.setProperties(
        Collections.singletonList(
            createProperty(StringProperty.class, "stringPropertyRefObject1")));

    ObjectType resolvedType2 = new ObjectType();
    resolvedType2.setId("RefObject2");
    resolvedType2.setProperties(
        Collections.singletonList(
            createProperty(StringProperty.class, "stringPropertyRefObject2")));

    Domain domain = new Domain();
    domain.setDomain("domain-name");
    domain.setTypes(Arrays.asList(objectType, resolvedType2));

    expect(javaBuilderFactory.createClassBuilder("my.test.package.domain-name", "SomeObjectType1"))
        .andReturn(javaClassBuilder1);
    javaClassBuilder1.setJavaDoc("Description1");

    expect(javaBuilderFactory.createClassBuilder("my.test.package.domain-name", "RefObject2"))
        .andReturn(javaClassBuilder2);

    javaClassBuilder2.addPrivateField(
        "stringPropertyRefObject2", "String", "stringPropertyRefObject2Description");

    javaClassBuilder1.addImport("my.test.package.domain-name", "RefObject2");
    javaClassBuilder1.addImport("my.test.package.testpackage", "RefObject1");

    javaClassBuilder1.addPrivateField(
        "refPropertyName1", "RefObject1", "Some property description");

    javaClassBuilder1.addPrivateField(
        "refPropertyName2", "RefObject2", "refPropertyName2Description");

    javaClassBuilder1.generateGettersAndSetters();
    javaClassBuilder2.generateGettersAndSetters();

    expect(resolver.resolve("TestPackage", "RefObject1")).andReturn(resolvedType1);
    expect(resolver.resolve("domain-name", "RefObject2")).andReturn(resolvedType2);

    replayAll();

    List<Builder> builderList = builder.build(domain, resolver);

    verifyAll();

    assertEquals(2, builderList.size());
    assertEquals(javaClassBuilder1, builderList.get(0));
    assertEquals(javaClassBuilder2, builderList.get(1));
  }

  @Test
  public void
      testTypesGeneratorGeneratesCorrectTypesOnClassTypeWithRefPropertyToObjectTypeWithoutProperties()
          throws InstantiationException, IllegalAccessException {
    RefProperty refProperty1 = createProperty(RefProperty.class, "refPropertyName1");

    refProperty1.setDescription("Some property description");
    refProperty1.setRef("TestPackage.RefObject1");

    RefProperty refProperty2 = createProperty(RefProperty.class, "refPropertyName2");
    refProperty2.setRef("RefObject2");

    ObjectType objectType = createObjectType("someObjectType1", "Description1");
    objectType.setProperties(Arrays.asList(refProperty1, refProperty2));

    ObjectType resolvedType1 = new ObjectType();
    resolvedType1.setId("RefObject1");

    ObjectType resolvedType2 = new ObjectType();
    resolvedType2.setId("RefObject2");

    Domain domain = new Domain();
    domain.setDomain("domain-name");
    domain.setTypes(Arrays.asList(objectType, resolvedType2));

    expect(javaBuilderFactory.createClassBuilder("my.test.package.domain-name", "RefObject2"))
        .andReturn(javaClassBuilder2);
    javaClassBuilder2.generateGettersAndSetters();

    expect(javaBuilderFactory.createClassBuilder("my.test.package.domain-name", "SomeObjectType1"))
        .andReturn(javaClassBuilder1);
    javaClassBuilder1.setJavaDoc("Description1");

    javaClassBuilder1.addPrivateField(
        "refPropertyName1", "Map<String, Object>", "Some property description");
    javaClassBuilder1.addPrivateField(
        "refPropertyName2", "Map<String, Object>", "refPropertyName2Description");

    javaClassBuilder1.generateGettersAndSetters();

    javaClassBuilder1.addImport("java.util", "Map");
    javaClassBuilder1.addImport("java.util", "Map");

    expect(resolver.resolve("TestPackage", "RefObject1")).andReturn(resolvedType1);
    expect(resolver.resolve("domain-name", "RefObject2")).andReturn(resolvedType2);

    replayAll();

    List<Builder> builderList = builder.build(domain, resolver);

    verifyAll();

    assertEquals(1, builderList.size());
    assertEquals(javaClassBuilder1, builderList.get(0));
  }

  @Test
  public void
      testTypesGeneratorGeneratesCorrectTypesOnClassTypeWithRefPropertyToObjectTypeWithSameName()
          throws InstantiationException, IllegalAccessException {
    RefProperty refProperty1 = createProperty(RefProperty.class, "refPropertyName1");

    refProperty1.setDescription("Some property description");
    refProperty1.setRef("TestPackage.SomeObjectType1");

    ObjectType objectType = createObjectType("someObjectType1", "Description1");
    objectType.setProperties(Collections.singletonList(refProperty1));

    ObjectType resolvedType1 = new ObjectType();
    resolvedType1.setId("SomeObjectType1");
    resolvedType1.setProperties(
        Collections.singletonList(
            createProperty(StringProperty.class, "stringPropertySomeObjectType1")));

    ObjectType resolvedType2 = new ObjectType();
    resolvedType2.setId("SomeObjectType1-resolved");

    Domain domain = new Domain();
    domain.setDomain("domain-name");
    domain.setTypes(Arrays.asList(objectType, resolvedType2));

    expect(javaBuilderFactory.createClassBuilder("my.test.package.domain-name", "SomeObjectType1"))
        .andReturn(javaClassBuilder1);
    expect(
            javaBuilderFactory.createClassBuilder(
                "my.test.package.domain-name", "SomeObjectType1-resolved"))
        .andReturn(javaClassBuilder2);
    javaClassBuilder1.setJavaDoc("Description1");

    javaClassBuilder1.addPrivateField(
        "refPropertyName1", "SomeObjectType1", "Some property description");

    javaClassBuilder1.generateGettersAndSetters();
    javaClassBuilder2.generateGettersAndSetters();

    expect(resolver.resolve("TestPackage", "SomeObjectType1")).andReturn(resolvedType1);

    replayAll();

    List<Builder> builderList = builder.build(domain, resolver);

    verifyAll();

    assertEquals(1, builderList.size());
    assertEquals(javaClassBuilder1, builderList.get(0));
  }

  @Test
  public void
      testTypesGeneratorGeneratesCorrectTypesOnClassTypeWithRefPropertyToObjectTypeWithSameNameImportingAsFullyQualifiedType()
          throws InstantiationException, IllegalAccessException {

    builder = new TypesBuilder(BASE_PACKAGE_NAME, javaBuilderFactory, false, true);

    RefProperty refProperty1 = createProperty(RefProperty.class, "refPropertyName1");

    refProperty1.setDescription("Some property description");
    refProperty1.setRef("TestPackage.SomeObjectType1");

    ObjectType objectType = createObjectType("someObjectType1", "Description1");
    objectType.setProperties(Collections.singletonList(refProperty1));

    ObjectType resolvedType1 = new ObjectType();
    resolvedType1.setId("SomeObjectType1");
    resolvedType1.setProperties(
        Collections.singletonList(
            createProperty(StringProperty.class, "stringPropertySomeObjectType1")));

    ObjectType resolvedType2 = new ObjectType();
    resolvedType2.setId("SomeObjectType1-resolved");

    Domain domain = new Domain();
    domain.setDomain("domain-name");
    domain.setTypes(Arrays.asList(objectType, resolvedType2));

    expect(javaBuilderFactory.createClassBuilder("my.test.package.domain-name", "SomeObjectType1"))
        .andReturn(javaClassBuilder1);
    expect(
            javaBuilderFactory.createClassBuilder(
                "my.test.package.domain-name", "SomeObjectType1-resolved"))
        .andReturn(javaClassBuilder2);
    javaClassBuilder1.setJavaDoc("Description1");

    javaClassBuilder1.addPrivateField(
        "refPropertyName1",
        "my.test.package.testpackage.SomeObjectType1",
        "Some property description");

    javaClassBuilder1.generateGettersAndSetters();
    javaClassBuilder2.generateGettersAndSetters();

    expect(resolver.resolve("TestPackage", "SomeObjectType1")).andReturn(resolvedType1);

    replayAll();

    List<Builder> builderList = builder.build(domain, resolver);

    verifyAll();

    assertEquals(1, builderList.size());
    assertEquals(javaClassBuilder1, builderList.get(0));
  }

  @Test
  public void
      testTypesGeneratorWithRefImportsGeneratesCorrectTypesOnClassTypeWithRefPropertyToObjectType()
          throws InstantiationException, IllegalAccessException {
    RefProperty refProperty1 = createProperty(RefProperty.class, "refPropertyName1");

    refProperty1.setDescription("Some property description");
    refProperty1.setRef("TestPackage.RefObject1");

    RefProperty refProperty2 = createProperty(RefProperty.class, "refPropertyName2");
    refProperty2.setRef("RefObject2");

    ObjectType objectType = createObjectType("someObjectType1", "Description1");
    objectType.setProperties(Arrays.asList(refProperty1, refProperty2));

    ObjectType resolvedType1 = new ObjectType();
    resolvedType1.setId("RefObject1");
    resolvedType1.setProperties(
        Collections.singletonList(createProperty(StringProperty.class, "stringRefObject1")));

    ObjectType resolvedType2 = new ObjectType();
    resolvedType2.setId("RefObject2");
    resolvedType2.setProperties(
        Collections.singletonList(createProperty(StringProperty.class, "stringRefObject2")));

    Domain domain = new Domain();
    domain.setDomain("domain-name");
    domain.setTypes(Arrays.asList(objectType, resolvedType2));

    expect(javaBuilderFactory.createClassBuilder("my.test.package.domain-name", "SomeObjectType1"))
        .andReturn(javaClassBuilder1);
    javaClassBuilder1.setJavaDoc("Description1");

    expect(javaBuilderFactory.createClassBuilder("my.test.package.domain-name", "RefObject2"))
        .andReturn(javaClassBuilder2);
    javaClassBuilder2.addPrivateField("stringRefObject2", "String", "stringRefObject2Description");

    javaClassBuilder1.addImport("my.test.package.testpackage", "RefObject1");
    javaClassBuilder1.addPrivateField(
        "refPropertyName1", "RefObject1", "Some property description");

    javaClassBuilder1.addImport("my.test.package.domain-name", "RefObject2");
    javaClassBuilder1.addPrivateField(
        "refPropertyName2", "RefObject2", "refPropertyName2Description");

    javaClassBuilder1.generateGettersAndSetters();
    javaClassBuilder2.generateGettersAndSetters();

    expect(resolver.resolve("TestPackage", "RefObject1")).andReturn(resolvedType1);
    expect(resolver.resolve("domain-name", "RefObject2")).andReturn(resolvedType2);

    replayAll();

    builder = new TypesBuilder(BASE_PACKAGE_NAME, javaBuilderFactory);
    List<Builder> builderList = builder.build(domain, resolver);

    verifyAll();

    assertEquals(2, builderList.size());
    assertEquals(javaClassBuilder1, builderList.get(0));
    assertEquals(javaClassBuilder2, builderList.get(1));
  }

  @Test
  public void testTypesGeneratorGeneratesCorrectTypesOnClassTypeWithRefPropertyToArrayType()
      throws InstantiationException, IllegalAccessException {
    RefProperty refProperty = createProperty(RefProperty.class, "refPropertyName1");

    refProperty.setDescription("Some property description");
    refProperty.setRef("TestPackage.RefArray");

    ObjectType objectType = createObjectType("someObjectType1", "Description1");
    objectType.setProperties(Collections.singletonList(refProperty));

    Domain domain = new Domain();
    domain.setDomain("domain-name");
    domain.setTypes(Collections.singletonList(objectType));

    expect(javaBuilderFactory.createClassBuilder("my.test.package.domain-name", "SomeObjectType1"))
        .andReturn(javaClassBuilder1);
    javaClassBuilder1.setJavaDoc("Description1");

    javaClassBuilder1.addPrivateField(
        "refPropertyName1", "List<String>", "Some property description");

    javaClassBuilder1.generateGettersAndSetters();

    javaClassBuilder1.addImport("java.util", "List");

    ArrayType resolvedType = new ArrayType();
    resolvedType.setItems(
        new com.github.kklisura.cdt.protocol.definition.types.type.array.items.StringArrayItem());

    expect(resolver.resolve("TestPackage", "RefArray")).andReturn(resolvedType);

    replayAll();

    List<Builder> builderList = builder.build(domain, resolver);

    verifyAll();

    assertEquals(1, builderList.size());
    assertEquals(javaClassBuilder1, builderList.get(0));
  }

  @Test
  public void testTypesGeneratorGeneratesCorrectTypesOnClassTypeWithRefPropertyToPrimitiveType()
      throws InstantiationException, IllegalAccessException {
    RefProperty refProperty1 = createProperty(RefProperty.class, "refPropertyName1");

    refProperty1.setDescription("Some property description");
    refProperty1.setRef("TestPackage.RefObject1");

    RefProperty refProperty2 = createProperty(RefProperty.class, "refPropertyName2");
    refProperty2.setRef("RefObject2");

    ObjectType objectType = createObjectType("someObjectType1", "Description1");
    objectType.setProperties(Arrays.asList(refProperty1, refProperty2));

    Domain domain = new Domain();
    domain.setDomain("domain-name");
    domain.setTypes(Collections.singletonList(objectType));

    expect(javaBuilderFactory.createClassBuilder("my.test.package.domain-name", "SomeObjectType1"))
        .andReturn(javaClassBuilder1);
    javaClassBuilder1.setJavaDoc("Description1");

    javaClassBuilder1.addPrivateField("refPropertyName1", "String", "Some property description");
    javaClassBuilder1.addPrivateField("refPropertyName2", "Double", "refPropertyName2Description");

    javaClassBuilder1.generateGettersAndSetters();

    Type resolvedType1 = new StringType();
    resolvedType1.setId("RefPrimitiveType1");

    Type resolvedType2 = new NumberType();
    resolvedType2.setId("RefPrimitiveType2");

    expect(resolver.resolve("TestPackage", "RefObject1")).andReturn(resolvedType1);
    expect(resolver.resolve("domain-name", "RefObject2")).andReturn(resolvedType2);

    replayAll();

    List<Builder> builderList = builder.build(domain, resolver);

    verifyAll();

    assertEquals(1, builderList.size());
    assertEquals(javaClassBuilder1, builderList.get(0));
  }

  @Test
  public void
      testTypesGeneratorGeneratesCorrectTypesOnClassTypeWithArrayPropertyOnSimpleArrayItemTypes()
          throws InstantiationException, IllegalAccessException {
    Map<Class<? extends ArrayItem>, String> arrayItems = new HashMap<>();

    arrayItems.put(ObjectArrayItem.class, "Object");
    arrayItems.put(AnyArrayItem.class, "Object");
    arrayItems.put(StringArrayItem.class, "String");
    arrayItems.put(IntegerArrayItem.class, "Integer");
    arrayItems.put(NumberArrayItem.class, "Double");

    for (Map.Entry<Class<? extends ArrayItem>, String> arrayItem : arrayItems.entrySet()) {
      ArrayProperty arrayProperty = createProperty(ArrayProperty.class, "arrayPropertyName");

      arrayProperty.setDescription("Some property description");
      arrayProperty.setItems(arrayItem.getKey().newInstance());

      ObjectType objectType = createObjectType("someObjectType1", "Description1");
      objectType.setProperties(Collections.singletonList(arrayProperty));

      Domain domain = new Domain();
      domain.setDomain("domain-name");
      domain.setTypes(Collections.singletonList(objectType));

      resetAll();

      expect(
              javaBuilderFactory.createClassBuilder(
                  "my.test.package.domain-name", "SomeObjectType1"))
          .andReturn(javaClassBuilder1);
      javaClassBuilder1.setJavaDoc("Description1");

      javaClassBuilder1.addPrivateField(
          "arrayPropertyName", "List<" + arrayItem.getValue() + ">", "Some property description");

      javaClassBuilder1.generateGettersAndSetters();

      javaClassBuilder1.addImport("java.util", "List");

      replayAll();

      List<Builder> builderList = builder.build(domain, TypesBuilder.NULL_DOMAIN_TYPE_RESOLVER);

      verifyAll();

      assertEquals(1, builderList.size());
      assertEquals(javaClassBuilder1, builderList.get(0));
    }
  }

  @Test
  public void
      testTypesGeneratorGeneratesCorrectTypesOnClassTypeWithArrayPropertyOnRefArrayItemTypeToObjectTypeWithoutProperties()
          throws InstantiationException, IllegalAccessException {
    RefArrayItem refArrayItem1 = new RefArrayItem();
    refArrayItem1.setRef("RefObject1");

    RefArrayItem refArrayItem2 = new RefArrayItem();
    refArrayItem2.setRef("Test.RefObject2");

    ArrayProperty arrayProperty1 = createProperty(ArrayProperty.class, "arrayPropertyName1");
    arrayProperty1.setDescription("Some property description");
    arrayProperty1.setItems(refArrayItem1);

    ArrayProperty arrayProperty2 = createProperty(ArrayProperty.class, "arrayPropertyName2");
    arrayProperty2.setItems(refArrayItem2);

    ObjectType objectType = createObjectType("someObjectType1", "Description1");
    objectType.setProperties(Arrays.asList(arrayProperty1, arrayProperty2));

    Type resolvedType1 = new ObjectType();
    resolvedType1.setId("RefObject1");

    ObjectType resolvedType2 = new ObjectType();
    resolvedType2.setId("RefObject2");
    resolvedType2.setProperties(Collections.singletonList(new StringProperty()));

    Domain domain = new Domain();
    domain.setDomain("domain-name");
    domain.setTypes(Arrays.asList(objectType, resolvedType1));

    resetAll();

    expect(javaBuilderFactory.createClassBuilder("my.test.package.domain-name", "SomeObjectType1"))
        .andReturn(javaClassBuilder1);
    javaClassBuilder1.setJavaDoc("Description1");

    expect(javaBuilderFactory.createClassBuilder("my.test.package.domain-name", "RefObject1"))
        .andReturn(javaClassBuilder2);
    javaClassBuilder2.generateGettersAndSetters();

    javaClassBuilder1.addImport("my.test.package.test", "RefObject2");

    javaClassBuilder1.addPrivateField(
        "arrayPropertyName1", "List<Map<String, Object>>", "Some property description");
    javaClassBuilder1.addPrivateField(
        "arrayPropertyName2", "List<RefObject2>", "arrayPropertyName2Description");

    javaClassBuilder1.addImport("java.util", "Map");

    javaClassBuilder1.addImport("java.util", "List");
    javaClassBuilder1.addImport("java.util", "List");

    javaClassBuilder1.generateGettersAndSetters();

    expect(resolver.resolve("domain-name", "RefObject1")).andReturn(resolvedType1);

    expect(resolver.resolve("Test", "RefObject2")).andReturn(resolvedType2);

    replayAll();

    List<Builder> builderList = builder.build(domain, resolver);

    verifyAll();

    assertEquals(1, builderList.size());
    assertEquals(javaClassBuilder1, builderList.get(0));
  }

  @Test
  public void
      testTypesGeneratorGeneratesCorrectTypesOnClassTypeWithArrayPropertyOnRefArrayItemTypeToObjectTypeWithProperties()
          throws InstantiationException, IllegalAccessException {
    RefArrayItem refArrayItem1 = new RefArrayItem();
    refArrayItem1.setRef("RefObject1");

    RefArrayItem refArrayItem2 = new RefArrayItem();
    refArrayItem2.setRef("Test.RefObject2");

    ArrayProperty arrayProperty1 = createProperty(ArrayProperty.class, "arrayPropertyName1");
    arrayProperty1.setDescription("Some property description");
    arrayProperty1.setItems(refArrayItem1);

    ArrayProperty arrayProperty2 = createProperty(ArrayProperty.class, "arrayPropertyName2");
    arrayProperty2.setItems(refArrayItem2);

    ObjectType objectType = createObjectType("someObjectType1", "Description1");
    objectType.setProperties(Arrays.asList(arrayProperty1, arrayProperty2));

    ObjectType resolvedType1 = new ObjectType();
    resolvedType1.setId("RefObject1");
    resolvedType1.setProperties(
        Collections.singletonList(
            createProperty(StringProperty.class, "stringPropertyRefObject1")));

    ObjectType resolvedType2 = new ObjectType();
    resolvedType2.setId("RefObject2");
    resolvedType2.setProperties(
        Collections.singletonList(
            createProperty(StringProperty.class, "stringPropertyRefObject2")));

    Domain domain = new Domain();
    domain.setDomain("domain-name");
    domain.setTypes(Arrays.asList(objectType, resolvedType1));

    resetAll();

    expect(javaBuilderFactory.createClassBuilder("my.test.package.domain-name", "SomeObjectType1"))
        .andReturn(javaClassBuilder1);
    javaClassBuilder1.setJavaDoc("Description1");

    expect(javaBuilderFactory.createClassBuilder("my.test.package.domain-name", "RefObject1"))
        .andReturn(javaClassBuilder2);

    javaClassBuilder2.addPrivateField(
        "stringPropertyRefObject1", "String", "stringPropertyRefObject1Description");

    javaClassBuilder1.addImport("my.test.package.domain-name", "RefObject1");
    javaClassBuilder1.addImport("my.test.package.test", "RefObject2");

    javaClassBuilder1.addPrivateField(
        "arrayPropertyName1", "List<RefObject1>", "Some property description");
    javaClassBuilder1.addPrivateField(
        "arrayPropertyName2", "List<RefObject2>", "arrayPropertyName2Description");

    javaClassBuilder1.addImport("java.util", "List");
    javaClassBuilder1.addImport("java.util", "List");

    javaClassBuilder1.generateGettersAndSetters();
    javaClassBuilder2.generateGettersAndSetters();

    expect(resolver.resolve("domain-name", "RefObject1")).andReturn(resolvedType1);

    expect(resolver.resolve("Test", "RefObject2")).andReturn(resolvedType2);

    replayAll();

    List<Builder> builderList = builder.build(domain, resolver);

    verifyAll();

    assertEquals(2, builderList.size());
    assertEquals(javaClassBuilder1, builderList.get(0));
    assertEquals(javaClassBuilder2, builderList.get(1));
  }

  @Test
  public void
      testTypesGeneratorGeneratesCorrectTypesOnClassTypeWithArrayPropertyOnRefArrayItemTypeToArrayType()
          throws InstantiationException, IllegalAccessException {
    RefArrayItem refArrayItem = new RefArrayItem();
    refArrayItem.setRef("RefArray1");

    ArrayProperty arrayProperty1 = createProperty(ArrayProperty.class, "arrayPropertyName1");
    arrayProperty1.setDescription("Some property description");
    arrayProperty1.setItems(refArrayItem);

    ObjectType objectType = createObjectType("someObjectType1", "Description1");
    objectType.setProperties(Collections.singletonList(arrayProperty1));

    Domain domain = new Domain();
    domain.setDomain("domain-name");
    domain.setTypes(Collections.singletonList(objectType));

    resetAll();

    expect(javaBuilderFactory.createClassBuilder("my.test.package.domain-name", "SomeObjectType1"))
        .andReturn(javaClassBuilder1);
    javaClassBuilder1.setJavaDoc("Description1");

    javaClassBuilder1.addPrivateField(
        "arrayPropertyName1", "List<List<Double>>", "Some property description");

    javaClassBuilder1.generateGettersAndSetters();

    javaClassBuilder1.addImport("java.util", "List");
    javaClassBuilder1.addImport("java.util", "List");

    ArrayType resolvedType = new ArrayType();
    resolvedType.setItems(
        new com.github.kklisura.cdt.protocol.definition.types.type.array.items.NumberArrayItem());

    expect(resolver.resolve("domain-name", "RefArray1")).andReturn(resolvedType);

    replayAll();

    List<Builder> builderList = builder.build(domain, resolver);

    verifyAll();

    assertEquals(1, builderList.size());
    assertEquals(javaClassBuilder1, builderList.get(0));
  }

  @Test
  public void
      testTypesGeneratorGeneratesCorrectTypesOnClassTypeWithArrayPropertyOnRefArrayItemTypeToPrimitiveType()
          throws InstantiationException, IllegalAccessException {
    RefArrayItem refArrayItem1 = new RefArrayItem();
    refArrayItem1.setRef("RefObject1");

    RefArrayItem refArrayItem2 = new RefArrayItem();
    refArrayItem2.setRef("Test.RefObject2");

    ArrayProperty arrayProperty1 = createProperty(ArrayProperty.class, "arrayPropertyName1");
    arrayProperty1.setDescription("Some property description");
    arrayProperty1.setItems(refArrayItem1);

    ArrayProperty arrayProperty2 = createProperty(ArrayProperty.class, "arrayPropertyName2");
    arrayProperty2.setItems(refArrayItem2);

    ObjectType objectType = createObjectType("someObjectType1", "Description1");
    objectType.setProperties(Arrays.asList(arrayProperty1, arrayProperty2));

    Domain domain = new Domain();
    domain.setDomain("domain-name");
    domain.setTypes(Collections.singletonList(objectType));

    resetAll();

    expect(javaBuilderFactory.createClassBuilder("my.test.package.domain-name", "SomeObjectType1"))
        .andReturn(javaClassBuilder1);
    javaClassBuilder1.setJavaDoc("Description1");

    javaClassBuilder1.addPrivateField(
        "arrayPropertyName1", "List<String>", "Some property description");
    javaClassBuilder1.addPrivateField(
        "arrayPropertyName2", "List<Double>", "arrayPropertyName2Description");

    javaClassBuilder1.generateGettersAndSetters();

    javaClassBuilder1.addImport("java.util", "List");
    javaClassBuilder1.addImport("java.util", "List");

    Type resolvedType1 = new StringType();

    Type resolvedType2 = new NumberType();

    expect(resolver.resolve("domain-name", "RefObject1")).andReturn(resolvedType1);

    expect(resolver.resolve("Test", "RefObject2")).andReturn(resolvedType2);

    replayAll();

    List<Builder> builderList = builder.build(domain, resolver);

    verifyAll();

    assertEquals(1, builderList.size());
    assertEquals(javaClassBuilder1, builderList.get(0));
  }

  @Test
  public void
      testTypesGeneratorGeneratesCorrectTypesOnClassTypeWithArrayPropertyOnEnumArrayItemType()
          throws InstantiationException, IllegalAccessException {
    EnumArrayItem enumArrayItem = new EnumArrayItem();
    enumArrayItem.setDescription("Some property description");
    enumArrayItem.setEnumValues(Arrays.asList("enumValue1", "enumValue2"));

    ArrayProperty arrayProperty = createProperty(ArrayProperty.class, "arrayPropertyName1");
    arrayProperty.setItems(enumArrayItem);

    ObjectType objectType = createObjectType("someObjectType1", "Description1");
    objectType.setProperties(Collections.singletonList(arrayProperty));

    Domain domain = new Domain();
    domain.setDomain("domain-name");
    domain.setTypes(Collections.singletonList(objectType));

    resetAll();

    expect(javaBuilderFactory.createClassBuilder("my.test.package.domain-name", "SomeObjectType1"))
        .andReturn(javaClassBuilder1);

    expect(
            javaBuilderFactory.createEnumBuilder(
                "my.test.package.domain-name", "ArrayPropertyName1"))
        .andReturn(javaEnumBuilder1);

    javaClassBuilder1.addImport("java.util", "List");

    javaClassBuilder1.setJavaDoc("Description1");
    javaClassBuilder1.addPrivateField(
        "arrayPropertyName1", "List<ArrayPropertyName1>", "arrayPropertyName1Description");
    javaClassBuilder1.generateGettersAndSetters();

    javaEnumBuilder1.setJavaDoc("Some property description");
    javaEnumBuilder1.addEnumConstant("ENUM_VALUE_1", "enumValue1");
    javaEnumBuilder1.addEnumConstant("ENUM_VALUE_2", "enumValue2");

    replayAll();

    List<Builder> builderList = builder.build(domain, TypesBuilder.NULL_DOMAIN_TYPE_RESOLVER);

    verifyAll();

    assertEquals(1, builderList.size());

    assertTrue(builderList.get(0) instanceof CombinedBuilders);

    assertEquals(2, ((CombinedBuilders) builderList.get(0)).getBuilderList().size());

    Assert.assertEquals(
        javaEnumBuilder1, ((CombinedBuilders) builderList.get(0)).getBuilderList().get(0));
    Assert.assertEquals(
        javaClassBuilder1, ((CombinedBuilders) builderList.get(0)).getBuilderList().get(1));
  }

  private <T extends Property> T createProperty(Class<T> clazz, String name)
      throws IllegalAccessException, InstantiationException {
    T property = clazz.newInstance();
    property.setName(name);
    property.setDescription(name + "Description");
    return property;
  }

  private EnumType createEnumType(String name, String description, List<String> enumValues) {
    EnumType enumType = new EnumType();
    enumType.setId(name);
    enumType.setDescription(description);
    enumType.setEnumValues(enumValues);
    return enumType;
  }

  private ObjectType createObjectType(String name, String description) {
    ObjectType objectType = new ObjectType();
    objectType.setId(name);
    objectType.setDescription(description);
    return objectType;
  }
}
