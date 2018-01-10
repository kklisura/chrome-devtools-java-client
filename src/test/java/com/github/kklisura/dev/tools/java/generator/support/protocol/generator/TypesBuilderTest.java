package com.github.kklisura.dev.tools.java.generator.support.protocol.generator;

import com.github.kklisura.dev.tools.java.generator.protocol.types.Domain;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.EnumType;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.ObjectType;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.Property;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.AnyProperty;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.ArrayProperty;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.BooleanProperty;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.EnumProperty;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.IntegerProperty;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.NumberProperty;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.ObjectProperty;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.RefProperty;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.StringProperty;
import com.github.kklisura.dev.tools.java.generator.support.java.builder.Builder;
import com.github.kklisura.dev.tools.java.generator.support.java.builder.JavaBuilderFactory;
import com.github.kklisura.dev.tools.java.generator.support.java.builder.JavaClassBuilder;
import com.github.kklisura.dev.tools.java.generator.support.java.builder.JavaEnumBuilder;
import com.github.kklisura.dev.tools.java.generator.support.java.builder.support.CombinedBuilders;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Kenan Klisura on 02/01/2018.
 *
 * @author Kenan Klisura
 */
@RunWith(EasyMockRunner.class)
public class TypesBuilderTest extends EasyMockSupport {
	private static final String BASE_PACKAGE_NAME = "my.test.package";

	@Mock
	private JavaBuilderFactory javaBuilderFactory;

	@Mock
	private JavaEnumBuilder javaEnumBuilder1;

	@Mock
	private JavaEnumBuilder javaEnumBuilder2;

	@Mock
	private JavaClassBuilder javaClassBuilder1;

	@Mock
	private JavaClassBuilder javaClassBuilder2;

	private TypesBuilder builder;

	@Before
	public void setUp() throws Exception {
		builder = new TypesBuilder(BASE_PACKAGE_NAME, javaBuilderFactory);
	}

	@Test
	public void testTypesGeneratorGeneratesCorrectTypesOnEmptyDomain() {
		Domain domain = new Domain();
		assertTrue(builder.build(domain).isEmpty());
	}

	@Test
	public void testTypesGeneratorGeneratesCorrectTypesOnEnumType() {
		Domain domain = new Domain();
		domain.setDomain("domain-name");
		domain.setTypes(Arrays.asList(
				createEnumType("someEnumType1", "Description1", null),
				createEnumType("someEnumType2", "Description2", Arrays.asList("VAL1", "Val2"))
		));

		expect(javaBuilderFactory.createEnumBuilder("my.test.package.domain-name", "someEnumType1"))
				.andReturn(javaEnumBuilder1);
		javaEnumBuilder1.setJavaDoc("Description1");

		expect(javaBuilderFactory.createEnumBuilder("my.test.package.domain-name", "someEnumType2"))
				.andReturn(javaEnumBuilder2);
		javaEnumBuilder2.setJavaDoc("Description2");
		javaEnumBuilder2.addEnumConstant("VAL1");
		javaEnumBuilder2.addEnumConstant("Val2");

		replayAll();

		List<Builder> builderList = builder.build(domain);

		verifyAll();

		assertEquals(2, builderList.size());
		assertEquals(builderList.get(0), javaEnumBuilder1);
		assertEquals(builderList.get(1), javaEnumBuilder2);
	}

	@Test
	public void testTypesGeneratorGeneratesCorrectTypesOnClassType() {
		Domain domain = new Domain();
		domain.setDomain("domain-name");
		domain.setTypes(Arrays.asList(
				createObjectType("someObjectType1", "Description1"),
				createObjectType("someObjectType2", "Description2")
		));

		expect(javaBuilderFactory.createClassBuilder("my.test.package.domain-name", "someObjectType1"))
				.andReturn(javaClassBuilder1);
		javaClassBuilder1.setJavaDoc("Description1");
		javaClassBuilder1.generateGettersAndSetters();

		expect(javaBuilderFactory.createClassBuilder("my.test.package.domain-name", "someObjectType2"))
				.andReturn(javaClassBuilder2);
		javaClassBuilder2.setJavaDoc("Description2");
		javaClassBuilder2.generateGettersAndSetters();

		replayAll();

		List<Builder> builderList = builder.build(domain);

		verifyAll();

		assertEquals(2, builderList.size());
		assertEquals(builderList.get(0), javaClassBuilder1);
		assertEquals(builderList.get(1), javaClassBuilder2);
	}

