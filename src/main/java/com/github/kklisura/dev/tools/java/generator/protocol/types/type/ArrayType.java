package com.github.kklisura.dev.tools.java.generator.protocol.types.type;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.kklisura.dev.tools.java.generator.protocol.types.Type;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.array.ArrayItem;
import lombok.Getter;

/**
 * Array type.
 *
 * @author Kenan Klisura
 */
@Getter
@JsonDeserialize(using = JsonDeserializer.None.class)
public class ArrayType extends Type {
	private ArrayItem items;

	private Long minItems;

	private Long maxItems;
}
