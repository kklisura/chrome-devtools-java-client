package com.github.kklisura.cdt.services.impl.utils;

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

import jakarta.websocket.CloseReason;

/**
 * Web socket related utils.
 *
 * @author Kenan Klisura
 */
public final class WebSocketUtils {
  private static final String TYRUS_BUFFER_OVERFLOW = "Buffer overflow.";

  private WebSocketUtils() {
    // Empty ctor.
  }

  /**
   * Is the reason for closing tyrus buffer overflow.
   *
   * @param closeReason Close reason.
   * @return True if this is unexpected close due to buffer overflow.
   */
  public static boolean isTyrusBufferOverflowCloseReason(CloseReason closeReason) {
    return CloseReason.CloseCodes.UNEXPECTED_CONDITION.equals(closeReason.getCloseCode())
        && TYRUS_BUFFER_OVERFLOW.equals(closeReason.getReasonPhrase());
  }
}
