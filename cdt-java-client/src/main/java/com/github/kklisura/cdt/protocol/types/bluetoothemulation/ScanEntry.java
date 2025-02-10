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

/** Stores the advertisement packet information that is sent by a Bluetooth device. */
public class ScanEntry {

  private String deviceAddress;

  private Integer rssi;

  private ScanRecord scanRecord;

  public String getDeviceAddress() {
    return deviceAddress;
  }

  public void setDeviceAddress(String deviceAddress) {
    this.deviceAddress = deviceAddress;
  }

  public Integer getRssi() {
    return rssi;
  }

  public void setRssi(Integer rssi) {
    this.rssi = rssi;
  }

  public ScanRecord getScanRecord() {
    return scanRecord;
  }

  public void setScanRecord(ScanRecord scanRecord) {
    this.scanRecord = scanRecord;
  }
}
