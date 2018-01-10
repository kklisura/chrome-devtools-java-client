package com.github.kklisura.dev.tools.java.generator.support.java.builder.impl;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.kklisura.dev.tools.java.generator.support.java.builder.JavaClassBuilder;
import com.github.kklisura.dev.tools.java.generator.support.java.builder.utils.JavadocUtils;

/**
 * Java class generator.
 *
 * @author Kenan Klisura
 */
public class JavaClassBuilderImpl extends BaseBuilder implements JavaClassBuilder {
	private ClassOrInterfaceDeclaration declaration;
	private String name;

	/**
	 * Instantiates a new class builder implementation.
	 *
	 * @param packageName Package name.
	 * @param name Class name.
	 */
	public JavaClassBuilderImpl(String packageName, String name) {
		super(packageName);
		this.declaration = getCompilationUnit().addClass(name);
		this.name = name;
	}

	/**
	 * Returns a class name.
	 * @return Class name.
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Sets the java doc for this class.
	 * @param comment Comment.
	 */
	@Override
	public void setJavaDoc(String comment) {
		declaration.setJavadocComment(JavadocUtils.createJavadocComment(comment));
	}

	/**
	 * Adds the private field of a given type to this class.
	 * @param name Field name.
	 * @param type Field type.
	 */
	@Override
	public void addPrivateField(String name, String type) {
		declaration.addField(type, name, Modifier.PRIVATE);
	}
}
