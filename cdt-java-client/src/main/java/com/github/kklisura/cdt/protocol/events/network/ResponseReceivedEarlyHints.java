package com.github.kklisura.cdt.protocol.events.network;

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
import java.util.Map;

/**
 * Fired when 103 Early Hints headers is received in addition to the common response. Not every
 * responseReceived event will have an responseReceivedEarlyHints fired. Only one
 * responseReceivedEarlyHints may be fired for eached responseReceived event.
 */
@Experimental
public class ResponseReceivedEarlyHints {

  private String requestId;

  private Map<String, Object> headers;

  /** Request identifier. Used to match this information to another responseReceived event. */
  public String getRequestId() {
    return requestId;
  }

  /** Request identifier. Used to match this information to another responseReceived event. */
  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  /**
   * Raw response headers as they were received over the wire. Duplicate headers in the response are
   * represented as a single key with their values concatentated using `\n` as the separator. See
   * also `headersText` that contains verbatim text for HTTP/1.*.
   */
  public Map<String, Object> getHeaders() {
    return headers;
  }

  /**
   * Raw response headers as they were received over the wire. Duplicate headers in the response are
   * represented as a single key with their values concatentated using `\n` as the separator. See
   * also `headersText` that contains verbatim text for HTTP/1.*.
   */
  public void setHeaders(Map<String, Object> headers) {
    this.headers = headers;
  }
}
