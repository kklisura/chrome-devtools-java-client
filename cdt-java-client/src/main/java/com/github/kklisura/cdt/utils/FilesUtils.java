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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Files utils.
 *
 * @author Kenan Klisura
 */
public final class FilesUtils {
  /**
   * Deletes the files/directories on a given path.
   *
   * @param path Path to delete.
   */
  public static void deleteQuietly(Path path) {
    if (path != null) {
      try {
        File file = path.toFile();
        if (file.isDirectory()) {
          File[] directoryFiles = file.listFiles();

          if (directoryFiles != null) {
            for (File directoryFile : directoryFiles) {
              deleteQuietly(directoryFile.toPath());
            }
          }
        }

        file.delete();
      } catch (Exception e) {
        // Ignore this exception.
      }
    }
  }

  /**
   * Returns a random temp dir.
   *
   * @return Random temp data dir absolute path.
   * @throws RuntimeException If it fails to create temp directory.
   */
  public static String randomTempDir(String prefix) {
    try {
      return Files.createTempDirectory(prefix).toAbsolutePath().toString();
    } catch (IOException e) {
      throw new RuntimeException("Failed creating temp directory for " + prefix, e);
    }
  }
}
