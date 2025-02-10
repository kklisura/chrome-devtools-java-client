package com.github.kklisura.cdt.protocol.types.storage;

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
import java.util.List;

/** A single Related Website Set object. */
@Experimental
public class RelatedWebsiteSet {

  private List<String> primarySites;

  private List<String> associatedSites;

  private List<String> serviceSites;

  /** The primary site of this set, along with the ccTLDs if there is any. */
  public List<String> getPrimarySites() {
    return primarySites;
  }

  /** The primary site of this set, along with the ccTLDs if there is any. */
  public void setPrimarySites(List<String> primarySites) {
    this.primarySites = primarySites;
  }

  /** The associated sites of this set, along with the ccTLDs if there is any. */
  public List<String> getAssociatedSites() {
    return associatedSites;
  }

  /** The associated sites of this set, along with the ccTLDs if there is any. */
  public void setAssociatedSites(List<String> associatedSites) {
    this.associatedSites = associatedSites;
  }

  /** The service sites of this set, along with the ccTLDs if there is any. */
  public List<String> getServiceSites() {
    return serviceSites;
  }

  /** The service sites of this set, along with the ccTLDs if there is any. */
  public void setServiceSites(List<String> serviceSites) {
    this.serviceSites = serviceSites;
  }
}
