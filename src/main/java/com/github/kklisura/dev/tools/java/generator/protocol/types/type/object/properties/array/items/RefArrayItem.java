package com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.array.items;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.array.ArrayItem;
import lombok.Getter;

/**
 * Ref array item type.
 *
 * @author Kenan Klisura
 */
@Getter
@JsonDeserialize(using = JsonDeserializer.None.class)
public class RefArrayItem extends ArrayItem {
	@JsonProperty("$ref")
	private String ref;
}
