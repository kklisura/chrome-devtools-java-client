package com.github.kklisura.cdtp.services.types;

import com.github.kklisura.cdtp.protocol.support.types.EventHandler;
import com.github.kklisura.cdtp.protocol.support.types.EventListener;
import com.github.kklisura.cdtp.services.ChromeDevToolsService;

/**
 * Event listener implementation.
 *
 * @author Kenan Klisura
 */
public class EventListenerImpl implements EventListener {
  private ChromeDevToolsService service;

  private String key;
  private EventHandler handler;
  private Class<?> paramType;

  /**
   * Instantiates a new Event listener.
   *
   * @param key Domain.event key.
   * @param handler Event handler.
   * @param paramType Event param type.
   * @param service Service.
   */
  public EventListenerImpl(
      String key, EventHandler handler, Class<?> paramType, ChromeDevToolsService service) {
    this.service = service;
    this.key = key;
    this.paramType = paramType;
    this.handler = handler;
  }

  @Override
  public void off() {
    unsubscribe();
  }

  @Override
  public void unsubscribe() {
    service.removeEventListener(this);
  }

  /**
   * Gets the domain.event key.
   *
   * @return Key. key
   */
  public String getKey() {
    return key;
  }

  /**
   * Gets the event handler.
   *
   * @return Event handler.
   */
  public EventHandler getHandler() {
    return handler;
  }

  /**
   * Gets param type.
   *
   * @return Event param type.
   */
  public Class<?> getParamType() {
    return paramType;
  }
}
