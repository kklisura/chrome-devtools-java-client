package com.github.kklisura.dev.tools.java.generator.protocol;

import com.github.kklisura.dev.tools.java.generator.protocol.types.Domain;
import com.github.kklisura.dev.tools.java.generator.protocol.types.Version;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * DevTools protocol definition.
 *
 * @author Kenan Klisura
 */
@Getter
@Setter
public class DevToolsProtocol {
	private Version version;

	private List<Domain> domains;
}
