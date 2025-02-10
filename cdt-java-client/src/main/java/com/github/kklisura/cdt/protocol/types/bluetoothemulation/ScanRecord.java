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

import com.github.kklisura.cdt.protocol.support.annotations.Optional;
import java.util.List;

/** Stores the byte data of the advertisement packet sent by a Bluetooth device. */
public class ScanRecord {

  @Optional private String name;

  @Optional private List<String> uuids;

  @Optional private Integer appearance;

  @Optional private Integer txPower;

  @Optional private List<ManufacturerData> manufacturerData;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getUuids() {
    return uuids;
  }

  public void setUuids(List<String> uuids) {
    this.uuids = uuids;
  }

  /** Stores the external appearance description of the device. */
  public Integer getAppearance() {
    return appearance;
  }

  /** Stores the external appearance description of the device. */
  public void setAppearance(Integer appearance) {
    this.appearance = appearance;
  }

  /** Stores the transmission power of a broadcasting device. */
  public Integer getTxPower() {
    return txPower;
  }

  /** Stores the transmission power of a broadcasting device. */
  public void setTxPower(Integer txPower) {
    this.txPower = txPower;
  }

  /**
   * Key is the company identifier and the value is an array of bytes of manufacturer specific data.
   */
  public List<ManufacturerData> getManufacturerData() {
    return manufacturerData;
  }

  /**
   * Key is the company identifier and the value is an array of bytes of manufacturer specific data.
   */
  public void setManufacturerData(List<ManufacturerData> manufacturerData) {
    this.manufacturerData = manufacturerData;
  }
}
