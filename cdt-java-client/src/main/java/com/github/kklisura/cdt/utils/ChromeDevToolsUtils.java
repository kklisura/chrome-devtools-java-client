package com.github.kklisura.cdt.utils;

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

import com.github.kklisura.cdt.protocol.support.types.EventHandler;
import com.github.kklisura.cdt.protocol.support.types.EventListener;
import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.function.Function;

/**
 * Dev tools utility methods.
 *
 * @author Kenan Klisura
 */
public final class ChromeDevToolsUtils {
  /**
   * Closes a closable quietly.
   *
   * @param closeable Closable implementation.
   */
  public static void closeQuietly(Closeable closeable) {
    if (closeable != null) {
      try {
        closeable.close();
      } catch (IOException e) {
        // Ignore this exception.
      }
    }
  }

  /**
   * Waits for event from a given event consumer.
   *
   * @param eventConsumer Event consumer.
   * @param <T> Type of an event.
   */
  public static <T> void waitForEvent(Function<EventHandler<T>, EventListener> eventConsumer) {
    final CountDownLatch countDownLatch = new CountDownLatch(1);

    final EventListener eventListener =
        eventConsumer.apply(
            event -> {
              countDownLatch.countDown();
            });

    try {
      countDownLatch.await();
    } catch (InterruptedException e) {
      throw new RuntimeException("Interrupted while waiting for event.", e);
    } finally {
      eventListener.unsubscribe();
    }
  }
}
