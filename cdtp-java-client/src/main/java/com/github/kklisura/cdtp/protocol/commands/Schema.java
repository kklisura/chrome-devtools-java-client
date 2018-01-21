package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Returns;
import com.github.kklisura.cdtp.protocol.types.schema.Domain;
import java.util.List;

/**
 * Provides information about the protocol schema.
 */
public interface Schema {

	/**
	 * Returns supported domains.
	 */
	@Returns("domains")
	List<Domain> getDomains();
}
