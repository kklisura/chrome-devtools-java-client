package com.github.kklisura.dev.tools.java.generator.support.protocol.generator.support;

import com.github.kklisura.dev.tools.java.generator.protocol.types.Domain;
import com.github.kklisura.dev.tools.java.generator.protocol.types.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Type build request.
 *
 * @author Kenan Klisura
 */
@Getter
@Setter
@AllArgsConstructor
public class TypeBuildRequest<T extends Type> {
	private Domain domain;
	private T type;

	DomainTypeResolver domainTypeResolver;
}
