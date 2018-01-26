package com.github.kklisura.cdtp.protocol.support.types;

/**
 * Event handler definition.
 *
 * @author Kenan Klisura
 */
@FunctionalInterface
public interface EventHandler<T> {
  /**
   * Handles the event of type T.
   *
   * @param event Event
   */
  void onEvent(T event);
}
