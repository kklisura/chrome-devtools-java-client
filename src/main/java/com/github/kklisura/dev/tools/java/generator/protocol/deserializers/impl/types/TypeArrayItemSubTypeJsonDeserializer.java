package com.github.kklisura.dev.tools.java.generator.protocol.deserializers.impl.types;

import com.github.kklisura.dev.tools.java.generator.protocol.deserializers.BaseSubTypeJsonDeserializer;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.array.ArrayItem;

/**
 * ArrayItem type json deserializer.
 *
 * @author Kenan Klisura
 */
public class TypeArrayItemSubTypeJsonDeserializer extends BaseSubTypeJsonDeserializer<ArrayItem> {
	public TypeArrayItemSubTypeJsonDeserializer() {
		super(ArrayItem.class);
	}
}
