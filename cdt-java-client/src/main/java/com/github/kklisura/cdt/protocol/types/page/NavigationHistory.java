package com.github.kklisura.cdt.protocol.types.page;

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

import java.util.List;

public class NavigationHistory {

  private Integer currentIndex;

  private List<NavigationEntry> entries;

  /** Index of the current navigation history entry. */
  public Integer getCurrentIndex() {
    return currentIndex;
  }

  /** Index of the current navigation history entry. */
  public void setCurrentIndex(Integer currentIndex) {
    this.currentIndex = currentIndex;
  }

  /** Array of navigation history entries. */
  public List<NavigationEntry> getEntries() {
    return entries;
  }

  /** Array of navigation history entries. */
  public void setEntries(List<NavigationEntry> entries) {
    this.entries = entries;
  }
}
