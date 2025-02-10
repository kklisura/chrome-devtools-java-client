package com.github.kklisura.cdt.protocol.types.network;

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

import com.github.kklisura.cdt.protocol.support.annotations.Experimental;

/**
 * A cookie should have been blocked by 3PCD but is exempted and stored from a response with the
 * corresponding reason. A cookie could only have at most one exemption reason.
 */
@Experimental
public class ExemptedSetCookieWithReason {

  private CookieExemptionReason exemptionReason;

  private String cookieLine;

  private Cookie cookie;

  /** The reason the cookie was exempted. */
  public CookieExemptionReason getExemptionReason() {
    return exemptionReason;
  }

  /** The reason the cookie was exempted. */
  public void setExemptionReason(CookieExemptionReason exemptionReason) {
    this.exemptionReason = exemptionReason;
  }

  /** The string representing this individual cookie as it would appear in the header. */
  public String getCookieLine() {
    return cookieLine;
  }

  /** The string representing this individual cookie as it would appear in the header. */
  public void setCookieLine(String cookieLine) {
    this.cookieLine = cookieLine;
  }

  /** The cookie object representing the cookie. */
  public Cookie getCookie() {
    return cookie;
  }

  /** The cookie object representing the cookie. */
  public void setCookie(Cookie cookie) {
    this.cookie = cookie;
  }
}
