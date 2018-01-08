package com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.array.items;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.array.ArrayItem;

/**
 * Typed array item item.
 *
 * @author Kenan Klisura
 */
@JsonDeserialize(using = JsonDeserializer.None.class)
public class TypedArrayItem extends ArrayItem {
}
