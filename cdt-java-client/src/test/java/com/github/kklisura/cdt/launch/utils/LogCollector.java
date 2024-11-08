package com.github.kklisura.cdt.launch.utils;

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

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import java.util.List;

/**
 * An appender that collects the log events to an array.
 *
 * @author Kenan Klisura
 */
public class LogCollector extends AppenderBase<ILoggingEvent> {
  private final List<String> loggingEventList;

  public LogCollector(List<String> loggingEventList) {
    this.name = "loggingEvents-appender";
    this.loggingEventList = loggingEventList;
    start();
  }

  @Override
  protected void append(ILoggingEvent eventObject) {
    loggingEventList.add("[" + eventObject.getLevel() + "] " + eventObject.getMessage());
  }
}
