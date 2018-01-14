package com.github.kklisura.cdtp.definition.builder.support.java.builder;

/**
 * Import aware builder.
 *
 * @author Kenan Klisura
 */
public interface JavaImportAwareBuilder extends Builder {
	/**
	 * Adds import statement to this interface.
	 *
	 * @param packageName Package name.
	 * @param object      Object name.
	 */
	void addImport(String packageName, String object);
}
