package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.types.security.CertificateErrorAction;

/**
 * Security
 */
@Experimental
public interface Security {

	/**
	 * Enables tracking security state changes.
	 */
	void enable();

	/**
	 * Disables tracking security state changes.
	 */
	void disable();

	/**
	 * Handles a certificate error that fired a certificateError event.
	 */
	void handleCertificateError(@ParamName("eventId") Integer eventId, @ParamName("action") CertificateErrorAction action);

	/**
	 * Enable/disable overriding certificate errors. If enabled, all certificate error events need to be handled by the DevTools client and should be answered with handleCertificateError commands.
	 */
	void setOverrideCertificateErrors(@ParamName("override") Boolean override);
}
