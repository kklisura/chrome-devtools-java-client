package com.github.kklisura.dev.tools.java.generator.support.java.builder.impl;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.expr.ThisExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.kklisura.dev.tools.java.generator.support.java.builder.JavaEnumBuilder;
import com.github.kklisura.dev.tools.java.generator.support.java.builder.utils.JavadocUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Java enum builder implementation.
 *
 * @author Kenan Klisura
 */
public class JavaEnumBuilderImpl extends BaseBuilder implements JavaEnumBuilder {
	private static final String PROPERTY_NAME = "propertyName";

	private EnumDeclaration declaration;

	/**
	 * Instantiates new java enum builder.
	 *
	 * @param packageName Package name.
	 * @param name        Enum name.
	 */
	public JavaEnumBuilderImpl(String packageName, String name) {
		super(packageName);
		declaration = getCompilationUnit().addEnum(name);
		declaration.setName(name);

		declaration.addField("String", PROPERTY_NAME, Modifier.FINAL);

		// Add constructor
		ConstructorDeclaration constructorDeclaration = declaration.addConstructor();
		Parameter parameter = new Parameter();
		parameter.setName(PROPERTY_NAME);
		parameter.setType("String");
		parameter.setModifier(Modifier.FINAL, true);
		constructorDeclaration.setParameters(NodeList.nodeList(parameter));

		BlockStmt constructorBody = new BlockStmt();

		FieldAccessExpr fieldAccessExpr = new FieldAccessExpr(new ThisExpr(), PROPERTY_NAME);
		AssignExpr assignExpr = new AssignExpr(fieldAccessExpr, new NameExpr(PROPERTY_NAME), AssignExpr.Operator.ASSIGN);
		ExpressionStmt expressionStmt = new ExpressionStmt(assignExpr);

		constructorBody.setStatements(NodeList.nodeList(expressionStmt));
		constructorDeclaration.setBody(constructorBody);

		// Add getter
		String methodName = "get" + StringUtils.capitalize(PROPERTY_NAME);
		MethodDeclaration getterMethodDeclaration = declaration.addMethod(methodName, Modifier.PUBLIC);
		getterMethodDeclaration.setType("String");

		BlockStmt methodBody = new BlockStmt();

		ReturnStmt returnStmt = new ReturnStmt(new FieldAccessExpr(new ThisExpr(), PROPERTY_NAME));
		methodBody.setStatements(NodeList.nodeList(returnStmt));

		getterMethodDeclaration.setBody(methodBody);
	}

	/**
	 * Adds new enum constant.
	 *
	 * @param name Constant name.
	 * @param value Real constant value.
	 */
	public void addEnumConstant(String name, String value) {
		EnumConstantDeclaration enumConstantDeclaration = new EnumConstantDeclaration(name);
		enumConstantDeclaration.setArguments(NodeList.nodeList(new StringLiteralExpr(value)));
		declaration.addEntry(enumConstantDeclaration);
	}

	/**
	 * Sets the java doc comment for this enum.
	 *
	 * @param comment Comment.
	 */
	@Override
	public void setJavaDoc(String comment) {
		declaration.setJavadocComment(JavadocUtils.createJavadocComment(comment));
	}

	/**
	 * Return enum name.
	 *
	 * @return Enum name.
	 */
	@Override
	public String getName() {
		return declaration.getNameAsString();
	}
}
