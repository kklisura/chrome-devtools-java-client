package com.github.kklisura.cdt.definition.builder.support.java.builder.support;

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

import com.github.kklisura.cdt.definition.builder.support.java.builder.Builder;
import com.github.kklisura.cdt.definition.builder.support.java.builder.SourceProject;
import java.io.IOException;
import java.util.List;

/**
 * Combines multiple builders in a single builder.
 *
 * @author Kenan Klisura
 */
public class CombinedBuilders implements Builder {
  private List<Builder> builderList;

  /**
   * Instantiates a new Combined builders with list of builders to combine.
   *
   * @param builderList Builders.
   */
  public CombinedBuilders(List<Builder> builderList) {
    this.builderList = builderList;
  }

  /**
   * Gets builder list.
   *
   * @return Builder list.
   */
  public List<Builder> getBuilderList() {
    return builderList;
  }

  @Override
  public void build(SourceProject sourceProject) throws IOException {
    for (Builder builder : builderList) {
      builder.build(sourceProject);
    }
  }
}
