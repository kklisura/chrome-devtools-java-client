package com.github.kklisura.dev.tools.java.generator.protocol.types.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.kklisura.dev.tools.java.generator.protocol.types.Type;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Enum type.
 *
 * @author Kenan Klisura
 */
@Setter
@Getter
@JsonDeserialize(using = JsonDeserializer.None.class)
public class EnumType extends Type {
	@JsonProperty("enum")
	private List<String> enumValues;
}
