package com.github.kklisura.dev.tools.java.generator.protocol.types;

import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.Property;
import lombok.Getter;

import java.util.List;

/**
 * Event type.
 *
 * @author Kenan Klisura
 */
@Getter
public class Event {
	private String name;

	private String description;

	private Boolean experimental;

	private List<Property> parameters;
}
