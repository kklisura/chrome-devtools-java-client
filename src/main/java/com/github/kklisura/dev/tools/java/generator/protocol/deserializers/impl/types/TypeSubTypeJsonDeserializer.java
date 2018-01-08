package com.github.kklisura.dev.tools.java.generator.protocol.deserializers.impl.types;

import com.github.kklisura.dev.tools.java.generator.protocol.deserializers.BaseSubTypeJsonDeserializer;
import com.github.kklisura.dev.tools.java.generator.protocol.types.Type;

/**
 * Type json deserializer.
 *
 * @author Kenan Klisura
 */
public class TypeSubTypeJsonDeserializer extends BaseSubTypeJsonDeserializer<Type> {
	public TypeSubTypeJsonDeserializer() {
		super(Type.class);
	}
}
