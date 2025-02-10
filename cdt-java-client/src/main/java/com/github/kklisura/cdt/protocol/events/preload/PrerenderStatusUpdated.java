package com.github.kklisura.cdt.protocol.events.preload;

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
import com.github.kklisura.cdt.protocol.types.preload.PreloadingAttemptKey;
import com.github.kklisura.cdt.protocol.types.preload.PreloadingStatus;
import com.github.kklisura.cdt.protocol.types.preload.PrerenderFinalStatus;
import com.github.kklisura.cdt.protocol.types.preload.PrerenderMismatchedHeaders;
import java.util.List;

/** Fired when a prerender attempt is updated. */
public class PrerenderStatusUpdated {

  private PreloadingAttemptKey key;

  private String pipelineId;

  private PreloadingStatus status;

  @Optional private PrerenderFinalStatus prerenderStatus;

  @Optional private String disallowedMojoInterface;

  @Optional private List<PrerenderMismatchedHeaders> mismatchedHeaders;

  public PreloadingAttemptKey getKey() {
    return key;
  }

  public void setKey(PreloadingAttemptKey key) {
    this.key = key;
  }

  public String getPipelineId() {
    return pipelineId;
  }

  public void setPipelineId(String pipelineId) {
    this.pipelineId = pipelineId;
  }

  public PreloadingStatus getStatus() {
    return status;
  }

  public void setStatus(PreloadingStatus status) {
    this.status = status;
  }

  public PrerenderFinalStatus getPrerenderStatus() {
    return prerenderStatus;
  }

  public void setPrerenderStatus(PrerenderFinalStatus prerenderStatus) {
    this.prerenderStatus = prerenderStatus;
  }

  /**
   * This is used to give users more information about the name of Mojo interface that is
   * incompatible with prerender and has caused the cancellation of the attempt.
   */
  public String getDisallowedMojoInterface() {
    return disallowedMojoInterface;
  }

  /**
   * This is used to give users more information about the name of Mojo interface that is
   * incompatible with prerender and has caused the cancellation of the attempt.
   */
  public void setDisallowedMojoInterface(String disallowedMojoInterface) {
    this.disallowedMojoInterface = disallowedMojoInterface;
  }

  public List<PrerenderMismatchedHeaders> getMismatchedHeaders() {
    return mismatchedHeaders;
  }

  public void setMismatchedHeaders(List<PrerenderMismatchedHeaders> mismatchedHeaders) {
    this.mismatchedHeaders = mismatchedHeaders;
  }
}
