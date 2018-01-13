package com.github.kklisura.dev.tools.java.generator.protocol.types.type.array.items;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.kklisura.dev.tools.java.generator.protocol.types.type.array.ArrayItem;
import lombok.Getter;

/**
 * Number array item type.
 *
 * @author Kenan Klisura
 */
@Getter
@JsonDeserialize(using = JsonDeserializer.None.class)
public class NumberArrayItem extends TypedArrayItem {
}
