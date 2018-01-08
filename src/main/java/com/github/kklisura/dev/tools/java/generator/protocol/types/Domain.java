package com.github.kklisura.dev.tools.java.generator.protocol.types;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
