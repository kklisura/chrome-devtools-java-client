package com.github.kklisura.cdt.definition.builder.protocol.types.type.array.items;

import com.github.kklisura.cdt.definition.builder.protocol.types.type.array.ArrayItem;
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
