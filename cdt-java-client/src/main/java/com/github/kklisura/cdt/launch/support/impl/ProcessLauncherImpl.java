package com.github.kklisura.cdt.launch.support.impl;

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

import com.github.kklisura.cdt.launch.support.ProcessLauncher;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Process launcher implementation.
 *
 * @author Kenan Klisura
 */
public class ProcessLauncherImpl implements ProcessLauncher {
  @Override
  public Process launch(String program, List<String> args) throws IOException {
    List<String> arguments = new ArrayList<>();
    arguments.add(program);
    arguments.addAll(args);

    ProcessBuilder processBuilder =
        new ProcessBuilder()
            .command(arguments)
            .redirectErrorStream(true)
            .redirectOutput(Redirect.PIPE);

    return processBuilder.start();
  }

  @Override
  public boolean isExecutable(String binaryPath) {
    Path path = Paths.get(binaryPath);
    return isExecutable(path);
  }

  private boolean isExecutable(Path path) {
    return Files.isRegularFile(path) && Files.isReadable(path) && Files.isExecutable(path);
  }
}
