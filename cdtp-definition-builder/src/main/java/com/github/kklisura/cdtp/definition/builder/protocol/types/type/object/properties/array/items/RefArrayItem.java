package com.github.kklisura.cdtp.definition.builder.protocol.types.type.object.properties.array.items;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.object.properties.array.ArrayItem;
import lombok.Getter;
import lombok.Setter;

/**
 * Ref array item type.
 *
 * @author Kenan Klisura
 */
@Getter
@Setter
@JsonDeserialize(using = JsonDeserializer.None.class)
public class RefArrayItem extends ArrayItem {
  @JsonProperty("$ref")
  private String ref;
}
