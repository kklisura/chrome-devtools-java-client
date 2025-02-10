package com.github.kklisura.cdt.protocol.types.emulation;

/*-
 * #%L
 * cdt-java-client
 * %%
 * Copyright (C) 2018 - 2025 Kenan Klisura
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

/**
 * Used to specify sensor types to emulate. See https://w3c.github.io/sensors/#automation for more
 * information.
 */
public enum SensorType {
  @JsonProperty("absolute-orientation")
  ABSOLUTE_ORIENTATION,
  @JsonProperty("accelerometer")
  ACCELEROMETER,
  @JsonProperty("ambient-light")
  AMBIENT_LIGHT,
  @JsonProperty("gravity")
  GRAVITY,
  @JsonProperty("gyroscope")
  GYROSCOPE,
  @JsonProperty("linear-acceleration")
  LINEAR_ACCELERATION,
  @JsonProperty("magnetometer")
  MAGNETOMETER,
  @JsonProperty("relative-orientation")
  RELATIVE_ORIENTATION
}
