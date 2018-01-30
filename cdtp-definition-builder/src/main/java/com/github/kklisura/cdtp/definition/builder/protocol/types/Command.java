package com.github.kklisura.cdtp.definition.builder.protocol.types;

import com.github.kklisura.cdtp.definition.builder.protocol.types.type.object.Property;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Command.
 *
 * @author Kenan Klisura
 */
@Getter
@Setter
public class Command {
  private String name;

  private String description;

  private Boolean deprecated;

  private Boolean experimental;

  private String redirect;

  private List<Property> parameters;

  private List<Property> returns;

  private List<String> handlers;
}
