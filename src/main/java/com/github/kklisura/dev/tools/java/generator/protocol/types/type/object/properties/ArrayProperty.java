package com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.Property;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.array.ArrayItem;
import lombok.Getter;

/**
 * Array property
 *
 * @author Kenan Klisura
 */
@Getter
@JsonDeserialize(using = JsonDeserializer.None.class)
public class ArrayProperty extends Property {
	private ArrayItem items;

	private Long minItems;

	private Long maxItems;
}
