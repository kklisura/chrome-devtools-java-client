package com.github.kklisura.cdt.protocol.events.profiler;

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

import com.github.kklisura.cdt.protocol.support.annotations.Optional;
import com.github.kklisura.cdt.protocol.types.debugger.Location;
import com.github.kklisura.cdt.protocol.types.profiler.Profile;

public class ConsoleProfileFinished {

  private String id;

  private Location location;

  private Profile profile;

  @Optional private String title;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  /** Location of console.profileEnd(). */
  public Location getLocation() {
    return location;
  }

  /** Location of console.profileEnd(). */
  public void setLocation(Location location) {
    this.location = location;
  }

  public Profile getProfile() {
    return profile;
  }

  public void setProfile(Profile profile) {
    this.profile = profile;
  }

  /** Profile title passed as an argument to console.profile(). */
  public String getTitle() {
    return title;
  }

  /** Profile title passed as an argument to console.profile(). */
  public void setTitle(String title) {
    this.title = title;
  }
}
