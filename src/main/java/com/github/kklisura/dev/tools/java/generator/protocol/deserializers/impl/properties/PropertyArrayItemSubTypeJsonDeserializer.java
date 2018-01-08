package com.github.kklisura.dev.tools.java.generator.protocol.deserializers.impl.properties;

import com.github.kklisura.dev.tools.java.generator.protocol.deserializers.BaseSubTypeJsonDeserializer;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.properties.array.ArrayItem;

/**
 * Property array item json deserializer.
 *
 * @author Kenan Klisura
 */
public class PropertyArrayItemSubTypeJsonDeserializer extends BaseSubTypeJsonDeserializer<ArrayItem> {
	public PropertyArrayItemSubTypeJsonDeserializer() {
		super(ArrayItem.class);
	}
}
