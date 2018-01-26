package com.github.kklisura.cdtp.definition.builder.protocol.deserializers.impl.properties;

import com.github.kklisura.cdtp.definition.builder.protocol.deserializers.BaseSubTypeJsonDeserializer;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.object.properties.array.ArrayItem;

/**
 * Property array item json deserializer.
 *
 * @author Kenan Klisura
 */
public class PropertyArrayItemSubTypeJsonDeserializer
    extends BaseSubTypeJsonDeserializer<ArrayItem> {
  public PropertyArrayItemSubTypeJsonDeserializer() {
    super(ArrayItem.class);
  }
}
