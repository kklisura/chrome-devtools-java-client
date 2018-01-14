package com.github.kklisura.cdtp.definition.builder.protocol.deserializers.impl.types;

import com.github.kklisura.cdtp.definition.builder.protocol.deserializers.BaseSubTypeJsonDeserializer;
import com.github.kklisura.cdtp.definition.builder.protocol.types.Type;

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
