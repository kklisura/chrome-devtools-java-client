package com.github.kklisura.cdt.definition.builder.protocol.deserializers.impl.types;

import com.github.kklisura.cdt.definition.builder.protocol.deserializers.BaseSubTypeJsonDeserializer;
import com.github.kklisura.cdt.definition.builder.protocol.types.type.array.ArrayItem;

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
