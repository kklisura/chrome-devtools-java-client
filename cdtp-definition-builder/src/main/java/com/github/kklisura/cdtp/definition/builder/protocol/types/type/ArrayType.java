package com.github.kklisura.cdtp.definition.builder.protocol.types.type;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.kklisura.cdtp.definition.builder.protocol.types.Type;
import com.github.kklisura.cdtp.definition.builder.protocol.types.type.array.ArrayItem;
import lombok.Getter;
import lombok.Setter;

/**
 * Array type.
 *
 * @author Kenan Klisura
 */
@Getter
@Setter
@JsonDeserialize(using = JsonDeserializer.None.class)
public class ArrayType extends Type {
	private ArrayItem items;

	private Long minItems;

	private Long maxItems;
}
