package com.github.kklisura.cdt.protocol.definition.types.type.array.items;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
public class RefArrayItem extends TypedArrayItem {
  @JsonProperty("$ref")
  private String ref;
}
