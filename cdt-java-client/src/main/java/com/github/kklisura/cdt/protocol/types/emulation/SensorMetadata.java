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
public class SensorMetadata {

  @Optional private Boolean available;

  @Optional private Double minimumFrequency;

  @Optional private Double maximumFrequency;

  public Boolean getAvailable() {
    return available;
  }

  public void setAvailable(Boolean available) {
    this.available = available;
  }

  public Double getMinimumFrequency() {
    return minimumFrequency;
  }

  public void setMinimumFrequency(Double minimumFrequency) {
    this.minimumFrequency = minimumFrequency;
  }

  public Double getMaximumFrequency() {
    return maximumFrequency;
  }

  public void setMaximumFrequency(Double maximumFrequency) {
    this.maximumFrequency = maximumFrequency;
  }
}
