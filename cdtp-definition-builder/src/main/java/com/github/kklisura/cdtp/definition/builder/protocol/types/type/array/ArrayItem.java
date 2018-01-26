package com.github.kklisura.cdtp.definition.builder.protocol.types.type.array;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.kklisura.cdtp.definition.builder.protocol.deserializers.impl.types.TypeArrayItemSubTypeJsonDeserializer;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.array.items.IntegerArrayItem;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.array.items.NumberArrayItem;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.array.items.StringArrayItem;
import lombok.Getter;

/**
 * Array item type.
 *
 * @author Kenan Klisura
 */
@Getter
@JsonSubTypes({
  @JsonSubTypes.Type(value = StringArrayItem.class, name = "string"),
  @JsonSubTypes.Type(value = IntegerArrayItem.class, name = "integer"),
  @JsonSubTypes.Type(value = NumberArrayItem.class, name = "number")
})
@JsonDeserialize(using = TypeArrayItemSubTypeJsonDeserializer.class)
public abstract class ArrayItem {
  private String description;
}
