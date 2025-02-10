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
import java.util.List;

@Experimental
public class FileHandler {

  private String action;

  private String name;

  @Optional private List<ImageResource> icons;

  @Optional private List<FileFilter> accepts;

  private String launchType;

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<ImageResource> getIcons() {
    return icons;
  }

  public void setIcons(List<ImageResource> icons) {
    this.icons = icons;
  }

  /** Mimic a map, name is the key, accepts is the value. */
  public List<FileFilter> getAccepts() {
    return accepts;
  }

  /** Mimic a map, name is the key, accepts is the value. */
  public void setAccepts(List<FileFilter> accepts) {
    this.accepts = accepts;
  }

  /** Won't repeat the enums, using string for easy comparison. Same as the other enums below. */
  public String getLaunchType() {
    return launchType;
  }

  /** Won't repeat the enums, using string for easy comparison. Same as the other enums below. */
  public void setLaunchType(String launchType) {
    this.launchType = launchType;
  }
}
