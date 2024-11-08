package com.github.kklisura.cdt.protocol.types.page;

/*-
 * #%L
 * cdt-java-client
 * %%
 * Copyright (C) 2018 - 2023 Kenan Klisura
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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * All Permissions Policy features. This enum should match the one defined in
 * renderer/core/feature_policy/feature_policy_features.json5.
 */
public enum PermissionsPolicyFeature {
  @JsonProperty("accelerometer")
  ACCELEROMETER,
  @JsonProperty("ambient-light-sensor")
  AMBIENT_LIGHT_SENSOR,
  @JsonProperty("autoplay")
  AUTOPLAY,
  @JsonProperty("camera")
  CAMERA,
  @JsonProperty("ch-dpr")
  CH_DPR,
  @JsonProperty("ch-device-memory")
  CH_DEVICE_MEMORY,
  @JsonProperty("ch-downlink")
  CH_DOWNLINK,
  @JsonProperty("ch-ect")
  CH_ECT,
  @JsonProperty("ch-lang")
  CH_LANG,
  @JsonProperty("ch-rtt")
  CH_RTT,
  @JsonProperty("ch-ua")
  CH_UA,
  @JsonProperty("ch-ua-arch")
  CH_UA_ARCH,
  @JsonProperty("ch-ua-platform")
  CH_UA_PLATFORM,
  @JsonProperty("ch-ua-model")
  CH_UA_MODEL,
  @JsonProperty("ch-ua-mobile")
  CH_UA_MOBILE,
  @JsonProperty("ch-ua-full-version")
  CH_UA_FULL_VERSION,
  @JsonProperty("ch-ua-platform-version")
  CH_UA_PLATFORM_VERSION,
  @JsonProperty("ch-viewport-width")
  CH_VIEWPORT_WIDTH,
  @JsonProperty("ch-width")
  CH_WIDTH,
  @JsonProperty("clipboard-read")
  CLIPBOARD_READ,
  @JsonProperty("clipboard-write")
  CLIPBOARD_WRITE,
  @JsonProperty("conversion-measurement")
  CONVERSION_MEASUREMENT,
  @JsonProperty("cross-origin-isolated")
  CROSS_ORIGIN_ISOLATED,
  @JsonProperty("display-capture")
  DISPLAY_CAPTURE,
  @JsonProperty("document-domain")
  DOCUMENT_DOMAIN,
  @JsonProperty("encrypted-media")
  ENCRYPTED_MEDIA,
  @JsonProperty("execution-while-out-of-viewport")
  EXECUTION_WHILE_OUT_OF_VIEWPORT,
  @JsonProperty("execution-while-not-rendered")
  EXECUTION_WHILE_NOT_RENDERED,
  @JsonProperty("focus-without-user-activation")
  FOCUS_WITHOUT_USER_ACTIVATION,
  @JsonProperty("fullscreen")
  FULLSCREEN,
  @JsonProperty("frobulate")
  FROBULATE,
  @JsonProperty("gamepad")
  GAMEPAD,
  @JsonProperty("geolocation")
  GEOLOCATION,
  @JsonProperty("gyroscope")
  GYROSCOPE,
  @JsonProperty("hid")
  HID,
  @JsonProperty("idle-detection")
  IDLE_DETECTION,
  @JsonProperty("interest-cohort")
  INTEREST_COHORT,
  @JsonProperty("magnetometer")
  MAGNETOMETER,
  @JsonProperty("microphone")
  MICROPHONE,
  @JsonProperty("midi")
  MIDI,
  @JsonProperty("otp-credentials")
  OTP_CREDENTIALS,
  @JsonProperty("payment")
  PAYMENT,
  @JsonProperty("picture-in-picture")
  PICTURE_IN_PICTURE,
  @JsonProperty("publickey-credentials-get")
  PUBLICKEY_CREDENTIALS_GET,
  @JsonProperty("screen-wake-lock")
  SCREEN_WAKE_LOCK,
  @JsonProperty("serial")
  SERIAL,
  @JsonProperty("storage-access-api")
  STORAGE_ACCESS_API,
  @JsonProperty("sync-xhr")
  SYNC_XHR,
  @JsonProperty("trust-token-redemption")
  TRUST_TOKEN_REDEMPTION,
  @JsonProperty("usb")
  USB,
  @JsonProperty("vertical-scroll")
  VERTICAL_SCROLL,
  @JsonProperty("web-share")
  WEB_SHARE,
  @JsonProperty("xr-spatial-tracking")
  XR_SPATIAL_TRACKING
}
