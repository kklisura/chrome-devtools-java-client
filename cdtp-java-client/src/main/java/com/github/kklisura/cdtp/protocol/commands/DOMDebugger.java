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
import com.github.kklisura.cdtp.protocol.types.domdebugger.DOMBreakpointType;
import com.github.kklisura.cdtp.protocol.types.domdebugger.EventListener;
import java.util.List;

/**
 * DOM debugging allows setting breakpoints on particular DOM operations and events. JavaScript
 * execution will stop on these operations as if there was a regular breakpoint set.
 */
public interface DOMDebugger {

  /**
   * Sets breakpoint on particular operation with DOM.
   *
   * @param nodeId Identifier of the node to set breakpoint on.
   * @param type Type of the operation to stop upon.
   */
  void setDOMBreakpoint(
      @ParamName("nodeId") Integer nodeId, @ParamName("type") DOMBreakpointType type);

  /**
   * Removes DOM breakpoint that was set using <code>setDOMBreakpoint</code>.
   *
   * @param nodeId Identifier of the node to remove breakpoint from.
   * @param type Type of the breakpoint to remove.
   */
  void removeDOMBreakpoint(
      @ParamName("nodeId") Integer nodeId, @ParamName("type") DOMBreakpointType type);

  /**
   * Sets breakpoint on particular DOM event.
   *
   * @param eventName DOM Event name to stop on (any DOM event will do).
   */
  void setEventListenerBreakpoint(@ParamName("eventName") String eventName);

  /**
   * Sets breakpoint on particular DOM event.
   *
   * @param eventName DOM Event name to stop on (any DOM event will do).
   * @param targetName EventTarget interface name to stop on. If equal to <code>"*"</code> or not
   *     provided, will stop on any EventTarget.
   */
  void setEventListenerBreakpoint(
      @ParamName("eventName") String eventName,
      @Experimental @Optional @ParamName("targetName") String targetName);

  /**
   * Removes breakpoint on particular DOM event.
   *
   * @param eventName Event name.
   */
  void removeEventListenerBreakpoint(@ParamName("eventName") String eventName);

  /**
   * Removes breakpoint on particular DOM event.
   *
   * @param eventName Event name.
   * @param targetName EventTarget interface name.
   */
  void removeEventListenerBreakpoint(
      @ParamName("eventName") String eventName,
      @Experimental @Optional @ParamName("targetName") String targetName);

  /**
   * Sets breakpoint on particular native event.
   *
   * @param eventName Instrumentation name to stop on.
   */
  @Experimental
  void setInstrumentationBreakpoint(@ParamName("eventName") String eventName);

  /**
   * Removes breakpoint on particular native event.
   *
   * @param eventName Instrumentation name to stop on.
   */
  @Experimental
  void removeInstrumentationBreakpoint(@ParamName("eventName") String eventName);

  /**
   * Sets breakpoint on XMLHttpRequest.
   *
   * @param url Resource URL substring. All XHRs having this substring in the URL will get stopped
   *     upon.
   */
  void setXHRBreakpoint(@ParamName("url") String url);

  /**
   * Removes breakpoint from XMLHttpRequest.
   *
   * @param url Resource URL substring.
   */
  void removeXHRBreakpoint(@ParamName("url") String url);

  /**
   * Returns event listeners of the given object.
   *
   * @param objectId Identifier of the object to return listeners for.
   */
  @Experimental
  @Returns("listeners")
  List<EventListener> getEventListeners(@ParamName("objectId") String objectId);

  /**
   * Returns event listeners of the given object.
   *
   * @param objectId Identifier of the object to return listeners for.
   * @param depth The maximum depth at which Node children should be retrieved, defaults to 1. Use
   *     -1 for the entire subtree or provide an integer larger than 0.
   * @param pierce Whether or not iframes and shadow roots should be traversed when returning the
   *     subtree (default is false). Reports listeners for all contexts if pierce is enabled.
   */
  @Experimental
  @Returns("listeners")
  List<EventListener> getEventListeners(
      @ParamName("objectId") String objectId,
      @Experimental @Optional @ParamName("depth") Integer depth,
      @Experimental @Optional @ParamName("pierce") Boolean pierce);
}
