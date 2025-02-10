package com.github.kklisura.cdt.protocol.types.runtime;

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

public class HeapUsage {

  private Double usedSize;

  private Double totalSize;

  private Double embedderHeapUsedSize;

  private Double backingStorageSize;

  /** Used JavaScript heap size in bytes. */
  public Double getUsedSize() {
    return usedSize;
  }

  /** Used JavaScript heap size in bytes. */
  public void setUsedSize(Double usedSize) {
    this.usedSize = usedSize;
  }

  /** Allocated JavaScript heap size in bytes. */
  public Double getTotalSize() {
    return totalSize;
  }

  /** Allocated JavaScript heap size in bytes. */
  public void setTotalSize(Double totalSize) {
    this.totalSize = totalSize;
  }

  /** Used size in bytes in the embedder's garbage-collected heap. */
  public Double getEmbedderHeapUsedSize() {
    return embedderHeapUsedSize;
  }

  /** Used size in bytes in the embedder's garbage-collected heap. */
  public void setEmbedderHeapUsedSize(Double embedderHeapUsedSize) {
    this.embedderHeapUsedSize = embedderHeapUsedSize;
  }

  /** Size in bytes of backing storage for array buffers and external strings. */
  public Double getBackingStorageSize() {
    return backingStorageSize;
  }

  /** Size in bytes of backing storage for array buffers and external strings. */
  public void setBackingStorageSize(Double backingStorageSize) {
    this.backingStorageSize = backingStorageSize;
  }
}
