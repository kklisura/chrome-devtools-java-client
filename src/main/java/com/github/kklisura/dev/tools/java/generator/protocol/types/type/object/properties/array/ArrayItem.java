package com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.array;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.kklisura.dev.tools.java.generator.protocol.deserializers.impl.properties.PropertyArrayItemSubTypeJsonDeserializer;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.array.items.AnyArrayItem;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.array.items.EnumArrayItem;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.array.items.RefArrayItem;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.array.items.TypedArrayItem;
import lombok.Getter;

/**
 * Array item property.
 *
 * @author Kenan Klisura
 */
@Getter
@JsonSubTypes({
		@JsonSubTypes.Type(value = TypedArrayItem.class, name = "string"),
		@JsonSubTypes.Type(value = RefArrayItem.class, name = "ref"),
		@JsonSubTypes.Type(value = AnyArrayItem.class, name = "any"),
		@JsonSubTypes.Type(value = TypedArrayItem.class, name = "integer"),
		@JsonSubTypes.Type(value = TypedArrayItem.class, name = "number"),
		@JsonSubTypes.Type(value = EnumArrayItem.class, name = "enum"),
		@JsonSubTypes.Type(value = TypedArrayItem.class, name = "object")
})
@JsonDeserialize(using = PropertyArrayItemSubTypeJsonDeserializer.class)
public abstract class ArrayItem {
	private String type;

	private String description;
}
