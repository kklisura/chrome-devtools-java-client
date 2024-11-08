package com.github.kklisura.cdt.protocol.definition.types.type.object;

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
import com.github.kklisura.cdt.protocol.definition.deserializers.impl.properties.PropertySubTypeJsonDeserializer;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.AnyProperty;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.ArrayProperty;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.BooleanProperty;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.EnumProperty;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.IntegerProperty;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.NumberProperty;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.ObjectProperty;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.RefProperty;
import com.github.kklisura.cdt.protocol.definition.types.type.object.properties.StringProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Object property.
 *
 * @author Kenan Klisura
 */
@Setter
@Getter
@JsonSubTypes({
  @JsonSubTypes.Type(value = StringProperty.class, name = "string"),
  @JsonSubTypes.Type(value = NumberProperty.class, name = "number"),
  @JsonSubTypes.Type(value = BooleanProperty.class, name = "boolean"),
  @JsonSubTypes.Type(value = AnyProperty.class, name = "any"),
  @JsonSubTypes.Type(value = ArrayProperty.class, name = "array"),
  @JsonSubTypes.Type(value = RefProperty.class, name = "ref"),
  @JsonSubTypes.Type(value = IntegerProperty.class, name = "integer"),
  @JsonSubTypes.Type(value = EnumProperty.class, name = "enum"),
  @JsonSubTypes.Type(value = ObjectProperty.class, name = "object")
})
@JsonDeserialize(using = PropertySubTypeJsonDeserializer.class)
public abstract class Property {
  private String name;

  private String type;

  private String description;

  private Boolean optional;

  private Boolean experimental;

  private Boolean deprecated;
}
