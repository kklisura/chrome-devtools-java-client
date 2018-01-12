package com.github.kklisura.dev.tools.java.generator.support.protocol.generator;

import com.github.kklisura.dev.tools.java.generator.protocol.types.Domain;
import com.github.kklisura.dev.tools.java.generator.protocol.types.Type;
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
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.array.ArrayItem;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.array.items.AnyArrayItem;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.array.items.EnumArrayItem;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.array.items.IntegerArrayItem;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.array.items.NumberArrayItem;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.array.items.ObjectArrayItem;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.array.items.RefArrayItem;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.array.items.StringArrayItem;
import com.github.kklisura.dev.tools.java.generator.support.java.builder.Builder;
import com.github.kklisura.dev.tools.java.generator.support.java.builder.JavaBuilderFactory;
import com.github.kklisura.dev.tools.java.generator.support.java.builder.JavaClassBuilder;
import com.github.kklisura.dev.tools.java.generator.support.java.builder.JavaEnumBuilder;
import com.github.kklisura.dev.tools.java.generator.support.java.builder.support.CombinedBuilders;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static com.github.kklisura.dev.tools.java.generator.support.utils.StringUtils.*;

/**
 * TypesBuilder builds a types for a domain.
 *
 * @author Kenan Klisura
 */
public class TypesBuilder {
	private static final Logger LOGGER = LoggerFactory.getLogger(TypesBuilder.class);

	private static final String DEPRECATED_ANNOTATION = "Deprecated";
	private static final String EXPERIMENTAL_ANNOTATION = "Experimental";
	private static final String OPTIONAL_ANNOTATION = "Optional";

	private static final Map<Class, String> PROPERTY_TO_JAVA_TYPE_MAP = new HashMap<>();
	private static final Map<Class, String> ARRAY_ITEM_TYPE_TO_JAVA_TYPE_MAP = new HashMap<>();

	private final Map<Class, Function<TypeBuildRequest, Builder>> typeHandlers = new HashMap<>();
	private final Map<Class, Function<PropertyBuildRequest, PropertyHandlerResult>> propertyHandlers = new HashMap<>();
	private final Map<Class, Function<ArrayItemBuildRequest, ArrayItemHandlerResult>> arrayItemHandlers = new HashMap<>();

	private JavaBuilderFactory javaBuilderFactory;
	private String basePackageName;

	// Register property class to java type mapping.
	static {
		registerProperty(StringProperty.class, "String");
		registerProperty(NumberProperty.class, "Double");
		registerProperty(BooleanProperty.class, "Boolean");
		registerProperty(AnyProperty.class, "Object");
		registerProperty(IntegerProperty.class, "Integer");
		registerProperty(ObjectProperty.class, "Object");
	}

	// Register handlers of properties. These types are special and require special handling.
	{
		registerProperty(EnumProperty.class, this::addEnumProperty);
		registerProperty(RefProperty.class, this::addRefProperty);
		registerProperty(ArrayProperty.class, this::addArrayProperty);
	}

	// Register array item types.
	static {
		registerArrayItem(ObjectArrayItem.class, "Object");
		registerArrayItem(AnyArrayItem.class, "Object");
		registerArrayItem(StringArrayItem.class, "String");
		registerArrayItem(IntegerArrayItem.class, "Integer");
		registerArrayItem(NumberArrayItem.class, "Double");
	}

	// Register handlers for some array items types. These types are special and require special handling.
	{
		registerArrayItem(EnumArrayItem.class, this::addEnumArrayItem);
		registerArrayItem(RefArrayItem.class, this::addRefArrayItem);
	}

	// Register handlers of property types. These types are special and require special handling.
	{
		registerType(EnumType.class, this::buildEnumType);
		registerType(ObjectType.class, this::buildClass);
	}

	/**
	 * Ctor.
	 *
	 * @param basePackageName Package name for all types.
	 * @param javaBuilderFactory Builder factory.
	 */
	public TypesBuilder(String basePackageName, JavaBuilderFactory javaBuilderFactory) {
		this.basePackageName = basePackageName;
		this.javaBuilderFactory = javaBuilderFactory;
	}

	/**
	 * Builds a types for a given domain.
	 *
	 * @param domain Domain to build type for.
	 * @return List of generated items.
	 */
	public List<Builder> build(Domain domain) {
		final List<Type> types = domain.getTypes();
		List<Builder> result = new ArrayList<>();

		if (CollectionUtils.isNotEmpty(types)) {
			for (Type type : types) {
				Function<TypeBuildRequest, Builder> buildRequestBuilderFunction = typeHandlers.get(type.getClass());
				if (buildRequestBuilderFunction != null) {
					TypeBuildRequest typeBuildRequest = new TypeBuildRequest<>(domain, type);
					Builder builder = buildRequestBuilderFunction.apply(typeBuildRequest);

					result.add(builder);
				}
			}
		}

		return result;
	}

