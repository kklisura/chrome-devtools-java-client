package com.github.kklisura.cdtp.definition.builder.protocol.types;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.kklisura.cdtp.definition.builder.protocol.deserializers.impl.types.TypeSubTypeJsonDeserializer;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.ArrayType;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.IntegerType;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.object.ObjectType;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.EnumType;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.NumberType;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.StringType;
import lombok.Getter;
import lombok.Setter;

/**
 * Domain Type definition.
 *
 * @author Kenan Klisura
 */
@Setter
@Getter
@JsonSubTypes({
		@JsonSubTypes.Type(value = ObjectType.class, name = "object"),
		@JsonSubTypes.Type(value = StringType.class, name = "string"),
		@JsonSubTypes.Type(value = EnumType.class, name = "enum"),
		@JsonSubTypes.Type(value = IntegerType.class, name = "integer"),
		@JsonSubTypes.Type(value = NumberType.class, name = "number"),
		@JsonSubTypes.Type(value = ArrayType.class, name = "array")
})
@JsonDeserialize(using = TypeSubTypeJsonDeserializer.class)
public abstract class Type {
	private String id;

	private String type;

	private String description;

	private Boolean experimental;
}
