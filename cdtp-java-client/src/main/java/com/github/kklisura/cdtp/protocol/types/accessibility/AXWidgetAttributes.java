package com.github.kklisura.cdtp.protocol.types.accessibility;

/*-
 * #%L
 * cdpt-java-client
 * %%
 * Copyright (C) 2018 Kenan Klisura
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

import com.fasterxml.jackson.annotation.JsonProperty;

/** Attributes which apply to widgets. */
public enum AXWidgetAttributes {
  @JsonProperty("autocomplete")
  AUTOCOMPLETE,
  @JsonProperty("haspopup")
  HASPOPUP,
  @JsonProperty("level")
  LEVEL,
  @JsonProperty("multiselectable")
  MULTISELECTABLE,
  @JsonProperty("orientation")
  ORIENTATION,
  @JsonProperty("multiline")
  MULTILINE,
  @JsonProperty("readonly")
  READONLY,
  @JsonProperty("required")
  REQUIRED,
  @JsonProperty("valuemin")
  VALUEMIN,
  @JsonProperty("valuemax")
  VALUEMAX,
  @JsonProperty("valuetext")
  VALUETEXT
}
