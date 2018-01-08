package com.github.kklisura.dev.tools.java.generator.protocol.types.type.array;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.kklisura.dev.tools.java.generator.protocol.deserializers.impl.types.TypeArrayItemSubTypeJsonDeserializer;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.array.items.TypedArrayItem;
import lombok.Getter;

/**
 * Array item type.
 *
 * @author Kenan Klisura
 */
@Getter
@JsonSubTypes({
		@JsonSubTypes.Type(value = TypedArrayItem.class, name = "string"),
		@JsonSubTypes.Type(value = TypedArrayItem.class, name = "integer"),
		@JsonSubTypes.Type(value = TypedArrayItem.class, name = "number")
})
@JsonDeserialize(using = TypeArrayItemSubTypeJsonDeserializer.class)
public abstract class ArrayItem {
	private String description;
}
