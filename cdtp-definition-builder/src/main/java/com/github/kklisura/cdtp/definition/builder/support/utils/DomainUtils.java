package com.github.kklisura.cdtp.definition.builder.support.utils;

import com.github.kklisura.cdtp.definition.builder.protocol.DevToolsProtocol;
import com.github.kklisura.cdtp.definition.builder.protocol.types.Domain;
import com.github.kklisura.cdtp.definition.builder.protocol.types.Type;
import com.github.kklisura.cdtp.definition.builder.support.protocol.builder.support.DomainTypeResolver;
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
  public static DomainTypeResolver devToolsProtocolResolver(DevToolsProtocol protocol) {
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
