package com.github.kklisura.cdtp.definition.builder.support.java.builder.impl;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.utils.CodeGenerationUtils;
import com.github.javaparser.utils.SourceRoot;
import com.github.kklisura.cdtp.definition.builder.support.java.builder.Builder;
import lombok.Getter;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Base builder.
 *
 * @author Kenan Klisura
 */
@Getter
public abstract class BaseBuilder implements Builder {
	private CompilationUnit compilationUnit;
	private String packageName;

	/**
	 * Instantiates a new base builder given a package name.
	 *
	 * @param packageName Package name.
	 */
	public BaseBuilder(String packageName) {
		this.compilationUnit = new CompilationUnit(packageName);
		this.packageName = packageName;
	}

	/**
	 * Returns package name for this builder.
	 *
	 * @return Package name for this builder.
	 */
	protected String getPackageName() {
		return packageName;
	}

	/**
	 * Returns a name for this build item.
	 * @return Build item name.
	 */
	public abstract String getName();

	/**
	 * Builds an item. Generates a code.
	 *
	 * @param sourceRoot Root path to save generated code.
	 * @throws IOException If saving fails.
	 */
	@Override
	public void build(SourceRoot sourceRoot) throws IOException {
		String packageName = compilationUnit.getPackageDeclaration().get().getName().getIdentifier();

		Path path = sourceRoot.getRoot().resolve(CodeGenerationUtils.packageToPath(packageName));

		compilationUnit.setStorage(path.resolve(getName() + ".java"));
		sourceRoot.add(compilationUnit);
	}
}
