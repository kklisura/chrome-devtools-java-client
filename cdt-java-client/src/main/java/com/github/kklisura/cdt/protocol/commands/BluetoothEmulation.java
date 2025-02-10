package com.github.kklisura.cdt.protocol.commands;

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
import com.github.kklisura.cdt.protocol.support.annotations.ParamName;
import com.github.kklisura.cdt.protocol.types.bluetoothemulation.CentralState;
import com.github.kklisura.cdt.protocol.types.bluetoothemulation.ManufacturerData;
import com.github.kklisura.cdt.protocol.types.bluetoothemulation.ScanEntry;
import java.util.List;

/** This domain allows configuring virtual Bluetooth devices to test the web-bluetooth API. */
@Experimental
public interface BluetoothEmulation {

  /**
   * Enable the BluetoothEmulation domain.
   *
   * @param state State of the simulated central.
   */
  void enable(@ParamName("state") CentralState state);

  /** Disable the BluetoothEmulation domain. */
  void disable();

  /**
   * Simulates a peripheral with |address|, |name| and |knownServiceUuids| that has already been
   * connected to the system.
   *
   * @param address
   * @param name
   * @param manufacturerData
   * @param knownServiceUuids
   */
  void simulatePreconnectedPeripheral(
      @ParamName("address") String address,
      @ParamName("name") String name,
      @ParamName("manufacturerData") List<ManufacturerData> manufacturerData,
      @ParamName("knownServiceUuids") List<String> knownServiceUuids);

  /**
   * Simulates an advertisement packet described in |entry| being received by the central.
   *
   * @param entry
   */
  void simulateAdvertisement(@ParamName("entry") ScanEntry entry);
}
