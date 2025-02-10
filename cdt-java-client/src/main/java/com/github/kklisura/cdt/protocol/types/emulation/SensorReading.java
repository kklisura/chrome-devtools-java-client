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

import com.github.kklisura.cdt.protocol.support.annotations.Experimental;
import com.github.kklisura.cdt.protocol.support.annotations.Optional;

@Experimental
public class SensorReading {

  @Optional private SensorReadingSingle single;

  @Optional private SensorReadingXYZ xyz;

  @Optional private SensorReadingQuaternion quaternion;

  public SensorReadingSingle getSingle() {
    return single;
  }

  public void setSingle(SensorReadingSingle single) {
    this.single = single;
  }

  public SensorReadingXYZ getXyz() {
    return xyz;
  }

  public void setXyz(SensorReadingXYZ xyz) {
    this.xyz = xyz;
  }

  public SensorReadingQuaternion getQuaternion() {
    return quaternion;
  }

  public void setQuaternion(SensorReadingQuaternion quaternion) {
    this.quaternion = quaternion;
  }
}
