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

import static com.github.kklisura.cdt.definition.builder.support.protocol.builder.TypesBuilder.LIST_CLASS_NAME;
import static com.github.kklisura.cdt.definition.builder.support.utils.StringUtils.*;

import com.github.kklisura.cdt.definition.builder.support.java.builder.Builder;
import com.github.kklisura.cdt.definition.builder.support.java.builder.JavaBuilderFactory;
import com.github.kklisura.cdt.definition.builder.support.java.builder.JavaInterfaceBuilder;
import com.github.kklisura.cdt.definition.builder.support.java.builder.support.CombinedBuilders;
import com.github.kklisura.cdt.definition.builder.support.java.builder.support.MethodParam;
import com.github.kklisura.cdt.definition.builder.support.protocol.builder.support.DomainTypeResolver;
import com.github.kklisura.cdt.definition.builder.support.protocol.builder.support.PropertyHandlerResult;
import com.github.kklisura.cdt.definition.builder.support.protocol.builder.support.TypeBuildRequest;
import com.github.kklisura.cdt.protocol.definition.types.Command;
import com.github.kklisura.cdt.protocol.definition.types.Domain;
import com.github.kklisura.cdt.protocol.definition.types.Event;
import com.github.kklisura.cdt.protocol.definition.types.type.object.ObjectType;
import com.github.kklisura.cdt.protocol.definition.types.type.object.Property;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Builds a domain commands.
 *
 * @author Kenan Klisura
 */
public class CommandBuilder {
  private static final String EVENT_PREFIX = "on";
  private static final String EVENT_LISTENER_ARGUMENT_NAME = "eventListener";
  private static final String EVENT_LISTENER_ARGUMENT_TYPE = "EventHandler";
  private static final String EVENT_LISTENER_RESULT = "EventListener";

  private static final String NEW_LINE = "\r\n";

  private static final String COMMENT_PARAM = "@param";
  private static final String EMPTY_SPACE = " ";
  private static final String OBJECT_CLASS_PROPERTY = ".class";

  private String basePackageName;
  private String typesPackageName;
  private String eventPackageName;

  private String supportTypesPackageName;

  private JavaBuilderFactory javaBuilderFactory;

  /**
   * Creates a new command builder.
   *
   * @param basePackageName Base package name for this command.
   * @param javaBuilderFactory Java builder factory.
   * @param typesPackageName Package name for types.
   * @param eventPackageName Package name for events.
   * @param supportTypesPackageName Support types package name.
   */
  public CommandBuilder(
      String basePackageName,
      JavaBuilderFactory javaBuilderFactory,
      String typesPackageName,
      String eventPackageName,
      String supportTypesPackageName) {
    this.javaBuilderFactory = javaBuilderFactory;

    this.basePackageName = basePackageName;
    this.typesPackageName = typesPackageName;
    this.eventPackageName = eventPackageName;

    this.supportTypesPackageName = supportTypesPackageName;
  }

  /**
   * Build builder.
   *
   * @param domain the domain
   * @return the builder
   */
  public Builder build(Domain domain, DomainTypeResolver domainTypeResolver) {
    JavaInterfaceBuilder interfaceBuilder =
        javaBuilderFactory.createInterfaceBuilder(basePackageName, toEnumClass(domain.getDomain()));

    if (StringUtils.isNotEmpty(domain.getDescription())) {
      interfaceBuilder.setJavaDoc(domain.getDescription());
    }

    if (Boolean.TRUE.equals(domain.getExperimental())) {
      interfaceBuilder.addAnnotation(TypesBuilder.EXPERIMENTAL_ANNOTATION);
    }

    if (Boolean.TRUE.equals(domain.getDeprecated())) {
      interfaceBuilder.addAnnotation(TypesBuilder.DEPRECATED_ANNOTATION);
    }

    List<Builder> builders = new LinkedList<>();

    addCommands(domain, interfaceBuilder, domainTypeResolver, builders);
    addEvents(domain, interfaceBuilder);

    if (builders.isEmpty()) {
      return interfaceBuilder;
    }
    builders.add(interfaceBuilder);
    return new CombinedBuilders(builders);
  }

