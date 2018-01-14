package com.github.kklisura.dev.tools.java.generator.support.protocol.generator.support;

import com.github.kklisura.dev.tools.java.generator.protocol.types.Type;

/**
 * Domain type resolves type given a domain and its object.
 */
@FunctionalInterface
public interface DomainTypeResolver {
	Type resolve(String domain, String object);
}
