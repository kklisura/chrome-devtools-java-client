package com.github.kklisura.cdtp.definition.builder.support.protocol.builder;

import com.github.kklisura.cdtp.definition.builder.protocol.types.Domain;
import com.github.kklisura.cdtp.definition.builder.protocol.types.Type;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.ArrayType;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.EnumType;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.IntegerType;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.NumberType;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.StringType;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.object.ObjectType;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.object.Property;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.object.properties.AnyProperty;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.object.properties.ArrayProperty;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.object.properties.BooleanProperty;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.object.properties.EnumProperty;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.object.properties.IntegerProperty;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.object.properties.NumberProperty;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.object.properties.ObjectProperty;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.object.properties.RefProperty;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.object.properties.StringProperty;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.object.properties.array.ArrayItem;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.object.properties.array.items.AnyArrayItem;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.object.properties.array.items.EnumArrayItem;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.object.properties.array.items.IntegerArrayItem;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.object.properties.array.items.NumberArrayItem;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.object.properties.array.items.ObjectArrayItem;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.object.properties.array.items.RefArrayItem;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.object.properties.array.items.StringArrayItem;
import com.github.kklisura.cdtp.definition.builder.support.java.builder.Builder;
import com.github.kklisura.cdtp.definition.builder.support.java.builder.JavaBuilderFactory;
import com.github.kklisura.cdtp.definition.builder.support.java.builder.JavaClassBuilder;
import com.github.kklisura.cdtp.definition.builder.support.java.builder.JavaEnumBuilder;
import com.github.kklisura.cdtp.definition.builder.support.java.builder.JavaImportAwareBuilder;
import com.github.kklisura.cdtp.definition.builder.support.java.builder.support.CombinedBuilders;
import com.github.kklisura.cdtp.definition.builder.support.protocol.builder.support.DomainTypeResolver;
import com.github.kklisura.cdtp.definition.builder.support.protocol.builder.support.PropertyHandlerResult;
import com.github.kklisura.cdtp.definition.builder.support.protocol.builder.support.TypeBuildRequest;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * TypesBuilder builds a types for a domain.
 *
 * @author Kenan Klisura
 */
public class TypesBuilder {
	public static final DomainTypeResolver NULL_DOMAIN_TYPE_RESOLVER = ((domain, object) -> null);

	private static final Logger LOGGER = LoggerFactory.getLogger(TypesBuilder.class);

	public static final String LIST_PACKAGE = "java.util";
	public static final String LIST_CLASS_NAME = "List";

	public static final String DEPRECATED_ANNOTATION = "Deprecated";
	public static final String EXPERIMENTAL_ANNOTATION = "Experimental";
	public static final String OPTIONAL_ANNOTATION = "Optional";

	private static final Map<Class, String> TYPE_TO_JAVA_TYPE_MAP = new HashMap<>();
	private static final Map<Class, String> PROPERTY_TO_JAVA_TYPE_MAP = new HashMap<>();
	private static final Map<Class, String> ARRAY_ITEM_TYPE_TO_JAVA_TYPE_MAP = new HashMap<>();
	private static final Map<Class, String> ARRAY_TYPE_ITEM_TYPE_TO_JAVA_TYPE_MAP = new HashMap<>();

	private final Map<Class, Function<TypeBuildRequest, TypeHandlerResult>> typeHandlers = new HashMap<>();
	private final Map<Class, Function<PropertyBuildRequest, PropertyHandlerResult>> propertyHandlers = new HashMap<>();
	private final Map<Class, Function<ArrayItemBuildRequest, ArrayItemHandlerResult>> arrayItemHandlers = new HashMap<>();

	private String basePackageName;
	private JavaBuilderFactory javaBuilderFactory;

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

	// Registers array item types to java types.
	static {
		registerTypeArrayItem(com.github.kklisura.cdtp.definition.builder.protocol.types.type.array.items.StringArrayItem.class, "String");
		registerTypeArrayItem(com.github.kklisura.cdtp.definition.builder.protocol.types.type.array.items.NumberArrayItem.class, "Double");
		registerTypeArrayItem(com.github.kklisura.cdtp.definition.builder.protocol.types.type.array.items.IntegerArrayItem.class, "Integer");
	}

