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
import com.github.kklisura.cdt.protocol.support.annotations.Optional;
import java.util.List;

/**
 * A cookie associated with the request which may or may not be sent with it. Includes the cookies
 * itself and reasons for blocking or exemption.
 */
@Experimental
public class AssociatedCookie {

  private Cookie cookie;

  private List<CookieBlockedReason> blockedReasons;

  @Optional private CookieExemptionReason exemptionReason;

  /** The cookie object representing the cookie which was not sent. */
  public Cookie getCookie() {
    return cookie;
  }

  /** The cookie object representing the cookie which was not sent. */
  public void setCookie(Cookie cookie) {
    this.cookie = cookie;
  }

  /** The reason(s) the cookie was blocked. If empty means the cookie is included. */
  public List<CookieBlockedReason> getBlockedReasons() {
    return blockedReasons;
  }

  /** The reason(s) the cookie was blocked. If empty means the cookie is included. */
  public void setBlockedReasons(List<CookieBlockedReason> blockedReasons) {
    this.blockedReasons = blockedReasons;
  }

  /**
   * The reason the cookie should have been blocked by 3PCD but is exempted. A cookie could only
   * have at most one exemption reason.
   */
  public CookieExemptionReason getExemptionReason() {
    return exemptionReason;
  }

  /**
   * The reason the cookie should have been blocked by 3PCD but is exempted. A cookie could only
   * have at most one exemption reason.
   */
  public void setExemptionReason(CookieExemptionReason exemptionReason) {
    this.exemptionReason = exemptionReason;
  }
}
