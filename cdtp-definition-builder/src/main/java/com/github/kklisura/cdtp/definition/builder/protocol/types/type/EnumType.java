package com.github.kklisura.cdtp.definition.builder.protocol.types.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.kklisura.cdtp.definition.builder.protocol.types.Type;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Enum type.
 *
 * @author Kenan Klisura
 */
@Setter
@Getter
@JsonDeserialize(using = JsonDeserializer.None.class)
public class EnumType extends Type {
  @JsonProperty("enum")
  private List<String> enumValues;
}
