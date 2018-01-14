package com.github.kklisura.cdtp.definition.builder.support.protocol.builder.support;

import com.github.kklisura.cdtp.definition.builder.protocol.types.Domain;
import com.github.kklisura.cdtp.definition.builder.protocol.types.Type;
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
