package com.github.kklisura.cdt.definition.builder.support.protocol.builder.support;

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

import com.github.kklisura.cdt.protocol.definition.types.Domain;
import com.github.kklisura.cdt.protocol.definition.types.Type;
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
