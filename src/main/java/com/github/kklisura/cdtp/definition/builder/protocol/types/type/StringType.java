package com.github.kklisura.cdtp.definition.builder.protocol.types.type;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.kklisura.cdtp.definition.builder.protocol.types.Type;

/**
 * String type.
 *
 * @author Kenan Klisura
 */
@JsonDeserialize(using = JsonDeserializer.None.class)
public class StringType extends Type {
}
