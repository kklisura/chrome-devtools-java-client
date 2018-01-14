package com.github.kklisura.dev.tools.java.generator.support.java.builder;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Method param.
 *
 * @author Kenan Klisura
 */
@Getter
@Setter
public class MethodParam {
	private String type;

	private String name;

	private List<String> annotations;
}
