package com.github.kklisura.cdtp.protocol.commands;

/*-
 * #%L
 * cdpt-java-client
 * %%
 * Copyright (C) 2018 Kenan Klisura
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

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.support.annotations.ParamName;
import com.github.kklisura.cdtp.protocol.support.annotations.Returns;
import com.github.kklisura.cdtp.protocol.types.cachestorage.Cache;
import com.github.kklisura.cdtp.protocol.types.cachestorage.CachedResponse;
import com.github.kklisura.cdtp.protocol.types.cachestorage.RequestEntries;
import java.util.List;

@Experimental
public interface CacheStorage {

  /**
   * Requests cache names.
   *
   * @param securityOrigin Security origin.
   */
  @Returns("caches")
  List<Cache> requestCacheNames(@ParamName("securityOrigin") String securityOrigin);

  /**
   * Requests data from cache.
   *
   * @param cacheId ID of cache to get entries from.
   * @param skipCount Number of records to skip.
   * @param pageSize Number of records to fetch.
   */
  RequestEntries requestEntries(
      @ParamName("cacheId") String cacheId,
      @ParamName("skipCount") Integer skipCount,
      @ParamName("pageSize") Integer pageSize);

  /**
   * Deletes a cache.
   *
   * @param cacheId Id of cache for deletion.
   */
  void deleteCache(@ParamName("cacheId") String cacheId);

  /**
   * Deletes a cache entry.
   *
   * @param cacheId Id of cache where the entry will be deleted.
   * @param request URL spec of the request.
   */
  void deleteEntry(@ParamName("cacheId") String cacheId, @ParamName("request") String request);

  /**
   * Fetches cache entry.
   *
   * @param cacheId Id of cache that contains the enty.
   * @param requestURL URL spec of the request.
   */
  @Returns("response")
  CachedResponse requestCachedResponse(
      @ParamName("cacheId") String cacheId, @ParamName("requestURL") String requestURL);
}