  private void addEvents(Domain domain, JavaInterfaceBuilder interfaceBuilder) {
    List<Event> events = domain.getEvents();
    if (CollectionUtils.isNotEmpty(events)) {
      for (Event event : events) {
        final String method = EVENT_PREFIX + StringUtils.capitalize(event.getName());

        interfaceBuilder.addImport(supportTypesPackageName, EVENT_LISTENER_RESULT);
        interfaceBuilder.addImport(supportTypesPackageName, EVENT_LISTENER_ARGUMENT_TYPE);

        final String eventsPackageName =
            buildPackageName(eventPackageName, domain.getDomain().toLowerCase());

        interfaceBuilder.addImport(eventsPackageName, toEnumClass(event.getName()));

        MethodParam methodParam = new MethodParam();
        methodParam.setName(EVENT_LISTENER_ARGUMENT_NAME);
        methodParam.setType(
            EVENT_LISTENER_ARGUMENT_TYPE + "<" + toEnumClass(event.getName()) + ">");

        interfaceBuilder.addMethod(
            method,
            event.getDescription(),
            Collections.singletonList(methodParam),
            EVENT_LISTENER_RESULT);

        interfaceBuilder.addParametrizedMethodAnnotation(
            method, TypesBuilder.EVENT_NAME, event.getName());

        if (Boolean.TRUE.equals(event.getDeprecated())) {
          interfaceBuilder.addMethodAnnotation(method, TypesBuilder.DEPRECATED_ANNOTATION);
        }

        if (Boolean.TRUE.equals(event.getExperimental())) {
          interfaceBuilder.addMethodAnnotation(method, TypesBuilder.EXPERIMENTAL_ANNOTATION);
        }
      }
    }
  }

  /** Adds commands to this interface. */
  private void addCommands(
      Domain domain,
      JavaInterfaceBuilder interfaceBuilder,
      DomainTypeResolver domainTypeResolver,
      List<Builder> builders) {
    List<Command> commands = domain.getCommands();
    if (CollectionUtils.isNotEmpty(commands)) {
      for (Command command : commands) {
        // Do not generate commands with redirect value. Source
        // https://github.com/ChromeDevTools/debugger-protocol-viewer/issues/26
        if (StringUtils.isEmpty(command.getRedirect())) {
          processCommand(command, domain, interfaceBuilder, domainTypeResolver, builders);
        }
      }
    }
  }

  private void processCommand(
      Command command,
      Domain domain,
      JavaInterfaceBuilder interfaceBuilder,
      DomainTypeResolver domainTypeResolver,
      List<Builder> builders) {
    List<Property> parameters = command.getParameters();
    if (parameters == null) {
      parameters = new ArrayList<>();
    }

    // Generate methods with mandatory params first
    List<Property> mandatoryParams =
        filterParameters(parameters, property -> !Boolean.TRUE.equals(property.getOptional()));
    command.setParameters(mandatoryParams);
    addCommand(command, domain, interfaceBuilder, domainTypeResolver, builders);

    if (mandatoryParams.size() != parameters.size()) {
      // Generate methods with rest of the params
      command.setParameters(parameters);
      addCommand(command, domain, interfaceBuilder, domainTypeResolver, builders);
    }
  }

