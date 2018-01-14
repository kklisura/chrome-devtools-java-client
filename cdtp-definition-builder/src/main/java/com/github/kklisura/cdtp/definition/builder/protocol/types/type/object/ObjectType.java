package com.github.kklisura.cdtp.definition.builder.protocol.types.type.object;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.kklisura.cdtp.definition.builder.protocol.types.Type;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Object type.
 *
 * @author Kenan Klisura
 */
@Setter
@Getter
@JsonDeserialize(using = JsonDeserializer.None.class)
public class ObjectType extends Type {
	private List<Property> properties;
}
