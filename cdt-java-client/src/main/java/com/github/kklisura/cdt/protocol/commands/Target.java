package com.github.kklisura.cdt.protocol.commands;

/*-
 * #%L
 * cdt-java-client
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

import com.github.kklisura.cdt.protocol.events.target.AttachedToTarget;
import com.github.kklisura.cdt.protocol.events.target.DetachedFromTarget;
import com.github.kklisura.cdt.protocol.events.target.ReceivedMessageFromTarget;
import com.github.kklisura.cdt.protocol.events.target.TargetCreated;
import com.github.kklisura.cdt.protocol.events.target.TargetDestroyed;
import com.github.kklisura.cdt.protocol.events.target.TargetInfoChanged;
import com.github.kklisura.cdt.protocol.support.annotations.EventName;
import com.github.kklisura.cdt.protocol.support.annotations.Experimental;
import com.github.kklisura.cdt.protocol.support.annotations.Optional;
import com.github.kklisura.cdt.protocol.support.annotations.ParamName;
import com.github.kklisura.cdt.protocol.support.annotations.Returns;
import com.github.kklisura.cdt.protocol.support.types.EventHandler;
import com.github.kklisura.cdt.protocol.support.types.EventListener;
import com.github.kklisura.cdt.protocol.types.target.RemoteLocation;
import com.github.kklisura.cdt.protocol.types.target.TargetInfo;
import java.util.List;

/** Supports additional targets discovery and allows to attach to them. */
@Experimental
public interface Target {

  /**
   * Controls whether to discover available targets and notify via <code>
   * targetCreated/targetInfoChanged/targetDestroyed</code> events.
   *
   * @param discover Whether to discover available targets.
   */
  void setDiscoverTargets(@ParamName("discover") Boolean discover);

  /**
   * Controls whether to automatically attach to new targets which are considered to be related to
   * this one. When turned on, attaches to all existing related targets as well. When turned off,
   * automatically detaches from all currently attached targets.
   *
   * @param autoAttach Whether to auto-attach to related targets.
   * @param waitForDebuggerOnStart Whether to pause new targets when attaching to them. Use <code>
   *     Runtime.runIfWaitingForDebugger</code> to run paused targets.
   */
  void setAutoAttach(
      @ParamName("autoAttach") Boolean autoAttach,
      @ParamName("waitForDebuggerOnStart") Boolean waitForDebuggerOnStart);

  /** @param value Whether to attach to frames. */
  void setAttachToFrames(@ParamName("value") Boolean value);

  /**
   * Enables target discovery for the specified locations, when <code>setDiscoverTargets</code> was
   * set to <code>true</code>.
   *
   * @param locations List of remote locations.
   */
  void setRemoteLocations(@ParamName("locations") List<RemoteLocation> locations);

  /**
   * Sends protocol message over session with given id.
   *
   * @param message
   */
  void sendMessageToTarget(@ParamName("message") String message);

  /**
   * Sends protocol message over session with given id.
   *
   * @param message
   * @param sessionId Identifier of the session.
   * @param targetId Deprecated.
   */
  void sendMessageToTarget(
      @ParamName("message") String message,
      @Optional @ParamName("sessionId") String sessionId,
      @Deprecated @Optional @ParamName("targetId") String targetId);

  /**
   * Returns information about a target.
   *
   * @param targetId
   */
  @Returns("targetInfo")
  TargetInfo getTargetInfo(@ParamName("targetId") String targetId);

  /**
   * Activates (focuses) the target.
   *
   * @param targetId
   */
  void activateTarget(@ParamName("targetId") String targetId);

  /**
   * Closes the target. If the target is a page that gets closed too.
   *
   * @param targetId
   */
  @Returns("success")
  Boolean closeTarget(@ParamName("targetId") String targetId);

  /**
   * Attaches to the target with given id.
   *
   * @param targetId
   */
  @Returns("sessionId")
  String attachToTarget(@ParamName("targetId") String targetId);

  /** Detaches session with given id. */
  void detachFromTarget();

  /**
   * Detaches session with given id.
   *
   * @param sessionId Session to detach.
   * @param targetId Deprecated.
   */
  void detachFromTarget(
      @Optional @ParamName("sessionId") String sessionId,
      @Deprecated @Optional @ParamName("targetId") String targetId);

  /**
   * Creates a new empty BrowserContext. Similar to an incognito profile but you can have more than
   * one.
   */
  @Returns("browserContextId")
  String createBrowserContext();

  /**
   * Deletes a BrowserContext, will fail of any open page uses it.
   *
   * @param browserContextId
   */
  @Returns("success")
  Boolean disposeBrowserContext(@ParamName("browserContextId") String browserContextId);

  /**
   * Creates a new page.
   *
   * @param url The initial URL the page will be navigated to.
   */
  @Returns("targetId")
  String createTarget(@ParamName("url") String url);

  /**
   * Creates a new page.
   *
   * @param url The initial URL the page will be navigated to.
   * @param width Frame width in DIP (headless chrome only).
   * @param height Frame height in DIP (headless chrome only).
   * @param browserContextId The browser context to create the page in (headless chrome only).
   */
  @Returns("targetId")
  String createTarget(
      @ParamName("url") String url,
      @Optional @ParamName("width") Integer width,
      @Optional @ParamName("height") Integer height,
      @Optional @ParamName("browserContextId") String browserContextId);

  /** Retrieves a list of available targets. */
  @Returns("targetInfos")
  List<TargetInfo> getTargets();

  /** Issued when a possible inspection target is created. */
  @EventName("targetCreated")
  EventListener onTargetCreated(EventHandler<TargetCreated> eventListener);

  /**
   * Issued when some information about a target has changed. This only happens between <code>
   * targetCreated</code> and <code>targetDestroyed</code>.
   */
  @EventName("targetInfoChanged")
  EventListener onTargetInfoChanged(EventHandler<TargetInfoChanged> eventListener);

  /** Issued when a target is destroyed. */
  @EventName("targetDestroyed")
  EventListener onTargetDestroyed(EventHandler<TargetDestroyed> eventListener);

  /**
   * Issued when attached to target because of auto-attach or <code>attachToTarget</code> command.
   */
  @EventName("attachedToTarget")
  EventListener onAttachedToTarget(EventHandler<AttachedToTarget> eventListener);

  /**
   * Issued when detached from target for any reason (including <code>detachFromTarget</code>
   * command). Can be issued multiple times per target if multiple sessions have been attached to
   * it.
   */
  @EventName("detachedFromTarget")
  EventListener onDetachedFromTarget(EventHandler<DetachedFromTarget> eventListener);

  /**
   * Notifies about a new protocol message received from the session (as reported in <code>
   * attachedToTarget</code> event).
   */
  @EventName("receivedMessageFromTarget")
  EventListener onReceivedMessageFromTarget(EventHandler<ReceivedMessageFromTarget> eventListener);
}
