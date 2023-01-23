package com.github.kklisura.cdt.definition.builder.support.protocol.builder;

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

import com.github.kklisura.cdt.definition.builder.support.java.builder.Builder;
import com.github.kklisura.cdt.definition.builder.support.java.builder.JavaBuilderFactory;
import com.github.kklisura.cdt.definition.builder.support.java.builder.JavaImportAwareBuilder;
import com.github.kklisura.cdt.definition.builder.support.protocol.builder.support.DomainTypeResolver;
import com.github.kklisura.cdt.definition.builder.support.protocol.builder.support.TypeBuildRequest;
import com.github.kklisura.cdt.protocol.definition.types.Domain;
import com.github.kklisura.cdt.protocol.definition.types.Event;
import com.github.kklisura.cdt.protocol.definition.types.type.object.ObjectType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;

/**
 * Event builder builds the classes for the protocol events.
 *
 * @author Kenan Klisura
 */
public class EventBuilder extends TypesBuilder {
  private String typesPackageName;

  /**
   * Ctor.
   *
   * @param basePackageName Package name for events.
   * @param javaBuilderFactory Builder factory.
   * @param typesPackageName Package name where types reside.
   */
  public EventBuilder(
      String basePackageName, JavaBuilderFactory javaBuilderFactory, String typesPackageName) {
    super(basePackageName, javaBuilderFactory, true);
    this.typesPackageName = typesPackageName;
  }

  @Override
  public List<Builder> build(Domain domain, DomainTypeResolver domainTypeResolver) {
    final List<Event> domainEvents = domain.getEvents();
    List<EventHandlerResult> result = new ArrayList<>();

    if (CollectionUtils.isNotEmpty(domainEvents)) {
      for (Event event : domainEvents) {
        result.add(buildEvent(event, domain, domainTypeResolver));
      }
    }

    return result.stream()
        .map(EventHandlerResult::getBuilder)
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
  }

  @Override
  protected String addRefImportStatement(
      JavaImportAwareBuilder importAwareBuilder,
      String refValue,
      ObjectType objectType,
      Domain domain,
      DomainTypeResolver domainTypeResolver) {
    return addRefImportStatement(
        typesPackageName, importAwareBuilder, refValue, objectType, domain, domainTypeResolver);
  }

  private EventHandlerResult buildEvent(
      Event event, Domain domain, DomainTypeResolver domainTypeResolver) {
    ObjectType objectType = new ObjectType();
    objectType.setId(event.getName());
    objectType.setExperimental(event.getExperimental());
    objectType.setDeprecated(event.getDeprecated());
    objectType.setDescription(event.getDescription());
    objectType.setProperties(event.getParameters());

    TypeBuildRequest<ObjectType> request =
        new TypeBuildRequest<>(domain, objectType, domainTypeResolver);
    TypeHandlerResult result = buildClass(request);
    return new EventHandlerResult(result.getBuilder());
  }

  @Getter
  @AllArgsConstructor
  private class EventHandlerResult {
    private Builder builder;
  }
}
