package com.github.kklisura.cdt.launch.support;

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

import java.io.IOException;
import java.util.List;

/**
 * Process launcher interface.
 *
 * @author Kenan Klisura
 */
public interface ProcessLauncher {
  /**
   * Launches a given program with a given list of arguments.
   *
   * @param program Program path to launch.
   * @param args Program arguments.
   * @return Process.
   * @throws IOException If an I/O error occurs during process start.
   */
  Process launch(String program, List<String> args) throws IOException;

  /**
   * Returns true if given path is executable or not.
   *
   * @param binaryPath Binary path.
   * @return True if binary path is executable.
   */
  boolean isExecutable(String binaryPath);
}
