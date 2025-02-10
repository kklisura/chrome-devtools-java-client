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
import com.github.kklisura.cdt.protocol.support.annotations.Optional;
import com.github.kklisura.cdt.protocol.support.annotations.ParamName;
import com.github.kklisura.cdt.protocol.support.annotations.ReturnTypeParameter;
import com.github.kklisura.cdt.protocol.support.annotations.Returns;
import com.github.kklisura.cdt.protocol.types.pwa.DisplayMode;
import com.github.kklisura.cdt.protocol.types.pwa.OsAppState;
import java.util.List;

/** This domain allows interacting with the browser to control PWAs. */
@Experimental
public interface PWA {

  /**
   * Returns the following OS state for the given manifest id.
   *
   * @param manifestId The id from the webapp's manifest file, commonly it's the url of the site
   *     installing the webapp. See https://web.dev/learn/pwa/web-app-manifest.
   */
  OsAppState getOsAppState(@ParamName("manifestId") String manifestId);

  /**
   * Installs the given manifest identity, optionally using the given install_url or IWA bundle
   * location.
   *
   * <p>TODO(crbug.com/337872319) Support IWA to meet the following specific requirement.
   * IWA-specific install description: If the manifest_id is isolated-app://,
   * install_url_or_bundle_url is required, and can be either an http(s) URL or file:// URL pointing
   * to a signed web bundle (.swbn). The .swbn file's signing key must correspond to manifest_id. If
   * Chrome is not in IWA dev mode, the installation will fail, regardless of the state of the
   * allowlist.
   *
   * @param manifestId
   */
  void install(@ParamName("manifestId") String manifestId);

  /**
   * Installs the given manifest identity, optionally using the given install_url or IWA bundle
   * location.
   *
   * <p>TODO(crbug.com/337872319) Support IWA to meet the following specific requirement.
   * IWA-specific install description: If the manifest_id is isolated-app://,
   * install_url_or_bundle_url is required, and can be either an http(s) URL or file:// URL pointing
   * to a signed web bundle (.swbn). The .swbn file's signing key must correspond to manifest_id. If
   * Chrome is not in IWA dev mode, the installation will fail, regardless of the state of the
   * allowlist.
   *
   * @param manifestId
   * @param installUrlOrBundleUrl The location of the app or bundle overriding the one derived from
   *     the manifestId.
   */
  void install(
      @ParamName("manifestId") String manifestId,
      @Optional @ParamName("installUrlOrBundleUrl") String installUrlOrBundleUrl);

  /**
   * Uninstalls the given manifest_id and closes any opened app windows.
   *
   * @param manifestId
   */
  void uninstall(@ParamName("manifestId") String manifestId);

  /**
   * Launches the installed web app, or an url in the same web app instead of the default start url
   * if it is provided. Returns a page Target.TargetID which can be used to attach to via
   * Target.attachToTarget or similar APIs.
   *
   * @param manifestId
   */
  @Returns("targetId")
  String launch(@ParamName("manifestId") String manifestId);

  /**
   * Launches the installed web app, or an url in the same web app instead of the default start url
   * if it is provided. Returns a page Target.TargetID which can be used to attach to via
   * Target.attachToTarget or similar APIs.
   *
   * @param manifestId
   * @param url
   */
  @Returns("targetId")
  String launch(@ParamName("manifestId") String manifestId, @Optional @ParamName("url") String url);

  /**
   * Opens one or more local files from an installed web app identified by its manifestId. The web
   * app needs to have file handlers registered to process the files. The API returns one or more
   * page Target.TargetIDs which can be used to attach to via Target.attachToTarget or similar APIs.
   * If some files in the parameters cannot be handled by the web app, they will be ignored. If none
   * of the files can be handled, this API returns an error. If no files are provided as the
   * parameter, this API also returns an error.
   *
   * <p>According to the definition of the file handlers in the manifest file, one Target.TargetID
   * may represent a page handling one or more files. The order of the returned Target.TargetIDs is
   * not guaranteed.
   *
   * <p>TODO(crbug.com/339454034): Check the existences of the input files.
   *
   * @param manifestId
   * @param files
   */
  @Returns("targetIds")
  @ReturnTypeParameter(String.class)
  List<String> launchFilesInApp(
      @ParamName("manifestId") String manifestId, @ParamName("files") List<String> files);

  /**
   * Opens the current page in its web app identified by the manifest id, needs to be called on a
   * page target. This function returns immediately without waiting for the app to finish loading.
   *
   * @param manifestId
   */
  void openCurrentPageInApp(@ParamName("manifestId") String manifestId);

  /**
   * Changes user settings of the web app identified by its manifestId. If the app was not
   * installed, this command returns an error. Unset parameters will be ignored; unrecognized values
   * will cause an error.
   *
   * <p>Unlike the ones defined in the manifest files of the web apps, these settings are provided
   * by the browser and controlled by the users, they impact the way the browser handling the web
   * apps.
   *
   * <p>See the comment of each parameter.
   *
   * @param manifestId
   */
  void changeAppUserSettings(@ParamName("manifestId") String manifestId);

  /**
   * Changes user settings of the web app identified by its manifestId. If the app was not
   * installed, this command returns an error. Unset parameters will be ignored; unrecognized values
   * will cause an error.
   *
   * <p>Unlike the ones defined in the manifest files of the web apps, these settings are provided
   * by the browser and controlled by the users, they impact the way the browser handling the web
   * apps.
   *
   * <p>See the comment of each parameter.
   *
   * @param manifestId
   * @param linkCapturing If user allows the links clicked on by the user in the app's scope, or
   *     extended scope if the manifest has scope extensions and the flags
   *     `DesktopPWAsLinkCapturingWithScopeExtensions` and `WebAppEnableScopeExtensions` are
   *     enabled.
   *     <p>Note, the API does not support resetting the linkCapturing to the initial value,
   *     uninstalling and installing the web app again will reset it.
   *     <p>TODO(crbug.com/339453269): Setting this value on ChromeOS is not supported yet.
   * @param displayMode
   */
  void changeAppUserSettings(
      @ParamName("manifestId") String manifestId,
      @Optional @ParamName("linkCapturing") Boolean linkCapturing,
      @Optional @ParamName("displayMode") DisplayMode displayMode);
}
