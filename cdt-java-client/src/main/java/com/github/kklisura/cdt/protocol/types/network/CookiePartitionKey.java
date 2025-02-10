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
 * cookiePartitionKey object The representation of the components of the key that are created by the
 * cookiePartitionKey class contained in net/cookies/cookie_partition_key.h.
 */
@Experimental
public class CookiePartitionKey {

  private String topLevelSite;

  private Boolean hasCrossSiteAncestor;

  /**
   * The site of the top-level URL the browser was visiting at the start of the request to the
   * endpoint that set the cookie.
   */
  public String getTopLevelSite() {
    return topLevelSite;
  }

  /**
   * The site of the top-level URL the browser was visiting at the start of the request to the
   * endpoint that set the cookie.
   */
  public void setTopLevelSite(String topLevelSite) {
    this.topLevelSite = topLevelSite;
  }

  /** Indicates if the cookie has any ancestors that are cross-site to the topLevelSite. */
  public Boolean getHasCrossSiteAncestor() {
    return hasCrossSiteAncestor;
  }

  /** Indicates if the cookie has any ancestors that are cross-site to the topLevelSite. */
  public void setHasCrossSiteAncestor(Boolean hasCrossSiteAncestor) {
    this.hasCrossSiteAncestor = hasCrossSiteAncestor;
  }
}
