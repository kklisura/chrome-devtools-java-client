package com.github.kklisura.cdtp.definition.builder.protocol.deserializers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedClassResolver;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.List;

/**
 * Base sub-type json deserializer uses type property
 *
 * @param <T> the type parameter
 * @author Kenan Klisura
 */
public class BaseSubTypeJsonDeserializer<T> extends StdDeserializer<T> {
	private static final String TYPE_PROPERTY = "type";
	private static final String REF_PROPERTY = "$ref";
	private static final String ENUM_PROPERTY = "enum";

	private static final String STRING_PROPERTY_VALUE = "string";

	/**
	 * Creates a new instance of BaseSubTypeJsonDeserializer with a given class.
	 *
	 * @param clazz Base class.
	 */
	public BaseSubTypeJsonDeserializer(Class<?> clazz) {
		super(clazz);
	}

	@SuppressWarnings("unchecked")
	public T deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
		ObjectCodec objectCodec = jsonParser.getCodec();
		ObjectNode objectNode = objectCodec.readTree(jsonParser);

		String typeValue = null;
		JsonNode type = objectNode.get(TYPE_PROPERTY);
		if (type != null) {
			typeValue = type.asText();

			if (STRING_PROPERTY_VALUE.equals(typeValue)) {
				if (objectNode.get(ENUM_PROPERTY) != null) {
					typeValue = "enum";
				}
			}
		} else {
			if (objectNode.get(REF_PROPERTY) != null) {
				typeValue = "ref";
			}
		}

		if (typeValue == null) {
			throw new JsonParseException(jsonParser, "Unknown object type.");
		}

		DeserializationConfig config = context.getConfig();

		AnnotatedClass annotatedClass = AnnotatedClassResolver.resolveWithoutSuperTypes(config, handledType());
		List<NamedType> subtypes = config.getAnnotationIntrospector().findSubtypes(annotatedClass);

		for (NamedType namedType : subtypes) {
			if (typeValue.equals(namedType.getName())) {
				return (T) objectCodec.treeToValue(objectNode, namedType.getType());
			}
		}

		throw new JsonParseException(jsonParser, "Unknown object type " + typeValue + ".");
	}
}