  private void addCommand(
      Command command,
      Domain domain,
      JavaInterfaceBuilder interfaceBuilder,
      DomainTypeResolver domainTypeResolver,
      List<Builder> builders) {
    final String method = command.getName();

    List<MethodParam> methodParams =
        buildMethodParams(command, domain, interfaceBuilder, domainTypeResolver, builders);

    ReturnType returnType =
        buildReturnType(command, domain, interfaceBuilder, domainTypeResolver, builders);
    String description =
        buildMethodParamDescription(command.getDescription(), command.getParameters());

    interfaceBuilder.addMethod(method, description, methodParams, returnType.getType());

    if (Boolean.TRUE.equals(command.getDeprecated())) {
      interfaceBuilder.addMethodAnnotation(method, TypesBuilder.DEPRECATED_ANNOTATION);
    }

    if (Boolean.TRUE.equals(command.getExperimental())) {
      interfaceBuilder.addMethodAnnotation(method, TypesBuilder.EXPERIMENTAL_ANNOTATION);
    }

    List<Property> returns = command.getReturns();
    if (CollectionUtils.isNotEmpty(returns)) {
      if (returns.size() == 1) {
        interfaceBuilder.addParametrizedMethodAnnotation(
            method, TypesBuilder.RETURNS_ANNOTATION, returns.get(0).getName());

        if (returnType.isTyped()) {
          interfaceBuilder.addParametrizedMethodAnnotation(
              method,
              TypesBuilder.RETURN_TYPE_PARAMETER_ANNOTATION,
              buildReturnClasses(returnType.getSubType()));
        }
      }
    }
  }

  private String buildMethodParamDescription(String description, List<Property> parameters) {
    StringBuilder result = new StringBuilder();
    if (StringUtils.isNotEmpty(description)) {
      result.append(description);
    }

    if (CollectionUtils.isNotEmpty(parameters)) {
      result.append(NEW_LINE);

      for (Property property : parameters) {
        result.append(NEW_LINE);
        result.append(COMMENT_PARAM + EMPTY_SPACE);
        result.append(property.getName());
        if (StringUtils.isNotEmpty(property.getDescription())) {
          result.append(EMPTY_SPACE);
          result.append(property.getDescription());
        }
      }
    }

    return result.toString();
  }

  public static class ReturnType {
    private static final ReturnType VOID = new ReturnType(null);

    private final String type;
    private final String subType;
    private final boolean typed;

    public ReturnType(String type) {
      this(type, null, false);
    }

    public ReturnType(String type, String subType, boolean typed) {
      this.type = type;
      this.subType = subType;
      this.typed = typed;
    }

    public String getType() {
      return type;
    }

    public String getSubType() {
      return subType;
    }

    public boolean isTyped() {
      return typed;
    }
  }

  private ReturnType buildReturnType(
      Command command,
      Domain domain,
      JavaInterfaceBuilder interfaceBuilder,
      DomainTypeResolver domainTypeResolver,
      List<Builder> builders) {
    List<Property> returns = command.getReturns();

    TypesBuilder typesBuilder = new TypesBuilder(typesPackageName, javaBuilderFactory, false, true);

    if (CollectionUtils.isNotEmpty(returns)) {
      if (returns.size() == 1) {
        final Property property = returns.get(0);

        ObjectType objectType = new ObjectType();
        objectType.setId(toEnumClass(domain.getDomain()));
        TypeBuildRequest<ObjectType> request =
            new TypeBuildRequest<>(domain, objectType, domainTypeResolver);

        PropertyHandlerResult result =
            typesBuilder.getPropertyHandleResult(property, request, interfaceBuilder);

        addBuilder(domain, property, result, interfaceBuilder, builders);

        return new ReturnType(result.getType(), result.getSubType(), result.isTyped());
      } else {
        String name = getReturnTypeFromGetter(command.getName());

        ObjectType objectType = new ObjectType();
        objectType.setId(name);
        objectType.setProperties(returns);

        TypeBuildRequest<ObjectType> request =
            new TypeBuildRequest<>(domain, objectType, domainTypeResolver);

        TypesBuilder.TypeHandlerResult result = typesBuilder.buildClass(request);

        builders.add(result.getBuilder());

        // Since these properties are not ref type, we need to manually import them.
        String packageName = buildPackageName(typesPackageName, domain.getDomain().toLowerCase());
        interfaceBuilder.addImport(packageName, name);

        return new ReturnType(name);
      }
    }

    return ReturnType.VOID;
  }

