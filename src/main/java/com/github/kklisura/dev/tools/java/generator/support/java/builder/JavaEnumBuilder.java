package com.github.kklisura.dev.tools.java.generator.support.java.builder;

/**
 * Java enum type builder.
 *
 * @author Kenan Klisura
 */
public interface JavaEnumBuilder extends Builder {
	/**
	 * Adds new enum constant.
	 * @param name Constant name.
	 */
	void addEnumConstant(String name);

	/**
	 * Sets the java doc for this class.
	 * @param comment Comment.
	 */
	void setJavaDoc(String comment);
}
