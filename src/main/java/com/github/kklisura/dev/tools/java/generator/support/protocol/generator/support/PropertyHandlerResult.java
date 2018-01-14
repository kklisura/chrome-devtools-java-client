package com.github.kklisura.dev.tools.java.generator.support.protocol.generator.support;

import com.github.kklisura.dev.tools.java.generator.support.java.builder.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Property handler result.
 *
 * @author Kenan Klisura
 */
@Getter
@Setter
public class PropertyHandlerResult {
	private Builder builder;
	private String type;
}
