package com.github.kklisura.cdt.protocol.definition.types.type.object.properties.array;

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

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.kklisura.cdt.protocol.definition.deserializers.impl.properties.PropertyArrayItemSubTypeJsonDeserializer;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.array.items.AnyArrayItem;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.array.items.EnumArrayItem;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.array.items.IntegerArrayItem;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.array.items.NumberArrayItem;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.array.items.ObjectArrayItem;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.array.items.RefArrayItem;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.array.items.StringArrayItem;
import lombok.Getter;
import lombok.Setter;

/**
 * Array item property.
 *
 * @author Kenan Klisura
 */
@Setter
@Getter
@JsonSubTypes({
  @JsonSubTypes.Type(value = RefArrayItem.class, name = "ref"),
  @JsonSubTypes.Type(value = AnyArrayItem.class, name = "any"),
  @JsonSubTypes.Type(value = EnumArrayItem.class, name = "enum"),
  @JsonSubTypes.Type(value = StringArrayItem.class, name = "string"),
  @JsonSubTypes.Type(value = IntegerArrayItem.class, name = "integer"),
  @JsonSubTypes.Type(value = NumberArrayItem.class, name = "number"),
  @JsonSubTypes.Type(value = ObjectArrayItem.class, name = "object")
})
@JsonDeserialize(using = PropertyArrayItemSubTypeJsonDeserializer.class)
public abstract class ArrayItem {
  private String type;

  private String description;
}
