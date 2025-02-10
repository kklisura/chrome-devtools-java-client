package com.github.kklisura.cdt.protocol.types.pwa;

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

import java.util.List;

/**
 * The following types are the replica of
 * https://crsrc.org/c/chrome/browser/web_applications/proto/web_app_os_integration_state.proto;drc=9910d3be894c8f142c977ba1023f30a656bc13fc;l=67
 */
public class FileHandlerAccept {

  private String mediaType;

  private List<String> fileExtensions;

  /**
   * New name of the mimetype according to
   * https://www.iana.org/assignments/media-types/media-types.xhtml
   */
  public String getMediaType() {
    return mediaType;
  }

  /**
   * New name of the mimetype according to
   * https://www.iana.org/assignments/media-types/media-types.xhtml
   */
  public void setMediaType(String mediaType) {
    this.mediaType = mediaType;
  }

  public List<String> getFileExtensions() {
    return fileExtensions;
  }

  public void setFileExtensions(List<String> fileExtensions) {
    this.fileExtensions = fileExtensions;
  }
}
