package com.github.kklisura.cdt.definition.builder.protocol.types.type.object;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.kklisura.cdt.definition.builder.protocol.types.Type;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Object type.
 *
 * @author Kenan Klisura
 */
@Setter
@Getter
@JsonDeserialize(using = JsonDeserializer.None.class)
public class ObjectType extends Type {
  private List<Property> properties;
}
