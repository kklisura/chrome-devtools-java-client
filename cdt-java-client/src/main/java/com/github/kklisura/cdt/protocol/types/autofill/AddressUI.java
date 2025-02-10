package com.github.kklisura.cdt.protocol.types.autofill;

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
 * Defines how an address can be displayed like in chrome://settings/addresses. Address UI is a two
 * dimensional array, each inner array is an "address information line", and when rendered in a UI
 * surface should be displayed as such. The following address UI for instance: [[{name: "GIVE_NAME",
 * value: "Jon"}, {name: "FAMILY_NAME", value: "Doe"}], [{name: "CITY", value: "Munich"}, {name:
 * "ZIP", value: "81456"}]] should allow the receiver to render: Jon Doe Munich 81456
 */
public class AddressUI {

  private List<AddressFields> addressFields;

  /** A two dimension array containing the representation of values from an address profile. */
  public List<AddressFields> getAddressFields() {
    return addressFields;
  }

  /** A two dimension array containing the representation of values from an address profile. */
  public void setAddressFields(List<AddressFields> addressFields) {
    this.addressFields = addressFields;
  }
}