  private List<MethodParam> buildMethodParams(
      Command command,
      Domain domain,
      JavaInterfaceBuilder interfaceBuilder,
      DomainTypeResolver domainTypeResolver,
      List<Builder> builders) {
    List<MethodParam> methodParams = new ArrayList<>();

    TypesBuilder typesBuilder = new TypesBuilder(typesPackageName, javaBuilderFactory);

    List<Property> parameters = command.getParameters();
    if (CollectionUtils.isNotEmpty(parameters)) {
      for (Property property : parameters) {
        ObjectType objectType = new ObjectType();
        objectType.setId(toEnumClass(command.getName()));
        TypeBuildRequest<ObjectType> request =
            new TypeBuildRequest<>(domain, objectType, domainTypeResolver);

        PropertyHandlerResult result =
            typesBuilder.getPropertyHandleResult(property, request, interfaceBuilder);

        addBuilder(domain, property, result, interfaceBuilder, builders);

        MethodParam methodParam = new MethodParam();
        methodParam.setType(result.getType());
        methodParam.setName(property.getName());
        methodParam.setAnnotations(new ArrayList<>());

        if (Boolean.TRUE.equals(property.getDeprecated())) {
          methodParam
              .getAnnotations()
              .add(new MethodParam.Annotation(TypesBuilder.DEPRECATED_ANNOTATION));
        }

        if (Boolean.TRUE.equals(property.getExperimental())) {
          methodParam
              .getAnnotations()
              .add(new MethodParam.Annotation(TypesBuilder.EXPERIMENTAL_ANNOTATION));
        }

        if (Boolean.TRUE.equals(property.getOptional())) {
          methodParam
              .getAnnotations()
              .add(new MethodParam.Annotation(TypesBuilder.OPTIONAL_ANNOTATION));
        }

        methodParam
            .getAnnotations()
            .add(
                new MethodParam.Annotation(
                    TypesBuilder.PARAM_NAME_ANNOTATION, methodParam.getName()));

        methodParams.add(methodParam);
      }
    }

    return methodParams;
  }

  private void addBuilder(
      Domain domain,
      Property property,
      PropertyHandlerResult result,
      JavaInterfaceBuilder interfaceBuilder,
      List<Builder> builders) {
    if (result.getBuilder() != null) {
      builders.add(result.getBuilder());

      // Since these properties are not ref type, we need to manually import them.
      String packageName = buildPackageName(typesPackageName, domain.getDomain().toLowerCase());

      interfaceBuilder.addImport(packageName, getTypeName(result));
    }
  }

  private List<Property> filterParameters(
      List<Property> parameters, Predicate<Property> filterPredicate) {
    if (CollectionUtils.isNotEmpty(parameters)) {
      return parameters.stream().filter(filterPredicate).collect(Collectors.toList());
    }

    return parameters;
  }

  private String getTypeName(PropertyHandlerResult propertyHandlerResult) {
    String type = propertyHandlerResult.getType();
    if (type.startsWith(LIST_CLASS_NAME)) {
      return type.substring(LIST_CLASS_NAME.length() + 1, type.length() - 1);
    }

    return type;
  }

  static String buildReturnClasses(String returnType) {
    return "{" + buildReturnClassesRecursive(returnType) + "}";
  }

  private static String buildReturnClassesRecursive(String returnType) {
    if (!returnType.contains("<")) {
      return returnType + OBJECT_CLASS_PROPERTY;
    }

    final int start = returnType.indexOf("<");
    final int end = returnType.lastIndexOf(">");

    final String clazz = returnType.substring(0, start);
    final String parameter = returnType.substring(start + 1, end);

    return clazz + OBJECT_CLASS_PROPERTY + "," + buildReturnClassesRecursive(parameter);
  }
}
