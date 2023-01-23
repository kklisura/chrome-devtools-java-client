package com.github.kklisura.cdt.protocol.commands;

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

import com.github.kklisura.cdt.protocol.events.storage.CacheStorageContentUpdated;
import com.github.kklisura.cdt.protocol.events.storage.CacheStorageListUpdated;
import com.github.kklisura.cdt.protocol.events.storage.IndexedDBContentUpdated;
import com.github.kklisura.cdt.protocol.events.storage.IndexedDBListUpdated;
import com.github.kklisura.cdt.protocol.support.annotations.EventName;
import com.github.kklisura.cdt.protocol.support.annotations.Experimental;
import com.github.kklisura.cdt.protocol.support.annotations.Optional;
import com.github.kklisura.cdt.protocol.support.annotations.ParamName;
import com.github.kklisura.cdt.protocol.support.annotations.ReturnTypeParameter;
import com.github.kklisura.cdt.protocol.support.annotations.Returns;
import com.github.kklisura.cdt.protocol.support.types.EventHandler;
import com.github.kklisura.cdt.protocol.support.types.EventListener;
import com.github.kklisura.cdt.protocol.types.network.Cookie;
import com.github.kklisura.cdt.protocol.types.network.CookieParam;
import com.github.kklisura.cdt.protocol.types.storage.TrustTokens;
import com.github.kklisura.cdt.protocol.types.storage.UsageAndQuota;
import java.util.List;

@Experimental
public interface Storage {

  /**
   * Clears storage for origin.
   *
   * @param origin Security origin.
   * @param storageTypes Comma separated list of StorageType to clear.
   */
  void clearDataForOrigin(
      @ParamName("origin") String origin, @ParamName("storageTypes") String storageTypes);

  /** Returns all browser cookies. */
  @Returns("cookies")
  @ReturnTypeParameter(Cookie.class)
  List<Cookie> getCookies();

  /**
   * Returns all browser cookies.
   *
   * @param browserContextId Browser context to use when called on the browser endpoint.
   */
  @Returns("cookies")
  @ReturnTypeParameter(Cookie.class)
  List<Cookie> getCookies(@Optional @ParamName("browserContextId") String browserContextId);

  /**
   * Sets given cookies.
   *
   * @param cookies Cookies to be set.
   */
  void setCookies(@ParamName("cookies") List<CookieParam> cookies);

  /**
   * Sets given cookies.
   *
   * @param cookies Cookies to be set.
   * @param browserContextId Browser context to use when called on the browser endpoint.
   */
  void setCookies(
      @ParamName("cookies") List<CookieParam> cookies,
      @Optional @ParamName("browserContextId") String browserContextId);

  /** Clears cookies. */
  void clearCookies();

  /**
   * Clears cookies.
   *
   * @param browserContextId Browser context to use when called on the browser endpoint.
   */
  void clearCookies(@Optional @ParamName("browserContextId") String browserContextId);

  /**
   * Returns usage and quota in bytes.
   *
   * @param origin Security origin.
   */
  UsageAndQuota getUsageAndQuota(@ParamName("origin") String origin);

  /**
   * Override quota for the specified origin
   *
   * @param origin Security origin.
   */
  @Experimental
  void overrideQuotaForOrigin(@ParamName("origin") String origin);

  /**
   * Override quota for the specified origin
   *
   * @param origin Security origin.
   * @param quotaSize The quota size (in bytes) to override the original quota with. If this is
   *     called multiple times, the overridden quota will be equal to the quotaSize provided in the
   *     final call. If this is called without specifying a quotaSize, the quota will be reset to
   *     the default value for the specified origin. If this is called multiple times with different
   *     origins, the override will be maintained for each origin until it is disabled (called
   *     without a quotaSize).
   */
  @Experimental
  void overrideQuotaForOrigin(
      @ParamName("origin") String origin, @Optional @ParamName("quotaSize") Double quotaSize);

  /**
   * Registers origin to be notified when an update occurs to its cache storage list.
   *
   * @param origin Security origin.
   */
  void trackCacheStorageForOrigin(@ParamName("origin") String origin);

  /**
   * Registers origin to be notified when an update occurs to its IndexedDB.
   *
   * @param origin Security origin.
   */
  void trackIndexedDBForOrigin(@ParamName("origin") String origin);

  /**
   * Unregisters origin from receiving notifications for cache storage.
   *
   * @param origin Security origin.
   */
  void untrackCacheStorageForOrigin(@ParamName("origin") String origin);

  /**
   * Unregisters origin from receiving notifications for IndexedDB.
   *
   * @param origin Security origin.
   */
  void untrackIndexedDBForOrigin(@ParamName("origin") String origin);

  /** Returns the number of stored Trust Tokens per issuer for the current browsing context. */
  @Experimental
  @Returns("tokens")
  @ReturnTypeParameter(TrustTokens.class)
  List<TrustTokens> getTrustTokens();

  /**
   * Removes all Trust Tokens issued by the provided issuerOrigin. Leaves other stored data,
   * including the issuer's Redemption Records, intact.
   *
   * @param issuerOrigin
   */
  @Experimental
  @Returns("didDeleteTokens")
  Boolean clearTrustTokens(@ParamName("issuerOrigin") String issuerOrigin);

  /** A cache's contents have been modified. */
  @EventName("cacheStorageContentUpdated")
  EventListener onCacheStorageContentUpdated(
      EventHandler<CacheStorageContentUpdated> eventListener);

  /** A cache has been added/deleted. */
  @EventName("cacheStorageListUpdated")
  EventListener onCacheStorageListUpdated(EventHandler<CacheStorageListUpdated> eventListener);

  /** The origin's IndexedDB object store has been modified. */
  @EventName("indexedDBContentUpdated")
  EventListener onIndexedDBContentUpdated(EventHandler<IndexedDBContentUpdated> eventListener);

  /** The origin's IndexedDB database list has been modified. */
  @EventName("indexedDBListUpdated")
  EventListener onIndexedDBListUpdated(EventHandler<IndexedDBListUpdated> eventListener);
}
