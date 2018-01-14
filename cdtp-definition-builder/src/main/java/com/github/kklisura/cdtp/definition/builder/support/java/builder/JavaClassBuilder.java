package com.github.kklisura.cdtp.definition.builder.support.java.builder;

/**
 * Java class builder.
 *
 * @author Kenan Klisura
 */
public interface JavaClassBuilder extends JavaImportAwareBuilder {
	/**
	 * Sets the java doc for this class.
	 *
	 * @param comment Comment.
	 */
	void setJavaDoc(String comment);

	/**
	 * Adds a private field to this class with a given type.
	 *
	 * @param name Field name. Could not be in correct format.
	 * @param type Field type.
	 * @param description Field description.
	 */
	void addPrivateField(String name, String type, String description);

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
}
