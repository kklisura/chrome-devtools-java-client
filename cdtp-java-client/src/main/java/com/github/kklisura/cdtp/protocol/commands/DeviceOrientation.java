package com.github.kklisura.cdtp.protocol.commands;

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

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.ParamName;

@Experimental
public interface DeviceOrientation {

  /**
   * Overrides the Device Orientation.
   *
   * @param alpha Mock alpha
   * @param beta Mock beta
   * @param gamma Mock gamma
   */
  void setDeviceOrientationOverride(
      @ParamName("alpha") Double alpha,
      @ParamName("beta") Double beta,
      @ParamName("gamma") Double gamma);

  /** Clears the overridden Device Orientation. */
  void clearDeviceOrientationOverride();
}
