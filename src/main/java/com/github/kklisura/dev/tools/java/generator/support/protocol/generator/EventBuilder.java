package com.github.kklisura.dev.tools.java.generator.support.protocol.generator;

import com.github.kklisura.dev.tools.java.generator.protocol.types.Domain;
import com.github.kklisura.dev.tools.java.generator.protocol.types.Event;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.object.ObjectType;
import com.github.kklisura.dev.tools.java.generator.support.java.builder.Builder;
import com.github.kklisura.dev.tools.java.generator.support.java.builder.JavaBuilderFactory;
import com.github.kklisura.dev.tools.java.generator.support.java.builder.JavaClassBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
	 * @param basePackageName    Package name for events.
	 * @param javaBuilderFactory Builder factory.
	 * @param typesPackageName   Package name where types reside.
	 */
	public EventBuilder(String basePackageName, JavaBuilderFactory javaBuilderFactory, String typesPackageName) {
		super(basePackageName, javaBuilderFactory);
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
	protected String addRefImportStatement(JavaClassBuilder javaClassBuilder, String refValue, Domain domain,
										   DomainTypeResolver domainTypeResolver) {
		return addRefImportStatement(typesPackageName, javaClassBuilder, refValue, domain, domainTypeResolver);
	}

	private EventHandlerResult buildEvent(Event event, Domain domain, DomainTypeResolver domainTypeResolver) {
		ObjectType objectType = new ObjectType();
		objectType.setId(event.getName());
		objectType.setExperimental(event.getExperimental());
		objectType.setDescription(event.getDescription());
		objectType.setProperties(event.getParameters());

		TypeBuildRequest<ObjectType> request = new TypeBuildRequest<>(domain, objectType, domainTypeResolver);
		TypeHandlerResult result = buildClass(request);
		return new EventHandlerResult(result.getBuilder());
	}

	@Getter
	@AllArgsConstructor
	private class EventHandlerResult {
		private Builder builder;
	}
}
