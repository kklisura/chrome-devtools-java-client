package com.github.kklisura.cdtp.definition.builder.support.protocol.builder;

import com.github.kklisura.cdtp.definition.builder.protocol.types.type.object.ObjectType;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.object.properties.ArrayProperty;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.object.properties.StringProperty;
import com.github.kklisura.cdtp.definition.builder.support.java.builder.Builder;
import com.github.kklisura.cdtp.definition.builder.support.java.builder.JavaBuilderFactory;
import com.github.kklisura.cdtp.definition.builder.support.java.builder.JavaClassBuilder;
import com.github.kklisura.cdtp.definition.builder.support.java.builder.JavaEnumBuilder;
import com.github.kklisura.cdtp.definition.builder.support.java.builder.JavaInterfaceBuilder;
import com.github.kklisura.cdtp.definition.builder.support.java.builder.support.CombinedBuilders;
import com.github.kklisura.cdtp.definition.builder.support.java.builder.support.MethodParam;
import com.github.kklisura.cdtp.definition.builder.support.protocol.builder.support.DomainTypeResolver;
import com.github.kklisura.cdtp.definition.builder.protocol.types.Command;
import com.github.kklisura.cdtp.definition.builder.protocol.types.Domain;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.object.properties.BooleanProperty;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.object.properties.NumberProperty;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.object.properties.RefProperty;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.object.properties.array.items.EnumArrayItem;
import org.easymock.Capture;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

	@Mock
	private JavaBuilderFactory javaBuilderFactory;

	@Mock
	private DomainTypeResolver resolver;

	@Mock
	private JavaInterfaceBuilder interfaceBuilder;

	@Mock
	private JavaClassBuilder javaClassBuilder;

	@Mock
	private JavaEnumBuilder javaEnumBuilder;

	private CommandBuilder commandBuilder;

	@Before
	public void setUp() throws Exception {
		commandBuilder = new CommandBuilder(BASE_PACKAGE_NAME, javaBuilderFactory, TYPE_PACKAGE_NAME, EVENTS_PACKAGE_NAME);
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

		domain.setCommands(Arrays.asList(command1, command2));

		expect(javaBuilderFactory.createInterfaceBuilder(BASE_PACKAGE_NAME, "DomainName"))
				.andReturn(interfaceBuilder);
		interfaceBuilder.setJavaDoc("Description");

		Capture<List<MethodParam>> methodParamCapture1 = Capture.newInstance();
		interfaceBuilder.addMethod(eq("command1"), eq("command1 description"), capture(methodParamCapture1), eq(null));

		interfaceBuilder.addMethodAnnotation("command1", "Deprecated");
		interfaceBuilder.addMethodAnnotation("command1", "Experimental");

		Capture<List<MethodParam>> methodParamCapture2 = Capture.newInstance();
		interfaceBuilder.addMethod(eq("command2"), eq("command2 description"), capture(methodParamCapture2), eq(null));

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

		final NumberProperty numberParam = new NumberProperty();
		numberParam.setName("numberParam1");
		numberParam.setExperimental(Boolean.TRUE);

		final BooleanProperty booleanReturnParam = new BooleanProperty();

		command.setParameters(Arrays.asList(stringParam, numberParam));
		command.setReturns(Collections.singletonList(booleanReturnParam));

		domain.setCommands(Arrays.asList(command));

		expect(javaBuilderFactory.createInterfaceBuilder(BASE_PACKAGE_NAME, "DomainName"))
				.andReturn(interfaceBuilder);
		interfaceBuilder.setJavaDoc("Description");

		Capture<List<MethodParam>> methodParamCapture = Capture.newInstance();
		interfaceBuilder.addMethod(eq("command"), eq("command description"), capture(methodParamCapture), eq("Boolean"));

		replayAll();

		assertEquals(interfaceBuilder, commandBuilder.build(domain, resolver));

		verifyAll();

		List<MethodParam> params = methodParamCapture.getValue();
		assertEquals(2, params.size());
		Assert.assertEquals(stringParam.getName(), params.get(0).getName());
		assertEquals("String", params.get(0).getType());
		assertEquals("Deprecated", params.get(0).getAnnotations().get(0));

		assertEquals(numberParam.getName(), params.get(1).getName());
		assertEquals("Double", params.get(1).getType());
		assertEquals("Experimental", params.get(1).getAnnotations().get(0));
	}

	@Test
	public void testBuildCommandWithMethodWithComplexParams() {
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

		EnumArrayItem enumArrayItem = new EnumArrayItem();
		enumArrayItem.setEnumValues(Arrays.asList("enum1", "enum2"));
		arrayProperty.setItems(enumArrayItem);

		final BooleanProperty booleanReturnParam = new BooleanProperty();

		command.setParameters(Arrays.asList(refParam, arrayProperty));
		command.setReturns(Collections.singletonList(booleanReturnParam));

		domain.setCommands(Arrays.asList(command));

		expect(javaBuilderFactory.createEnumBuilder("com.github.kklisura.types.domainname", "EnumParam1"))
				.andReturn(javaEnumBuilder);

		javaEnumBuilder.addEnumConstant("ENUM_1", "enum1");
		javaEnumBuilder.addEnumConstant("ENUM_2", "enum2");

		expect(javaBuilderFactory.createInterfaceBuilder(BASE_PACKAGE_NAME, "DomainName"))
				.andReturn(interfaceBuilder);
		interfaceBuilder.setJavaDoc("Description");

		Capture<List<MethodParam>> methodParamCapture = Capture.newInstance();
		interfaceBuilder.addMethod(eq("command"), eq("command description"), capture(methodParamCapture), eq("Boolean"));

		final ObjectType resolvedRefType = new ObjectType();
		resolvedRefType.setId("TestRef");

		expect(resolver.resolve("domainName", "TestRef"))
				.andReturn(resolvedRefType);

		interfaceBuilder.addImport("com.github.kklisura.types.domainname", "TestRef");
		interfaceBuilder.addImport("java.util", "List");
		interfaceBuilder.addImport("com.github.kklisura.types.domainname", "EnumParam1");

		replayAll();

		Builder build = commandBuilder.build(domain, resolver);

		verifyAll();

		assertTrue(build instanceof CombinedBuilders);

		List<Builder> builderList = ((CombinedBuilders) build).getBuilderList();
		assertEquals(2, builderList.size());

		assertEquals(javaEnumBuilder, builderList.get(0));
		assertEquals(interfaceBuilder, builderList.get(1));

		List<MethodParam> params = methodParamCapture.getValue();
		assertEquals(2, params.size());
		assertEquals(refParam.getName(), params.get(0).getName());
		assertEquals("TestRef", params.get(0).getType());
		assertEquals("Deprecated", params.get(0).getAnnotations().get(0));

		Assert.assertEquals(arrayProperty.getName(), params.get(1).getName());
		assertEquals("List<EnumParam1>", params.get(1).getType());
		assertEquals("Experimental", params.get(1).getAnnotations().get(0));
		assertEquals("Optional", params.get(1).getAnnotations().get(1));
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
		domain.setCommands(Arrays.asList(command));

		expect(javaBuilderFactory.createClassBuilder(TYPE_PACKAGE_NAME + ".domainname", "CommandSomeResult"))
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
		interfaceBuilder.addMethod(eq("getCommandSomeResult"), eq("command description"), capture(methodParamCapture), eq("CommandSomeResult"));

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
}