	// Register type class mapping to java types.
	static {
		registerType(StringType.class, "String");
		registerType(IntegerType.class, "Integer");
		registerType(NumberType.class, "Double");
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
	 * @param domainTypeResolver Domain type resolver.
	 * @return List of generated items.
	 */
	public List<Builder> build(Domain domain, DomainTypeResolver domainTypeResolver) {
		final List<Type> domainTypes = domain.getTypes();
		List<TypeHandlerResult> result = new ArrayList<>();

		if (CollectionUtils.isNotEmpty(domainTypes)) {
			for (Type type : domainTypes) {
				result.add(buildType(type, domain, domainTypeResolver));
			}
		}

		return result.stream()
				.map(TypeHandlerResult::getBuilder)
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
	}

	private TypeHandlerResult buildType(Type type, Domain domain, DomainTypeResolver domainTypeResolver) {
		Function<TypeBuildRequest, TypeHandlerResult> buildRequestBuilderFunction = typeHandlers.get(type.getClass());
		if (buildRequestBuilderFunction != null) {
			TypeBuildRequest typeBuildRequest = new TypeBuildRequest<>(domain, type, domainTypeResolver);
			return buildRequestBuilderFunction.apply(typeBuildRequest);
		}

		return new TypeHandlerResult();
	}

	private TypeHandlerResult buildEnumType(TypeBuildRequest<EnumType> request) {
		final Domain domain = request.getDomain();
		final EnumType type = request.getType();

		String packageName = com.github.kklisura.cdtp.definition.builder.support.utils.StringUtils.buildPackageName(basePackageName, domain.getDomain().toLowerCase());
		String name = com.github.kklisura.cdtp.definition.builder.support.utils.StringUtils.toEnumClass(type.getId());

		TypeHandlerResult result = new TypeHandlerResult();
		result.setBuilder(buildEnum(packageName, name, type.getDescription(), type.getEnumValues()));
		return result;
	}

	public TypeHandlerResult buildClass(TypeBuildRequest<ObjectType> request) {
		final Domain domain = request.getDomain();
		final ObjectType type = request.getType();

		String packageName = com.github.kklisura.cdtp.definition.builder.support.utils.StringUtils.buildPackageName(basePackageName, domain.getDomain().toLowerCase());
		String name = com.github.kklisura.cdtp.definition.builder.support.utils.StringUtils.toEnumClass(type.getId());

		JavaClassBuilder classBuilder = javaBuilderFactory.createClassBuilder(packageName, name);
		if (StringUtils.isNotEmpty(type.getDescription())) {
			classBuilder.setJavaDoc(type.getDescription());
		}

		if (Boolean.TRUE.equals(type.getExperimental())) {
			classBuilder.addAnnotation(EXPERIMENTAL_ANNOTATION);
		}

		List<Builder> additionalBuilders = new ArrayList<>();
		addProperties(request, classBuilder, additionalBuilders);

		classBuilder.generateGettersAndSetters();

		TypeHandlerResult result = new TypeHandlerResult();

		if (additionalBuilders.isEmpty()) {
			result.setBuilder(classBuilder);
		} else {
			additionalBuilders.add(classBuilder);
			result.setBuilder(new CombinedBuilders(additionalBuilders));
		}

		return result;
	}

	private void addProperties(TypeBuildRequest<ObjectType> request, JavaClassBuilder javaClassBuilder,
							   List<Builder> additionalBuilders) {
		final ObjectType type = request.getType();

		if (CollectionUtils.isNotEmpty(type.getProperties())) {
			for (Property property : type.getProperties()) {
				PropertyHandlerResult result = addProperty(property, request, javaClassBuilder);
				if (result.getBuilder() != null) {
					additionalBuilders.add(result.getBuilder());
				}
			}
		}
	}

	private PropertyHandlerResult addProperty(Property property, TypeBuildRequest<ObjectType> request,
											  JavaClassBuilder javaClassBuilder) {
		PropertyHandlerResult result = getPropertyHandleResult(property, request, javaClassBuilder);
		javaClassBuilder.addPrivateField(property.getName(), result.getType(), property.getDescription());

		if (Boolean.TRUE.equals(property.getDeprecated())) {
			javaClassBuilder.addFieldAnnotation(property.getName(), DEPRECATED_ANNOTATION);
		}

		if (Boolean.TRUE.equals(property.getExperimental())) {
			javaClassBuilder.addFieldAnnotation(property.getName(), EXPERIMENTAL_ANNOTATION);
		}

		if (Boolean.TRUE.equals(property.getOptional())) {
			javaClassBuilder.addFieldAnnotation(property.getName(), OPTIONAL_ANNOTATION);
		}

		return result;
	}

	public PropertyHandlerResult getPropertyHandleResult(Property property, TypeBuildRequest<ObjectType> request,
														 JavaImportAwareBuilder importAwareBuilder) {
		PropertyHandlerResult result = new PropertyHandlerResult();

		Function<PropertyBuildRequest, PropertyHandlerResult> fn = propertyHandlers.get(property.getClass());
		if (fn != null) {
			// Build request object
			PropertyBuildRequest<Property> buildRequest = new PropertyBuildRequest<>();
			buildRequest.setImportAwareBuilder(importAwareBuilder);
			buildRequest.setDomain(request.getDomain());
			buildRequest.setProperty(property);
			buildRequest.setDomainTypeResolver(request.getDomainTypeResolver());
			buildRequest.setObjectType(request.getType());
			result = fn.apply(buildRequest);
		} else {
			result.setType(getPropertyJavaType(property));
		}

		return result;
	}

	private PropertyHandlerResult addEnumProperty(PropertyBuildRequest<EnumProperty> request) {
		final Domain domain = request.getDomain();
		final EnumProperty property = request.getProperty();

		String packageName = com.github.kklisura.cdtp.definition.builder.support.utils.StringUtils.buildPackageName(basePackageName, domain.getDomain().toLowerCase());
		String name = com.github.kklisura.cdtp.definition.builder.support.utils.StringUtils.toEnumClass(property.getName());

		PropertyHandlerResult result = new PropertyHandlerResult();
		result.setBuilder(buildEnum(packageName, name, property.getDescription(), property.getEnumValues()));
		result.setType(name);

		return result;
	}

	private PropertyHandlerResult addRefProperty(PropertyBuildRequest<RefProperty> request) {
		final RefProperty property = request.getProperty();

		String objectName = addRefImportStatement(request.getImportAwareBuilder(), property.getRef(),
				request.getObjectType(), request.getDomain(), request.getDomainTypeResolver());

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
			buildRequest.setImportAwareBuilder(request.getImportAwareBuilder());
			buildRequest.setDomain(request.getDomain());
			buildRequest.setProperty(arrayItem);
			buildRequest.setArrayProperty(arrayProperty);
			buildRequest.setDomainTypeResolver(request.getDomainTypeResolver());
			buildRequest.setObjectType(request.getObjectType());

			ArrayItemHandlerResult itemResult = fn.apply(buildRequest);

			result.setBuilder(itemResult.getBuilder());
			result.setType(itemResult.getType());
		} else {
			result.setType(buildArrayJavaType(getArrayItemJavaType(arrayItem)));
		}

		request.getImportAwareBuilder().addImport(LIST_PACKAGE, LIST_CLASS_NAME);

		return result;
	}

