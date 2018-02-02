package com.github.kklisura.cdt.definition.builder.protocol.types;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Domain object.
 *
 * @author Kenan Klisura
 */
@Getter
@Setter
public class Domain {
  private String domain;

  private Boolean experimental;

  private String description;

  private Boolean deprecated;

  private List<String> dependencies;

  private List<Type> types;

  private List<Command> commands;

  private List<Event> events;
}
