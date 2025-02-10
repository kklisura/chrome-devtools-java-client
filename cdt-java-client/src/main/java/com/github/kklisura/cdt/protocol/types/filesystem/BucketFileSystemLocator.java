package com.github.kklisura.cdt.protocol.types.filesystem;

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
import java.util.List;

public class BucketFileSystemLocator {

  private String storageKey;

  @Optional private String bucketName;

  private List<String> pathComponents;

  /** Storage key */
  public String getStorageKey() {
    return storageKey;
  }

  /** Storage key */
  public void setStorageKey(String storageKey) {
    this.storageKey = storageKey;
  }

  /**
   * Bucket name. Not passing a `bucketName` will retrieve the default Bucket.
   * (https://developer.mozilla.org/en-US/docs/Web/API/Storage_API#storage_buckets)
   */
  public String getBucketName() {
    return bucketName;
  }

  /**
   * Bucket name. Not passing a `bucketName` will retrieve the default Bucket.
   * (https://developer.mozilla.org/en-US/docs/Web/API/Storage_API#storage_buckets)
   */
  public void setBucketName(String bucketName) {
    this.bucketName = bucketName;
  }

  /** Path to the directory using each path component as an array item. */
  public List<String> getPathComponents() {
    return pathComponents;
  }

  /** Path to the directory using each path component as an array item. */
  public void setPathComponents(List<String> pathComponents) {
    this.pathComponents = pathComponents;
  }
}
