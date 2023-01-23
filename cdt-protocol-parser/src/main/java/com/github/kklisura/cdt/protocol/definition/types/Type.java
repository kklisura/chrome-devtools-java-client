package com.github.kklisura.cdt.protocol.definition.types;

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
import com.github.kklisura.cdt.protocol.definition.deserializers.impl.types.TypeSubTypeJsonDeserializer;
import com.github.kklisura.cdt.protocol.definition.types.type.ArrayType;
import com.github.kklisura.cdt.protocol.definition.types.type.EnumType;
import com.github.kklisura.cdt.protocol.definition.types.type.IntegerType;
import com.github.kklisura.cdt.protocol.definition.types.type.NumberType;
import com.github.kklisura.cdt.protocol.definition.types.type.StringType;
import com.github.kklisura.cdt.protocol.definition.types.type.object.ObjectType;
import lombok.Getter;
import lombok.Setter;

/**
 * Domain Type definition.
 *
 * @author Kenan Klisura
 */
@Setter
@Getter
@JsonSubTypes({
  @JsonSubTypes.Type(value = ObjectType.class, name = "object"),
  @JsonSubTypes.Type(value = StringType.class, name = "string"),
  @JsonSubTypes.Type(value = EnumType.class, name = "enum"),
  @JsonSubTypes.Type(value = IntegerType.class, name = "integer"),
  @JsonSubTypes.Type(value = NumberType.class, name = "number"),
  @JsonSubTypes.Type(value = ArrayType.class, name = "array")
})
@JsonDeserialize(using = TypeSubTypeJsonDeserializer.class)
public abstract class Type {
  private String id;

  private String type;

  private String description;

  private Boolean experimental;

  private Boolean deprecated;
}
