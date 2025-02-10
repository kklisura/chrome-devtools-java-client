package com.github.kklisura.cdt.protocol.types.bluetoothemulation;

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

/** Stores the manufacturer data */
public class ManufacturerData {

  private Integer key;

  private String data;

  /**
   * Company identifier
   * https://bitbucket.org/bluetooth-SIG/public/src/main/assigned_numbers/company_identifiers/company_identifiers.yaml
   * https://usb.org/developers
   */
  public Integer getKey() {
    return key;
  }

  /**
   * Company identifier
   * https://bitbucket.org/bluetooth-SIG/public/src/main/assigned_numbers/company_identifiers/company_identifiers.yaml
   * https://usb.org/developers
   */
  public void setKey(Integer key) {
    this.key = key;
  }

  /** Manufacturer-specific data (Encoded as a base64 string when passed over JSON) */
  public String getData() {
    return data;
  }

  /** Manufacturer-specific data (Encoded as a base64 string when passed over JSON) */
  public void setData(String data) {
    this.data = data;
  }
}
