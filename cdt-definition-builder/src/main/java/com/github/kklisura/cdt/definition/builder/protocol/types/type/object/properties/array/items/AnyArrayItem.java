package com.github.kklisura.cdt.definition.builder.protocol.types.type.object.properties.array.items;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.kklisura.cdt.definition.builder.protocol.types.type.object.properties.array.ArrayItem;

/**
 * Created by Kenan Klisura on 18/12/2017.
 *
 * @author Kenan Klisura
 */
@JsonDeserialize(using = JsonDeserializer.None.class)
public class AnyArrayItem extends ArrayItem {}
