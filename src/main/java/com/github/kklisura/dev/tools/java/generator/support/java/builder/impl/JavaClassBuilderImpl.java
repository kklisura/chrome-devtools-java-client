package com.github.kklisura.dev.tools.java.generator.support.java.builder.impl;

import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.MarkerAnnotationExpr;
import com.github.javaparser.ast.expr.Name;
import com.github.kklisura.dev.tools.java.generator.support.java.builder.JavaClassBuilder;
import com.github.kklisura.dev.tools.java.generator.support.java.builder.utils.JavadocUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * Java class generator.
 *
 * @author Kenan Klisura
 */
public class JavaClassBuilderImpl extends BaseBuilder implements JavaClassBuilder {
	public static final Logger LOGGER = LoggerFactory.getLogger(JavaClassBuilderImpl.class);

	private String name;
	private ClassOrInterfaceDeclaration declaration;

	private String annotationsPackage;

	/**
	 * Instantiates a new class builder implementation.
	 *
	 * @param packageName Package name.
	 * @param name Class name.
	 * @param annotationsPackage Package where support annotations are located (Optional, Experimental...)
	 */
	public JavaClassBuilderImpl(String packageName, String name, String annotationsPackage) {
		super(packageName);
		this.name = name;
		this.declaration = getCompilationUnit().addClass(name);
		this.annotationsPackage = annotationsPackage;
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

			addImport(annotationsPackage, annotationName);
		} else {
			throw new RuntimeException("Field " + name + " is not present in current class.");
		}
	}

	@Override
	public void addAnnotation(String annotationName) {
		MarkerAnnotationExpr annotationExpr = new MarkerAnnotationExpr();
		annotationExpr.setName(annotationName);
		declaration.addAnnotation(annotationExpr);

		addImport(annotationsPackage, annotationName);
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

		if (!isAlreadyImported(name)) {
			getCompilationUnit().addImport(new ImportDeclaration(name, false, false));
		}
	}

	private boolean isAlreadyImported(Name name) {
		NodeList<ImportDeclaration> imports = getCompilationUnit().getImports();
		if (CollectionUtils.isNotEmpty(imports)) {
			for (ImportDeclaration importDeclaration : imports) {
				if (name.equals(importDeclaration.getName())) {
					return true;
				}
			}
		}

		return false;
	}
}
