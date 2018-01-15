package com.github.kklisura.cdtp.definition.builder;

import lombok.Getter;
import lombok.Setter;
import org.kohsuke.args4j.Option;

import java.io.File;

/**
 * Application configuration.
 *
 * @author Kenan Klisura
 */
@Getter
@Setter
public class Configuration {
	@Option(name = "--base-package", usage = "Base package name (com.github.kklisura.cdtp.protocol)",
			metaVar = "PACKAGE", required = true)
	private String basePackage;

	@Option(name = "--protocol", usage = "JSON protocol file (protocol.json)", metaVar = "PROTOCOL_JSON",
			required = true)
	private File protocolFile;

	@Option(name = "--output", usage = "Output project location.", metaVar = "DIR", required = true)
	private File outputProjectLocation;
}
