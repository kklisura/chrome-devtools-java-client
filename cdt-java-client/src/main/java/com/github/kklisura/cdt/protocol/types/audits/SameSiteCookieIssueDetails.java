package com.github.kklisura.cdt.protocol.types.audits;

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

import com.github.kklisura.cdt.protocol.support.annotations.Optional;
import java.util.List;

/**
 * This information is currently necessary, as the front-end has a difficult time finding a specific
 * cookie. With this, we can convey specific error information without the cookie.
 */
public class SameSiteCookieIssueDetails {

  private AffectedCookie cookie;

  private List<SameSiteCookieWarningReason> cookieWarningReasons;

  private List<SameSiteCookieExclusionReason> cookieExclusionReasons;

  private SameSiteCookieOperation operation;

  @Optional private String siteForCookies;

  @Optional private String cookieUrl;

  @Optional private AffectedRequest request;

  public AffectedCookie getCookie() {
    return cookie;
  }

  public void setCookie(AffectedCookie cookie) {
    this.cookie = cookie;
  }

  public List<SameSiteCookieWarningReason> getCookieWarningReasons() {
    return cookieWarningReasons;
  }

  public void setCookieWarningReasons(List<SameSiteCookieWarningReason> cookieWarningReasons) {
    this.cookieWarningReasons = cookieWarningReasons;
  }

  public List<SameSiteCookieExclusionReason> getCookieExclusionReasons() {
    return cookieExclusionReasons;
  }

  public void setCookieExclusionReasons(
      List<SameSiteCookieExclusionReason> cookieExclusionReasons) {
    this.cookieExclusionReasons = cookieExclusionReasons;
  }

  /**
   * Optionally identifies the site-for-cookies and the cookie url, which may be used by the
   * front-end as additional context.
   */
  public SameSiteCookieOperation getOperation() {
    return operation;
  }

  /**
   * Optionally identifies the site-for-cookies and the cookie url, which may be used by the
   * front-end as additional context.
   */
  public void setOperation(SameSiteCookieOperation operation) {
    this.operation = operation;
  }

  public String getSiteForCookies() {
    return siteForCookies;
  }

  public void setSiteForCookies(String siteForCookies) {
    this.siteForCookies = siteForCookies;
  }

  public String getCookieUrl() {
    return cookieUrl;
  }

  public void setCookieUrl(String cookieUrl) {
    this.cookieUrl = cookieUrl;
  }

  public AffectedRequest getRequest() {
    return request;
  }

  public void setRequest(AffectedRequest request) {
    this.request = request;
  }
}
