package com.github.kklisura.cdt.protocol.types.webauthn;

/*-
 * #%L
 * cdt-java-client
 * %%
 * Copyright (C) 2018 - 2020 Kenan Klisura
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

import com.github.kklisura.cdt.protocol.support.annotations.Optional;

public class VirtualAuthenticatorOptions {

  private AuthenticatorProtocol protocol;

  private AuthenticatorTransport transport;

  @Optional private Boolean hasResidentKey;

  @Optional private Boolean hasUserVerification;

  @Optional private Boolean automaticPresenceSimulation;

  @Optional private Boolean isUserVerified;

  public AuthenticatorProtocol getProtocol() {
    return protocol;
  }

  public void setProtocol(AuthenticatorProtocol protocol) {
    this.protocol = protocol;
  }

  public AuthenticatorTransport getTransport() {
    return transport;
  }

  public void setTransport(AuthenticatorTransport transport) {
    this.transport = transport;
  }

  /** Defaults to false. */
  public Boolean getHasResidentKey() {
    return hasResidentKey;
  }

  /** Defaults to false. */
  public void setHasResidentKey(Boolean hasResidentKey) {
    this.hasResidentKey = hasResidentKey;
  }

  /** Defaults to false. */
  public Boolean getHasUserVerification() {
    return hasUserVerification;
  }

  /** Defaults to false. */
  public void setHasUserVerification(Boolean hasUserVerification) {
    this.hasUserVerification = hasUserVerification;
  }

  /**
   * If set to true, tests of user presence will succeed immediately. Otherwise, they will not be
   * resolved. Defaults to true.
   */
  public Boolean getAutomaticPresenceSimulation() {
    return automaticPresenceSimulation;
  }

  /**
   * If set to true, tests of user presence will succeed immediately. Otherwise, they will not be
   * resolved. Defaults to true.
   */
  public void setAutomaticPresenceSimulation(Boolean automaticPresenceSimulation) {
    this.automaticPresenceSimulation = automaticPresenceSimulation;
  }

  /** Sets whether User Verification succeeds or fails for an authenticator. Defaults to false. */
  public Boolean getIsUserVerified() {
    return isUserVerified;
  }

  /** Sets whether User Verification succeeds or fails for an authenticator. Defaults to false. */
  public void setIsUserVerified(Boolean isUserVerified) {
    this.isUserVerified = isUserVerified;
  }
}
