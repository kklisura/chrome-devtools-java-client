package com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.array;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.kklisura.dev.tools.java.generator.protocol.deserializers.impl.properties.PropertyArrayItemSubTypeJsonDeserializer;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.array.items.AnyArrayItem;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.array.items.EnumArrayItem;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.array.items.IntegerArrayItem;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.array.items.NumberArrayItem;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.array.items.ObjectArrayItem;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.array.items.RefArrayItem;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.array.items.StringArrayItem;
import lombok.Getter;
import lombok.Setter;

/**
 * Array item property.
 *
 * @author Kenan Klisura
 */
@Setter
@Getter
@JsonSubTypes({
		@JsonSubTypes.Type(value = RefArrayItem.class, name = "ref"),
		@JsonSubTypes.Type(value = AnyArrayItem.class, name = "any"),
		@JsonSubTypes.Type(value = EnumArrayItem.class, name = "enum"),
		@JsonSubTypes.Type(value = StringArrayItem.class, name = "string"),
		@JsonSubTypes.Type(value = IntegerArrayItem.class, name = "integer"),
		@JsonSubTypes.Type(value = NumberArrayItem.class, name = "number"),
		@JsonSubTypes.Type(value = ObjectArrayItem.class, name = "object")
})
@JsonDeserialize(using = PropertyArrayItemSubTypeJsonDeserializer.class)
public abstract class ArrayItem {
	private String type;

	private String description;
}
