package com.github.kklisura.cdtp.definition.builder.protocol.types.type.object.properties;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.object.Property;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Enum property.
 *
 * @author Kenan Klisura
 */
@Getter
@Setter
@JsonDeserialize(using = JsonDeserializer.None.class)
public class EnumProperty extends Property {
  @JsonProperty("enum")
  private List<String> enumValues;
}
