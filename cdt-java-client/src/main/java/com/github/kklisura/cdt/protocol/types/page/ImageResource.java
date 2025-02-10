package com.github.kklisura.cdt.protocol.types.page;

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
import com.github.kklisura.cdt.protocol.support.annotations.Optional;

/** The image definition used in both icon and screenshot. */
@Experimental
public class ImageResource {

  private String url;

  @Optional private String sizes;

  @Optional private String type;

  /** The src field in the definition, but changing to url in favor of consistency. */
  public String getUrl() {
    return url;
  }

  /** The src field in the definition, but changing to url in favor of consistency. */
  public void setUrl(String url) {
    this.url = url;
  }

  public String getSizes() {
    return sizes;
  }

  public void setSizes(String sizes) {
    this.sizes = sizes;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