	private ArrayItemHandlerResult addRefArrayItem(ArrayItemBuildRequest<RefArrayItem> request) {
		final RefArrayItem property = request.getProperty();

		String objectName = addRefImportStatement(request.getImportAwareBuilder(), property.getRef(),
				request.getObjectType(), request.getDomain(), request.getDomainTypeResolver());

		ArrayItemHandlerResult result = new ArrayItemHandlerResult();
		result.setType(buildArrayJavaType(objectName));
		return result;
	}

	private ArrayItemHandlerResult addEnumArrayItem(ArrayItemBuildRequest<EnumArrayItem> request) {
		final Domain domain = request.getDomain();
		final EnumArrayItem property = request.getProperty();

		String packageName = com.github.kklisura.cdtp.definition.builder.support.utils.StringUtils.buildPackageName(basePackageName, domain.getDomain().toLowerCase());
		String name = com.github.kklisura.cdtp.definition.builder.support.utils.StringUtils.toEnumClass(request.getArrayProperty().getName());

		ArrayItemHandlerResult result = new ArrayItemHandlerResult();
		result.setBuilder(buildEnum(packageName, name, property.getDescription(), property.getEnumValues()));
		result.setType(buildArrayJavaType(name));
		return result;
	}

	protected String addRefImportStatement(JavaImportAwareBuilder importAwareBuilder, String refValue,
										   ObjectType objectType, Domain domain, DomainTypeResolver domainTypeResolver) {
		return addRefImportStatement(basePackageName, importAwareBuilder, refValue, objectType, domain,
				domainTypeResolver);
	}

	protected String addRefImportStatement(String packageName, JavaImportAwareBuilder importAwareBuilder,
										   String refValue, ObjectType objectType, Domain domain,
										   DomainTypeResolver domainTypeResolver) {
		String namespace = domain.getDomain();
		String ref = refValue;

		int i = refValue.indexOf('.');
		if (i != -1) {
			namespace = refValue.substring(0, i);
			ref = refValue.substring(i + 1);
		}

		// If this ref is pointing to some primitive type (integer, number, string...) we return their java types.
		Type type = domainTypeResolver.resolve(namespace, ref);
		if (isArrayType(type)) {
			importAwareBuilder.addImport(LIST_PACKAGE, LIST_CLASS_NAME);

			ArrayType arrayType = (ArrayType) type;
			return buildArrayJavaType(getArrayItemJavaType(arrayType.getItems()));
		}
		if (!isComplexType(type)) {
			return getTypeJavaType(type);
		}

		String importPackageName = com.github.kklisura.cdtp.definition.builder.support.utils.StringUtils.buildPackageName(packageName, namespace.toLowerCase());

		String currentObjectName = com.github.kklisura.cdtp.definition.builder.support.utils.StringUtils.toEnumClass(objectType.getId());
		if (currentObjectName.equals(ref)) {
			return importPackageName + "." + ref;
		}

		importAwareBuilder.addImport(importPackageName, ref);

		return ref;
	}

