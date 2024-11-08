package com.github.kklisura.cdt.definition.builder.support.utils;

/*-
 * #%L
 * cdt-java-protocol-builder
 * %%
 * Copyright (C) 2018 - 2023 Kenan Klisura
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.github.kklisura.cdt.definition.builder.support.protocol.builder.support.DomainTypeResolver;
import com.github.kklisura.cdt.protocol.definition.DevToolsProtocol;
import com.github.kklisura.cdt.protocol.definition.types.Domain;
import com.github.kklisura.cdt.protocol.definition.types.Type;
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
