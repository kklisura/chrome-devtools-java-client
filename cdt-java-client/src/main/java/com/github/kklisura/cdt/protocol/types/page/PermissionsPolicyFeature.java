package com.github.kklisura.cdt.protocol.types.page;

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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * All Permissions Policy features. This enum should match the one defined in
 * third_party/blink/renderer/core/permissions_policy/permissions_policy_features.json5.
 */
public enum PermissionsPolicyFeature {
  @JsonProperty("accelerometer")
  ACCELEROMETER,
  @JsonProperty("all-screens-capture")
  ALL_SCREENS_CAPTURE,
  @JsonProperty("ambient-light-sensor")
  AMBIENT_LIGHT_SENSOR,
  @JsonProperty("attribution-reporting")
  ATTRIBUTION_REPORTING,
  @JsonProperty("autoplay")
  AUTOPLAY,
  @JsonProperty("bluetooth")
  BLUETOOTH,
  @JsonProperty("browsing-topics")
  BROWSING_TOPICS,
  @JsonProperty("camera")
  CAMERA,
  @JsonProperty("captured-surface-control")
  CAPTURED_SURFACE_CONTROL,
  @JsonProperty("ch-dpr")
  CH_DPR,
  @JsonProperty("ch-device-memory")
  CH_DEVICE_MEMORY,
  @JsonProperty("ch-downlink")
  CH_DOWNLINK,
  @JsonProperty("ch-ect")
  CH_ECT,
  @JsonProperty("ch-prefers-color-scheme")
  CH_PREFERS_COLOR_SCHEME,
  @JsonProperty("ch-prefers-reduced-motion")
  CH_PREFERS_REDUCED_MOTION,
  @JsonProperty("ch-prefers-reduced-transparency")
  CH_PREFERS_REDUCED_TRANSPARENCY,
  @JsonProperty("ch-rtt")
  CH_RTT,
  @JsonProperty("ch-save-data")
  CH_SAVE_DATA,
  @JsonProperty("ch-ua")
  CH_UA,
  @JsonProperty("ch-ua-arch")
  CH_UA_ARCH,
  @JsonProperty("ch-ua-bitness")
  CH_UA_BITNESS,
  @JsonProperty("ch-ua-high-entropy-values")
  CH_UA_HIGH_ENTROPY_VALUES,
  @JsonProperty("ch-ua-platform")
  CH_UA_PLATFORM,
  @JsonProperty("ch-ua-model")
  CH_UA_MODEL,
  @JsonProperty("ch-ua-mobile")
  CH_UA_MOBILE,
  @JsonProperty("ch-ua-form-factors")
  CH_UA_FORM_FACTORS,
  @JsonProperty("ch-ua-full-version")
  CH_UA_FULL_VERSION,
  @JsonProperty("ch-ua-full-version-list")
  CH_UA_FULL_VERSION_LIST,
  @JsonProperty("ch-ua-platform-version")
  CH_UA_PLATFORM_VERSION,
  @JsonProperty("ch-ua-wow64")
  CH_UA_WOW_64,
  @JsonProperty("ch-viewport-height")
  CH_VIEWPORT_HEIGHT,
  @JsonProperty("ch-viewport-width")
  CH_VIEWPORT_WIDTH,
  @JsonProperty("ch-width")
  CH_WIDTH,
  @JsonProperty("clipboard-read")
  CLIPBOARD_READ,
  @JsonProperty("clipboard-write")
  CLIPBOARD_WRITE,
  @JsonProperty("compute-pressure")
  COMPUTE_PRESSURE,
  @JsonProperty("controlled-frame")
  CONTROLLED_FRAME,
  @JsonProperty("cross-origin-isolated")
  CROSS_ORIGIN_ISOLATED,
  @JsonProperty("deferred-fetch")
  DEFERRED_FETCH,
  @JsonProperty("deferred-fetch-minimal")
  DEFERRED_FETCH_MINIMAL,
  @JsonProperty("digital-credentials-get")
  DIGITAL_CREDENTIALS_GET,
  @JsonProperty("direct-sockets")
  DIRECT_SOCKETS,
  @JsonProperty("direct-sockets-private")
  DIRECT_SOCKETS_PRIVATE,
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
  @JsonProperty("fenced-unpartitioned-storage-read")
  FENCED_UNPARTITIONED_STORAGE_READ,
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
  @JsonProperty("identity-credentials-get")
  IDENTITY_CREDENTIALS_GET,
  @JsonProperty("idle-detection")
  IDLE_DETECTION,
  @JsonProperty("interest-cohort")
  INTEREST_COHORT,
  @JsonProperty("join-ad-interest-group")
  JOIN_AD_INTEREST_GROUP,
  @JsonProperty("keyboard-map")
  KEYBOARD_MAP,
  @JsonProperty("local-fonts")
  LOCAL_FONTS,
  @JsonProperty("magnetometer")
  MAGNETOMETER,
  @JsonProperty("media-playback-while-not-visible")
  MEDIA_PLAYBACK_WHILE_NOT_VISIBLE,
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
  @JsonProperty("popins")
  POPINS,
  @JsonProperty("private-aggregation")
  PRIVATE_AGGREGATION,
  @JsonProperty("private-state-token-issuance")
  PRIVATE_STATE_TOKEN_ISSUANCE,
  @JsonProperty("private-state-token-redemption")
  PRIVATE_STATE_TOKEN_REDEMPTION,
  @JsonProperty("publickey-credentials-create")
  PUBLICKEY_CREDENTIALS_CREATE,
  @JsonProperty("publickey-credentials-get")
  PUBLICKEY_CREDENTIALS_GET,
  @JsonProperty("run-ad-auction")
  RUN_AD_AUCTION,
  @JsonProperty("screen-wake-lock")
  SCREEN_WAKE_LOCK,
  @JsonProperty("serial")
  SERIAL,
  @JsonProperty("shared-autofill")
  SHARED_AUTOFILL,
  @JsonProperty("shared-storage")
  SHARED_STORAGE,
  @JsonProperty("shared-storage-select-url")
  SHARED_STORAGE_SELECT_URL,
  @JsonProperty("smart-card")
  SMART_CARD,
  @JsonProperty("speaker-selection")
  SPEAKER_SELECTION,
  @JsonProperty("storage-access")
  STORAGE_ACCESS,
  @JsonProperty("sub-apps")
  SUB_APPS,
  @JsonProperty("sync-xhr")
  SYNC_XHR,
  @JsonProperty("unload")
  UNLOAD,
  @JsonProperty("usb")
  USB,
  @JsonProperty("usb-unrestricted")
  USB_UNRESTRICTED,
  @JsonProperty("vertical-scroll")
  VERTICAL_SCROLL,
  @JsonProperty("web-app-installation")
  WEB_APP_INSTALLATION,
  @JsonProperty("web-printing")
  WEB_PRINTING,
  @JsonProperty("web-share")
  WEB_SHARE,
  @JsonProperty("window-management")
  WINDOW_MANAGEMENT,
  @JsonProperty("xr-spatial-tracking")
  XR_SPATIAL_TRACKING
}
