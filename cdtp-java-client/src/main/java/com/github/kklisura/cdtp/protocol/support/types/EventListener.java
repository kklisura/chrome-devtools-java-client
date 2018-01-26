package com.github.kklisura.cdtp.protocol.support.types;

/**
 * Event listener interface.
 *
 * @author Kenan Klisura
 */
public interface EventListener {
  /** Alias to unsubscribe. */
  void off();

  /** Unsubscribe this event listener. */
  void unsubscribe();
}
