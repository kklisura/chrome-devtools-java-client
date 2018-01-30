package com.github.kklisura.cdtp.protocol.events.dom;

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
import java.util.List;

/** Fired when <code>Element</code>'s inline style is modified via a CSS property modification. */
@Experimental
public class InlineStyleInvalidated {

  private List<Integer> nodeIds;

  /** Ids of the nodes for which the inline styles have been invalidated. */
  public List<Integer> getNodeIds() {
    return nodeIds;
  }

  /** Ids of the nodes for which the inline styles have been invalidated. */
  public void setNodeIds(List<Integer> nodeIds) {
    this.nodeIds = nodeIds;
  }
}
