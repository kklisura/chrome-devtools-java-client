package com.github.kklisura.cdt.definition.builder.protocol.deserializers.impl.properties;

import com.github.kklisura.cdt.definition.builder.protocol.deserializers.BaseSubTypeJsonDeserializer;
import com.github.kklisura.cdt.definition.builder.protocol.types.type.object.Property;

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
