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
import com.github.kklisura.cdtp.protocol.support.annotations.Optional;
import com.github.kklisura.cdtp.protocol.support.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.support.annotations.Returns;
import com.github.kklisura.cdtp.protocol.types.accessibility.AXNode;
import java.util.List;

@Experimental
public interface Accessibility {

  /**
   * Fetches the accessibility node and partial accessibility tree for this DOM node, if it exists.
   *
   * @param nodeId ID of node to get the partial accessibility tree for.
   */
  @Experimental
  @Returns("nodes")
  List<AXNode> getPartialAXTree(@ParamName("nodeId") Integer nodeId);

  /**
   * Fetches the accessibility node and partial accessibility tree for this DOM node, if it exists.
   *
   * @param nodeId ID of node to get the partial accessibility tree for.
   * @param fetchRelatives Whether to fetch this nodes ancestors, siblings and children. Defaults to
   *     true.
   */
  @Experimental
  @Returns("nodes")
  List<AXNode> getPartialAXTree(
      @ParamName("nodeId") Integer nodeId,
      @Optional @ParamName("fetchRelatives") Boolean fetchRelatives);
}
