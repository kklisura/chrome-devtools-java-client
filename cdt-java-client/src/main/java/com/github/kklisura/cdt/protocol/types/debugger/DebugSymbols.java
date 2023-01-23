package com.github.kklisura.cdt.protocol.types.debugger;

/*-
 * #%L
 * cdt-java-client
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

import com.github.kklisura.cdt.protocol.support.annotations.Optional;

/** Debug symbols available for a wasm script. */
public class DebugSymbols {

  private DebugSymbolsType type;

  @Optional private String externalURL;

  /** Type of the debug symbols. */
  public DebugSymbolsType getType() {
    return type;
  }

  /** Type of the debug symbols. */
  public void setType(DebugSymbolsType type) {
    this.type = type;
  }

  /** URL of the external symbol source. */
  public String getExternalURL() {
    return externalURL;
  }

  /** URL of the external symbol source. */
  public void setExternalURL(String externalURL) {
    this.externalURL = externalURL;
  }
}
