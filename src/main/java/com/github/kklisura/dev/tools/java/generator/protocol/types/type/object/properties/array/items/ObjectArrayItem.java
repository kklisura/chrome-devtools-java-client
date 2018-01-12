package com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.array.items;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.array.ArrayItem;

/**
 * Object array item type.
 *
 * @author Kenan Klisura
 */
@JsonDeserialize(using = JsonDeserializer.None.class)
public class ObjectArrayItem extends ArrayItem {
}