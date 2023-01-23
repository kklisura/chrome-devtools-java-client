package com.github.kklisura.cdt.protocol.commands;

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

import com.github.kklisura.cdt.protocol.support.annotations.Experimental;
import com.github.kklisura.cdt.protocol.support.annotations.Optional;
import com.github.kklisura.cdt.protocol.support.annotations.ParamName;
import com.github.kklisura.cdt.protocol.support.annotations.ReturnTypeParameter;
import com.github.kklisura.cdt.protocol.support.annotations.Returns;
import com.github.kklisura.cdt.protocol.types.accessibility.AXNode;
import java.util.List;

@Experimental
public interface Accessibility {

  /** Disables the accessibility domain. */
  void disable();

  /**
   * Enables the accessibility domain which causes `AXNodeId`s to remain consistent between method
   * calls. This turns on accessibility for the page, which can impact performance until
   * accessibility is disabled.
   */
  void enable();

  /**
   * Fetches the accessibility node and partial accessibility tree for this DOM node, if it exists.
   */
  @Experimental
  @Returns("nodes")
  @ReturnTypeParameter(AXNode.class)
  List<AXNode> getPartialAXTree();

  /**
   * Fetches the accessibility node and partial accessibility tree for this DOM node, if it exists.
   *
   * @param nodeId Identifier of the node to get the partial accessibility tree for.
   * @param backendNodeId Identifier of the backend node to get the partial accessibility tree for.
   * @param objectId JavaScript object id of the node wrapper to get the partial accessibility tree
   *     for.
   * @param fetchRelatives Whether to fetch this nodes ancestors, siblings and children. Defaults to
   *     true.
   */
  @Experimental
  @Returns("nodes")
  @ReturnTypeParameter(AXNode.class)
  List<AXNode> getPartialAXTree(
      @Optional @ParamName("nodeId") Integer nodeId,
      @Optional @ParamName("backendNodeId") Integer backendNodeId,
      @Optional @ParamName("objectId") String objectId,
      @Optional @ParamName("fetchRelatives") Boolean fetchRelatives);

  /** Fetches the entire accessibility tree for the root Document */
  @Experimental
  @Returns("nodes")
  @ReturnTypeParameter(AXNode.class)
  List<AXNode> getFullAXTree();

  /**
   * Fetches the entire accessibility tree for the root Document
   *
   * @param max_depth The maximum depth at which descendants of the root node should be retrieved.
   *     If omitted, the full tree is returned.
   */
  @Experimental
  @Returns("nodes")
  @ReturnTypeParameter(AXNode.class)
  List<AXNode> getFullAXTree(@Optional @ParamName("max_depth") Integer max_depth);

  /**
   * Fetches a particular accessibility node by AXNodeId. Requires `enable()` to have been called
   * previously.
   *
   * @param id
   */
  @Experimental
  @Returns("nodes")
  @ReturnTypeParameter(AXNode.class)
  List<AXNode> getChildAXNodes(@ParamName("id") String id);

  /**
   * Query a DOM node's accessibility subtree for accessible name and role. This command computes
   * the name and role for all nodes in the subtree, including those that are ignored for
   * accessibility, and returns those that mactch the specified name and role. If no DOM node is
   * specified, or the DOM node does not exist, the command returns an error. If neither
   * `accessibleName` or `role` is specified, it returns all the accessibility nodes in the subtree.
   */
  @Experimental
  @Returns("nodes")
  @ReturnTypeParameter(AXNode.class)
  List<AXNode> queryAXTree();

  /**
   * Query a DOM node's accessibility subtree for accessible name and role. This command computes
   * the name and role for all nodes in the subtree, including those that are ignored for
   * accessibility, and returns those that mactch the specified name and role. If no DOM node is
   * specified, or the DOM node does not exist, the command returns an error. If neither
   * `accessibleName` or `role` is specified, it returns all the accessibility nodes in the subtree.
   *
   * @param nodeId Identifier of the node for the root to query.
   * @param backendNodeId Identifier of the backend node for the root to query.
   * @param objectId JavaScript object id of the node wrapper for the root to query.
   * @param accessibleName Find nodes with this computed name.
   * @param role Find nodes with this computed role.
   */
  @Experimental
  @Returns("nodes")
  @ReturnTypeParameter(AXNode.class)
  List<AXNode> queryAXTree(
      @Optional @ParamName("nodeId") Integer nodeId,
      @Optional @ParamName("backendNodeId") Integer backendNodeId,
      @Optional @ParamName("objectId") String objectId,
      @Optional @ParamName("accessibleName") String accessibleName,
      @Optional @ParamName("role") String role);
}
