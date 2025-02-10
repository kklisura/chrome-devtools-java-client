package com.github.kklisura.cdt.protocol.types.webauthn;

/*-
 * #%L
 * cdt-java-client
 * %%
 * Copyright (C) 2018 - 2025 Kenan Klisura
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

public class Credential {

  private String credentialId;

  private Boolean isResidentCredential;

  @Optional private String rpId;

  private String privateKey;

  @Optional private String userHandle;

  private Integer signCount;

  @Optional private String largeBlob;

  @Optional private Boolean backupEligibility;

  @Optional private Boolean backupState;

  @Optional private String userName;

  @Optional private String userDisplayName;

  public String getCredentialId() {
    return credentialId;
  }

  public void setCredentialId(String credentialId) {
    this.credentialId = credentialId;
  }

  public Boolean getIsResidentCredential() {
    return isResidentCredential;
  }

  public void setIsResidentCredential(Boolean isResidentCredential) {
    this.isResidentCredential = isResidentCredential;
  }

  /** Relying Party ID the credential is scoped to. Must be set when adding a credential. */
  public String getRpId() {
    return rpId;
  }

  /** Relying Party ID the credential is scoped to. Must be set when adding a credential. */
  public void setRpId(String rpId) {
    this.rpId = rpId;
  }

  /**
   * The ECDSA P-256 private key in PKCS#8 format. (Encoded as a base64 string when passed over
   * JSON)
   */
  public String getPrivateKey() {
    return privateKey;
  }

  /**
   * The ECDSA P-256 private key in PKCS#8 format. (Encoded as a base64 string when passed over
   * JSON)
   */
  public void setPrivateKey(String privateKey) {
    this.privateKey = privateKey;
  }

  /**
   * An opaque byte sequence with a maximum size of 64 bytes mapping the credential to a specific
   * user. (Encoded as a base64 string when passed over JSON)
   */
  public String getUserHandle() {
    return userHandle;
  }

  /**
   * An opaque byte sequence with a maximum size of 64 bytes mapping the credential to a specific
   * user. (Encoded as a base64 string when passed over JSON)
   */
  public void setUserHandle(String userHandle) {
    this.userHandle = userHandle;
  }

  /**
   * Signature counter. This is incremented by one for each successful assertion. See
   * https://w3c.github.io/webauthn/#signature-counter
   */
  public Integer getSignCount() {
    return signCount;
  }

  /**
   * Signature counter. This is incremented by one for each successful assertion. See
   * https://w3c.github.io/webauthn/#signature-counter
   */
  public void setSignCount(Integer signCount) {
    this.signCount = signCount;
  }

  /**
   * The large blob associated with the credential. See
   * https://w3c.github.io/webauthn/#sctn-large-blob-extension (Encoded as a base64 string when
   * passed over JSON)
   */
  public String getLargeBlob() {
    return largeBlob;
  }

  /**
   * The large blob associated with the credential. See
   * https://w3c.github.io/webauthn/#sctn-large-blob-extension (Encoded as a base64 string when
   * passed over JSON)
   */
  public void setLargeBlob(String largeBlob) {
    this.largeBlob = largeBlob;
  }

  /**
   * Assertions returned by this credential will have the backup eligibility (BE) flag set to this
   * value. Defaults to the authenticator's defaultBackupEligibility value.
   */
  public Boolean getBackupEligibility() {
    return backupEligibility;
  }

  /**
   * Assertions returned by this credential will have the backup eligibility (BE) flag set to this
   * value. Defaults to the authenticator's defaultBackupEligibility value.
   */
  public void setBackupEligibility(Boolean backupEligibility) {
    this.backupEligibility = backupEligibility;
  }

  /**
   * Assertions returned by this credential will have the backup state (BS) flag set to this value.
   * Defaults to the authenticator's defaultBackupState value.
   */
  public Boolean getBackupState() {
    return backupState;
  }

  /**
   * Assertions returned by this credential will have the backup state (BS) flag set to this value.
   * Defaults to the authenticator's defaultBackupState value.
   */
  public void setBackupState(Boolean backupState) {
    this.backupState = backupState;
  }

  /**
   * The credential's user.name property. Equivalent to empty if not set.
   * https://w3c.github.io/webauthn/#dom-publickeycredentialentity-name
   */
  public String getUserName() {
    return userName;
  }

  /**
   * The credential's user.name property. Equivalent to empty if not set.
   * https://w3c.github.io/webauthn/#dom-publickeycredentialentity-name
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * The credential's user.displayName property. Equivalent to empty if not set.
   * https://w3c.github.io/webauthn/#dom-publickeycredentialuserentity-displayname
   */
  public String getUserDisplayName() {
    return userDisplayName;
  }

  /**
   * The credential's user.displayName property. Equivalent to empty if not set.
   * https://w3c.github.io/webauthn/#dom-publickeycredentialuserentity-displayname
   */
  public void setUserDisplayName(String userDisplayName) {
    this.userDisplayName = userDisplayName;
  }
}
