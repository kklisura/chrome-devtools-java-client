package com.github.kklisura.cdt.protocol.types.overlay;

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

/** Configuration for Window Controls Overlay */
public class WindowControlsOverlayConfig {

  private Boolean showCSS;

  private String selectedPlatform;

  private String themeColor;

  /** Whether the title bar CSS should be shown when emulating the Window Controls Overlay. */
  public Boolean getShowCSS() {
    return showCSS;
  }

  /** Whether the title bar CSS should be shown when emulating the Window Controls Overlay. */
  public void setShowCSS(Boolean showCSS) {
    this.showCSS = showCSS;
  }

  /** Selected platforms to show the overlay. */
  public String getSelectedPlatform() {
    return selectedPlatform;
  }

  /** Selected platforms to show the overlay. */
  public void setSelectedPlatform(String selectedPlatform) {
    this.selectedPlatform = selectedPlatform;
  }

  /** The theme color defined in app manifest. */
  public String getThemeColor() {
    return themeColor;
  }

  /** The theme color defined in app manifest. */
  public void setThemeColor(String themeColor) {
    this.themeColor = themeColor;
  }
}
