package com.github.kklisura.dev.tools.java.generator.support.java.builder.utils;

import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.javadoc.Javadoc;
import com.github.javaparser.javadoc.description.JavadocDescription;
import com.github.javaparser.javadoc.description.JavadocSnippet;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

/**
 * Javadoc utils.
 *
 * @author Kenan Klisura
 */
@UtilityClass
public class JavadocUtils {
	/**
	 * Creates a javadoc comment object given a comment string.
	 * @param comment Comment.
	 * @return Javadoc comment object.
	 */
	public JavadocComment createJavadocComment(String comment) {
		JavadocSnippet javadocSnippet = new JavadocSnippet("");
		if (StringUtils.isNotEmpty(comment)) {
			javadocSnippet = new JavadocSnippet(comment);
		}

		JavadocDescription description = new JavadocDescription();
		description.addElement(javadocSnippet);
		Javadoc javadoc = new Javadoc(description);
		return javadoc.toComment(" ");
	}
}
