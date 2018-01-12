package com.github.kklisura.dev.tools.java.generator.support.java.builder.impl;

import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.MarkerAnnotationExpr;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.kklisura.dev.tools.java.generator.support.java.builder.JavaClassBuilder;
import com.github.kklisura.dev.tools.java.generator.support.java.builder.utils.JavadocUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Java class generator.
 *
 * @author Kenan Klisura
 */
public class JavaClassBuilderImpl extends BaseBuilder implements JavaClassBuilder {
	public static final Logger LOGGER = LoggerFactory.getLogger(JavaClassBuilderImpl.class);

	private static final Map<String, Class> NATIVE_IMPORTS = new HashMap<>();

	static {
		NATIVE_IMPORTS.put("List", List.class);
	}

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
	 *
	 * @param name Field name.
	 * @param type Field type.
	 */
	@Override
	public void addPrivateField(String name, String type) {
		declaration.addField(type, name, Modifier.PRIVATE);
	}

	/**
	 * Adds annotation to field.
	 *
	 * @param name Field name. Could not be in correct format.
	 * @param annotationName Annotation name.
	 */
	@Override
	public void addFieldAnnotation(String name, String annotationName) {
		Optional<FieldDeclaration> fieldDeclaration = declaration.getFieldByName(name);
		if (fieldDeclaration.isPresent()) {
			MarkerAnnotationExpr annotationExpr = new MarkerAnnotationExpr();
			annotationExpr.setName(annotationName);
			fieldDeclaration.get().addAnnotation(annotationExpr);
		} else {
			throw new RuntimeException("Field " + name + " is not present in current class.");
		}
	}

	@Override
	public void addAnnotation(String annotationName) {
		NormalAnnotationExpr annotationExpr = new NormalAnnotationExpr();
		annotationExpr.setName(annotationName);
		declaration.addAnnotation(annotationExpr);
	}

	@Override
	public void generateGettersAndSetters() {
		List<FieldDeclaration> fields = declaration.getFields();
		for (FieldDeclaration fieldDeclaration : fields) {
			fieldDeclaration.createGetter();
			fieldDeclaration.createSetter();
		}
	}

	@Override
	public void addImport(String packageName, String object) {
		Name name = new Name();
		name.setQualifier(new Name(packageName));
		name.setIdentifier(object);

		getCompilationUnit().addImport(new ImportDeclaration(name, false, false));
	}
}
