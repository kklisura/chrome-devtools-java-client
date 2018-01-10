package com.github.kklisura.dev.tools.java.generator.support.java.builder;

import com.github.javaparser.utils.SourceRoot;

import java.io.IOException;

/**
 * Builder contract.
 *
 * @author Kenan Klisura
 */
public interface Builder {
	/**
	 * Builds an item. Generates a code.
	 *
	 * @param sourceRoot Source root.
	 * @throws IOException If saving fails.
	 */
	void build(SourceRoot sourceRoot) throws IOException;
}
