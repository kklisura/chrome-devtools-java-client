package com.github.kklisura.dev.tools.java.generator.protocol.deserializers.impl.properties;

import com.github.kklisura.dev.tools.java.generator.protocol.deserializers.BaseSubTypeJsonDeserializer;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.Property;

/**
 * Property json deserializer.
 *
 * @author Kenan Klisura
 */
public class PropertySubTypeJsonDeserializer extends BaseSubTypeJsonDeserializer<Property> {
	public PropertySubTypeJsonDeserializer() {
		super(Property.class);
	}
}
