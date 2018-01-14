package com.github.kklisura.cdtp.definition.builder.support.java.builder.impl;

import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.MarkerAnnotationExpr;
import com.github.javaparser.ast.expr.Name;
import com.github.kklisura.cdtp.definition.builder.support.java.builder.JavaInterfaceBuilder;
import com.github.kklisura.cdtp.definition.builder.support.java.builder.support.MethodParam;
import com.github.kklisura.cdtp.definition.builder.support.java.builder.utils.JavadocUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static com.github.kklisura.cdtp.definition.builder.support.java.builder.impl.utils.CompilationUnitUtils.isImported;
import static com.github.kklisura.cdtp.definition.builder.support.java.builder.utils.JavadocUtils.INDENTATION_NO_INDENTATION;
import static com.github.kklisura.cdtp.definition.builder.support.java.builder.utils.JavadocUtils.INDENTATION_TAB;

/**
 * Java interface builder.
 *
 * @author Kenan Klisura
 */
public class JavaInterfaceBuilderImpl extends BaseBuilder implements JavaInterfaceBuilder {
	private static final String DEPRECATED_ANNOTATION = "Deprecated";

	private String name;
	private ClassOrInterfaceDeclaration declaration;
	private String annotationsPackage;

	/**
	 * Instantiates a new class builder implementation.
	 *
	 * @param packageName        Package name.
	 * @param name               Interface name.
	 * @param annotationsPackage Package where support annotations are located (Optional, Experimental...)
	 */
	public JavaInterfaceBuilderImpl(String packageName, String name, String annotationsPackage) {
		super(packageName);
		this.name = name;
		this.declaration = getCompilationUnit().addInterface(name);
		this.annotationsPackage = annotationsPackage;
	}

	@Override
	public void setJavaDoc(String comment) {
		if (StringUtils.isNotEmpty(comment)) {
			declaration.setJavadocComment(JavadocUtils.createJavadocComment(comment, INDENTATION_NO_INDENTATION));
		}
	}

	@Override
	public void addAnnotation(String annotationName) {
		MarkerAnnotationExpr annotationExpr = new MarkerAnnotationExpr();
		annotationExpr.setName(annotationName);
		declaration.addAnnotation(annotationExpr);

		if (!DEPRECATED_ANNOTATION.equals(annotationName)) {
			addImport(annotationsPackage, annotationName);
		}
	}

	@Override
	public void addMethodAnnotation(String methodName, String annotationName) {
		List<MethodDeclaration> methods = declaration.getMethodsByName(methodName);
		for (MethodDeclaration methodDeclaration : methods) {
			methodDeclaration.addMarkerAnnotation(annotationName);
		}

		if (!DEPRECATED_ANNOTATION.equals(annotationName)) {
			addImport(annotationsPackage, annotationName);
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void addImport(String packageName, String object) {
		Name name = new Name();
		name.setQualifier(new Name(packageName));
		name.setIdentifier(object);

		if (!getPackageName().equalsIgnoreCase(packageName) && !isImported(getCompilationUnit(), name)) {
			getCompilationUnit().addImport(new ImportDeclaration(name, false, false));
		}
	}

	@Override
	public void addMethod(String name, String description, List<MethodParam> methodParams, String returnType) {
		MethodDeclaration methodDeclaration = declaration.addMethod(name);
		methodDeclaration.setBody(null);

		if (StringUtils.isNotEmpty(description)) {
			methodDeclaration.setJavadocComment(JavadocUtils.createJavadocComment(description, INDENTATION_TAB));
		}

		if (StringUtils.isNotEmpty(returnType)) {
			methodDeclaration.setType(returnType);
		}

		if (CollectionUtils.isNotEmpty(methodParams)) {
			for (MethodParam methodParam : methodParams) {
				Parameter parameter = methodDeclaration.addAndGetParameter(methodParam.getType(),
						methodParam.getName());

				if (CollectionUtils.isNotEmpty(methodParam.getAnnotations())) {
					for (String annotation : methodParam.getAnnotations()) {
						parameter.addMarkerAnnotation(annotation);

						if (!DEPRECATED_ANNOTATION.equals(annotation)) {
							addImport(annotationsPackage, annotation);
						}
					}
				}
			}
		}
	}
}