	@Test
	public void testTypesGeneratorGeneratesCorrectTypesOnClassTypeWithProperties() throws InstantiationException, IllegalAccessException {
		ObjectType objectType = createObjectType("someObjectType1", "Description1");
		objectType.setProperties(Arrays.asList(
				createProperty(StringProperty.class, "propertyTypeString"),
				createProperty(NumberProperty.class, "propertyTypeDouble"),
				createProperty(BooleanProperty.class, "propertyTypeBoolean"),
				createProperty(AnyProperty.class, "propertyTypeObject"),
				createProperty(IntegerProperty.class, "propertyTypeInteger"),
				createProperty(ObjectProperty.class, "propertyTypeObject"),
				createProperty(ArrayProperty.class, "propertyTypeList"),
				createProperty(RefProperty.class, "propertyTypeObject")
		));

		Domain domain = new Domain();
		domain.setDomain("domain-name");
		domain.setTypes(Collections.singletonList(objectType));

		expect(javaBuilderFactory.createClassBuilder("my.test.package.domain-name", "someObjectType1"))
				.andReturn(javaClassBuilder1);
		javaClassBuilder1.setJavaDoc("Description1");
		javaClassBuilder1.addPrivateField("propertyTypeString", "String");
		javaClassBuilder1.addPrivateField("propertyTypeDouble", "Double");
		javaClassBuilder1.addPrivateField("propertyTypeBoolean", "Boolean");
		javaClassBuilder1.addPrivateField("propertyTypeObject", "Object");
		javaClassBuilder1.addPrivateField("propertyTypeInteger", "Integer");
		javaClassBuilder1.addPrivateField("propertyTypeObject", "Object");
		javaClassBuilder1.addPrivateField("propertyTypeList", "List");
		javaClassBuilder1.addPrivateField("propertyTypeObject", "Object");

		javaClassBuilder1.generateGettersAndSetters();

		replayAll();

		List<Builder> builderList = builder.build(domain);

		verifyAll();

		assertEquals(1, builderList.size());
		assertEquals(builderList.get(0), javaClassBuilder1);
	}

	@Test
	public void testTypesGeneratorGeneratesCorrectTypesOnClassTypeWithEnumProperty() throws InstantiationException, IllegalAccessException {
		EnumProperty enumProperty = createProperty(EnumProperty.class, "enumProperty");

		enumProperty.setDescription("Some property description");
		enumProperty.setEnumValues(Arrays.asList("enum1", "enum2"));

		ObjectType objectType = createObjectType("someObjectType1", "Description1");
		objectType.setProperties(Collections.singletonList(enumProperty));

		Domain domain = new Domain();
		domain.setDomain("domain-name");
		domain.setTypes(Collections.singletonList(objectType));


		expect(javaBuilderFactory.createClassBuilder("my.test.package.domain-name", "someObjectType1"))
				.andReturn(javaClassBuilder1);
		javaClassBuilder1.setJavaDoc("Description1");
		javaClassBuilder1.generateGettersAndSetters();

		expect(javaBuilderFactory.createEnumBuilder("my.test.package.domain-name", "enumProperty"))
				.andReturn(javaEnumBuilder1);
		javaEnumBuilder1.setJavaDoc("Some property description");

		javaEnumBuilder1.addEnumConstant("enum1");
		javaEnumBuilder1.addEnumConstant("enum2");

		replayAll();

		List<Builder> builderList = builder.build(domain);

		verify();

		assertEquals(1, builderList.size());
		assertTrue(builderList.get(0) instanceof CombinedBuilders);

		assertEquals(2, ((CombinedBuilders) builderList.get(0)).getBuilderList().size());

		assertTrue(((CombinedBuilders) builderList.get(0)).getBuilderList().get(0) instanceof JavaEnumBuilder);
		assertTrue(((CombinedBuilders) builderList.get(0)).getBuilderList().get(1) instanceof JavaClassBuilder);
	}

	private <T extends Property> T createProperty(Class<T> clazz, String name)
			throws IllegalAccessException, InstantiationException {
		T property = clazz.newInstance();
		property.setName(name);
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