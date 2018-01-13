package com.github.kklisura.dev.tools.java.generator.support.utils;

import com.github.kklisura.dev.tools.java.generator.protocol.DevToolsProtocol;
import com.github.kklisura.dev.tools.java.generator.protocol.types.Domain;
import com.github.kklisura.dev.tools.java.generator.protocol.types.Type;
import com.github.kklisura.dev.tools.java.generator.support.protocol.generator.TypesBuilder;
import lombok.experimental.UtilityClass;

/**
 * Domain utils.
 *
 * @author Kenan Klisura
 */
@UtilityClass
public class DomainUtils {
	/**
	 * Builds a resolver based on dev tools protocol.
	 *
	 * @param protocol Dev tools protocol.
	 * @return Domain type resolver.
	 */
	public static TypesBuilder.DomainTypeResolver devToolsProtocolResolver(DevToolsProtocol protocol) {
		return ((domain, object) -> {
			for (Domain protocolDomain : protocol.getDomains()) {
				if (protocolDomain.getDomain().equals(domain)) {
					for (Type type : protocolDomain.getTypes()) {
						if (type.getId().equals(object)) {
							return type;
						}
					}

					throw new RuntimeException("Type " + object + " on domain " + domain + " not found!");
				}
			}

			throw new RuntimeException("Type " + object + " on domain " + domain + " not found!");
		});
	}
}