	private Builder buildEnumType(TypeBuildRequest<EnumType> request) {
		final Domain domain = request.getDomain();
		final EnumType type = request.getType();

		String packageName = buildPackageName(basePackageName, domain.getDomain().toLowerCase());

		return buildEnum(packageName, toEnumClass(type.getId()), type.getDescription(), type.getEnumValues());
	}

	private Builder buildClass(TypeBuildRequest<ObjectType> request) {
		final Domain domain = request.getDomain();
		final ObjectType type = request.getType();

		String packageName = buildPackageName(basePackageName, domain.getDomain().toLowerCase());

		JavaClassBuilder classBuilder = javaBuilderFactory.createClassBuilder(packageName, type.getId());
		classBuilder.setJavaDoc(type.getDescription());

		if (Boolean.TRUE.equals(type.getExperimental())) {
			classBuilder.addAnnotation(EXPERIMENTAL_ANNOTATION);
		}

		List<Builder> additionalBuilders = new ArrayList<>();
		addProperties(domain, classBuilder, type, additionalBuilders);

		classBuilder.generateGettersAndSetters();

		if (additionalBuilders.isEmpty()) {
			return classBuilder;
		}
		additionalBuilders.add(classBuilder);
		return new CombinedBuilders(additionalBuilders);
	}

	private void addProperties(Domain domain, JavaClassBuilder javaClassBuilder, ObjectType type,
							   List<Builder> additionalBuilders) {
		if (CollectionUtils.isNotEmpty(type.getProperties())) {
			for (Property property : type.getProperties()) {
				Function<PropertyBuildRequest, PropertyHandlerResult> fn = propertyHandlers.get(property.getClass());
				if (fn != null) {
					// Build request object
					PropertyBuildRequest<Property> request = new PropertyBuildRequest<>();
					request.setJavaClassBuilder(javaClassBuilder);
					request.setDomain(domain);
					request.setProperty(property);

					PropertyHandlerResult result = fn.apply(request);
					if (result.getBuilder() != null) {
						additionalBuilders.add(result.getBuilder());
					}

					javaClassBuilder.addPrivateField(property.getName(), result.getType());
				} else {
					// TODO(kklisura): Add support for description properties; Add javadoc on getters
					javaClassBuilder.addPrivateField(property.getName(), getJavaType(property));
				}

				if (Boolean.TRUE.equals(property.getDeprecated())) {
					javaClassBuilder.addFieldAnnotation(property.getName(), DEPRECATED_ANNOTATION);
				}

				if (Boolean.TRUE.equals(property.getExperimental())) {
					javaClassBuilder.addFieldAnnotation(property.getName(), EXPERIMENTAL_ANNOTATION);
				}

				if (Boolean.TRUE.equals(property.getOptional())) {
					javaClassBuilder.addFieldAnnotation(property.getName(), OPTIONAL_ANNOTATION);
				}
			}
		}
	}

	private PropertyHandlerResult addEnumProperty(PropertyBuildRequest<EnumProperty> request) {
		final Domain domain = request.getDomain();
		final EnumProperty property = request.getProperty();

		String packageName = buildPackageName(basePackageName, domain.getDomain().toLowerCase());
		String name = toEnumClass(property.getName());

		PropertyHandlerResult result = new PropertyHandlerResult();
		result.setBuilder(buildEnum(packageName, name, property.getDescription(), property.getEnumValues()));
		result.setType(name);

		return result;
	}

	private PropertyHandlerResult addRefProperty(PropertyBuildRequest<RefProperty> request) {
		final RefProperty property = request.getProperty();

		String objectName = addRefImportStatement(request.getJavaClassBuilder(), property.getRef());

		PropertyHandlerResult result = new PropertyHandlerResult();
		result.setType(objectName);

		return result;
	}

	private PropertyHandlerResult addArrayProperty(PropertyBuildRequest<ArrayProperty> request) {
		final ArrayProperty arrayProperty = request.getProperty();
		final ArrayItem arrayItem = arrayProperty.getItems();

		PropertyHandlerResult result = new PropertyHandlerResult();

		Function<ArrayItemBuildRequest, ArrayItemHandlerResult> fn = arrayItemHandlers.get(arrayItem.getClass());
		if (fn != null) {
			ArrayItemBuildRequest<ArrayItem> buildRequest = new ArrayItemBuildRequest<>();
			buildRequest.setJavaClassBuilder(request.getJavaClassBuilder());
			buildRequest.setDomain(request.getDomain());
			buildRequest.setProperty(arrayItem);
			buildRequest.setArrayProperty(arrayProperty);

			ArrayItemHandlerResult itemResult = fn.apply(buildRequest);

			result.setBuilder(itemResult.getBuilder());
			result.setType(itemResult.getType());
		} else {
			result.setType(buildArrayJavaType(getTypedArrayItemType(arrayItem)));
		}

		return result;
	}

