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
import com.github.kklisura.dev.tools.java.generator.support.java.builder.Builder;
import com.github.kklisura.dev.tools.java.generator.support.java.builder.JavaBuilderFactory;
import com.github.kklisura.dev.tools.java.generator.support.java.builder.JavaClassBuilder;
import com.github.kklisura.dev.tools.java.generator.support.java.builder.JavaEnumBuilder;
import com.github.kklisura.dev.tools.java.generator.support.java.builder.support.CombinedBuilders;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

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

	private static final Map<Object, String> PROPERTY_TO_JAVA_TYPE_MAP = new HashMap<>();

	private final Map<Object, Function<TypeBuildRequest, Builder>> typeHandlers = new HashMap<>();
	private final Map<Object, Function<PropertyBuildRequest, Builder>> propertyHandlers = new HashMap<>();

	private JavaBuilderFactory javaBuilderFactory;
	private String basePackageName;

	// Register property class to java type mapping.
	static {
		PROPERTY_TO_JAVA_TYPE_MAP.put(StringProperty.class, "String");
		PROPERTY_TO_JAVA_TYPE_MAP.put(NumberProperty.class, "Double");
		PROPERTY_TO_JAVA_TYPE_MAP.put(BooleanProperty.class, "Boolean");
		PROPERTY_TO_JAVA_TYPE_MAP.put(AnyProperty.class, "Object");
		PROPERTY_TO_JAVA_TYPE_MAP.put(IntegerProperty.class, "Integer");
		PROPERTY_TO_JAVA_TYPE_MAP.put(EnumProperty.class, "Enum");
		PROPERTY_TO_JAVA_TYPE_MAP.put(ObjectProperty.class, "Object");
		PROPERTY_TO_JAVA_TYPE_MAP.put(ArrayProperty.class, "List");
		PROPERTY_TO_JAVA_TYPE_MAP.put(RefProperty.class, "Object");
	}

	// Register handlers of property types.
	{
		typeHandlers.put(EnumType.class, this::buildEnumType);
		typeHandlers.put(ObjectType.class, this::buildClass);
	}

	// Register handlers of properties
	{
		propertyHandlers.put(EnumProperty.class, this::addEnumProperty);
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

		String packageName = buildPackageName(domain);

		return buildEnum(packageName, type.getId(), type.getDescription(), type.getEnumValues());
	}

	private Builder buildClass(TypeBuildRequest<ObjectType> request) {
		final Domain domain = request.getDomain();
		final ObjectType type = request.getType();

		String packageName = buildPackageName(domain);

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

	private void addProperties(Domain domain, JavaClassBuilder classBuilder, ObjectType type,
							   List<Builder> additionalBuilders) {
		if (CollectionUtils.isNotEmpty(type.getProperties())) {
			for (Property property : type.getProperties()) {
				Function<PropertyBuildRequest, Builder> handler = propertyHandlers.get(property.getClass());
				if (handler != null) {
					additionalBuilders.add(handler.apply(new PropertyBuildRequest<>(domain, property)));
				} else {
					// TODO(kklisura): Add support for description properties; Add javadoc on getters
					classBuilder.addPrivateField(property.getName(), getJavaType(property));

					if (Boolean.TRUE.equals(property.getDeprecated())) {
						classBuilder.addFieldAnnotation(property.getName(), DEPRECATED_ANNOTATION);
					}

					if (Boolean.TRUE.equals(property.getExperimental())) {
						classBuilder.addFieldAnnotation(property.getName(), EXPERIMENTAL_ANNOTATION);
					}

					if (Boolean.TRUE.equals(property.getOptional())) {
						classBuilder.addFieldAnnotation(property.getName(), OPTIONAL_ANNOTATION);
					}
				}
			}
		}
	}

	private Builder addEnumProperty(PropertyBuildRequest<EnumProperty> request) {
		final Domain domain = request.getDomain();
		final EnumProperty property = request.getProperty();

		String packageName = buildPackageName(domain);
		String name = property.getName();

		return buildEnum(packageName, name, property.getDescription(), property.getEnumValues());
	}

	private Builder buildEnum(String packageName, String name, String description, List<String> enumValues) {
		JavaEnumBuilder enumBuilder = javaBuilderFactory.createEnumBuilder(packageName, name);
		enumBuilder.setJavaDoc(description);

		if (CollectionUtils.isNotEmpty(enumValues)) {
			for (String enumValue : enumValues) {
				enumBuilder.addEnumConstant(enumValue);
			}
		}

		return enumBuilder;
	}

	private String buildPackageName(Domain domain) {
		return this.basePackageName + "." + domain.getDomain().toLowerCase();
	}

	private String getJavaType(Property property) {
		return PROPERTY_TO_JAVA_TYPE_MAP.get(property.getClass());
	}

	@Getter
	@AllArgsConstructor
	public class TypeBuildRequest<T extends Type> {
		private Domain domain;
		private T type;
	}

	@Getter
	@AllArgsConstructor
	public class PropertyBuildRequest<T extends Property> {
		private Domain domain;
		private T property;
	}
}
