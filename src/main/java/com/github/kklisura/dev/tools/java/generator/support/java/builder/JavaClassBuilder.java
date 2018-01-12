package com.github.kklisura.dev.tools.java.generator.support.java.builder;

/**
 * Java class builder.
 *
 * @author Kenan Klisura
 */
public interface JavaClassBuilder extends Builder {
	/**
	 * Sets the java doc for this class.
	 * @param comment Comment.
	 */
	void setJavaDoc(String comment);

	/**
	 * Adds a private field to this class with a given type.
	 * @param name Field name. Could not be in correct format.
	 * @param type Field type.
	 */
	void addPrivateField(String name, String type);

	/**
	 * Adds annotation to specified field.
	 *
	 * @param name Field name. Could not be in correct format.
	 * @param annotationName Annotation name.
	 */
	void addFieldAnnotation(String name, String annotationName);

	/**
	 * Adds annotation to this class.
	 * @param annotationName Annotation name.
	 */
	void addAnnotation(String annotationName);

	/**
	 * Generates getters and setters for all fields.
	 */
	void generateGettersAndSetters();

	/**
	 * Adds an import statement given a package name and its object name.
	 *
	 * @param packageName Package name.
	 * @param object Object name.
	 */
	void addImport(String packageName, String object);
}
