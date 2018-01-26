package com.github.kklisura.cdtp.definition.builder.protocol.types.type.object.properties;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.object.Property;
import lombok.Getter;
import lombok.Setter;

/**
 * Ref property.
 *
 * @author Kenan Klisura
 */
@Getter
@Setter
@JsonDeserialize(using = JsonDeserializer.None.class)
public class RefProperty extends Property {
  @JsonProperty("$ref")
  private String ref;
}
