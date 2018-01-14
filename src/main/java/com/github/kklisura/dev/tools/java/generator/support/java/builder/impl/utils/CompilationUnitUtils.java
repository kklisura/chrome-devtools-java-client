package com.github.kklisura.dev.tools.java.generator.support.java.builder.impl.utils;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.Name;
import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.CollectionUtils;

/**
 * Compilation unit utility class.
 *
 * @author Kenan Klisura
 */
@UtilityClass
public class CompilationUnitUtils {
	/**
	 * Checks if given name is already imported on compilation unit.
	 *
	 * @param compilationUnit Compilation unit.
	 * @param name            Name to check for.
	 * @return True if name is already imported.
	 */
	public static boolean isImported(CompilationUnit compilationUnit, Name name) {
		NodeList<ImportDeclaration> imports = compilationUnit.getImports();
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
