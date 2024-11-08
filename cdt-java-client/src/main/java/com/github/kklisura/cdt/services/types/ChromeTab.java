package com.github.kklisura.cdt.services.types;

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

/**
 * Chrome tab.
 *
 * @author Kenan Klisura
 */
public final class ChromeTab {
  public static final String PAGE_TYPE = "page";

  private String id;

  private String parentId;

  private String description;

  private String title;

  private String type;

  private String url;

  private String devtoolsFrontendUrl;

  private String webSocketDebuggerUrl;

  private String faviconUrl;

  /**
   * Returns true if given tab is a page tab.
   *
   * @return True if this tab is page.
   */
  public boolean isPageType() {
    return PAGE_TYPE.equals(type);
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * Gets parent id.
   *
   * @return the parent id
   */
  public String getParentId() {
    return parentId;
  }

  /**
   * Gets description.
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Gets title.
   *
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * Gets type.
   *
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * Gets url.
   *
   * @return the url
   */
  public String getUrl() {
    return url;
  }

  /**
   * Gets devtools frontend url.
   *
   * @return the devtools frontend url
   */
  public String getDevtoolsFrontendUrl() {
    return devtoolsFrontendUrl;
  }

  /**
   * Gets web socket debugger url.
   *
   * @return the web socket debugger url
   */
  public String getWebSocketDebuggerUrl() {
    return webSocketDebuggerUrl;
  }

  /**
   * Gets favicon url.
   *
   * @return the favicon url
   */
  public String getFaviconUrl() {
    return faviconUrl;
  }
}
