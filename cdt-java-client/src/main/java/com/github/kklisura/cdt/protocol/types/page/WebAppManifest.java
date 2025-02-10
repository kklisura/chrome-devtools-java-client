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
public class WebAppManifest {

  @Optional private String backgroundColor;

  @Optional private String description;

  @Optional private String dir;

  @Optional private String display;

  @Optional private List<String> displayOverrides;

  @Optional private List<FileHandler> fileHandlers;

  @Optional private List<ImageResource> icons;

  @Optional private String id;

  @Optional private String lang;

  @Optional private LaunchHandler launchHandler;

  @Optional private String name;

  @Optional private String orientation;

  @Optional private Boolean preferRelatedApplications;

  @Optional private List<ProtocolHandler> protocolHandlers;

  @Optional private List<RelatedApplication> relatedApplications;

  @Optional private String scope;

  @Optional private List<ScopeExtension> scopeExtensions;

  @Optional private List<Screenshot> screenshots;

  @Optional private ShareTarget shareTarget;

  @Optional private String shortName;

  @Optional private List<Shortcut> shortcuts;

  @Optional private String startUrl;

  @Optional private String themeColor;

  public String getBackgroundColor() {
    return backgroundColor;
  }

  public void setBackgroundColor(String backgroundColor) {
    this.backgroundColor = backgroundColor;
  }

  /** The extra description provided by the manifest. */
  public String getDescription() {
    return description;
  }

  /** The extra description provided by the manifest. */
  public void setDescription(String description) {
    this.description = description;
  }

  public String getDir() {
    return dir;
  }

  public void setDir(String dir) {
    this.dir = dir;
  }

  public String getDisplay() {
    return display;
  }

  public void setDisplay(String display) {
    this.display = display;
  }

  /** The overrided display mode controlled by the user. */
  public List<String> getDisplayOverrides() {
    return displayOverrides;
  }

  /** The overrided display mode controlled by the user. */
  public void setDisplayOverrides(List<String> displayOverrides) {
    this.displayOverrides = displayOverrides;
  }

  /** The handlers to open files. */
  public List<FileHandler> getFileHandlers() {
    return fileHandlers;
  }

  /** The handlers to open files. */
  public void setFileHandlers(List<FileHandler> fileHandlers) {
    this.fileHandlers = fileHandlers;
  }

  public List<ImageResource> getIcons() {
    return icons;
  }

  public void setIcons(List<ImageResource> icons) {
    this.icons = icons;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getLang() {
    return lang;
  }

  public void setLang(String lang) {
    this.lang = lang;
  }

  /**
   * TODO(crbug.com/1231886): This field is non-standard and part of a Chrome experiment. See:
   * https://github.com/WICG/web-app-launch/blob/main/launch_handler.md
   */
  public LaunchHandler getLaunchHandler() {
    return launchHandler;
  }

  /**
   * TODO(crbug.com/1231886): This field is non-standard and part of a Chrome experiment. See:
   * https://github.com/WICG/web-app-launch/blob/main/launch_handler.md
   */
  public void setLaunchHandler(LaunchHandler launchHandler) {
    this.launchHandler = launchHandler;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOrientation() {
    return orientation;
  }

  public void setOrientation(String orientation) {
    this.orientation = orientation;
  }

  public Boolean getPreferRelatedApplications() {
    return preferRelatedApplications;
  }

  public void setPreferRelatedApplications(Boolean preferRelatedApplications) {
    this.preferRelatedApplications = preferRelatedApplications;
  }

  /** The handlers to open protocols. */
  public List<ProtocolHandler> getProtocolHandlers() {
    return protocolHandlers;
  }

  /** The handlers to open protocols. */
  public void setProtocolHandlers(List<ProtocolHandler> protocolHandlers) {
    this.protocolHandlers = protocolHandlers;
  }

  public List<RelatedApplication> getRelatedApplications() {
    return relatedApplications;
  }

  public void setRelatedApplications(List<RelatedApplication> relatedApplications) {
    this.relatedApplications = relatedApplications;
  }

  public String getScope() {
    return scope;
  }

  public void setScope(String scope) {
    this.scope = scope;
  }

  /**
   * Non-standard, see
   * https://github.com/WICG/manifest-incubations/blob/gh-pages/scope_extensions-explainer.md
   */
  public List<ScopeExtension> getScopeExtensions() {
    return scopeExtensions;
  }

  /**
   * Non-standard, see
   * https://github.com/WICG/manifest-incubations/blob/gh-pages/scope_extensions-explainer.md
   */
  public void setScopeExtensions(List<ScopeExtension> scopeExtensions) {
    this.scopeExtensions = scopeExtensions;
  }

  /** The screenshots used by chromium. */
  public List<Screenshot> getScreenshots() {
    return screenshots;
  }

  /** The screenshots used by chromium. */
  public void setScreenshots(List<Screenshot> screenshots) {
    this.screenshots = screenshots;
  }

  public ShareTarget getShareTarget() {
    return shareTarget;
  }

  public void setShareTarget(ShareTarget shareTarget) {
    this.shareTarget = shareTarget;
  }

  public String getShortName() {
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  public List<Shortcut> getShortcuts() {
    return shortcuts;
  }

  public void setShortcuts(List<Shortcut> shortcuts) {
    this.shortcuts = shortcuts;
  }

  public String getStartUrl() {
    return startUrl;
  }

  public void setStartUrl(String startUrl) {
    this.startUrl = startUrl;
  }

  public String getThemeColor() {
    return themeColor;
  }

  public void setThemeColor(String themeColor) {
    this.themeColor = themeColor;
  }
}