	private ArrayItemHandlerResult addRefArrayItem(ArrayItemBuildRequest<RefArrayItem> request) {
		final RefArrayItem property = request.getProperty();

		String objectName = addRefImportStatement(request.getJavaClassBuilder(), property.getRef());

		ArrayItemHandlerResult result = new ArrayItemHandlerResult();
		result.setType(buildArrayJavaType(objectName));
		return result;
	}

	private ArrayItemHandlerResult addEnumArrayItem(ArrayItemBuildRequest<EnumArrayItem> request) {
		// TODO(kklisura): Need to test this!
		final Domain domain = request.getDomain();
		final EnumArrayItem property = request.getProperty();

		String packageName = buildPackageName(basePackageName, domain.getDomain().toLowerCase());
		String name = toEnumClass(request.getArrayProperty().getName());

		ArrayItemHandlerResult result = new ArrayItemHandlerResult();
		result.setBuilder(buildEnum(packageName, name, property.getDescription(), property.getEnumValues()));
		result.setType(buildArrayJavaType(name));
		return result;
	}

	private String addRefImportStatement(JavaClassBuilder javaClassBuilder, String ref) {
		String namespace;

		int i = ref.indexOf('.');
		if (i != -1) {
			namespace = ref.substring(0, i);
			ref = ref.substring(i + 1);

			String importPackageName = buildPackageName(basePackageName, namespace);
			javaClassBuilder.addImport(importPackageName, ref);
		}

		return ref;
	}

	private Builder buildEnum(String packageName, String name, String description, List<String> enumValues) {
		JavaEnumBuilder enumBuilder = javaBuilderFactory.createEnumBuilder(packageName, name);
		enumBuilder.setJavaDoc(description);

		if (CollectionUtils.isNotEmpty(enumValues)) {
			for (String enumValue : enumValues) {
				enumBuilder.addEnumConstant(toEnumConstant(enumValue), enumValue);
			}
		}

		return enumBuilder;
	}

	private String getJavaType(Property property) {
		return PROPERTY_TO_JAVA_TYPE_MAP.get(property.getClass());
	}

	private static String buildArrayJavaType(String type) {
		return "List<" + type + ">";
	}

	private static String getTypedArrayItemType(ArrayItem arrayItem) {
		return ARRAY_ITEM_TYPE_TO_JAVA_TYPE_MAP.get(arrayItem.getClass());
	}

	/**
	 * Register array item to java type mapping.
	 *
	 * @param clazz Class of an array item.
	 * @param type Java type.
	 * @param <T> Array item class type.
	 */
	private static <T extends ArrayItem> void registerArrayItem(Class<T> clazz, String type) {
		ARRAY_ITEM_TYPE_TO_JAVA_TYPE_MAP.put(clazz, type);
	}

	/**
	 * Register array item to java type mapping.
	 *
	 * @param clazz Class of an array item.
	 * @param fn Handler function.
	 * @param <T> Array item class type.
	 */
	private <T extends ArrayItem> void registerArrayItem(Class<T> clazz,
														 Function<ArrayItemBuildRequest, ArrayItemHandlerResult> fn) {
		arrayItemHandlers.put(clazz, fn);
	}

	/**
	 * Registers java property to a property class.
	 *
	 * @param clazz Property class.
	 * @param type Java type.
	 * @param <T> Property class type.
	 */
	private static <T extends Property> void registerProperty(Class<T> clazz, String type) {
		PROPERTY_TO_JAVA_TYPE_MAP.put(clazz, type);
	}

	/**
	 * Register property handler.
	 *
	 * @param clazz Class which handler processes.
	 * @param fn Handler function.
	 * @param <T> Property class type.
	 */
	private <T extends Property> void registerProperty(Class<T> clazz,
													   Function<PropertyBuildRequest, PropertyHandlerResult> fn) {
		propertyHandlers.put(clazz, fn);
	}

	private <T extends Type> void registerType(Class<T> clazz, Function<TypeBuildRequest, Builder> fn) {
		typeHandlers.put(clazz, fn);
	}

	@Getter
	@AllArgsConstructor
	private class TypeBuildRequest<T extends Type> {
		private Domain domain;
		private T type;
	}

	@Getter
	@Setter
	private class PropertyBuildRequest<T extends Property> {
		private Domain domain;
		private T property;
		private JavaClassBuilder javaClassBuilder;
	}

	@Getter
	@Setter
	private class ArrayItemBuildRequest<T extends ArrayItem> {
		private Domain domain;
		private T property;
		private ArrayProperty arrayProperty;
		private JavaClassBuilder javaClassBuilder;
	}

	@Getter
	@Setter
	private class PropertyHandlerResult {
		private Builder builder;
		private String type;
	}

	@Getter
	@Setter
	private class ArrayItemHandlerResult {
		private Builder builder;
		private String type;
	}
}
