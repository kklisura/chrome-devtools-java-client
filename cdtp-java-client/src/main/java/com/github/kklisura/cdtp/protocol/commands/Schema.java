package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.types.schema.Domain;
import java.util.List;

/**
 * This domain is deprecated.
 */
@Deprecated
public interface Schema {

	/**
	 * Returns supported domains.
	 */
	List<Domain> getDomains();
}
