package com.github.kklisura.cdtp.services;

import com.github.kklisura.cdtp.services.exceptions.WebSocketServiceException;

import java.net.URI;
import java.util.function.Consumer;

/**
 * WebSocket service definition.
 *
 * @author Kenan Klisura
 */
public interface WebSocketService {
  /**
   * Connects to a specified URI.
   *
   * @param uri Server location.
   */
  void connect(URI uri) throws WebSocketServiceException;

  /**
   * Sends the message using web socket.
   *
   * @param message Message to send.
   */
  void send(String message) throws WebSocketServiceException;

  /**
   * After receiving the message calls the consumer with appropriate message.
   *
   * @param consumer Consumer to consume the message.
   */
  void addMessageHandler(Consumer<String> consumer) throws WebSocketServiceException;

  /** Closes the service. */
  void close();
}
