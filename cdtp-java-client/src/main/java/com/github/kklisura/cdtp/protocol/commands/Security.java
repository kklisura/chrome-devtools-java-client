package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.events.security.CertificateError;
import com.github.kklisura.cdtp.protocol.events.security.SecurityStateChanged;
import com.github.kklisura.cdtp.protocol.support.annotations.EventName;
import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.support.types.EventHandler;
import com.github.kklisura.cdtp.protocol.support.types.EventListener;
import com.github.kklisura.cdtp.protocol.types.security.CertificateErrorAction;

/** Security */
@Experimental
public interface Security {

  /** Enables tracking security state changes. */
  void enable();

  /** Disables tracking security state changes. */
  void disable();

  /**
   * Handles a certificate error that fired a certificateError event.
   *
   * @param eventId The ID of the event.
   * @param action The action to take on the certificate error.
   */
  void handleCertificateError(
      @ParamName("eventId") Integer eventId, @ParamName("action") CertificateErrorAction action);

  /**
   * Enable/disable overriding certificate errors. If enabled, all certificate error events need to
   * be handled by the DevTools client and should be answered with handleCertificateError commands.
   *
   * @param override If true, certificate errors will be overridden.
   */
  void setOverrideCertificateErrors(@ParamName("override") Boolean override);

  /** The security state of the page changed. */
  @EventName("securityStateChanged")
  EventListener onSecurityStateChanged(EventHandler<SecurityStateChanged> eventListener);

  /**
   * There is a certificate error. If overriding certificate errors is enabled, then it should be
   * handled with the handleCertificateError command. Note: this event does not fire if the
   * certificate error has been allowed internally.
   */
  @EventName("certificateError")
  EventListener onCertificateError(EventHandler<CertificateError> eventListener);
}
