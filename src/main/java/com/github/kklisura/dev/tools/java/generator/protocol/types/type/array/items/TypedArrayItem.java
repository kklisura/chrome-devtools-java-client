package com.github.kklisura.dev.tools.java.generator.protocol.types.type.array.items;

import com.github.kklisura.dev.tools.java.generator.protocol.types.type.array.ArrayItem;
import lombok.Getter;

/**
 * Typed array item.
 *
 * @author Kenan Klisura
 */
@Getter
public abstract class TypedArrayItem extends ArrayItem {
	private String type;
}
