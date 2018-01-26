package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.events.inspector.Detached;
import com.github.kklisura.cdtp.protocol.events.inspector.TargetCrashed;
import com.github.kklisura.cdtp.protocol.support.annotations.EventName;
import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.types.EventHandler;
import com.github.kklisura.cdtp.protocol.support.types.EventListener;

@Experimental
public interface Inspector {

  /** Enables inspector domain notifications. */
  void enable();

  /** Disables inspector domain notifications. */
  void disable();

  /** Fired when remote debugging connection is about to be terminated. Contains detach reason. */
  @EventName("detached")
  EventListener onDetached(EventHandler<Detached> eventListener);

  /** Fired when debugging target has crashed */
  @EventName("targetCrashed")
  EventListener onTargetCrashed(EventHandler<TargetCrashed> eventListener);
}
