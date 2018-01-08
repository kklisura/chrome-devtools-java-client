package com.github.kklisura.dev.tools.java.generator.protocol.types.type.object;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.kklisura.dev.tools.java.generator.protocol.deserializers.impl.properties.PropertySubTypeJsonDeserializer;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.AnyProperty;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.ArrayProperty;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.BooleanProperty;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.EnumProperty;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.IntegerProperty;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.NumberProperty;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.ObjectProperty;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.RefProperty;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.StringProperty;
import lombok.Getter;

/**
 * Object property.
 *
 * @author Kenan Klisura
 */
@Getter
@JsonSubTypes({
		@JsonSubTypes.Type(value = StringProperty.class, name = "string"),
		@JsonSubTypes.Type(value = NumberProperty.class, name = "number"),
		@JsonSubTypes.Type(value = BooleanProperty.class, name = "boolean"),
		@JsonSubTypes.Type(value = AnyProperty.class, name = "any"),
		@JsonSubTypes.Type(value = ArrayProperty.class, name = "array"),
		@JsonSubTypes.Type(value = RefProperty.class, name = "ref"),
		@JsonSubTypes.Type(value = IntegerProperty.class, name = "integer"),
		@JsonSubTypes.Type(value = EnumProperty.class, name = "enum"),
		@JsonSubTypes.Type(value = ObjectProperty.class, name = "object")
})
@JsonDeserialize(using = PropertySubTypeJsonDeserializer.class)
public abstract class Property {
	private String name;

	private String type;

	private String description;

	private Boolean optional;

	private Boolean experimental;

	private Boolean deprecated;
}