	private Builder buildEnum(String packageName, String name, String description, List<String> enumValues) {
		JavaEnumBuilder enumBuilder = javaBuilderFactory.createEnumBuilder(packageName, name);
		if (StringUtils.isNotEmpty(description)) {
			enumBuilder.setJavaDoc(description);
		}

		if (CollectionUtils.isNotEmpty(enumValues)) {
			for (String enumValue : enumValues) {
				enumBuilder.addEnumConstant(com.github.kklisura.cdtp.definition.builder.support.utils.StringUtils.toEnumConstant(enumValue), enumValue);
			}
		}

		return enumBuilder;
	}

	private static String getPropertyJavaType(Property property) {
		return PROPERTY_TO_JAVA_TYPE_MAP.get(property.getClass());
	}

	private static String getArrayItemJavaType(ArrayItem arrayItem) {
		return ARRAY_ITEM_TYPE_TO_JAVA_TYPE_MAP.get(arrayItem.getClass());
	}

	protected static String getArrayItemJavaType(com.github.kklisura.cdtp.definition.builder.protocol.types.type.array.ArrayItem arrayItem) {
		return ARRAY_TYPE_ITEM_TYPE_TO_JAVA_TYPE_MAP.get(arrayItem.getClass());
	}

	protected static String getTypeJavaType(Type type) {
		return TYPE_TO_JAVA_TYPE_MAP.get(type.getClass());
	}

	protected static String buildArrayJavaType(String type) {
		return LIST_CLASS_NAME + "<" + type + ">";
	}

	/**
	 * Register array item to java type mapping.
	 *
	 * @param clazz Class of an array item.
	 * @param javaType Java type.
	 * @param <T> Array item class type.
	 */
	private static <T extends ArrayItem> void registerArrayItem(Class<T> clazz, String javaType) {
		ARRAY_ITEM_TYPE_TO_JAVA_TYPE_MAP.put(clazz, javaType);
	}

	/**
	 * Register array item to java type mapping.
	 *
	 * @param clazz Class of an array item.
	 * @param javaType Java type.
	 * @param <T> Array item class type.
	 */
	private static <T extends com.github.kklisura.cdtp.definition.builder.protocol.types.type.array.ArrayItem> void registerTypeArrayItem(Class<T> clazz, String javaType) {
		ARRAY_TYPE_ITEM_TYPE_TO_JAVA_TYPE_MAP.put(clazz, javaType);
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
	 * @param javaType Java type.
	 * @param <T> Property class type.
	 */
	private static <T extends Property> void registerProperty(Class<T> clazz, String javaType) {
		PROPERTY_TO_JAVA_TYPE_MAP.put(clazz, javaType);
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

	/**
	 * Registers type handlers.
	 *
	 * @param clazz ype class.
	 * @param fn Handler.
	 * @param <T> Type class type.
	 */
	private <T extends Type> void registerType(Class<T> clazz, Function<TypeBuildRequest, TypeHandlerResult> fn) {
		typeHandlers.put(clazz, fn);
	}

	/**
	 * Registers types as java types.
	 *
	 * @param clazz Type class.
	 * @param javaType Java type.
	 * @param <T> Type class type.
	 */
	private static <T extends Type> void registerType(Class<T> clazz, String javaType) {
		TYPE_TO_JAVA_TYPE_MAP.put(clazz, javaType);
	}

	/**
	 * Returns true if given type is complex type. We assume complex types are those that need special handling
	 * like enums or classes.
	 *
	 * @param type Type.
	 * @return True if given type is complex type.
	 */
	protected boolean isComplexType(Type type) {
		return typeHandlers.containsKey(type.getClass());
	}

	/**
	 * Returns true if give type is array type.
	 *
	 * @param type Type.
	 * @return True if type is array type.
	 */
	protected boolean isArrayType(Type type) {
		return type instanceof ArrayType;
	}

	@Getter
	@Setter
	private class PropertyBuildRequest<T extends Property> {
		private Domain domain;
		private T property;
		private JavaImportAwareBuilder importAwareBuilder;
		private DomainTypeResolver domainTypeResolver;
		private ObjectType objectType;
	}

	@Getter
	@Setter
	private class ArrayItemBuildRequest<T extends ArrayItem> {
		private Domain domain;
		private T property;
		private ArrayProperty arrayProperty;
		private JavaImportAwareBuilder importAwareBuilder;
		private DomainTypeResolver domainTypeResolver;
		private ObjectType objectType;
	}

	@Getter
	@Setter
	protected class TypeHandlerResult {
		private Builder builder;
	}

	@Getter
	@Setter
	private class ArrayItemHandlerResult {
		private Builder builder;
		private String type;
	}
}
