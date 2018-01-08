package com.github.kklisura.dev.tools.java.generator.protocol.types;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

/**
 * Dev tools protocol version.
 *
 * @author Kenan Klisura
 */
@Getter
public class Version {
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private int major;

	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private int minor;
}
